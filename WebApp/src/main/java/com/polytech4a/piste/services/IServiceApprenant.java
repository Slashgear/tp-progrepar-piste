package com.polytech4a.piste.services;

import com.polytech4a.piste.beans.Apprenant;

import java.util.List;

/**
 * Created by Antoine CARON on 04/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public interface IServiceApprenant {

    List<Apprenant> findAll();

    void createApprenant(final Apprenant apprenant);

    Apprenant findById(int id);
}
