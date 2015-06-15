package com.polytech4a.piste.service;

import com.polytech4a.piste.beans.*;
import com.polytech4a.piste.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Alexandre
 *         14/06/2015
 */
@Service
public class ScoreService {
    @Autowired
    private ActionDAO actionDAO;
    @Autowired
    private EstAssocieDAO estAssocieDAO;
    @Autowired
    private ObjectifDAO objectifDAO;
    @Autowired
    private ObtientDAO obtientDAO;
    @Autowired
    private FixeDAO fixeDAO;

    public Map<Integer, Integer> getScoresMinimum() {
        List<Action> actionList = actionDAO.findAll();
        Map<Integer, Integer> scoresMinimum = Collections.synchronizedMap(new HashMap<>());
        actionList.forEach(action -> scoresMinimum.put(action.getNumaction(), action.getScoremin()));
        return scoresMinimum;
    }

    public Map<Integer, Integer> getCountScoreForApprenant(Integer numApprenant) {
        List<Objectif> objectifList = objectifDAO.findAll();
        Map<Integer, Integer> countScore = Collections.synchronizedMap(new HashMap<>());
        objectifList.forEach(objectif ->
                countScore.put(objectif.getNumobjectif(), estAssocieDAO.getCountScoreActionByObjectifAndApprenant(objectif.getNumobjectif(), numApprenant)));
        return countScore;
    }

    public Map<Integer, Integer> getCountScore() {
        List<Objectif> objectifList = objectifDAO.findAll();
        Map<Integer, Integer> countScore = Collections.synchronizedMap(new HashMap<>());
        objectifList.forEach(objectif ->
                countScore.put(objectif.getNumobjectif(), estAssocieDAO.getCountScoreActionByObjectif(objectif.getNumobjectif())));
        return countScore;
    }

    public Map<Integer, Integer> getCountScoreSuccessForObjectifForApprenant(Integer numApprenant) {
        List<Objectif> objectifList = objectifDAO.findAll();
        Map<Integer, Integer> countScore = Collections.synchronizedMap(new HashMap<>());
        objectifList.forEach(objectif ->
                countScore.put(objectif.getNumobjectif(), estAssocieDAO.getCountScoreSuccessActionByObjectifAndApprenant(objectif.getNumobjectif(), numApprenant)));
        return countScore;
    }

    public Map<Integer, Integer> getCountScoreFailureForObjectifForApprenant(Integer numApprenant) {
        List<Objectif> objectifList = objectifDAO.findAll();
        Map<Integer, Integer> countScore = Collections.synchronizedMap(new HashMap<>());
        objectifList.forEach(objectif -> {
            if (!countScore.containsKey(objectif.getNumobjectif()))
                countScore.put(objectif.getNumobjectif(), estAssocieDAO.getCountScoreFailureActionByObjectifAndApprenant(objectif.getNumobjectif(), numApprenant));
        });
        return countScore;
    }

    public Map<Integer, Integer> getCountScoreFailureForObjectif() {
        List<Objectif> objectifList = objectifDAO.findAll();
        Map<Integer, Integer> countScore = Collections.synchronizedMap(new HashMap<>());
        objectifList.forEach(objectif -> {
            if (!countScore.containsKey(objectif.getNumobjectif()))
                countScore.put(objectif.getNumobjectif(), estAssocieDAO.getCountScoreFailureActionByObjectif(objectif.getNumobjectif()));
        });
        return countScore;
    }

    public Map<Integer, Integer> getScoreForApprenant(Integer numApprenant) {
        List<Obtient> obtientList = obtientDAO.findByNumapprenant(numApprenant);
        Map<Integer, Integer> scores = Collections.synchronizedMap(new HashMap<>());
        obtientList.forEach(obtient ->
                scores.put(obtient.getNumaction(), obtient.getValeurdebut()));
        return scores;
    }

    public Map<Integer, Double> getAvgObjectifs() {
        Map<Integer, Double> score = Collections.synchronizedMap(new HashMap<>());
        Map<Integer, Integer> countScore = Collections.synchronizedMap(new HashMap<>());
        Map<Integer, List<Integer>> done = Collections.synchronizedMap(new HashMap<>());
        List<Object[]> actionAvg = estAssocieDAO.getAvgAction();
        Map<Integer, Integer> actionPoidMap = Collections.synchronizedMap(new HashMap<>());
        Map<Integer, Double> actionAvgMap = getAvgAction();
        actionAvg.forEach(objects -> actionPoidMap.put((Integer) objects[0], (Integer) objects[2]));
        List<EstAssocie> associeList = estAssocieDAO.findAll();
        associeList.forEach(estAssocie -> {
            if (actionAvgMap.containsKey(estAssocie.getNumaction()))
                if (!done.containsKey(estAssocie.getNumobjectif())) {
                    done.put(estAssocie.getNumobjectif(), new ArrayList<>());
                    score.put(estAssocie.getNumobjectif(), actionAvgMap.get(estAssocie.getNumaction()) * actionPoidMap.get(estAssocie.getNumaction()));
                    countScore.put(estAssocie.getNumobjectif(), actionPoidMap.get(estAssocie.getNumaction()));
                    done.get(estAssocie.getNumobjectif()).add(estAssocie.getNumaction());
                } else if (!done.get(estAssocie.getNumobjectif()).contains(estAssocie.getNumaction())) {
                    score.put(estAssocie.getNumobjectif(), score.get(estAssocie.getNumobjectif()) + actionAvgMap.get(estAssocie.getNumaction()) * actionPoidMap.get(estAssocie.getNumaction()));
                    countScore.put(estAssocie.getNumobjectif(), countScore.get(estAssocie.getNumobjectif()) + actionPoidMap.get(estAssocie.getNumaction()));
                    done.get(estAssocie.getNumobjectif()).add(estAssocie.getNumaction());
                }
        });

        Map<Integer, Double> scoreFinal = Collections.synchronizedMap(new HashMap<>());
        score.entrySet().forEach(integerDoubleEntry -> scoreFinal.put(integerDoubleEntry.getKey(), integerDoubleEntry.getValue() / countScore.get(integerDoubleEntry.getKey()).doubleValue()));
        return scoreFinal;
    }


    public Map<Integer, Double> getAvgAction() {
        final Map<Integer, Double> stats = Collections.synchronizedMap(new HashMap<>());
        List<Object[]> values = estAssocieDAO.getAvgAction();
        values.forEach(objects -> stats.put((Integer) objects[0], (Double) objects[1]));
        return stats;
    }

    public Map<Integer, Double> getAvgActionForAllObjectifsForAllMissions(Jeu jeu) {
        final Map<Integer, Double> stats = Collections.synchronizedMap(new HashMap<>());
        final Map<Integer, Double> avgObjectifs = getAvgObjectifs();
        jeu.getMissionsByNumjeu().forEach(mission -> {
            OptionalDouble avg = mission.getFixesByNummission().stream()
                    .filter(fixe -> avgObjectifs.containsKey(fixe.getNumobjectif()))
                    .mapToDouble(value -> avgObjectifs.get(value.getNumobjectif()))
                    .average();
            if (avg.isPresent()) stats.put(mission.getNummission(), avg.getAsDouble());
        });
        return stats;
    }

    public Map<Integer, Double> getAvgObjectifsForAllMissions(Jeu jeu) {
        final Map<Integer, Double> stats = Collections.synchronizedMap(new HashMap<>());
        final Map<Integer, Double> statsObj = getAvgObjectifs();
        jeu.getMissionsByNumjeu().forEach(mission -> {
                    OptionalDouble avg = fixeDAO.findFixesByNummission(mission.getNummission()).stream().filter(fixe -> statsObj.get(fixe.getNumobjectif()) != null).mapToDouble(value -> statsObj.get(value.getNumobjectif())).average();
                    if (avg.isPresent())
                        stats.put(mission.getNummission(), avg.getAsDouble());
                }
        );
        return stats;
    }


    public Map<Integer, Double> getAvgActionForAllObjectifsForApprenant(Jeu jeu, Integer numApprenant) {
        final Map<Integer, Double> stats = Collections.synchronizedMap(new HashMap<>());
        jeu.getMissionsByNumjeu().forEach(mission ->
                mission.getFixesByNummission().parallelStream().forEach(fixe -> {
                    if (!stats.containsKey(fixe.getNumobjectif()))
                        stats.put(fixe.getNumobjectif(), estAssocieDAO.getAvgActionByObjectifAndApprenant(fixe.getNumobjectif(), numApprenant));
                }));
        return stats;
    }

    public Map<Integer, Double> getAvgObjectifsForAllMissionsForApprenant(Jeu jeu, Integer numApprenant) {
        final Map<Integer, Double> stats = Collections.synchronizedMap(new HashMap<>());
        final Map<Integer, Double> statsObj = getAvgActionForAllObjectifsForApprenant(jeu, numApprenant);
        jeu.getMissionsByNumjeu().forEach(mission -> {
                    OptionalDouble avg = fixeDAO.findFixesByNummission(mission.getNummission()).stream().filter(fixe -> statsObj.get(fixe.getNumobjectif()) != null).mapToDouble(value -> statsObj.get(value.getNumobjectif())).average();
                    if (avg.isPresent())
                        stats.put(mission.getNummission(), avg.getAsDouble());
                }
        );
        return stats;
    }
}
