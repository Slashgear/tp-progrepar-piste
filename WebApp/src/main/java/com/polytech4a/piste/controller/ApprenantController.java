package com.polytech4a.piste.controller;

import com.polytech4a.piste.beans.Apprenant;
import com.polytech4a.piste.dao.ApprenantDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Adrien CHAUSSENDE on 08/06/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p>
 *          Controller of Apprenant related views.
 */
@Controller
@RequestMapping("/apprenant")
@Component
public class ApprenantController {
    private static final String DIR_VIEW = "apprenant";


    private static final String LIST_VIEW = "listeApprenant";
    private static final String FORM_VIEW = "formapprenant";

    @Autowired
    private ApprenantDAO apprenantDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String displayList(final ModelMap pModel) {
        List<Apprenant> listApprenants = apprenantDAO.findAll();
        if (!listApprenants.isEmpty()) {
            pModel.addAttribute("listeApprenants", listApprenants);
        } else {
            //TODO Call AGO Error Handling
        }
        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }

    @RequestMapping(value = "/ajout", method = RequestMethod.GET)
    public String displayAddForm(final ModelMap pModel) {
        pModel.addAttribute("legend", "Ajout d'un apprenant");
        pModel.addAttribute("confirmButtonLabel", "Ajouter");
        return String.format("%s/%s", DIR_VIEW, FORM_VIEW);
    }

    @RequestMapping(value = "/modifier/{id}", method = RequestMethod.GET)
    public String displayModifyForm(final ModelMap pModel, @PathVariable(value = "id") int id) {
        Apprenant apprenant = apprenantDAO.findOne(id);
        pModel.addAttribute("idApprenant", apprenant.getNumapprenant());
        pModel.addAttribute("nomApprenant", apprenant.getNomapprenant());
        pModel.addAttribute("prenomApprenant", apprenant.getPrenomapprenant());
        pModel.addAttribute("legend", "Modification d'un apprenant");
        pModel.addAttribute("confirmButtonLabel", "Modifier");
        return String.format("%s/%s", DIR_VIEW, FORM_VIEW);
    }

    @RequestMapping(value = "/ajout", method = RequestMethod.POST)
    public String submitAddForm(@RequestParam(value = "idApprenant") int id,
                                @RequestParam("nomApprenant") String nom,
                                @RequestParam("prenomApprenant") String prenom) {
        Apprenant apprenant = apprenantDAO.findOne(id);
        apprenant.setNomapprenant(nom);
        apprenant.setPrenomapprenant(prenom);
        apprenantDAO.save(apprenant);
        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }

    @RequestMapping(value = "/modifier/{id}", method = RequestMethod.POST)
    public String submitModifyForm(@PathVariable(value = "id") int id,
                                   @RequestParam("nomApprenant") String nom,
                                   @RequestParam("prenomApprenant") String prenom) {
        Apprenant apprenant = apprenantDAO.findOne(id);
        apprenant.setNomapprenant(nom);
        apprenant.setPrenomapprenant(prenom);
        apprenantDAO.save(apprenant);
        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }

    @RequestMapping(value = "/suppr/{id}", method = RequestMethod.GET)
    public String supprApprenant(final ModelMap pModel, @PathVariable(value = "id") int id) {
        Apprenant apprenant = apprenantDAO.findOne(id);
        if (apprenant != null) {
            //TODO Check suppression clef étrangère
            apprenantDAO.delete(id);
            pModel.addAttribute("isDeleted", "Apprenant n°" + id + " a été supprimé avec succès");
        } else {
            //TODO Call AGO Error Handling
        }
        return displayList(pModel);
    }
}
