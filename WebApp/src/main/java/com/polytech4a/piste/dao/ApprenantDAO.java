package com.polytech4a.piste.dao;

import com.polytech4a.piste.beans.Apprenant;
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
public interface ApprenantDAO extends JpaRepository<Apprenant, Integer> {

    @Query("from Apprenant a left join fetch a.inscriptionsByNumapprenant WHERE a.id = (:id)")
    Apprenant findByIdandInscription(@Param("id") Integer id);

    @Query("from Apprenant a where a.nomapprenant like concat('%', concat(:label, '%')) or a.prenomapprenant like concat('%', concat(:label, '%'))")
    List<Apprenant> findByLabel(@Param("label") String label);
}
