package com.polytech4a.piste.dao;


import com.polytech4a.piste.beans.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Alexandre
 *         08/06/2015
 */
public interface JeuDAO extends JpaRepository<Jeu, Integer> {

    @Query("select j from Jeu j where j.numjeu not in (select i.numjeu from Inscription i where i.apprenantByNumapprenant.numapprenant = :idApprenant)")
    List<Jeu> findAvailableJeuForApprenant(@Param("idApprenant") Integer idApprenant);

    @Query("select count(*) from Inscription i,Jeu j where i.numjeu=j.numjeu and j.numjeu=:idJeu")
    Integer getNumberofApprenant(@Param("idJeu") Integer idJeu);

    @Query("from Jeu j left join fetch j.inscriptionsByNumjeu WHERE j.id = (:id)")
    Jeu findByIdandInscription(@Param("id") Integer id);
}
