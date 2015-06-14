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

    @Query("select ea.numobjectif, avg(o.valeurdebut * i.poids)/sum(i.poids) from Fixe f, " +
            "EstAssocie ea, " +
            "Obtient o, " +
            "Indicateur i " +
            "where f.nummission = :numMission " +
            "and ea.numaction = o.numaction " +
            "and ea.numobjectif = f.numobjectif " +
            "and ea.numaction = i.numaction " +
            "group by ea.numobjectif")
    List<Object[]> getAvgActionForAllObjectifForMission(@Param(value = "numMission") Integer numMission);

    @Query("select f.nummission, avg(o.valeurdebut * i.poids)/sum(i.poids) from Mission m, " +
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
}
