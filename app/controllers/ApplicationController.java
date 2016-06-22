package controllers;

import controllers.action.Secured;
import models.*;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import repository.MembreRepository;
import views.html.*;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
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

    public ApplicationController() {
        repository = MembreRepository.instance;
    }
    public Result accueil() {
        String email = session("membre");
        Membre membre= Membre.byEmail(email);
        return ok(accueil.render(
                membre,
                formFactory.form(Publication.class),
                formFactory.form(Commentaire.class),
                Publication.publicationsLues(membre)
        ));
    }
    public Result rechargePub() {
        Membre membre= Membre.byEmail(session("membre"));
        List<Publication> publications=Publication.publicationsNonLues(membre);
        return ok(rechargePub.render(
                membre,
                formFactory.form(Commentaire.class),
                publications
        ));
    }

    public Result upload() {
        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture = body.getFile("picture");
        if (picture != null) {
            String fileName = picture.getFilename();
            String contentType = ""+picture.getContentType();
            String[] tableau=contentType.split("/");
            String extension=tableau[tableau.length-1];
            if(extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg") || extension.equals("gif")){
                File file = picture.getFile();
                String path=new File("").getAbsolutePath();
                File rep = new File(path+"/public/images/profil/");
                Date d=new Date();
                String nom=session("membre")+d.getTime()+fileName;
                Image image=new Image();
                Membre membre=Membre.byEmail(session("membre"));
                image.membre=membre;
                image.profil=membre.profil;
                image.chemin=path+"/public/images/profil/"+nom;
                image.nom=nom;
                image.save();
                boolean resultat = file.renameTo(new File(rep,nom));
                return redirect(controllers.routes.ApplicationController.accueil());
            } else {
                flash("error", "Une image svp");
                return ok("ok= "+extension);
            }
        } else {
            flash("error", "Missing file");
            return badRequest();
        }
    }

    public Result completeProfil(){

        Membre m= Membre.byEmail(session("membre"));
        return ok(completeProfil.render(m));
    }
}
