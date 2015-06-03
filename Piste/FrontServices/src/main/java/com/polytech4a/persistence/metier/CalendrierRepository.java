package com.polytech4a.persistence.metier;

import com.polytech4a.persistence.dao.Calendrier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexandre
 *         03/06/2015
 */
public interface CalendrierRepository extends JpaRepository<Calendrier, Long> {
}
