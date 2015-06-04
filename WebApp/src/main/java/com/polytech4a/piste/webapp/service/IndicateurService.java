package com.polytech4a.piste.webapp.service;

import com.polytech4a.piste.persistence.dao.Indicateur;
import com.polytech4a.piste.persistence.job.IndicateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alexandre
 *         04/06/2015
 */
@Service
public class IndicateurService {
    @Autowired
    private IndicateurRepository indicateurRepository;

    @Transactional
    public Indicateur saveAndFlush(Indicateur indicateur) {
        if (indicateur != null) indicateur = indicateurRepository.saveAndFlush(indicateur);
        return indicateur;
    }

    @Transactional
    public List<Indicateur> findAll() {
        return indicateurRepository.findAll();
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
