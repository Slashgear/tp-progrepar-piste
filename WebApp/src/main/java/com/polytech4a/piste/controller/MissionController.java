package com.polytech4a.piste.controller;

import com.polytech4a.piste.beans.Mission;
import com.polytech4a.piste.controller.components.breadcrumb.Breadcrumb;
import com.polytech4a.piste.controller.components.breadcrumb.BreadcrumbItem;
import com.polytech4a.piste.dao.MissionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

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
}
