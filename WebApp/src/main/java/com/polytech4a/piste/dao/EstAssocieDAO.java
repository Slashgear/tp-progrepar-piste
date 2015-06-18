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

    @Query("select sum(o.valeur * i.poids)/sum(i.poids) from Fixe f, " +
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

    @Query("select o.numaction, avg(o.valeur) as average, i.poids as poids  from Obtient o, \n" +
            "            Indicateur i \n" +
            "            where o.numaction = i.numaction\n" +
            "            group by o.numaction")
    List<Object[]> getAvgAction();

    @Query("select distinct i.numaction, i.poids from  Indicateur i")
    List<Object[]> getCoefAction();

    @Query("select sum(o.valeur * i.poids)/sum(i.poids) from EstAssocie ea, " +
            "Obtient o, " +
            "Indicateur i " +
            "where ea.numobjectif = :numObjectif " +
            "and o.numapprenant = :numApprenant " +
            "and ea.numaction = o.numaction " +
            "and ea.numaction = i.numaction ")
    Double getAvgActionByObjectifAndApprenant(@Param(value = "numObjectif") Integer numObjectif,
                                              @Param(value = "numApprenant") Integer numApprenant);

    @Query("select count(distinct o.numaction) from EstAssocie ea, " +
            "Obtient o " +
            "where ea.numobjectif = :numObjectif " +
            "and ea.numaction = o.numaction ")
    Integer getCountScoreActionByObjectif(@Param(value = "numObjectif") Integer numObjectif);

    @Query("select count(distinct o.numaction) from EstAssocie ea, " +
            "Obtient o " +
            "where ea.numobjectif = :numObjectif " +
            "and o.numapprenant = :numApprenant " +
            "and ea.numaction = o.numaction ")
    Integer getCountScoreActionByObjectifAndApprenant(@Param(value = "numObjectif") Integer numObjectif,
                                                      @Param(value = "numApprenant") Integer numApprenant);

    @Query("select count(distinct o.numaction) from EstAssocie ea, " +
            "Obtient o, " +
            "Action a " +
            "where ea.numobjectif = :numObjectif " +
            "and o.numapprenant = :numApprenant " +
            "and ea.numaction = o.numaction " +
            "and ea.numaction = a.numaction " +
            "and a.scoremin <= o.valeur")
    Integer getCountScoreSuccessActionByObjectifAndApprenant(@Param(value = "numObjectif") Integer numObjectif,
                                                             @Param(value = "numApprenant") Integer numApprenant);

    @Query("select count(distinct o.numaction) from EstAssocie ea, " +
            "Obtient o, " +
            "Action a " +
            "where ea.numobjectif = :numObjectif " +
            "and o.numapprenant = :numApprenant " +
            "and ea.numaction = o.numaction " +
            "and ea.numaction = a.numaction " +
            "and a.scoremin > o.valeur")
    Integer getCountScoreFailureActionByObjectifAndApprenant(@Param(value = "numObjectif") Integer numObjectif,
                                                             @Param(value = "numApprenant") Integer numApprenant);

    @Query("select count(distinct o.numaction) from EstAssocie ea, " +
            "Obtient o, " +
            "Action a " +
            "where ea.numobjectif = :numObjectif " +
            "and ea.numaction = o.numaction " +
            "and ea.numaction = a.numaction " +
            "and a.scoremin > o.valeur")
    Integer getCountScoreFailureActionByObjectif(@Param(value = "numObjectif") Integer numObjectif);

}
