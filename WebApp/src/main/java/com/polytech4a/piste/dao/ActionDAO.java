package com.polytech4a.piste.dao;

import com.polytech4a.piste.beans.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Antoine CARON on 04/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public interface ActionDAO extends JpaRepository<Action, Integer> {

    @Query("select o.valeurdebut-a.scoremin from Obtient o inner join Action a where o.numaction=a.numaction and a.numaction= :idAction and o.numapprenant= :idApprenant")
    Integer hasActionValidatedByApprenant(@Param("idAction") Integer idAction, @Param("idApprenant") Integer idApprenant);
}
