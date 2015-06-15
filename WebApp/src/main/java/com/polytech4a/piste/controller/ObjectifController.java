package com.polytech4a.piste.controller;

import com.polytech4a.piste.beans.Objectif;
import com.polytech4a.piste.controller.components.ErrorPage;
import com.polytech4a.piste.controller.components.ReturnButton;
import com.polytech4a.piste.controller.components.breadcrumb.Breadcrumb;
import com.polytech4a.piste.controller.components.breadcrumb.BreadcrumbItem;
import com.polytech4a.piste.dao.ApprenantDAO;
import com.polytech4a.piste.dao.ObjectifDAO;
import com.polytech4a.piste.service.ObjectifService;
import com.polytech4a.piste.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Alexandre
 *         15/06/2015
 */
@Controller
@RequestMapping(value = "objectif")
public class ObjectifController {
    private static final String DIR_VIEW = "objectif";

    private static final String LIST_VIEW = "listeobjectif";
    private static final String DETAILS_VIEW = "detailsobjectif";

    @Autowired
    private ObjectifDAO objectifDAO;
    @Autowired
    private ObjectifService objectifService;
    @Autowired
    private ApprenantDAO apprenantDAO;
    @Autowired
    private ScoreService scoreService;

    @RequestMapping(method = RequestMethod.GET)
    public String displayList(final ModelMap pModel) {
        List<Objectif> objectifList = new ArrayList<>();
        objectifList.addAll(objectifDAO.findAll());

        // Attributes
        pModel.addAttribute("objectifs", objectifList);

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Objectifs"));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }


    @RequestMapping(value = "{objectifId}", method = RequestMethod.GET)
    public String display(final ModelMap pModel, @PathVariable(value = "objectifId") Integer objectifId) {
        Objectif objectif = objectifService.findByNumobjectifAndFetchAll(objectifId);
        if (objectif == null)
            return ErrorPage.newError(pModel, String.format("Objectif n°%s non trouvée !", objectifId), DIR_VIEW);


        Map<Integer, Double> statsObjectifs = scoreService.getAvgObjectifs();
        pModel.addAttribute("statsObjectifs", statsObjectifs);
        Map<Integer, Integer> scoresMinimum = scoreService.getScoresMinimum();
        pModel.addAttribute("scoresMinimum", scoresMinimum);
        Map<Integer, Integer> statsObjectifsFailure = scoreService.getCountScoreFailureForObjectif();
        pModel.addAttribute("statsObjectifsFailure", statsObjectifsFailure);
        Map<Integer, Integer> countScore = scoreService.getCountScore();
        pModel.addAttribute("countScore", countScore);
        Map<Integer, Double> scoresActions = scoreService.getAvgAction();
        pModel.addAttribute("scoresActions", scoresActions);
        Map<Integer, Integer> coefActions = scoreService.getCoefActions();
        pModel.addAttribute("coefActions", coefActions);

        // Attributes
        pModel.addAttribute("objectif", objectif);

        // Return button
        ReturnButton.addToModel(pModel, "objectif");

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Objectifs", "objectif"),
                new BreadcrumbItem(String.format("Objectif #%s", objectif.getNumobjectif())));
        Breadcrumb.addToModel(pModel, breadcrumbList);


        pModel.addAttribute("actionNb", objectifDAO.getNumberofActionbyObjectif(objectifId));
        pModel.addAttribute("apprenantNb", apprenantDAO.findAll().size());
        return String.format("%s/%s", DIR_VIEW, DETAILS_VIEW);
    }
}
