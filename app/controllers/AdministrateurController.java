package controllers;

import controllers.action.SecuredAdmin;
import models.Administrateur;
import models.Membre;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import javax.inject.Inject;
import java.util.List;
import views.html.administrateur.*;

/**
 * Created by julio on 18/06/2016.
 */
public class AdministrateurController extends Controller {
    @Inject
    FormFactory formFactory;

    /**
     * action login
     * @return
     */
    public Result login(){
        //on ajoute un admin par défaut
        defaultAdmin();
        //on vérifie si l'admin s'est connecté
        if(!estConnecte()){
            return ok(login.render(formFactory.form(FormulaireConnexionAdmin.class)));
        }
        else{
            return redirect(routes.AdministrateurController.admin());
        }
    }
    /**
     * action authenticate
     * @return
     */
    public Result authenticate() {
        /**
         * récupération des données du formulaire
         */
        final Form<FormulaireConnexionAdmin> loginForm = formFactory.form(FormulaireConnexionAdmin.class).bindFromRequest();
        /**
         * On vérifie si les données de connexion sont valides
         * Sinon en envoie un message d'érreur
         */
        if(loginForm.hasErrors()){
            return badRequest(login.render(loginForm));
        }
        /**
         * si les données sont valides, on crée la session
         * ensuite on le redirige vers sa page personnelle
         */
        session().clear();
        session("administrateur", loginForm.get().getEmail());
        return redirect(routes.AdministrateurController.admin());
    }

    /**
     * action admin
     * @return
     */
    @Security.Authenticated(SecuredAdmin.class)
    public Result admin(){
        String email = session("administrateur");
        Membre membre= Membre.byEmail(email);
        return ok(admin.render(membre));
    }


    /**
     * fonction qui vérifie s'il existe un admin dans l'application
     * crée un admin par défaut s'il n'existe pas
     */
    public static void defaultAdmin(){
        List<Administrateur> listAdmins;
        listAdmins = Administrateur.listAdministrateurs();

        if(listAdmins.size() == 0){//il n'existe pas d'administrateur, on ajoute l'admin par défaut
            Administrateur admin = new Administrateur();
            admin.setPrenom("Admin");
            admin.setNom("Admin");
            Membre admin_membre = new Membre();
            admin_membre.setEmail("admin@gmail.com");
            admin_membre.setMotDePasse("admin111");
            admin.setMembre(admin_membre);

            admin.ajouter();
        }
    }

    /**
     * vérifier si l'admin est connecté
     * @return
     */
    public boolean estConnecte(){
        if(session("administrateur") == null){
            return false;
        }
        return true;
    }

    /**
     * recupérer l'admin connecté
     */
    public static Membre adminConnecte(){
        String email = session("administrateur");
        Membre membre= Membre.byEmail(email);
        return membre;
    }
}
