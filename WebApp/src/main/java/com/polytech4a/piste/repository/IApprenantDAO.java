package com.polytech4a.piste.repository;

import com.polytech4a.piste.beans.Apprenant;

import java.util.List;

/**
 * Created by Antoine CARON on 04/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public interface IApprenantDAO {

    /**
     * Returns all Apprenants.
     *
     * @return Apprenants.
     */
    List<Apprenant> findAll();

    /**
     * Persist an Apprenant.
     *
     * @param apprenant
     */
    void createApprenant(final Apprenant apprenant);
}
