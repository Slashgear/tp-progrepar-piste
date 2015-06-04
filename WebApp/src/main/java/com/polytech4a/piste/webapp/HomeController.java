package com.polytech4a.piste.webapp;

import com.polytech4a.piste.persistence.dao.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Antoine CARON on 04/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Controller
@RequestMapping("/bonjour")
@Component
public class HomeController {

    @Autowired
    private IServiceListAction serviceListAction;

    @RequestMapping(method = RequestMethod.GET)
    public String afficherBonjour(final ModelMap pModel) {
        pModel.addAttribute("personne", "Regis");
        List<Action> indicateurList = serviceListAction.rechercherAction();
        pModel.addAttribute("ListeIndicateur", indicateurList);
        return "bonjour";
    }
}
