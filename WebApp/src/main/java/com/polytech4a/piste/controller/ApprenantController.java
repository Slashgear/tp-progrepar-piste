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

import java.util.List;

/**
 * Created by Adrien CHAUSSENDE on 08/06/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 */
@Controller
@RequestMapping("/apprenant")
@Component
public class ApprenantController {

    @Autowired
    private ApprenantDAO apprenantDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String displayList(final ModelMap pModel) {
        List<Apprenant> listApprenants = apprenantDAO.findAll();
        pModel.addAttribute("listeApprenants", listApprenants);
        return "listeapprenant";
    }

    @RequestMapping(value = "/ajout", method = RequestMethod.GET)
    public String displayAddForm(final ModelMap pModel) {
        pModel.addAttribute("legend", "Ajout d'un apprenant");
        pModel.addAttribute("confirmButtonLabel", "Ajouter");
        return "formapprenant";
    }

    @RequestMapping(value = "/modifier/{id}", method = RequestMethod.GET)
    public String displayAddForm(final ModelMap pModel, @PathVariable(value = "id") int id) {
        Apprenant apprenant = apprenantDAO.findOne(id);
        pModel.addAttribute("idApprenant", apprenant.getNumapprenant());
        pModel.addAttribute("nomApprenant", apprenant.getNomapprenant());
        pModel.addAttribute("prenomApprenant", apprenant.getPrenomapprenant());
        pModel.addAttribute("legend", "Modification d'un apprenant");
        pModel.addAttribute("confirmButtonLabel", "Modifier");
        return "formapprenant";
    }
}
