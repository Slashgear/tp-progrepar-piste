package com.polytech4a.piste.service;

import com.polytech4a.piste.beans.Apprenant;
import com.polytech4a.piste.beans.Jeu;
import com.polytech4a.piste.beans.Mission;
import com.polytech4a.piste.beans.Objectif;
import com.polytech4a.piste.dao.EstAssocieDAO;
import com.polytech4a.piste.dao.FixeDAO;
import com.polytech4a.piste.dao.JeuDAO;
import com.polytech4a.piste.dao.MissionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alexandre
 *         14/06/2015
 */
@Service
public class JeuService {
    @Autowired
    private JeuDAO jeuDAO;
    @Autowired
    private MissionDAO missionDAO;
    @Autowired
    private FixeDAO fixeDAO;
    @Autowired
    private EstAssocieDAO estAssocieDAO;
    @Autowired
    private ScoreService scoreService;

    public Jeu findByNumjeuAndFetchAll(Integer numJeu) {
        Jeu jeu = jeuDAO.findOne(numJeu);
        if (jeu == null) return null;

        jeu.setMissionsByNumjeu(missionDAO.findMissionsByNumjeu(numJeu));
        jeu.getMissionsByNumjeu().forEach(mission -> {
            mission.setFixesByNummission(fixeDAO.findFixesByNummission(mission.getNummission()));
            mission.getFixesByNummission().forEach(fixe -> {
                Objectif objectif = fixe.getObjectifByNumobjectif();
                objectif.setEstAssociesByNumobjectif(estAssocieDAO.findEstassociesByNumobjectif(objectif.getNumobjectif()));
            });
        });
        return jeu;
    }

    public Integer getNumberFailureByJeu(Integer numJeu) {
        List<Apprenant> apprenantList = jeuDAO.getInscritByJeu(numJeu);
        List<Object[]> actionList = jeuDAO.getActionScoreMinByJeu(numJeu);
        Map<Integer, Integer> actionScoreMin = Collections.synchronizedMap(new HashMap<>());
        actionList.forEach(action1 -> actionScoreMin.put((Integer) action1[0], (Integer) action1[1]));
        Integer total = 0;
        for (Apprenant apprenant : apprenantList) {
            Map<Integer, Integer> scoresApprenant = scoreService.getScoreForApprenant(apprenant.getNumapprenant());
            if (actionList.size() ==
                    scoresApprenant.entrySet().stream()
                            .filter(integerIntegerEntry -> actionScoreMin.containsKey(integerIntegerEntry.getKey()) &&
                                    actionScoreMin.get(integerIntegerEntry.getKey()) > integerIntegerEntry.getValue()).count())
                total++;
        }
        return total;
    }

    public Integer getNumberSuccessByJeu(Integer numJeu) {
        List<Apprenant> apprenantList = jeuDAO.getInscritByJeu(numJeu);
        List<Object[]> actionList = jeuDAO.getActionScoreMinByJeu(numJeu);
        Map<Integer, Integer> actionScoreMin = Collections.synchronizedMap(new HashMap<>());
        actionList.forEach(action1 -> actionScoreMin.put((Integer) action1[0], (Integer) action1[1]));
        Integer total = 0;
        for (Apprenant apprenant : apprenantList) {
            Map<Integer, Integer> scoresApprenant = scoreService.getScoreForApprenant(apprenant.getNumapprenant());
            if (actionList.size() ==
                    scoresApprenant.entrySet().stream()
                            .filter(integerIntegerEntry -> actionScoreMin.containsKey(integerIntegerEntry.getKey()) &&
                                    actionScoreMin.get(integerIntegerEntry.getKey()) <= integerIntegerEntry.getValue()).count())
                total++;
        }
        return total;
    }

    public Boolean getAValideApprenantJeu(Integer numJeu, Integer numApprenant) {
        List<Object[]> actionList = jeuDAO.getActionScoreMinByJeu(numJeu);
        Map<Integer, Integer> actionScoreMin = Collections.synchronizedMap(new HashMap<>());
        actionList.forEach(action1 -> actionScoreMin.put((Integer) action1[0], (Integer) action1[1]));

        Map<Integer, Integer> scoresApprenant = scoreService.getScoreForApprenant(numApprenant);
        if (actionList.size() ==
                scoresApprenant.entrySet().stream()
                        .filter(integerIntegerEntry -> actionScoreMin.containsKey(integerIntegerEntry.getKey()) &&
                                actionScoreMin.get(integerIntegerEntry.getKey()) <= integerIntegerEntry.getValue()).count())
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public Boolean getANonValideApprenantJeu(Integer numJeu, Integer numApprenant) {
        List<Mission> missionList = missionDAO.findMissionsByNumjeu(numJeu);
        Map<Integer, Integer> missionScoreFailure = scoreService.getCountScoreFailureForMissionsForApprenant(numApprenant);
        for (Mission mission : missionList)
            if (missionScoreFailure.get(mission.getNummission()) != 0) return Boolean.TRUE;

        return Boolean.FALSE;
    }

    public Map<Integer, Boolean> getValideApprenant(Integer numApprenant) {
        Map<Integer, Boolean> booleanMap = Collections.synchronizedMap(new HashMap<>());
        List<Jeu> jeux = jeuDAO.findSubscribedJeuForApprenant(numApprenant);
        jeux.forEach(jeu -> {
            if (getAValideApprenantJeu(jeu.getNumjeu(), numApprenant)) booleanMap.put(jeu.getNumjeu(), Boolean.TRUE);
            else if (getANonValideApprenantJeu(jeu.getNumjeu(), numApprenant))
                booleanMap.put(jeu.getNumjeu(), Boolean.FALSE);
        });
        return booleanMap;
    }
}
