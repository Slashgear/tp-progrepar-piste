package com.polytech4a.piste.dao;

import com.polytech4a.piste.beans.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Antoine CARON on 10/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public interface InscriptionDAO extends JpaRepository<Inscription, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into Inscription values(:idApprenant,:idJeu)", nativeQuery = true)
    void insertInscription(@Param("idJeu") Integer idJeu, @Param("idApprenant") Integer idApprenant);
}
