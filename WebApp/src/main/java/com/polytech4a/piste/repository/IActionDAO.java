package com.polytech4a.piste.repository;

import com.polytech4a.piste.beans.Action;

import java.util.List;

/**
 * Created by Antoine CARON on 04/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public interface IActionDAO {
    /**
     * Returns All Actions.
     *
     * @return Actions.
     */
    List<Action> findAll();
}
