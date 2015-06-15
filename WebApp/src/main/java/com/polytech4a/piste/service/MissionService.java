package com.polytech4a.piste.service;

import com.polytech4a.piste.beans.Mission;
import com.polytech4a.piste.beans.Objectif;
import com.polytech4a.piste.dao.EstAssocieDAO;
import com.polytech4a.piste.dao.FixeDAO;
import com.polytech4a.piste.dao.MissionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alexandre
 *         15/06/2015
 */
@Service
public class MissionService {
    @Autowired
    private MissionDAO missionDAO;
    @Autowired
    private FixeDAO fixeDAO;
    @Autowired
    private EstAssocieDAO estAssocieDAO;

    public Mission findByNummissionAndFetchAll(Integer numMission) {
        Mission mission = missionDAO.findOne(numMission);
        if (mission == null) return null;
        mission.setFixesByNummission(fixeDAO.findFixesByNummission(mission.getNummission()));
        mission.getFixesByNummission().forEach(fixe -> {
            Objectif objectif = fixe.getObjectifByNumobjectif();
            objectif.setEstAssociesByNumobjectif(estAssocieDAO.findEstassociesByNumobjectif(objectif.getNumobjectif()));
        });
        return mission;
    }
}
