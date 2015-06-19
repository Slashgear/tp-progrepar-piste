package com.polytech4a.piste.dao;


import com.polytech4a.piste.beans.Apprenant;
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

    @Query("from Jeu j left join fetch j.inscriptionsByNumjeu WHERE j.id = (:id)")
    Jeu findByIdandInscription(@Param("id") Integer id);

    @Query("select count(distinct a.numaction ) from " +
            "Jeu as j inner join  j.missionsByNumjeu as m " +
            "inner join  m.fixesByNummission f " +
            "inner join f.objectifByNumobjectif as o " +
            "inner join o.estAssociesByNumobjectif as e " +
            "inner join e.actionByNumaction a where j.id=:idJeu")
    Integer getNumberofActionbyJeu(@Param("idJeu") Integer idJeu);

    @Query("select count(distinct m.nummission) from Jeu as j inner join  j.missionsByNumjeu as m where j.id=:idJeu")
    Integer getNumberofMissionByJeu(@Param("idJeu") Integer idJeu);

    @Query("select count(distinct o.numobjectif) from Jeu as j inner join  j.missionsByNumjeu as m inner join  m.fixesByNummission f inner join f.objectifByNumobjectif as o where j.id=:idJeu")
    Integer getNumberofObjectifByJeu(@Param("idJeu") Integer idJeu);

    @Query("select count(distinct i.numapprenant) from Jeu as j inner join j.inscriptionsByNumjeu as i where j.id=:idJeu")
    Integer getNumberofInscritByJeu(@Param("idJeu") Integer idJeu);

    @Query("select j from Jeu as j inner join j.inscriptionsByNumjeu as i where i.apprenantByNumapprenant.numapprenant = :idApprenant")
    List<Jeu> findSubscribedJeuForApprenant(@Param("idApprenant") Integer idApprenant);

    @Query("select distinct a.numaction, a.scoremin " +
            "from Mission m, " +
            "Fixe f, " +
            "Objectif o, " +
            "EstAssocie ea, " +
            "Action a " +
            "where m.numjeu = :idJeu " +
            "and m.nummission = f.nummission " +
            "and f.numobjectif = o.numobjectif " +
            "and o.numobjectif = ea.numobjectif " +
            "and a.numaction = ea.numaction")
    List<Object[]> getActionScoreMinByJeu(@Param("idJeu") Integer idJeu);

    @Query("select distinct i.apprenantByNumapprenant from Jeu as j inner join j.inscriptionsByNumjeu as i where j.id=:idJeu")
    List<Apprenant> getInscritByJeu(@Param("idJeu") Integer idJeu);

    @Query("select j from Jeu j inner join j.missionsByNumjeu m inner join m.fixesByNummission f inner join f.objectifByNumobjectif o inner join o.estAssociesByNumobjectif ea inner join ea.actionByNumaction a where a.numaction = :idAction")
    List<Jeu> findJeuByIdAction(@Param("idAction") Integer idAction);

    @Query("from Jeu j where j.libellejeu like concat('%', concat(:label, '%'))")
    List<Jeu> findByLabel(@Param("label") String label);
}
