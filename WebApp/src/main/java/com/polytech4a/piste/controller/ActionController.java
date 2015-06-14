package com.polytech4a.piste.controller;

import com.polytech4a.piste.beans.Action;
import com.polytech4a.piste.beans.EstAssocie;
import com.polytech4a.piste.controller.components.ErrorPage;
import com.polytech4a.piste.controller.components.ReturnButton;
import com.polytech4a.piste.controller.components.breadcrumb.Breadcrumb;
import com.polytech4a.piste.controller.components.breadcrumb.BreadcrumbItem;
import com.polytech4a.piste.dao.ActionDAO;
import com.polytech4a.piste.dao.EstAssocieDAO;
import com.polytech4a.piste.dao.ObjectifDAO;
import com.polytech4a.piste.dao.ObtientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private ObtientDAO obtientDAO;
    @Autowired
    private EstAssocieDAO estAssocieDAO;
    @Autowired
    private ObjectifDAO objectifDAO;

    @RequestMapping(value = "/{actionId}", method = RequestMethod.GET)
    public String display(final ModelMap pModel, @PathVariable(value = "actionId") Integer actionID) {
        Action action = actionDAO.findOne(actionID);
        if (action == null)
            return ErrorPage.newError(pModel, String.format("Action n°%s non trouvée !", actionID), DIR_VIEW);

        Double averageScore = obtientDAO.getAvgValeurdebutForAction(actionID);

        // Attributes
        pModel.addAttribute("action", action);
        pModel.addAttribute("averageScore", averageScore);

        // Return button
        ReturnButton.addToModel(pModel, "/action");

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", "/"),
                new BreadcrumbItem("Actions", "/action"),
                new BreadcrumbItem(String.format("Action #%s", action.getNumaction())));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        return String.format("%s/%s", DIR_VIEW, DETAILS_VIEW);
    }

    @RequestMapping(value = "/objectif/{objectifId}", method = RequestMethod.GET)
    public String displayListForObjectif(final ModelMap pModel, @PathVariable(value = "objectifId") Integer objectifId) {
        if (objectifDAO.findOne(objectifId) == null)
            return ErrorPage.newError(pModel, String.format("L'objectif n°%s n'a pas été trouvé !", objectifId));

        List<Action> actionList = new ArrayList<>();
        List<EstAssocie> estAssocieList = estAssocieDAO.findEstassociesByNumobjectif(objectifId);
        actionList.addAll(estAssocieList.stream().map(EstAssocie::getActionByNumaction).collect(Collectors.toList()));

        // Attributes
        pModel.addAttribute("listeActions", actionList);

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", "/"),
                new BreadcrumbItem("Actions", "/action"),
                new BreadcrumbItem(String.format("Objectif #%s", objectifId)));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayList(final ModelMap pModel) {
        List<Action> actionList = new ArrayList<>();
        actionList.addAll(actionDAO.findAll());

        // Attributes
        pModel.addAttribute("listeActions", actionList);

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", "/"),
                new BreadcrumbItem("Actions"));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }
}
