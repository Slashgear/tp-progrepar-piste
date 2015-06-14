package com.polytech4a.piste.ws;

import com.polytech4a.piste.beans.Jeu;
import com.polytech4a.piste.beans.Objectif;
import com.polytech4a.piste.dao.EstAssocieDAO;
import com.polytech4a.piste.dao.FixeDAO;
import com.polytech4a.piste.dao.JeuDAO;
import com.polytech4a.piste.dao.MissionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alexandre
 *         14/06/2015
 */
@Service
public class JeuWS {
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
}
