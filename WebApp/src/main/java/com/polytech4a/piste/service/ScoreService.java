package com.polytech4a.piste.service;

import com.polytech4a.piste.beans.Action;
import com.polytech4a.piste.beans.Objectif;
import com.polytech4a.piste.beans.Obtient;
import com.polytech4a.piste.dao.ActionDAO;
import com.polytech4a.piste.dao.EstAssocieDAO;
import com.polytech4a.piste.dao.ObjectifDAO;
import com.polytech4a.piste.dao.ObtientDAO;
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
public class ScoreService {
    @Autowired
    private ActionDAO actionDAO;
    @Autowired
    private EstAssocieDAO estAssocieDAO;
    @Autowired
    private ObjectifDAO objectifDAO;
    @Autowired
    private ObtientDAO obtientDAO;

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

    public Map<Integer, Double> getAvgScore() {
        List<Objectif> objectifList = objectifDAO.findAll();
        Map<Integer, Double> countScore = Collections.synchronizedMap(new HashMap<>());
        objectifList.forEach(objectif ->
                countScore.put(objectif.getNumobjectif(), estAssocieDAO.getAvgActionByObjectif(objectif.getNumobjectif())));
        return countScore;
    }
}
