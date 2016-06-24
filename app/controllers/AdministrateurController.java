package controllers;

import controllers.action.SecuredAdmin;
import models.Administrateur;
import models.Membre;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.administrateur.*;

import javax.inject.Inject;
import java.util.List;


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
        if(!adminExist()){//il n'existe pas d'administrateur, on ajoute l'admin par défaut
            Administrateur admin = new Administrateur();
            admin.prenom = "Admin";
            admin.nom = "Admin";
            Membre membre = new Membre();
            membre.email = "admin@gmail.com";
            membre.motDePasse = "admin111";
            membre.administrateur = admin;

            membre.ajouter();
        }
    }

    /**
     * vérifie s'il existe un administrateur dans la base de données
     * @return
     */
    public static boolean adminExist(){

        List<Administrateur> listAdmins = Administrateur.find.all();
        if(listAdmins==null){
            return false;
        }
        return true;
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
}
