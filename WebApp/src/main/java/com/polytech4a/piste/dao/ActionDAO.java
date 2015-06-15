package com.polytech4a.piste.dao;

import com.polytech4a.piste.beans.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Antoine CARON on 04/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public interface ActionDAO extends JpaRepository<Action, Integer> {

    @Query("select count(distinct a) from Apprenant a inner join  a.obtientsByNumapprenant as o inner join o.actionByNumaction as ac where o.valeurdebut >= ac.scoremin and ac.numaction=:idAction")
    Integer getNumberOfApprenantWhoValidateAction(@Param("idAction") Integer idAction);

    @Query("select count(distinct a) from Apprenant a inner join a.inscriptionsByNumapprenant as i " +
            "inner join i.jeuByNumjeu as j inner join j.missionsByNumjeu as m inner join m.fixesByNummission as fix " +
            "inner join fix.objectifByNumobjectif as obj inner join obj.estAssociesByNumobjectif as est\n" +
            "inner join est.actionByNumaction as ac where ac.numaction=:idAction")
    Integer getNumberOfApprenantforAction(@Param("idAction") Integer idAction);

    @Query("from Action a where actNumaction=:idAction")
    List<Action> findByActNumaction(@Param("idAction") Integer idAction);
}
