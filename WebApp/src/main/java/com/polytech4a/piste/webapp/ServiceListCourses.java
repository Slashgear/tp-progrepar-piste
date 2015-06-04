package com.polytech4a.piste.webapp;

import com.polytech4a.piste.persistence.dao.Action;
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
public class ServiceListCourses implements IServiceListAction {

    @Autowired
    private ListActionDAO dao;

    @Override
    public List<Action> rechercherAction() {
        return dao.rechercher();
    }
}
