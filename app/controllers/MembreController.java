package controllers;

import controllers.action.Secured;
import models.Membre;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.cvView.formation.formationView;
import views.html.membrePakage.elementEntete;

/**
 * Created by brick on 22/06/2016.
 */
@Security.Authenticated(Secured.class)
public class MembreController extends Controller {

    public Result accepterDemande(Long id){
        Membre m= Membre.byEmail(session("membre"));
        m.accepterDemande(id);
        return ok();
    }

    public Result envoyerDemande(Long id){
        Membre m= Membre.byEmail(session("membre"));
        m.envoyerDemande(id);
        return redirect(controllers.routes.ProfilController.suggessions());
    }

    public Result refuserDemande(Long id){
        Membre m= Membre.byEmail(session("membre"));
        m.refuserDemande(id);
        return ok();
    }

    public Result rechargeTout(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(elementEntete.render(m));
    }

}

