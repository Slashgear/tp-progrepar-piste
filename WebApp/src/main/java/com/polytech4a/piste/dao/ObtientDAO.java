package com.polytech4a.piste.dao;

import com.polytech4a.piste.beans.Obtient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Alexandre
 *         08/06/2015
 */
public interface ObtientDAO extends JpaRepository<Obtient, Integer> {
    List<Obtient> findByNumaction(Integer numAction);

    @Query("select avg(o.valeurdebut) from Obtient o where o.numaction = :numAction")
    Double getAvgValeurdebutForAction(@Param("numAction") Integer numAction);

    List<Obtient> findByNumapprenant(Integer numApprenant);
}
