package controllers;

import models.Membre;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.cvView.formation.ajouterFormulaireFormation;
import views.html.cvView.formation.formationView;
import views.html.cvView.experience.ajouterFormulaireExperience;
import views.html.cvView.experience.experienceView;


/**
 * Created by julio on 18/06/2016.
 */
public class ProfilController extends Controller {

    public Result formation(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(formationView.render(m));
    }

    public Result ajouterFormulaireFormation(){
        return ok(ajouterFormulaireFormation.render());
    }

    public Result experience(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(experienceView.render(m));
    }

    public Result ajouterFormulaireExperience(){
        return ok(ajouterFormulaireExperience.render());
    }

}
