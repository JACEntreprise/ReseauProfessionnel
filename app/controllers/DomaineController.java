package controllers;

import com.google.inject.Inject;
import models.Domaine;
import models.Membre;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.administrateur.domaine.*;
import java.util.List;

/**
 * Created by julio on 28/06/2016.
 */
public class DomaineController extends Controller {
    @Inject
    FormFactory formFactory;
    /**
     * action qui permet de lister l'ensemble des articles
     * @return
     */
    public Result lister(){
        //on recupère le membre connecté
        Membre admin = AdministrateurController.adminConnecte();

        //la liste de tous les domaines
        List<Domaine> listDomaines;
        listDomaines = Domaine.listeDomaine();
        //le formulaire d'ajout de domaine
        Form<Domaine> formDomaine = formFactory.form(Domaine.class);
        return ok(list_domaine.render(listDomaines,formDomaine,admin));
    }

    /**
     * ajouter un nouvel article
     */
    public Result ajouter(){
        //recupération des données du formulaire
        final Form<Domaine> domaineForm = formFactory.form(Domaine.class).bindFromRequest();

        //on crée le domaine
        Domaine domaine = new Domaine();
        domaine.setLibelle(domaineForm.get().getLibelle());

        //enregistrement dans la base de donnée
        domaine.ajouter();

        //on redirige vers la liste des domaines
        return redirect(routes.DomaineController.lister());

    }

    /**
     * action qui permet de supprimer un article
     * @return
     */
    public Result supprimer(){
        return ok("test");
    }

    /**
     * action qui permet de modifier un article
     * @return
     */
    public Result modifier(){
        return ok("test");
    }

}
