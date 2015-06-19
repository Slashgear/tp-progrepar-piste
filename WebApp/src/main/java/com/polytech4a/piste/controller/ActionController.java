package com.polytech4a.piste.controller;

import com.polytech4a.piste.beans.Action;
import com.polytech4a.piste.controller.components.ErrorPage;
import com.polytech4a.piste.controller.components.ReturnButton;
import com.polytech4a.piste.controller.components.breadcrumb.Breadcrumb;
import com.polytech4a.piste.controller.components.breadcrumb.BreadcrumbItem;
import com.polytech4a.piste.controller.components.chart.Chart;
import com.polytech4a.piste.controller.components.chart.ChartType;
import com.polytech4a.piste.controller.components.chart.Data;
import com.polytech4a.piste.dao.*;
import com.polytech4a.piste.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Alexandre
 *         08/06/2015
 */
@Controller
@RequestMapping(value = "action")
public class ActionController {
    private static final String DIR_VIEW = "action";

    private static final String LIST_VIEW = "listeaction";
    private static final String DETAILS_VIEW = "detailsaction";

    private static final String SEARCH_URL = "action/rechercher";
    private static final String SEARCH_LABEL = "Rechercher une action";

    @Autowired
    private ActionDAO actionDAO;
    @Autowired
    private ObtientDAO obtientDAO;
    @Autowired
    private EstAssocieDAO estAssocieDAO;
    @Autowired
    private ObjectifDAO objectifDAO;
    @Autowired
    private IndicateurDAO indicateurDAO;
    @Autowired
    private ScoreService scoreService;


    @RequestMapping(method = RequestMethod.GET)
    public String displayList(final ModelMap pModel) {
        List<Action> actionList = new ArrayList<>();
        actionList.addAll(actionDAO.findAll());

        // Attributes
        pModel.addAttribute("listeActions", actionList);
        Map<Integer, Double> scoresActions = scoreService.getAvgAction();
        pModel.addAttribute("scoresActions", scoresActions);
        Map<Integer, Integer> coefActions = scoreService.getCoefActions();
        pModel.addAttribute("coefActions", coefActions);

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Actions"));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        pModel.addAttribute("searchURL", SEARCH_URL);
        pModel.addAttribute("searchLabel", SEARCH_LABEL);

        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }

    @RequestMapping(value = "{actionId}", method = RequestMethod.GET)
    public String display(final ModelMap pModel, @PathVariable(value = "actionId") Integer actionID) {
        Action action = actionDAO.findOne(actionID);
        if (action == null)
            //return ErrorPage.newError(pModel, String.format("Action n°%s non trouvée !", actionID), DIR_VIEW);
            return ErrorPage.new404Error();

        Double averageScore = obtientDAO.getAvgValeurdebutForAction(actionID);

        // Attributes
        pModel.addAttribute("action", action);
        pModel.addAttribute("averageScore", averageScore);

        // Return button
        ReturnButton.addToModel(pModel, "action");

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Actions", "action"),
                new BreadcrumbItem(String.format("Action #%s", action.getNumaction())));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        Integer nbValidators = actionDAO.getNumberOfApprenantWhoValidateAction(actionID);
        pModel.addAttribute("nbValidators", nbValidators);
        Integer nbInscrits = actionDAO.getNumberOfApprenantforAction(actionID);
        pModel.addAttribute("nbInscrits", actionDAO.getNumberOfApprenantforAction(actionID));
        Integer nbObtient = obtientDAO.getNumberOfApprenantObtientforAction(actionID);
        pModel.addAttribute("nbObtient", nbObtient);
        Integer obt0_10 = obtientDAO.getNumberOfApprenantObtientforActionBetween(actionID, 0, 10);
        pModel.addAttribute("obt0_10", obt0_10);
        Integer obt10_14 = obtientDAO.getNumberOfApprenantObtientforActionBetween(actionID, 10, 14);
        pModel.addAttribute("obt10_14", obt10_14);
        Integer obt14_18 = obtientDAO.getNumberOfApprenantObtientforActionBetween(actionID, 14, 18);
        pModel.addAttribute("obt14_18", obt14_18);
        Integer obt18_20 = obtientDAO.getNumberOfApprenantObtientforActionBetween(actionID, 18, 20);
        pModel.addAttribute("obt18_20", obt18_20);
        pModel.addAttribute("childActions", actionDAO.findByActNumaction(actionID));
        pModel.addAttribute("coef", indicateurDAO.findByNumaction(actionID).getPoids());

        // PieChart 1
        Chart pieChartValidation = new Chart(ChartType.PIE, "Apprenants validant cette action");
        pieChartValidation.data.add(new Data("% d\\'apprenants validant l\\'action", nbValidators));
        pieChartValidation.data.add(new Data("% d\\'apprenants ne validant pas l\\'action", nbInscrits - nbValidators));
        pModel.addAttribute("pieChartValidation", pieChartValidation);

        // PieChart 2
        Chart pieChartObtention = new Chart(ChartType.PIE, "Apprenants inscrit ayant obtenu une note");
        pieChartObtention.data.add(new Data("% d\\'apprenants qui ont obtenu une note", nbObtient));
        pieChartObtention.data.add(new Data("% d\\'apprenants sans note", nbInscrits - nbObtient));
        pModel.addAttribute("pieChartObtention", pieChartObtention);

        // ColumnChart
        Chart columnChartRepartition = new Chart(ChartType.COLUMN, "Répartition des notes", new Data("", "Nombre d\\'apprenants"));
        columnChartRepartition.data.add(new Data("0 à 10", obt0_10));
        columnChartRepartition.data.add(new Data("10 à 14", obt10_14));
        columnChartRepartition.data.add(new Data("14 à 18", obt14_18));
        columnChartRepartition.data.add(new Data("18 à 20", obt18_20));
        pModel.addAttribute("columnChartRepartition", columnChartRepartition);

        pModel.addAttribute("pieChart", columnChartRepartition);

        pModel.addAttribute("searchURL", SEARCH_URL);
        pModel.addAttribute("searchLabel", SEARCH_LABEL);

        return String.format("%s/%s", DIR_VIEW, DETAILS_VIEW);
    }

    @RequestMapping(value = "rechercher", method = RequestMethod.POST)
    public String displaySearchResult(final ModelMap pModel, @ModelAttribute(value = "label") String label) {
        List<Action> actionList = new ArrayList<>();
        actionList.addAll(actionDAO.findByLabel(label));

        // Attributes
        pModel.addAttribute("listeActions", actionList);
        Map<Integer, Double> scoresActions = scoreService.getAvgAction();
        pModel.addAttribute("scoresActions", scoresActions);
        Map<Integer, Integer> coefActions = scoreService.getCoefActions();
        pModel.addAttribute("coefActions", coefActions);

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Actions", "action"),
                new BreadcrumbItem(String.format("Recherche : %s", label)));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        pModel.addAttribute("label", label);
        pModel.addAttribute("searchURL", SEARCH_URL);
        pModel.addAttribute("searchLabel", SEARCH_LABEL);

        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }
}
