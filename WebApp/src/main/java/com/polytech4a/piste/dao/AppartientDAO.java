package com.polytech4a.piste.dao;

import com.polytech4a.piste.beans.Appartient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexandre
 *         08/06/2015
 */
public interface AppartientDAO extends JpaRepository<Appartient, Integer> {
}
