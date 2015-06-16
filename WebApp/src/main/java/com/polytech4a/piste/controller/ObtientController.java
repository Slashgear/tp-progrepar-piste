package com.polytech4a.piste.controller;

import com.polytech4a.piste.beans.Action;
import com.polytech4a.piste.beans.Apprenant;
import com.polytech4a.piste.beans.Obtient;
import com.polytech4a.piste.dao.ActionDAO;
import com.polytech4a.piste.dao.ApprenantDAO;
import com.polytech4a.piste.dao.ObtientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "{idApprenant}/action/{idAction}/ajout", method = RequestMethod.GET)
    public String displayAddForm(final ModelMap pModel, @PathVariable(value = "idApprenant") Integer idApprenant, @PathVariable(value = "idAction") Integer idAction) {
        pModel.addAttribute("confirmButtonLabel", "Ajouter");
        Action action = actionDAO.findOne(idAction);
        Apprenant apprenant = apprenantDAO.findOne(idApprenant);
        if(obtientDAO.findByNumapprenantAndNumaction(idApprenant, idAction) == null) {
            pModel.addAttribute("apprenant", apprenant);
            pModel.addAttribute("action", action);
            pModel.addAttribute("formaction", "ajout");
            return String.format("%s/%s", DIR_VIEW, FORM_VIEW);
        } else {
            //TODO : Display message on form or other view. (and so change the returned link)
            return String.format("%s/%s", DIR_VIEW, FORM_VIEW);
        }


    }

    @RequestMapping(value = "{idApprenant}/action/{idAction}/modifier", method = RequestMethod.GET)
    public String displayModifyForm(final ModelMap pModel, @PathVariable(value = "idApprenant") Integer idApprenant, @PathVariable(value = "idAction") Integer idAction) {
        pModel.addAttribute("confirmButtonLabel", "Modifier");
        pModel.addAttribute("formaction", "modifier");
        Action action = actionDAO.findOne(idAction);
        Apprenant apprenant = apprenantDAO.findOne(idApprenant);
        pModel.addAttribute("apprenant", apprenant);
        pModel.addAttribute("action", action);
        Obtient note = obtientDAO.findByNumapprenantAndNumaction(idApprenant, idAction);
        pModel.addAttribute("note", note.getValeur());
        return String.format("%s/%s", DIR_VIEW, FORM_VIEW);
    }

    @RequestMapping(value = "/{idApprenant}/action/{idAction}/ajout", method = RequestMethod.POST)
    public String addObtention(final ModelMap pModel,
                               @PathVariable(value = "idApprenant") Integer idApprenant,
                               @PathVariable(value = "idAction") Integer idAction,
                               @RequestParam("note") Integer note) {
        Obtient obtient = new Obtient();
        obtient.setActionByNumaction(actionDAO.findOne(idAction));
        obtient.setNumaction(idAction);
        obtient.setNumapprenant(idApprenant);
        obtient.setApprenantByNumapprenant(apprenantDAO.findOne(idApprenant));
        obtient.setValeur(note);
        if(obtientDAO.save(obtient) != null) {
            pModel.addAttribute("apprenant", idApprenant);
            return displayModifyForm(pModel, idApprenant, idAction);
        } else {
            //TODO : handle when saving doesn't work
            return displayModifyForm(pModel, idApprenant, idAction);
        }

    }

    @RequestMapping(value = "/{idApprenant}/action/{idAction}/modifier", method = RequestMethod.POST)
    public String modifyObtention(final ModelMap pModel,
                                  @PathVariable(value = "idApprenant") Integer idApprenant,
                                  @PathVariable(value = "idAction") Integer idAction,
                                  @RequestParam("note") Integer note) {
        Obtient obtient = obtientDAO.findByNumapprenantAndNumaction(idApprenant, idAction);
        obtient.setActionByNumaction(actionDAO.findOne(idAction));
        obtient.setNumaction(idAction);
        obtient.setNumapprenant(idApprenant);
        obtient.setApprenantByNumapprenant(apprenantDAO.findOne(idApprenant));
        obtient.setValeur(note);
        if(obtientDAO.save(obtient) != null) {
            pModel.addAttribute("apprenant", idApprenant);
            return displayModifyForm(pModel, idApprenant, idAction);
        } else {
            //TODO : handle when saving doesn't work
            return displayModifyForm(pModel, idApprenant, idAction);
        }
    }
}
