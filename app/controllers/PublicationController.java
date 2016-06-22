package controllers;

import controllers.action.Secured;
import models.Commentaire;
import models.Membre;
import models.Publication;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import javax.inject.Inject;
import java.io.File;


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
        Publication pub=new Publication();
        pub.contenu=formParticulier.get().contenu;
        pub.membre=Membre.byEmail(session("membre"));
        Publication.creerNewPublication(pub);
        return redirect(controllers.routes.ApplicationController.accueil());
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

    public Result publicationNonLue(){
        Membre membre=Membre.byEmail(session("membre"));

        return redirect(controllers.routes.ApplicationController.accueil());
    }

}
