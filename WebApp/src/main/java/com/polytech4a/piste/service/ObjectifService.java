package com.polytech4a.piste.service;

import com.polytech4a.piste.beans.Objectif;
import com.polytech4a.piste.dao.EstAssocieDAO;
import com.polytech4a.piste.dao.ObjectifDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alexandre
 *         15/06/2015
 */
@Service
public class ObjectifService {
    @Autowired
    private ObjectifDAO objectifDAO;
    @Autowired
    private EstAssocieDAO estAssocieDAO;

    public Objectif findByNumobjectifAndFetchAll(Integer numObjectif) {
        Objectif objectif = objectifDAO.findOne(numObjectif);
        if (objectif == null) return null;
        objectif.setEstAssociesByNumobjectif(estAssocieDAO.findEstassociesByNumobjectif(objectif.getNumobjectif()));
        return objectif;
    }
}
