package com.polytech4a.piste.dao;

import com.polytech4a.piste.beans.Indicateur;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexandre
 *         08/06/2015
 */
public interface IndicateurDAO extends JpaRepository<Indicateur, Integer> {
    Indicateur findByNumaction(Integer numAction);
}
