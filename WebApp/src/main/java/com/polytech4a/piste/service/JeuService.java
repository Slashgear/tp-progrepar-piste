package com.polytech4a.piste.service;

import com.polytech4a.piste.beans.Jeu;
import com.polytech4a.piste.beans.Objectif;
import com.polytech4a.piste.dao.EstAssocieDAO;
import com.polytech4a.piste.dao.FixeDAO;
import com.polytech4a.piste.dao.JeuDAO;
import com.polytech4a.piste.dao.MissionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalDouble;

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

    public Map<Integer, Double> getAvgActionForAllObjectifsForAllMissions(Jeu jeu) {
        final Map<Integer, Double> stats = Collections.synchronizedMap(new HashMap<>());
        jeu.getMissionsByNumjeu().forEach(mission ->
                mission.getFixesByNummission().parallelStream().forEach(fixe -> {
                    if (!stats.containsKey(fixe.getNumobjectif()))
                        stats.put(fixe.getNumobjectif(), estAssocieDAO.getAvgActionByObjectif(fixe.getNumobjectif()));
                }));
        return stats;
    }

    public Map<Integer, Double> getAvgObjectifsForAllMissions(Jeu jeu) {
        final Map<Integer, Double> stats = Collections.synchronizedMap(new HashMap<>());
        final Map<Integer, Double> statsObj = getAvgActionForAllObjectifsForAllMissions(jeu);
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
