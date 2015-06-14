package com.polytech4a.piste.dao;

import com.polytech4a.piste.beans.Fixe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alexandre
 *         08/06/2015
 */
public interface FixeDAO extends JpaRepository<Fixe, Integer> {
    List<Fixe> findFixesByNummission(Integer nummission);
}
