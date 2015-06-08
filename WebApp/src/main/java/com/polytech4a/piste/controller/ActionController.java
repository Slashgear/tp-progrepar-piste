package com.polytech4a.piste.controller;

import com.polytech4a.piste.beans.Action;
import com.polytech4a.piste.dao.ActionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandre
 *         08/06/2015
 */
@Controller
@RequestMapping(value = "/action")
public class ActionController {

    @Autowired
    private ActionDAO actionDAO;

    @RequestMapping(value = "/{actionId}", method = RequestMethod.GET)
    public String getById(final ModelMap pModel, @PathVariable(value = "actionId") Integer actionID) {
        List<Action> actionList = new ArrayList<>();
        actionList.add(actionDAO.findOne(actionID));
        pModel.addAttribute("listeActions", actionList);
        return "listeaction";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(final ModelMap pModel) {
        pModel.addAttribute("listeActions", actionDAO.findAll());
        return "listeaction";
    }
}
