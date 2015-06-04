package com.polytech4a.piste.persistence.job;


import com.polytech4a.piste.persistence.dao.Action;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexandre
 *         03/06/2015
 */
public interface ActionRepository extends JpaRepository<Action, Long> {
}
