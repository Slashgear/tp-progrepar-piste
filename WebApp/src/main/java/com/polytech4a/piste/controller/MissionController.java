package com.polytech4a.piste.controller;

import com.polytech4a.piste.beans.Mission;
import com.polytech4a.piste.controller.components.ErrorPage;
import com.polytech4a.piste.controller.components.ReturnButton;
import com.polytech4a.piste.controller.components.breadcrumb.Breadcrumb;
import com.polytech4a.piste.controller.components.breadcrumb.BreadcrumbItem;
import com.polytech4a.piste.dao.ApprenantDAO;
import com.polytech4a.piste.dao.MissionDAO;
import com.polytech4a.piste.service.MissionService;
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
@RequestMapping(value = "mission")
public class MissionController {
    private static final String DIR_VIEW = "mission";

    private static final String LIST_VIEW = "listemission";
    private static final String DETAILS_VIEW = "detailsmission";

    @Autowired
    private MissionDAO missionDAO;
    @Autowired
    private MissionService missionService;
    @Autowired
    private ApprenantDAO apprenantDAO;
    @Autowired
    private ScoreService scoreService;

    @RequestMapping(method = RequestMethod.GET)
    public String displayList(final ModelMap pModel) {
        List<Mission> missionList = new ArrayList<>();
        missionList.addAll(missionDAO.findAll());

        // Attributes
        pModel.addAttribute("missions", missionList);

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Missions"));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }


    @RequestMapping(value = "{missionId}", method = RequestMethod.GET)
    public String display(final ModelMap pModel, @PathVariable(value = "missionId") Integer missionId) {
        Mission mission = missionService.findByNummissionAndFetchAll(missionId);
        if (mission == null)
            return ErrorPage.newError(pModel, String.format("Mission n°%s non trouvée !", missionId), DIR_VIEW);


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
        pModel.addAttribute("mission", mission);

        // Return button
        ReturnButton.addToModel(pModel, "mission");

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Missions", "mission"),
                new BreadcrumbItem(String.format("Mission #%s", mission.getNummission())));
        Breadcrumb.addToModel(pModel, breadcrumbList);


        pModel.addAttribute("actionNb", missionDAO.getNumberofActionbyMission(missionId));
        pModel.addAttribute("objectifNb", missionDAO.getNumberofObjectifByMission(missionId));
        pModel.addAttribute("apprenantNb", apprenantDAO.findAll().size());
        return String.format("%s/%s", DIR_VIEW, DETAILS_VIEW);
    }
}
