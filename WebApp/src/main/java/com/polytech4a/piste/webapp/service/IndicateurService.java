package com.polytech4a.piste.webapp.service;

import com.polytech4a.piste.persistence.dao.Indicateur;
import com.polytech4a.piste.persistence.job.IndicateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alexandre
 *         04/06/2015
 */
@Service
@Repository
public class IndicateurService {
    @Autowired
    private IndicateurRepository indicateurRepository;

    @Transactional
    public Indicateur saveAndFlush(Indicateur indicateur) {
        if (indicateur != null) indicateur = indicateurRepository.saveAndFlush(indicateur);
        return indicateur;
    }

    @Transactional
    public void delete(Long numIndic) {
        if (numIndic != null) indicateurRepository.delete(numIndic);
    }

    @Transactional
    public Indicateur getByNumAction(Long numAction) {
        return indicateurRepository.findByNumAction(numAction);
    }
}
