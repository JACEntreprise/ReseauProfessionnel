package controllers;

import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import repository.MembreRepository;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;

import static play.data.Form.form;


public class AuthController extends Controller {

    private final MembreRepository repository;
    @Inject
    FormFactory formFactory;

    public AuthController() {
        repository = MembreRepository.instance;
    }

    public Result deconnexion() {
        session().clear();
        return redirect(routes.HomeController.index());
    }

    /**
     * Authentification de l'utilisateur pour la connexion
     * @return
     */
    public Result authenticate() {
        /**
         * récupération des données du formulaire
         */
        final Form<FormulaireConnexion> loginForm =formFactory.form(FormulaireConnexion.class).bindFromRequest();
        /**
         * On vérifie si les données de connexion sont valides
         * Sinon en envoie un message d'érreur
         */
        if(loginForm.hasErrors()){
            return badRequest(views.html.index.render(
                    loginForm,
                    formFactory.form(FormulaireInscriptionParticulier.class),
                    formFactory.form(FormulaireInscriptionEntreprise.class)
            ));
        }
        /**
         * si les données sont valides, on crée la session
         * ensuite on le redirige vers sa page personnelle
         */
        session("membre", loginForm.get().getEmail());
        return redirect(controllers.routes.ApplicationController.accueil());
    }


    /**
     * Inscription d'un particulier
     * @return
     */
    public Result inscriptionParticulier() {
        /*
            Récupérartion des données du formulaire dans registerFormParticulier
         */
        final Form<FormulaireInscriptionParticulier> registerFormParticulier =formFactory.form(FormulaireInscriptionParticulier.class).bindFromRequest();
        /**
         * Tests si les données du formulaire sont correctes, sinon on retourne un message d'erreur
         */
        if(registerFormParticulier.hasErrors()){
            return badRequest(views.html.index.render(
                    formFactory.form(FormulaireConnexion.class),
                    registerFormParticulier,
                    formFactory.form(FormulaireInscriptionEntreprise.class)
            ));
        }
        /**
         * Si toutes les données du formulaire sont jugés correctes,
         * On crée un membre avec ces données (mot de passe et email)
         */
        Membre membre=new Membre();
        membre.setMotDePasse(repository.hash(registerFormParticulier.get().getMotDePasse()));
        membre.setEmail(registerFormParticulier.get().getEmail());
        membre.setSalt(repository.getSalt());
        Profil profil=new Profil();
        profil.save();
        membre.setProfil(profil);
        /**
         * On enregistre ce membre dans la base
         */
        membre.save();
        /**
         * On crée un particulier avec ce membre et les données du formulaire(prenom,nom)
         */
        Particulier particulier =new Particulier();
        particulier.prenom=registerFormParticulier.get().getPrenom();
        particulier.nom=registerFormParticulier.get().getNom();
        /**
         * On associe le membre au particulier crée précédemment
         */
        particulier.membre=membre;
        /**
         * On enrégistre le particulier dans la base de données
         */
        particulier.save();
        /**
         * on crée la session pour ce nouvel utilisateur
         * ensuite on le redirige vers sa page personnelle
         */
        session("membre", registerFormParticulier.get().getEmail());
        return redirect(controllers.routes.ApplicationController.accueil());
    }

    /**
     * Inscription pour une entreprise
     * @return
     */
    public Result inscriptionEntreprise() {
        /*
            Récupérartion des données du formulaire dans registerFormEntreprise
         */
        final Form<FormulaireInscriptionEntreprise> registerFormEntreprise =formFactory.form(FormulaireInscriptionEntreprise.class).bindFromRequest();
        /**
         * Tests si les données du formulaire sont correctes, sinon on retourne un message d'erreur
         */
        if(registerFormEntreprise.hasErrors()){
            return badRequest(views.html.index.render(
                    formFactory.form(FormulaireConnexion.class),
                    formFactory.form(FormulaireInscriptionParticulier.class),
                    registerFormEntreprise
            ));
        }
        /**
         * Si toutes les données du formulaire sont jugées correctes,
         * On crée un membre avec ces données (mot de passe et email)
         */
        Membre membre=new Membre();
        membre.setMotDePasse(repository.hash(registerFormEntreprise.get().getMotDePasse()));
        membre.setEmail(registerFormEntreprise.get().getEmail());
        membre.setSalt(repository.getSalt());
        Profil profil=new Profil();
        profil.save();
        membre.setProfil(profil);
        /**
         * On enregistre ce membre dans la base
         */
        membre.save();
        /**
         * On crée un particulier avec ce membre et les données du formulaire(prenom,nom)
         */
        Entreprise entreprise =new Entreprise();
        entreprise.raisonSocial=registerFormEntreprise.get().getRaisonSocial();
        /**
         * On associe le membre à l'entreprise créee précédemment
         */
        entreprise.membre=membre;
        /**
         * On enrégistre le particulier dans la base de données
         */
        entreprise.save();
        /**
         * on crée la session pour ce nouvel utilisateur
         * ensuite on le redirige vers sa page personnelle
         */
        session("membre", registerFormEntreprise.get().getEmail());
        return redirect(controllers.routes.ApplicationController.accueil());
    }

}
