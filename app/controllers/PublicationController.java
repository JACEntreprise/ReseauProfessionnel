package controllers;

import com.avaje.ebean.Ebean;
import controllers.action.Secured;
import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import javax.inject.Inject;
import java.io.File;
import java.util.Date;
import java.util.List;

import views.html.formulaire.formulairePublication;
import views.html.publication.*;


/**
 * Created by brick on 16/06/2016.
 */
@Security.Authenticated(Secured.class)
public class PublicationController extends Controller {
    @Inject
    FormFactory formFactory;
    public Result nouvelle() {
        /*
            Récupérartion des données du formulaire dans publication
         */
        final Form<Publication> formParticulier =formFactory.form(Publication.class).bindFromRequest();
        Membre m=Membre.byEmail(session("membre"));
        Publication.creerNewPublication(null,formParticulier.get().getContenu(),null,null,m);

        return redirect(controllers.routes.PublicationController.listePub());
    }

    public Result partagePhoto() {
        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        final Form<Publication> formParticulier =formFactory.form(Publication.class).bindFromRequest();

        /**
         * On recupere le fichier par son nom
         */
        Http.MultipartFormData.FilePart<File> picture = body.getFile("urlImage");

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
                File rep = new File(path+"/public/images/publication/");
                /**
                 * On crée notre propre nom de fichier qui sera concatané avec la datel'heure actuel l'email du membre et le nom original du fichier
                 */
                Date d=new Date();
                String nom="publication"+session("membre")+d.getTime()+fileName;
                Publication.creerNewPublication(null,formParticulier.get(). getContenu(),path+"/public/images/publication/"+nom,nom,Membre.byEmail(session("membre")));
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

    public Result publier() {
        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        final Form<Publication> formParticulier =formFactory.form(Publication.class).bindFromRequest();

        /**
         * On recupere le fichier par son nom
         */
        Http.MultipartFormData.FilePart<File> picture = body.getFile("urlImage");

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
                File rep = new File(path+"/public/images/publication/");
                /**
                 * On crée notre propre nom de fichier qui sera concatané avec la datel'heure actuel l'email du membre et le nom original du fichier
                 */
                Date d=new Date();
                String nom="publication"+session("membre")+d.getTime()+fileName;
                Publication.creerNewPublication(formParticulier.get().getTitre(),formParticulier.get().getContenu(),path+"/public/images/publication/"+nom,nom,Membre.byEmail(session("membre")));
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

    public Result commentaire(Long id){
         /*
            Récupérartion des données du formulaire dans commentaire
         */
        final Form<Commentaire> formCommentaire =formFactory.form(Commentaire.class).bindFromRequest();
        Commentaire commentaire= new Commentaire();
        commentaire.membre=Membre.byEmail(session("membre"));
        commentaire.publication=Publication.find.byId(id);
        commentaire.contenu=formCommentaire.get().contenu;
        commentaire.save();
        return redirect(controllers.routes.ApplicationController.accueil());
    }

    /**
     * Action pour ajouter un nouvel commentaire
     * et le rediriger vers la page d'afficahage des publications
     * @param id
     * @return
     */
    public Result ajouterCommentaire(Long id){
         /*
            Récupérartion des données du formulaire dans commentaire
         */
        final Form<Commentaire> formCommentaire =formFactory.form(Commentaire.class).bindFromRequest();
        Commentaire.creerNewCommentaire(formCommentaire.get().getContenu(),Publication.getPublication(id),Membre.byEmail(session("membre")));
        return redirect(controllers.routes.PublicationController.unePublication(id));
    }

    /**
     * Action pour ajouter un nouvel commentaire
     * et le rediriger vers la page d'afficahage d'une publication
     * @param id
     * @return
     */
    public Result ajouterCommentaireLaPub(Long id){
         /*
            Récupérartion des données du formulaire dans commentaire
         */
        final Form<Commentaire> formCommentaire =formFactory.form(Commentaire.class).bindFromRequest();
        Commentaire.creerNewCommentaire(formCommentaire.get().getContenu(),Publication.getPublication(id),Membre.byEmail(session("membre")));
        return redirect(controllers.routes.PublicationController.afficherLaPublication(id));
    }

    /**
     * Action pour recuperer les publications non lues
     * @return
     */
    public Result publicationNonLue(){
        Membre membre=Membre.byEmail(session("membre"));

        return redirect(controllers.routes.ApplicationController.accueil());
    }

    /**
     * Envoyer le formulaire de publication
     * @return
     */
    public Result formulairePublication(){
        Membre membre=Membre.byEmail(session("membre"));
        return ok(formulairePublication.render(membre));
    }

    /**
     * Action pour recuperer une publication
     * @param id
     * @return
     */
    public Result unePublication(Long id){
        Membre membre=Membre.byEmail(session("membre"));
        return ok(unePublication.render(membre,Publication.getPublication(id)));
    }

    /**
     * Action pour recuperer une publication rechargement automatique
     * @param id
     * @return
     */
    public Result laPub(Long id){
        Membre membre=Membre.byEmail(session("membre"));
        return ok(laPub.render(Publication.getPublication(id)));
    }

    /**
     * Action pour lister toutes les publications
     * @return
     */
    public Result listePub(){
        Membre membre=Membre.byEmail(session("membre"));
        return ok(publication.render(membre));
    }

    /**
     * afficher une publication
     * @param id
     * @return
     */
    public Result afficherUnePublication(Long id){
        Membre membre=Membre.byEmail(session("membre"));
        Publication pub=Publication.getPublication(id);
        if(pub==null){
            return redirect(controllers.routes.ApplicationController.accueil());
        }
        pub.lueToutesLesCommentairesNonLueDeLaPub(membre);
        return ok(afficherUnePublication.render(membre,pub));
    }

    /**
     * Afficher une publication recharge automatique
     * @param id
     * @return
     */
    public Result afficherLaPublication(Long id){
        Membre membre=Membre.byEmail(session("membre"));
        Publication pub=Publication.getPublication(id);
        pub.lueToutesLesCommentairesNonLueDeLaPub(membre);
        return ok(afficherLaPublication.render(membre,pub));
    }

    /**
     * Acton pour charger automatiquement la liste des commentaires d'une publication non lus
     * @param id
     * @return
     */
    public Result listeCommentaireNonLueDeLaPub(Long id){
        Membre membre=Membre.byEmail(session("membre"));
        Publication pub=Publication.getPublication(id);
        return ok(listeCommentaireNonLueDeLaPub.render(pub.listeCommentairesNonDeLaPub(membre)));
    }

    /**
     *
     * @param id
     * @return
     */
    public Result notificationsCommentaires(Long id){
        Membre membre=Membre.byEmail(session("membre"));
        Publication pub=Publication.getPublication(id);

        return redirect(controllers.routes.PublicationController.afficherLaPublication(id));
    }
}
