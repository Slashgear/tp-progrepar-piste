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

    public Map<Integer, Map<Integer, Double>> getAvgActionForAllObjectifsForAllMissions(Jeu jeu) {
        final Map<Integer, Map<Integer, Double>> stats = Collections.synchronizedMap(new HashMap<Integer, Map<Integer, Double>>());
        jeu.getMissionsByNumjeu().forEach(mission -> {
            final Map<Integer, Double> stat = Collections.synchronizedMap(new HashMap<Integer, Double>());
            mission.getFixesByNummission().parallelStream().forEach(fixe ->
                            stat.put(fixe.getNumobjectif(), estAssocieDAO.getAvgActionByObjectifAndMission(mission.getNummission(), fixe.getNumobjectif()))
            );
            stats.put(mission.getNummission(), stat);
        });
        return stats;
    }

    public Map<Integer, Double> getAvgObjectifsForAllMissions(Jeu jeu) {
        final Map<Integer, Double> stats = Collections.synchronizedMap(new HashMap<Integer, Double>());
        final Map<Integer, Map<Integer, Double>> statsObj = getAvgActionForAllObjectifsForAllMissions(jeu);
        statsObj.forEach((integer, integerDoubleMap) -> {
            OptionalDouble avg = integerDoubleMap.values().stream().filter(aDouble -> aDouble != null).mapToDouble(value -> value).average();
            if (avg.isPresent()) stats.put(integer, avg.getAsDouble());
        });
        return stats;
    }
}
