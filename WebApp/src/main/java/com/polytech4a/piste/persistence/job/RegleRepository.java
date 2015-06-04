package com.polytech4a.piste.persistence.job;

import com.polytech4a.piste.persistence.dao.Regle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Antoine CARON on 03/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public interface RegleRepository extends JpaRepository<Regle,Long>{

    /*List<Regle> findByNumRegle(Integer numRegle);*/
}
