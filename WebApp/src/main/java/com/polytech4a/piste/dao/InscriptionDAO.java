package com.polytech4a.piste.dao;

import com.polytech4a.piste.beans.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Antoine CARON on 10/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public interface InscriptionDAO extends JpaRepository<Inscription, Integer> {
}
