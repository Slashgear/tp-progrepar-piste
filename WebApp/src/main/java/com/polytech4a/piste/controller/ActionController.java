package com.polytech4a.piste.controller;

import com.polytech4a.piste.beans.Action;
import com.polytech4a.piste.controller.components.Error;
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
    private static final String DIR_VIEW = "action";

    private static final String LIST_VIEW = "listeaction";
    private static final String DETAILS_VIEW = "detailsaction";

    @Autowired
    private ActionDAO actionDAO;

    @RequestMapping(value = "/{actionId}", method = RequestMethod.GET)
    public String display(final ModelMap pModel, @PathVariable(value = "actionId") Integer actionID) {
        Action action = actionDAO.findOne(actionID);
        if (action == null)
            return Error.newError(pModel, String.format("Action n°%s non trouvée !", actionID), DIR_VIEW);

        pModel.addAttribute("action", action);
        pModel.addAttribute("returnPage", "/action");
        return String.format("%s/%s", DIR_VIEW, DETAILS_VIEW);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayList(final ModelMap pModel) {
        List<Action> actionList = new ArrayList<>();
        actionList.addAll(actionDAO.findAll());
        pModel.addAttribute("listeActions", actionList);
        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }
}
