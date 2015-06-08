package com.polytech4a.piste.services.impl;

import com.polytech4a.piste.beans.Apprenant;
import com.polytech4a.piste.repository.impl.ApprenantDAO;
import com.polytech4a.piste.services.IServiceApprenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Antoine CARON on 04/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Service
public class ServiceApprenant implements IServiceApprenant {

    @Autowired
    private ApprenantDAO dao;

    @Override
    public List<Apprenant> findAll() {
        return dao.findAll();
    }

    @Override
    public void createApprenant(final Apprenant apprenant) {
        dao.createApprenant(apprenant);
    }

    @Override
    public Apprenant findById(int id) {
        return dao.findById(id);
    }
}
