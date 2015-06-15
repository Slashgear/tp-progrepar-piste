package com.polytech4a.piste.controller;

import com.polytech4a.piste.beans.Apprenant;
import com.polytech4a.piste.beans.Jeu;
import com.polytech4a.piste.controller.components.ErrorPage;
import com.polytech4a.piste.controller.components.ReturnButton;
import com.polytech4a.piste.controller.components.breadcrumb.Breadcrumb;
import com.polytech4a.piste.controller.components.breadcrumb.BreadcrumbItem;
import com.polytech4a.piste.dao.ApprenantDAO;
import com.polytech4a.piste.dao.JeuDAO;
import com.polytech4a.piste.service.JeuService;
import com.polytech4a.piste.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;


/**
 * Created by Antoine CARON on 10/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/jeu")
public class JeuController {
    private static final String DIR_VIEW = "jeu";

    private static final String LIST_VIEW = "listejeu";
    private static final String DETAILS_VIEW = "detailsjeu";
    private static final String DETAILS_FOR_APPRENANT_VIEW = "detailsjeupourapprenant";

    @Autowired
    private JeuDAO jeuDAO;
    @Autowired
    private ApprenantDAO apprenantDAO;
    @Autowired
    private JeuService jeuService;
    @Autowired
    private ScoreService scoreService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String display(final ModelMap pModel, @PathVariable(value = "id") Integer id) {
        Jeu jeu = jeuService.findByNumjeuAndFetchAll(id);

        if (jeu == null) return ErrorPage.newError(pModel, String.format("Jeu n°%s non trouvée !", id), DIR_VIEW);

        // Attributes
        pModel.addAttribute("jeu", jeu);

        Map<Integer, Double> statsObjectifs = scoreService.getAvgObjectifs();
        pModel.addAttribute("statsObjectifs", statsObjectifs);
        Map<Integer, Double> statsMissions = scoreService.getAvgObjectifsForAllMissions(jeu);
        pModel.addAttribute("statsMissions", statsMissions);
        Map<Integer, Integer> scoresMinimum = scoreService.getScoresMinimum();
        pModel.addAttribute("scoresMinimum", scoresMinimum);
        Map<Integer, Integer> statsObjectifsFailure = scoreService.getCountScoreFailureForObjectif();
        pModel.addAttribute("statsObjectifsFailure", statsObjectifsFailure);
        Map<Integer, Integer> countScore = scoreService.getCountScore();
        pModel.addAttribute("countScore", countScore);
        Map<Integer, Double> scoresActions = scoreService.getAvgAction();
        pModel.addAttribute("scoresActions", scoresActions);

        // Return button
        ReturnButton.addToModel(pModel, "jeu");

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Jeux", "jeu"),
                new BreadcrumbItem(String.format("#%s", jeu.getNumjeu())));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        pModel.addAttribute("actionNb", jeuDAO.getNumberofActionbyJeu(id));
        pModel.addAttribute("missionNb", jeuDAO.getNumberofMissionByJeu(id));
        pModel.addAttribute("objectifNb", jeuDAO.getNumberofObjectifByJeu(id));
        pModel.addAttribute("inscritNb", jeuDAO.getNumberofInscritByJeu(id));
        pModel.addAttribute("apprenantNb", apprenantDAO.findAll().size());

        return String.format("%s/%s", DIR_VIEW, DETAILS_VIEW);
    }


    @RequestMapping(value = "{id}/apprenant/{numApprenant}", method = RequestMethod.GET)
    public String displayForApprenant(final ModelMap pModel, @PathVariable(value = "id") Integer id,
                                      @PathVariable(value = "numApprenant") Integer numApprenant) {
        Jeu jeu = jeuService.findByNumjeuAndFetchAll(id);
        Apprenant apprenant = apprenantDAO.findOne(numApprenant);

        if (jeu == null) return ErrorPage.newError(pModel, String.format("Jeu n°%s non trouvée !", id), DIR_VIEW);
        if (apprenant == null)
            return ErrorPage.newError(pModel, String.format("Apprenant n°%s non trouvée !", numApprenant), DIR_VIEW);

        // Attributes
        pModel.addAttribute("jeu", jeu);
        pModel.addAttribute("apprenant", apprenant);

        Map<Integer, Double> statsObjectifs = scoreService.getAvgActionForAllObjectifsForApprenant(jeu, numApprenant);
        pModel.addAttribute("statsObjectifs", statsObjectifs);
        Map<Integer, Double> statsMissions = scoreService.getAvgObjectifsForAllMissionsForApprenant(jeu, numApprenant);
        pModel.addAttribute("statsMissions", statsMissions);
        Map<Integer, Integer> scoresMinimum = scoreService.getScoresMinimum();
        pModel.addAttribute("scoresMinimum", scoresMinimum);
        Map<Integer, Integer> statsObjectifsFailure = scoreService.getCountScoreFailureForObjectifForApprenant(numApprenant);
        pModel.addAttribute("statsObjectifsFailure", statsObjectifsFailure);
        Map<Integer, Integer> countScore = scoreService.getCountScoreForApprenant(numApprenant);
        pModel.addAttribute("countScore", countScore);
        Map<Integer, Integer> scoresActions = scoreService.getScoreForApprenant(numApprenant);
        pModel.addAttribute("scoresActions", scoresActions);

        // Return button
        ReturnButton.addToModel(pModel, "jeu");

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Jeux", "jeu"),
                new BreadcrumbItem(String.format("#%s", jeu.getNumjeu()), String.format("jeu/%s", jeu.getNumjeu())),
                new BreadcrumbItem(String.format("Apprenants #%s", numApprenant)));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        pModel.addAttribute("actionNb", jeuDAO.getNumberofActionbyJeu(id));
        pModel.addAttribute("missionNb", jeuDAO.getNumberofMissionByJeu(id));
        pModel.addAttribute("objectifNb", jeuDAO.getNumberofObjectifByJeu(id));
        pModel.addAttribute("inscritNb", jeuDAO.getNumberofInscritByJeu(id));
        pModel.addAttribute("apprenantNb", apprenantDAO.findAll().size());

        return String.format("%s/%s", DIR_VIEW, DETAILS_FOR_APPRENANT_VIEW);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayList(final ModelMap pModel) {
        List<Jeu> jeux = jeuDAO.findAll();

        // Attributes
        pModel.addAttribute("jeux", jeux);

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Jeux"));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }
}
