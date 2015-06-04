package com.polytech4a.piste.services.impl;

import com.polytech4a.piste.beans.Action;
import com.polytech4a.piste.repository.impl.ActionDAO;
import com.polytech4a.piste.services.IServiceAction;
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
public class ServiceAction implements IServiceAction {

    @Autowired
    private ActionDAO dao;

    @Override
    public List<Action> findAll() {
        return dao.findAll();
    }
}
