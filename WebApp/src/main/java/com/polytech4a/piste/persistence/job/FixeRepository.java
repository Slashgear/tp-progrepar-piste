package com.polytech4a.piste.persistence.job;

import com.polytech4a.piste.persistence.dao.Fixe;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexandre
 *         03/06/2015
 */
public interface FixeRepository extends JpaRepository<Fixe, Long> {
}
