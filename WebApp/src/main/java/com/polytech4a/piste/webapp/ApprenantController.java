package com.polytech4a.piste.webapp;

import com.polytech4a.piste.beans.Apprenant;
import com.polytech4a.piste.services.IServiceApprenant;
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
    private IServiceApprenant serviceApprenant;

    @RequestMapping(value = "/ajout", method = RequestMethod.GET)
    public String displayAddForm(final ModelMap pModel) {
        pModel.addAttribute("legend", "Ajout d'un apprenant");
        pModel.addAttribute("confirmButtonLabel", "Ajouter");
        return "formapprenant";
    }

    @RequestMapping(value = "/modifier/{id}", method = RequestMethod.GET)
    public String displayAddForm(final ModelMap pModel, @PathVariable(value="id") int id) {
        Apprenant apprenant = serviceApprenant.findById(id);
        pModel.addAttribute("idApprenant", apprenant.getNumapprenant());
        pModel.addAttribute("nomApprenant", apprenant.getNomapprenant());
        pModel.addAttribute("prenomApprenant", apprenant.getPrenomapprenant());
        pModel.addAttribute("legend", "Modification d'un apprenant");
        pModel.addAttribute("confirmButtonLabel", "Modifier");
        return "formapprenant";
    }


    @RequestMapping(value = "/suppr", method = RequestMethod.GET)
    public String displayDeleteForm(final ModelMap pModel) {
        List<Apprenant> listApprenants = serviceApprenant.findAll();
        pModel.addAttribute("listeApprenants", listApprenants);
        return "suppressionapprenant";
    }
}
