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

    @Query("select avg(o.valeur) from Obtient o where o.numaction = :numAction")
    Double getAvgValeurdebutForAction(@Param("numAction") Integer numAction);


    List<Obtient> findByNumapprenant(Integer numApprenant);

    @Query("select count(distinct a.numapprenant) from Obtient o inner join o.apprenantByNumapprenant a where o.actionByNumaction.numaction=:idAction")
    Integer getNumberOfApprenantObtientforAction(@Param("idAction") Integer idAction);

    @Query("select count(distinct a.numapprenant) from Obtient o inner join o.apprenantByNumapprenant a " +
            "where o.actionByNumaction.numaction=:idAction and o.valeur<:valMax and o.valeur>=:valMin")
    Integer getNumberOfApprenantObtientforActionBetween(@Param("idAction") Integer idAction
            , @Param("valMin") Integer valMin, @Param("valMax") Integer valMax);

}
