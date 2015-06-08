package com.polytech4a.piste.repository;

import com.polytech4a.piste.beans.Apprenant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Antoine CARON on 04/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public interface IApprenantDAO extends JpaRepository<Apprenant, Integer> {
}
