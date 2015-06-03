package com.polytech4a.persistence.metier;

import com.polytech4a.persistence.dao.Appartient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexandre
 *         03/06/2015
 */
public interface AppartientRepository extends JpaRepository<Appartient, Long> {
}
