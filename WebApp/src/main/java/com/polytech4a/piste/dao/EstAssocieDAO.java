package com.polytech4a.piste.dao;

import com.polytech4a.piste.beans.EstAssocie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Alexandre
 *         08/06/2015
 */
public interface EstAssocieDAO extends JpaRepository<EstAssocie, Integer> {
    List<EstAssocie> findEstassociesByNumobjectif(Integer numobjectif);

    @Query("select avg(o.valeurdebut * i.poids)/sum(i.poids) from EstAssocie ea, " +
            "Obtient o, " +
            "Indicateur i " +
            "where ea.numobjectif = :numObjectif " +
            "and ea.numaction = o.numaction " +
            "and ea.numaction = i.numaction ")
    Double getAvgActionByObjectif(@Param(value = "numObjectif") Integer numObjectif);

    @Query("select avg(o.valeurdebut * i.poids)/sum(i.poids) from Fixe f, " +
            "EstAssocie ea, " +
            "Obtient o, " +
            "Indicateur i " +
            "where ea.numobjectif = :numObjectif " +
            "and f.nummission = :numMission " +
            "and ea.numaction = o.numaction " +
            "and ea.numobjectif = f.numobjectif " +
            "and ea.numaction = i.numaction ")
    Double getAvgActionByObjectifAndMission(@Param(value = "numMission") Integer numMission,
                                            @Param(value = "numObjectif") Integer numObjectif);

}
