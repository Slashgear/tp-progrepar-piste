package com.polytech4a.piste.dao;

import com.polytech4a.piste.beans.Objectif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Alexandre
 *         08/06/2015
 */
public interface ObjectifDAO extends JpaRepository<Objectif, Integer> {
    @Query("select count(distinct a.numaction )from Objectif o " +
            "inner join o.estAssociesByNumobjectif as e " +
            "inner join e.actionByNumaction a where o.numobjectif = :numObjectif")
    Integer getNumberofActionbyObjectif(@Param("numObjectif") Integer numObjectif);
}
