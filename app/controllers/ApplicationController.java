package controllers;

import controllers.action.Secured;
import controllers.membre.*;
import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import repository.MembreRepository;
import views.html.*;
import views.html.cvView.formation.ajouterFormulaireFormulaire;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


/**
 * Created by brick on 16/06/2016.
 */
@Security.Authenticated(Secured.class)
public class ApplicationController extends Controller {
    private final MembreRepository repository;
    @Inject
    FormFactory formFactory;

    /**
     * Contructeur pour initialiser le repository
     */
    public ApplicationController() {
        repository = MembreRepository.instance;
    }

    /**
     * Page d'accueil de l'application
     * la page que vous etes redirigée si vous etes connectées
     * @return
     */
    public Result accueil() {
        /**
         * On recupere le membre qui s'est connecté
         */
        Membre membre= Membre.byEmail(session("membre"));

        /**
         * On retourne sur la page
         * le membre
         * les formulaires de publication et de commentaire
         * et les publications concernant ce membre
         */
        return ok(accueil.render(
                membre,
                formFactory.form(Publication.class),
                formFactory.form(Commentaire.class),
                Publication.publicationsLues(membre)
        ));
    }

    /**
     * l'action qui sera executée quand il y'a une nouvelle publication
     * @return
     */
    public Result rechargePub() {
        /**
         * On recupere le membre qui s'est connecté
         */
        Membre membre= Membre.byEmail(session("membre"));

        /**
         * On recupere la liste de toutes les publications qui ne sont pas encore lues par ce membre
         */
        List<Publication> publications=Publication.publicationsNonLues(membre);

        /**
         * On envoie sur la page
         * le mebre
         * le formulaire de commentaire
         * et les publications non lues
         */
        return ok(rechargePub.render(
                membre,
                formFactory.form(Commentaire.class),
                publications
        ));
    }

    /**
     * Ation pour changer la photo de profil
     * @return
     */
    public Result upload() {
        Http.MultipartFormData<File> body = request().body().asMultipartFormData();

        /**
         * On recupere le fichier par son nom
         */
        Http.MultipartFormData.FilePart<File> picture = body.getFile("picture");

        /**
         * On verifie si e fichier recupéré est non vide
         */
        if (picture != null) {
            /**
             * On recupere le nom de ce fichier
             */
            String fileName = picture.getFilename();
            /**
             * son type de contenu
             */
            String contentType =picture.getContentType();
            /**
             * On verifie si le ichier est une image
             */
            if(contentType.equals("image/png") || contentType.equals("image/jpg") || contentType.equals("image/jpeg") || contentType.equals("image/gif")){
                /**
                 * On recupere maintenant le fichier
                 */
                File file = picture.getFile();
                /**
                 * On recupere le fichier temporaire
                 * c'est lui qu'on enregistra dans notre projet
                 */
                String path=new File("").getAbsolutePath();
                /**
                 * On crée le repertoire ou on va envoyé le fichier
                 */
                File rep = new File(path+"/public/images/profil/");
                /**
                 * On crée notre propre nom de fichier qui sera concatané avec la datel'heure actuel l'email du membre et le nom original du fichier
                 */
                Date d=new Date();
                String nom=session("membre")+d.getTime()+fileName;
                /**
                 * On crée l'image
                 */
                Image image=new Image();
                Membre membre=Membre.byEmail(session("membre"));
                image.membre=membre;
                image.setProfil(membre.getProfil());
                image.chemin=path+"/public/images/profil/"+nom;
                image.nom=nom;
                image.save();
                /**
                 * On envie l'image dans le repertoire
                 */
                boolean resultat = file.renameTo(new File(rep,nom));
                return redirect(controllers.routes.ApplicationController.accueil());
            } else {
                flash("error", "Une image svp");
                return badRequest();

            }
        } else {
            flash("error", "Une image svp");
            return badRequest();
        }
    }

    /**
     * Action pour completer son profil
     * @return
     */
    public Result completeProfil(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(completeProfil.render(m));
    }

    /**
     * valider les donnees saisies par le membre
     * et le redirigé vers la page pour changer sa photo de profil
     * @return
     */
    public Result validerDonneeProfil() throws ParseException {
        Membre m= Membre.byEmail(session("membre"));
        if(m.getEntreprise()==null){
            final Form<FormulaireParticulier> form =formFactory.form(FormulaireParticulier.class).bindFromRequest();
            String dateDeNaissance=form.get().getJour()+"/"+form.get().getMoi()+"/"+form.get().getAnnee();
            Particulier.completeProfil(m,form.get().getAdresse(),form.get().getTelephone(),form.get().getSiteweb(),form.get().getLieuDeNaissance(),dateDeNaissance);
        }else{
            final Form<FormulaireEntreprise> form =formFactory.form(FormulaireEntreprise.class).bindFromRequest();
            String dateCreation=form.get().getJour()+"/"+form.get().getMoi()+"/"+form.get().getAnnee();
            Entreprise.completeProfil(m,form.get().getAdresse(),form.get().getTelephone(),form.get().getSiteweb(),form.get().getDomaine(),dateCreation);
        }
        return redirect(controllers.routes.ApplicationController.accueil());
    }

}
