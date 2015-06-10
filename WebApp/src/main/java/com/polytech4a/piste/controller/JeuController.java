package com.polytech4a.piste.controller;

import com.polytech4a.piste.beans.Jeu;
import com.polytech4a.piste.dao.JeuDAO;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String display(final ModelMap pModel, @PathVariable(value = "id") Integer id) {
        Jeu jeu = jeuDAO.findOne(id);
        if (jeu != null) {
            pModel.addAttribute("jeu", jeu);
            return String.format("%s/%s", DIR_VIEW, DETAILS_VIEW);
        } else {
            return com.polytech4a.piste.controller.components.Error.newError(pModel, String.format("Jeu n°%s non trouvée !", id), DIR_VIEW);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayList(final ModelMap pModel) {
        List<Jeu> jeux = jeuDAO.findAll();
        pModel.addAttribute("jeux", jeux);
        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }
}
