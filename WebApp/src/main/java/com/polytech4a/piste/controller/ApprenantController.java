package com.polytech4a.piste.controller;

import com.polytech4a.piste.beans.Apprenant;
import com.polytech4a.piste.beans.Inscription;
import com.polytech4a.piste.beans.Jeu;
import com.polytech4a.piste.controller.components.ErrorPage;
import com.polytech4a.piste.controller.components.breadcrumb.Breadcrumb;
import com.polytech4a.piste.controller.components.breadcrumb.BreadcrumbItem;
import com.polytech4a.piste.dao.ApprenantDAO;
import com.polytech4a.piste.dao.InscriptionDAO;
import com.polytech4a.piste.dao.JeuDAO;
import com.polytech4a.piste.dao.ObtientDAO;
import com.polytech4a.piste.service.JeuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Adrien CHAUSSENDE on 08/06/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Controller of Apprenant related views.
 */
@Controller
@RequestMapping("apprenant")
@Component
public class ApprenantController {
    private static final String DIR_VIEW = "apprenant";


    private static final String LIST_VIEW = "listeApprenant";
    private static final String FORM_VIEW = "formapprenant";
    private static final String DETAILS_VIEW = "detailsapprenant";

    private static final String SEARCH_URL = "apprenant/rechercher";
    private static final String SEARCH_LABEL = "Rechercher un apprenant";

    @Autowired
    private ApprenantDAO apprenantDAO;
    @Autowired
    private JeuDAO jeuDAO;
    @Autowired
    private InscriptionDAO inscriptionDAO;
    @Autowired
    private ObtientDAO obtientDAO;
    @Autowired
    private JeuService jeuService;

    @RequestMapping(method = RequestMethod.GET)
    public String displayList(final ModelMap pModel) {
        List<Apprenant> listApprenants = apprenantDAO.findAll();
        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Apprenants"));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        pModel.addAttribute("listeApprenants", listApprenants);
        pModel.addAttribute("actionbutton", "apprenant/ajout");

        pModel.addAttribute("searchURL", SEARCH_URL);
        pModel.addAttribute("searchLabel", SEARCH_LABEL);

        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }

    @RequestMapping(value = "ajout", method = RequestMethod.GET)
    public String displayAddForm(final ModelMap pModel) {
        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Apprenants", "apprenant"),
                new BreadcrumbItem("Ajout"));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        pModel.addAttribute("legend", "Ajout d'un apprenant");
        pModel.addAttribute("confirmButtonLabel", "Ajouter");
        pModel.addAttribute("actionbutton", "apprenant/ajout");

        pModel.addAttribute("searchURL", SEARCH_URL);
        pModel.addAttribute("searchLabel", SEARCH_LABEL);

        return String.format("%s/%s", DIR_VIEW, FORM_VIEW);
    }

    @RequestMapping(value = "modifier/{id}", method = RequestMethod.GET)
    public String displayModifyForm(final ModelMap pModel, @PathVariable(value = "id") int id) {

        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Apprenants", "apprenant"),
                new BreadcrumbItem("Modifier"));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        Apprenant apprenant = apprenantDAO.findOne(id);
        pModel.addAttribute("idApprenant", apprenant.getNumapprenant());
        pModel.addAttribute("nomApprenant", apprenant.getNomapprenant());
        pModel.addAttribute("prenomApprenant", apprenant.getPrenomapprenant());
        pModel.addAttribute("legend", "Modification d'un apprenant");
        pModel.addAttribute("confirmButtonLabel", "Modifier");
        pModel.addAttribute("actionbutton", "apprenant/modifier/" + id);

        pModel.addAttribute("searchURL", SEARCH_URL);
        pModel.addAttribute("searchLabel", SEARCH_LABEL);

        return String.format("%s/%s", DIR_VIEW, FORM_VIEW);
    }

    @RequestMapping(value = "ajout", method = RequestMethod.POST)
    public String submitAddForm(final ModelMap pModel,
                                @RequestParam("nomApprenant") String nom,
                                @RequestParam("prenomApprenant") String prenom) {
        prenom = prenom.length() > 25 ? prenom.substring(0, 24) : prenom;
        nom = nom.length() > 25 ? nom.substring(0, 24) : nom;
        Apprenant apprenant = new Apprenant();
        apprenant.setNomapprenant(StringUtils.capitalize(StringUtils.lowerCase(nom)));
        apprenant.setPrenomapprenant(StringUtils.capitalize(StringUtils.lowerCase(prenom)));

        pModel.addAttribute("searchURL", SEARCH_URL);
        pModel.addAttribute("searchLabel", SEARCH_LABEL);

        if (apprenantDAO.save(apprenant) != null) {
            pModel.addAttribute("success", String.format("L'apprenant %s %s a été créé avec succès.", prenom, nom));
        } else {
            pModel.addAttribute("error", "Echec lors de la création de l'apprenant.");
        }
        return displayList(pModel);
    }

    @RequestMapping(value = "modifier/{id}", method = RequestMethod.POST)
    public String submitModifyForm(final ModelMap pModel,
                                   @PathVariable(value = "id") int id,
                                   @RequestParam("nomApprenant") String nom,
                                   @RequestParam("prenomApprenant") String prenom) {
        prenom = prenom.length() > 25 ? prenom.substring(0, 24) : prenom;
        nom = nom.length() > 25 ? nom.substring(0, 24) : nom;
        Apprenant apprenant = apprenantDAO.findOne(id);
        apprenant.setNomapprenant(StringUtils.capitalize(StringUtils.lowerCase(nom)));
        apprenant.setPrenomapprenant(StringUtils.capitalize(StringUtils.lowerCase(prenom)));
        if (apprenantDAO.save(apprenant) != null) {
            pModel.addAttribute("success", String.format("L'apprenant %s %s a été modifié avec succès.", prenom, nom));
        } else {
            pModel.addAttribute("error", "Echec lors de la modification de l'apprenant.");
        }

        pModel.addAttribute("searchURL", SEARCH_URL);
        pModel.addAttribute("searchLabel", SEARCH_LABEL);

        return displayList(pModel);
    }

    @RequestMapping(value = "suppr/{id}", method = RequestMethod.GET)
    @Transactional
    public String supprApprenant(final ModelMap pModel, @PathVariable(value = "id") Integer id) {
        Apprenant apprenant = apprenantDAO.findOne(id);

        pModel.addAttribute("searchURL", SEARCH_URL);
        pModel.addAttribute("searchLabel", SEARCH_LABEL);

        if (apprenant != null) {
            obtientDAO.findByNumapprenant(id).forEach(obtientDAO::delete);
            inscriptionDAO.deleteByNumapprenant(id);
            apprenantDAO.delete(id);
            pModel.addAttribute("isDeleted", String.format("L'apprenant %s %s a été supprimé avec succès.", apprenant.getPrenomapprenant(), apprenant.getNomapprenant()));
            return displayList(pModel);
        } else {
            //return ErrorPage.newError(pModel, String.format("Apprenant n°%s non trouvée !", id), DIR_VIEW);
            return ErrorPage.new404Error();
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String detailApprenant(final ModelMap pModel,
                                  @PathVariable(value = "id") int id) {
        Apprenant apprenant = apprenantDAO.findOne(id);

        pModel.addAttribute("searchURL", SEARCH_URL);
        pModel.addAttribute("searchLabel", SEARCH_LABEL);

        if (apprenant == null)
            return ErrorPage.new404Error();
        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", "/"),
                new BreadcrumbItem("Apprenants", "/apprenant"),
                new BreadcrumbItem(String.format("#%s", id)));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        pModel.addAttribute("apprenant", apprenant);

        List<Jeu> jeux = jeuDAO.findAvailableJeuForApprenant(id);
        if (!jeux.isEmpty()) {
            pModel.addAttribute("jeux", jeux);
            pModel.addAttribute("showinsc", "show");
        }

        List<Jeu> jeuxinsc = jeuDAO.findSubscribedJeuForApprenant(id);
        if (!jeuxinsc.isEmpty()) {
            pModel.addAttribute("jeuxinsc", jeuxinsc);
            pModel.addAttribute("showjeuinsc", "show");
            pModel.addAttribute("aValide", jeuService.getValideApprenant(id));
        }
        return String.format("%s/%s", DIR_VIEW, DETAILS_VIEW);
    }

    @RequestMapping(value = "inscrire", method = RequestMethod.POST)
    public String submitinscrireForm(final ModelMap pModel,
                                     @RequestParam("idApprenant") Integer idApprenant,
                                     @RequestParam("idJeu") Integer idJeu) {

        pModel.addAttribute("searchURL", SEARCH_URL);
        pModel.addAttribute("searchLabel", SEARCH_LABEL);

        Inscription inscription = new Inscription();
        inscription.setJeuByNumjeu(jeuDAO.findOne(idJeu));
        inscription.setApprenantByNumapprenant(apprenantDAO.findOne(idApprenant));
        inscription.setNumjeu(idJeu);
        inscription.setNumapprenant(idApprenant);
        inscriptionDAO.save(inscription);
        return detailApprenant(pModel, idApprenant);
    }

    @RequestMapping(value = "rechercher", method = RequestMethod.POST)
    public String displaySearchResult(final ModelMap pModel, @ModelAttribute(value = "label") String label) {
        List<Apprenant> listApprenants = apprenantDAO.findByLabel(label);
        // Breadcrumb set up
        Breadcrumb breadcrumbList = new Breadcrumb(
                new BreadcrumbItem("Accueil", ""),
                new BreadcrumbItem("Apprenants", "apprenant"),
                new BreadcrumbItem(String.format("Recherche : %s", label)));
        Breadcrumb.addToModel(pModel, breadcrumbList);

        pModel.addAttribute("listeApprenants", listApprenants);
        pModel.addAttribute("actionbutton", "apprenant/ajout");

        pModel.addAttribute("label", label);
        pModel.addAttribute("searchURL", SEARCH_URL);
        pModel.addAttribute("searchLabel", SEARCH_LABEL);

        return String.format("%s/%s", DIR_VIEW, LIST_VIEW);
    }
}
