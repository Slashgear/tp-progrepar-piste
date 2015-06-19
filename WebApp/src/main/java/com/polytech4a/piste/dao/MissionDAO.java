package com.polytech4a.piste.dao;

import com.polytech4a.piste.beans.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alexandre
 *         08/06/2015
 */
@Transactional
public interface MissionDAO extends JpaRepository<Mission, Integer> {
    List<Mission> findMissionsByNumjeu(Integer numJeu);

    @Query("select ea.numobjectif, avg(o.valeur * i.poids)/sum(i.poids) from Fixe f, " +
            "EstAssocie ea, " +
            "Obtient o, " +
            "Indicateur i " +
            "where f.nummission = :numMission " +
            "and ea.numaction = o.numaction " +
            "and ea.numobjectif = f.numobjectif " +
            "and ea.numaction = i.numaction " +
            "group by ea.numobjectif")
    List<Object[]> getAvgActionForAllObjectifForMission(@Param(value = "numMission") Integer numMission);

    @Query("select f.nummission, avg(o.valeur * i.poids)/sum(i.poids) from Mission m, " +
            "Fixe f, " +
            "EstAssocie ea, " +
            "Obtient o, " +
            "Indicateur i " +
            "where m.numjeu = :numJeu " +
            "and f.nummission = m.nummission " +
            "and ea.numaction = o.numaction " +
            "and ea.numobjectif = f.numobjectif " +
            "and ea.numaction = i.numaction " +
            "group by f.nummission")
    List<Object[]> getAvgAObjectifForAllMissions(@Param(value = "numJeu") Integer numJeu);

    @Query("select count(distinct o.numobjectif) from Mission m inner join  m.fixesByNummission f inner join f.objectifByNumobjectif as o where m.nummission=:numMission")
    Integer getNumberofObjectifByMission(@Param("numMission") Integer numMission);

    @Query("select count(distinct a.numaction )from " +
            "Mission m " +
            "inner join  m.fixesByNummission f " +
            "inner join f.objectifByNumobjectif as o " +
            "inner join o.estAssociesByNumobjectif as e " +
            "inner join e.actionByNumaction a where m.nummission = :numMission")
    Integer getNumberofActionbyMission(@Param("numMission") Integer numMission);

    @Query("from Mission m where m.libmission like concat('%', concat(:label, '%'))")
    List<Mission> findByLabel(@Param("label") String label);
}
