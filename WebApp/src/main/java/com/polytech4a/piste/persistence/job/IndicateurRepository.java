package com.polytech4a.piste.persistence.job;

import com.polytech4a.piste.persistence.dao.Indicateur;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexandre
 *         03/06/2015
 */
public interface IndicateurRepository extends JpaRepository<Indicateur, Long> {
    Indicateur findByNumAction(Long numAction);
}
