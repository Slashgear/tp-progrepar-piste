package com.polytech4a.piste.repository;

import com.polytech4a.piste.beans.Indicateur;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexandre
 *         08/06/2015
 */
public interface IIndicateurDAO extends JpaRepository<Indicateur, Integer> {
}
