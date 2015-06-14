package com.polytech4a.piste.controller;

import com.polytech4a.piste.beans.Jeu;
import com.polytech4a.piste.controller.components.ErrorPage;
import com.polytech4a.piste.controller.components.ReturnButton;
import com.polytech4a.piste.controller.components.breadcrumb.Breadcrumb;
import com.polytech4a.piste.controller.components.breadcrumb.BreadcrumbItem;
import com.polytech4a.piste.dao.ApprenantDAO;
import com.polytech4a.piste.dao.JeuDAO;
import com.polytech4a.piste.ws.JeuWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


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

    @Autowired
    private JeuDAO jeuDAO;
    @Autowired
    private ApprenantDAO apprenantDAO;
    @Autowired
    private JeuWS jeuWS;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String display(final ModelMap pModel, @PathVariable(value = "id") Integer id) {
        Jeu jeu = jeuWS.findByNumjeuAndFetchAll(id);

        if (jeu == null) return ErrorPage.newError(pModel, String.format("Jeu n°%s non trouvée !", id), DIR_VIEW);

        // Attributes
        pModel.addAttribute("jeu", jeu);

        // Return button
        ReturnButton.addToModel(pModel, "/jeu");

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", "/"),
                new BreadcrumbItem("Jeux", "/jeu"),
                new BreadcrumbItem(String.format("#%s", jeu.getNumjeu())));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        pModel.addAttribute("actionNb", jeuDAO.getNumberofActionbyJeu(id));
        pModel.addAttribute("missionNb", jeuDAO.getNumberofMissionByJeu(id));
        pModel.addAttribute("objectifNb", jeuDAO.getNumberofObjectifByJeu(id));
        pModel.addAttribute("inscritNb", jeuDAO.getNumberofInscritByJeu(id));
        pModel.addAttribute("apprenantNb", apprenantDAO.findAll().size());

        return String.format("%s/%s", DIR_VIEW, DETAILS_VIEW);

    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayList(final ModelMap pModel) {
        List<Jeu> jeux = jeuDAO.findAll();

        // Attributes
        pModel.addAttribute("jeux", jeux);

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", "/"),
                new BreadcrumbItem("Jeux"));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }
}
