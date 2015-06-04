package com.polytech4a.piste.webapp;

import com.polytech4a.piste.persistence.dao.Action;

import java.util.List;

/**
 * Created by Antoine CARON on 04/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public interface IServiceListAction {

    List<Action> rechercherAction();

}
