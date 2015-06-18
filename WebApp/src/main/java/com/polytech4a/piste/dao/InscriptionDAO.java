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
    @Query("delete from Inscription i where i.apprenantByNumapprenant.numapprenant = :idApprenant")
    void deleteByNumapprenant(@Param("idApprenant") Integer idApprenant);

    @Query("from Inscription where apprenantByNumapprenant.numapprenant=:idApprenant and jeuByNumjeu.numjeu=:idJeu")
    Inscription findInscriptionByNumapprenantAndNumjeu(@Param(value="idApprenant") Integer idApprenant, @Param(value="idJeu") Integer idJeu);
}
