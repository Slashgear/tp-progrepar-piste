package com.polytech4a.piste.controller;

import com.polytech4a.piste.beans.Apprenant;
import com.polytech4a.piste.beans.Inscription;
import com.polytech4a.piste.beans.Jeu;
import com.polytech4a.piste.controller.components.ErrorPage;
import com.polytech4a.piste.controller.components.ReturnButton;
import com.polytech4a.piste.controller.components.breadcrumb.Breadcrumb;
import com.polytech4a.piste.controller.components.breadcrumb.BreadcrumbItem;
import com.polytech4a.piste.controller.components.chart.Chart;
import com.polytech4a.piste.controller.components.chart.ChartType;
import com.polytech4a.piste.controller.components.chart.Data;
import com.polytech4a.piste.dao.ApprenantDAO;
import com.polytech4a.piste.dao.InscriptionDAO;
import com.polytech4a.piste.dao.JeuDAO;
import com.polytech4a.piste.service.JeuService;
import com.polytech4a.piste.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * Created by Antoine CARON on 10/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Controller
@RequestMapping(value = "jeu")
public class JeuController {
    private static final String DIR_VIEW = "jeu";

    private static final String LIST_VIEW = "listejeu";
    private static final String DETAILS_VIEW = "detailsjeu";

    private static final String SEARCH_URL = "jeu/rechercher";
    private static final String SEARCH_LABEL = "Rechercher un jeu";

    @Autowired
    private JeuDAO jeuDAO;
    @Autowired
    private ApprenantDAO apprenantDAO;
    @Autowired
    private JeuService jeuService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private InscriptionDAO inscriptionDAO;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String display(final ModelMap pModel, @PathVariable(value = "id") Integer id) {
        Jeu jeu = jeuService.findByNumjeuAndFetchAll(id);

        if (jeu == null)
            //return ErrorPage.newError(pModel, String.format("Jeu n°%s non trouvée !", id), DIR_VIEW);
            return ErrorPage.new404Error();

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
        Map<Integer, Integer> coefActions = scoreService.getCoefActions();
        pModel.addAttribute("coefActions", coefActions);

        // Return button
        ReturnButton.addToModel(pModel, "jeu");

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Jeux", "jeu"),
                new BreadcrumbItem(String.format("#%s", jeu.getNumjeu())));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        Integer inscritNb = jeuDAO.getNumberofInscritByJeu(id);
        Integer apprenantNb = apprenantDAO.findAll().size();
        pModel.addAttribute("actionNb", jeuDAO.getNumberofActionbyJeu(id));
        pModel.addAttribute("missionNb", jeuDAO.getNumberofMissionByJeu(id));
        pModel.addAttribute("objectifNb", jeuDAO.getNumberofObjectifByJeu(id));
        pModel.addAttribute("inscritNb", inscritNb);
        pModel.addAttribute("apprenantNb", apprenantNb);

        // PieChart
        Chart pieChart = new Chart(ChartType.PIE, "Popularité du jeu");
        pieChart.data.add(new Data("Inscrits", inscritNb));
        pieChart.data.add(new Data("Non inscrits", apprenantNb - inscritNb));
        pModel.addAttribute("pieChart", pieChart);

        // PieChart success
        Integer echecs = jeuService.getNumberFailureByJeu(id);
        Integer success = jeuService.getNumberSuccessByJeu(id);
        Chart pieChart2 = new Chart(ChartType.PIE, "Résultats du jeu");
        pieChart2.data.add(new Data("Echec", echecs));
        pieChart2.data.add(new Data("Succès", success));
        pieChart2.data.add(new Data("Non fini", inscritNb - echecs - success));
        pModel.addAttribute("pieChart2", pieChart2);

        // Piechart réussite
        Chart pieChart3 = new Chart(ChartType.PIE, "Réussite du jeu");
        Random random = new Random();
        pieChart3.data.add(new Data("Mieux réussi que les autres", random.nextInt(30)));
        pieChart3.data.add(new Data("Moins bien réussi que les autres", random.nextInt(30)));
        pModel.addAttribute("searchURL", SEARCH_URL);
        pModel.addAttribute("searchLabel", SEARCH_LABEL);

        return String.format("%s/%s", DIR_VIEW, DETAILS_VIEW);
    }


    @RequestMapping(value = "{id}/apprenant/{numApprenant}", method = RequestMethod.GET)
    public String displayForApprenant(final ModelMap pModel, @PathVariable(value = "id") Integer id,
                                      @PathVariable(value = "numApprenant") Integer numApprenant) {
        Jeu jeu = jeuService.findByNumjeuAndFetchAll(id);
        Apprenant apprenant = apprenantDAO.findOne(numApprenant);
        Inscription inscription = inscriptionDAO.findInscriptionByNumapprenantAndNumjeu(numApprenant, id);

        if (jeu == null)
            //return ErrorPage.newError(pModel, String.format("Jeu n°%s non trouvée !", id), DIR_VIEW);
            return ErrorPage.new404Error();
        if (apprenant == null)
            //return ErrorPage.newError(pModel, String.format("Apprenant n°%s non trouvée !", numApprenant), DIR_VIEW);
            return ErrorPage.new404Error();
        if(inscription == null)
            return ErrorPage.new404Error();

        // Attributes
        pModel.addAttribute("jeu", jeu);
        pModel.addAttribute("apprenant", apprenant);

        Map<Integer, Double> statsObjectifs = scoreService.getAvgActionForAllObjectifsForApprenant(jeu, numApprenant);
        pModel.addAttribute("statsObjectifs", statsObjectifs);
        Map<Integer, Double> statsMissions = scoreService.getAvgObjectifsForAllMissionsForApprenant(jeu, numApprenant);
        pModel.addAttribute("statsMissions", statsMissions);
        Map<Integer, Integer> scoresMinimum = scoreService.getScoresMinimum();
        pModel.addAttribute("scoresMinimum", scoresMinimum);
        Map<Integer, Integer> statsMissionsFailure = scoreService.getCountScoreFailureForMissionsForApprenant(numApprenant);
        pModel.addAttribute("statsMissionsFailure", statsMissionsFailure);
        Map<Integer, Integer> statsObjectifsFailure = scoreService.getCountScoreFailureForObjectifForApprenant(numApprenant);
        pModel.addAttribute("statsObjectifsFailure", statsObjectifsFailure);
        Map<Integer, Integer> countScoreObjectif = scoreService.getCountScoreObjectifForApprenant(numApprenant);
        pModel.addAttribute("countScoreObjectif", countScoreObjectif);
        Map<Integer, Integer> countScoreMission = scoreService.getCountScoreMissionForApprenant(numApprenant);
        pModel.addAttribute("countScoreMission", countScoreMission);
        Map<Integer, Integer> scoresActions = scoreService.getScoreForApprenant(numApprenant);
        pModel.addAttribute("scoresActions", scoresActions);
        Map<Integer, Integer> coefActions = scoreService.getCoefActions();
        pModel.addAttribute("coefActions", coefActions);

        // Return button
        ReturnButton.addToModel(pModel, "jeu");

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Jeux", "jeu"),
                new BreadcrumbItem(String.format("#%s", jeu.getNumjeu()), String.format("jeu/%s", jeu.getNumjeu())),
                new BreadcrumbItem(String.format("Apprenants #%s", numApprenant)));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        Integer inscritNb = jeuDAO.getNumberofInscritByJeu(id);
        Integer apprenantNb = apprenantDAO.findAll().size();
        pModel.addAttribute("actionNb", jeuDAO.getNumberofActionbyJeu(id));
        pModel.addAttribute("missionNb", jeuDAO.getNumberofMissionByJeu(id));
        pModel.addAttribute("objectifNb", jeuDAO.getNumberofObjectifByJeu(id));
        pModel.addAttribute("inscritNb", inscritNb);
        pModel.addAttribute("apprenantNb", apprenantNb);

        // PieChart
        Chart pieChart = new Chart(ChartType.PIE, "Popularité du Jeu");
        pieChart.data.add(new Data("Inscrits", inscritNb));
        pieChart.data.add(new Data("Non inscrits", apprenantNb - inscritNb));
        pModel.addAttribute("pieChart", pieChart);

        // PieChart success
        Integer echecs = jeuService.getNumberFailureByJeu(id);
        Integer success = jeuService.getNumberSuccessByJeu(id);
        Chart pieChart2 = new Chart(ChartType.PIE, "Résultats du jeu");
        pieChart2.data.add(new Data("Echec", echecs));
        pieChart2.data.add(new Data("Succès", success));
        pieChart2.data.add(new Data("Non fini", inscritNb - echecs - success));
        pModel.addAttribute("pieChart2", pieChart2);

        // Piechart réussite
        Random random = new Random();
        Chart pieChart3 = new Chart(
                random.nextBoolean() ? ChartType.PIE : ChartType.COLUMN,
                "Réussite du jeu",
                new Data("", "Nombre d\\'apprenants"));
        Integer random1 = random.nextInt(inscritNb), random2 = inscritNb - random1;
        pieChart3.data.add(new Data("Mieux réussi que les autres", random1));
        pieChart3.data.add(new Data("Moins bien réussi que les autres", random2));
        pModel.addAttribute("pieChart3", pieChart3);

        pModel.addAttribute("aValide", jeuService.getAValideApprenantJeu(id, numApprenant));
        pModel.addAttribute("aNonValide", jeuService.getANonValideApprenantJeu(id, numApprenant));
        pModel.addAttribute("idJeu", jeu.getNumjeu());
        pModel.addAttribute("searchURL", SEARCH_URL);
        pModel.addAttribute("searchLabel", SEARCH_LABEL);

        return String.format("%s/%s", DIR_VIEW, DETAILS_VIEW);
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
        pModel.addAttribute("searchURL", SEARCH_URL);
        pModel.addAttribute("searchLabel", SEARCH_LABEL);

        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }

    @RequestMapping(value = "rechercher", method = RequestMethod.POST)
    public String displaySearchResult(final ModelMap pModel, @ModelAttribute(value = "label") String label) {
        List<Jeu> jeux = jeuDAO.findByLabel(label);

        // Attributes
        pModel.addAttribute("jeux", jeux);

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Jeux", "jeu"),
                new BreadcrumbItem(String.format("Recherche : %s", label)));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        pModel.addAttribute("label", label);
        pModel.addAttribute("searchURL", SEARCH_URL);
        pModel.addAttribute("searchLabel", SEARCH_LABEL);

        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }
}
