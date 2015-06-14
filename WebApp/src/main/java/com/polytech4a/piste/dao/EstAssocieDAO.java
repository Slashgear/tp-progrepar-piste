package com.polytech4a.piste.dao;

import com.polytech4a.piste.beans.EstAssocie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alexandre
 *         08/06/2015
 */
public interface EstAssocieDAO extends JpaRepository<EstAssocie, Integer> {
    List<EstAssocie> findEstassociesByNumobjectif(Integer numobjectif);
}
