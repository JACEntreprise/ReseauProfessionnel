package controllers;

import controllers.action.Secured;
import models.Membre;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.cvView.formation.formationView;
import views.html.element_icon.content_icon_user;
import views.html.element_icon.icon_comment;
import views.html.element_icon.icon_user;
import views.html.membrePakage.elementEntete;
import views.html.element_icon.content_icon_comment;

/**
 * Created by brick on 22/06/2016.
 */
@Security.Authenticated(Secured.class)
public class MembreController extends Controller {
    /**
     * Action pour accepter une demande
     * @param id
     * @return
     */
    public Result accepterDemande(Long id){
        Membre m= Membre.byEmail(session("membre"));
        m.accepterDemande(id);
        return ok();
    }

    /**
     * Action pour envoyer une demande
     * @param id
     * @return
     */
    public Result envoyerDemande(Long id){
        Membre m= Membre.byEmail(session("membre"));
        m.envoyerDemande(id);
        return redirect(controllers.routes.ProfilController.suggessions());
    }

    /**
     * Action pour refuser une demande
     * @param id
     * @return
     */
    public Result refuserDemande(Long id){
        Membre m= Membre.byEmail(session("membre"));
        m.refuserDemande(id);
        return ok();
    }

    /**
     * charger automatiquement les notifications des commentaires
     * @return
     */
    public Result rechargeIconComment(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(icon_comment.render(m));
    }

    /**
     * charger automatiquement les notifications des membres
     * @return
     */
    public Result rechargeIconUser(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(icon_user.render(m));
    }

    /**
     * notification des commentaires
     * @return
     */
    public Result contentIconComment(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(content_icon_comment.render(m));
    }

    /**
     * notification des membres
     * @return
     */
    public Result contentIconUser(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(content_icon_user.render(m));
    }

}

