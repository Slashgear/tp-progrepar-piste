package com.polytech4a.piste.controller;

import com.polytech4a.piste.beans.Action;
import com.polytech4a.piste.beans.Apprenant;
import com.polytech4a.piste.beans.Jeu;
import com.polytech4a.piste.beans.Obtient;
import com.polytech4a.piste.controller.components.ErrorPage;
import com.polytech4a.piste.dao.ActionDAO;
import com.polytech4a.piste.dao.ApprenantDAO;
import com.polytech4a.piste.dao.JeuDAO;
import com.polytech4a.piste.dao.ObtientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Adrien CHAUSSENDE on 15/06/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/obtient")
public class ObtientController {
    private final static String DIR_VIEW = "obtentionnote";
    private final static String FORM_VIEW = "formobtention";

    @Autowired
    private ObtientDAO obtientDAO;
    @Autowired
    private ApprenantDAO apprenantDAO;
    @Autowired
    private ActionDAO actionDAO;
    @Autowired
    private JeuDAO jeuDAO;

    @RequestMapping(value = "{idApprenant}/action/{idAction}/jeu/{idJeu}/ajout", method = RequestMethod.GET)
    public String displayAddForm(final ModelMap pModel,
                                 @PathVariable(value = "idApprenant") Integer idApprenant,
                                 @PathVariable(value = "idAction") Integer idAction,
                                 @PathVariable(value = "idJeu") Integer idJeu) {
        pModel.addAttribute("confirmButtonLabel", "Ajouter");
        Action action = actionDAO.findOne(idAction);
        Apprenant apprenant = apprenantDAO.findOne(idApprenant);
        List<Jeu> jeuxpouraction = jeuDAO.findJeuByIdAction(idAction);
        List<Jeu> jeuxinsc = jeuDAO.findSubscribedJeuForApprenant(idApprenant);
        Jeu jeuapprenant = jeuDAO.findOne(idJeu);
        pModel.addAttribute("idJeu", idJeu);
        //At least one game is subscribed, so the Apprenant can have a grade to that Action
        if (jeuxinsc.contains(jeuapprenant) && jeuxpouraction.contains(jeuapprenant)) {
            //If he has not already a grade,
            if (obtientDAO.findByNumapprenantAndNumaction(idApprenant, idAction) == null) {
                //Display add form
                pModel.addAttribute("apprenant", apprenant);
                pModel.addAttribute("action", action);
                pModel.addAttribute("formaction", "ajout");
                return String.format("%s/%s", DIR_VIEW, FORM_VIEW);
            } else {
                //Display modify form
                return displayModifyForm(pModel, idApprenant, idAction, idJeu);
            }
        }
        return ErrorPage.new404Error();
    }

    @RequestMapping(value = "{idApprenant}/action/{idAction}/jeu/{idJeu}/modifier", method = RequestMethod.GET)
    public String displayModifyForm(final ModelMap pModel,
                                    @PathVariable(value = "idApprenant") Integer idApprenant,
                                    @PathVariable(value = "idAction") Integer idAction,
                                    @PathVariable(value = "idJeu") Integer idJeu) {
        pModel.addAttribute("confirmButtonLabel", "Modifier");
        pModel.addAttribute("formaction", "modifier");
        Action action = actionDAO.findOne(idAction);
        Apprenant apprenant = apprenantDAO.findOne(idApprenant);
        pModel.addAttribute("apprenant", apprenant);
        pModel.addAttribute("action", action);
        Obtient note = obtientDAO.findByNumapprenantAndNumaction(idApprenant, idAction);
        if (note != null) {
            pModel.addAttribute("note", note.getValeur());
            return String.format("%s/%s", DIR_VIEW, FORM_VIEW);
        } else {
            return ErrorPage.new404Error();
        }
    }

    @RequestMapping(value = "/{idApprenant}/action/{idAction}/jeu/{idJeu}/ajout", method = RequestMethod.POST)
    public String addObtention(final ModelMap pModel,
                               @PathVariable(value = "idApprenant") Integer idApprenant,
                               @PathVariable(value = "idAction") Integer idAction,
                               @PathVariable(value = "idJeu") Integer idJeu,
                               @RequestParam("note") Integer note) {
        Obtient obtient = new Obtient();
        obtient.setActionByNumaction(actionDAO.findOne(idAction));
        obtient.setNumaction(idAction);
        obtient.setNumapprenant(idApprenant);
        obtient.setApprenantByNumapprenant(apprenantDAO.findOne(idApprenant));
        obtient.setValeur(note);
        if (obtientDAO.save(obtient) != null) {
            pModel.addAttribute("apprenant", idApprenant);
            return displayModifyForm(pModel, idApprenant, idAction, idJeu);
        } else {
            pModel.addAttribute("errMessage", "Impossible d'ajouter la note pour cet apprenant.");
            return displayAddForm(pModel, idApprenant, idAction, idJeu);
        }

    }

    @RequestMapping(value = "/{idApprenant}/action/{idAction}/jeu/{idJeu}/modifier", method = RequestMethod.POST)
    public String modifyObtention(final ModelMap pModel,
                                  @PathVariable(value = "idApprenant") Integer idApprenant,
                                  @PathVariable(value = "idAction") Integer idAction,
                                  @PathVariable(value = "idJeu") Integer idJeu,
                                  @RequestParam("note") Integer note) {
        Obtient obtient = obtientDAO.findByNumapprenantAndNumaction(idApprenant, idAction);
        obtient.setActionByNumaction(actionDAO.findOne(idAction));
        obtient.setNumaction(idAction);
        obtient.setNumapprenant(idApprenant);
        obtient.setApprenantByNumapprenant(apprenantDAO.findOne(idApprenant));
        obtient.setValeur(note);
        if (obtientDAO.save(obtient) != null) {
            pModel.addAttribute("apprenant", idApprenant);
            return displayModifyForm(pModel, idApprenant, idAction, idJeu);
        } else {
            pModel.addAttribute("errMessage", "Impossible de modifier la note pour cet apprenant.");
            return displayModifyForm(pModel, idApprenant, idAction, idJeu);
        }
    }
}
