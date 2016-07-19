package controllers;

import controllers.cv.FormulaireExperience;
import controllers.cv.FormulaireFormation;
import controllers.membre.FormulaireParticulier;
import javassist.NotFoundException;
import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.cvView.formation.ajouterFormulaireFormation;
import views.html.cvView.formation.formationView;
import views.html.cvView.formation.listeFormation;
import views.html.cvView.formation.formulaireModifierFormation;
import views.html.cvView.experience.formulaireModifierExperience;
import views.html.cvView.experience.listeExperience;
import views.html.cvView.experience.ajouterFormulaireExperience;
import views.html.cvView.experience.experienceView;
import views.html.cvView.competence.formulaireModifierCompetence;
import views.html.cvView.competence.listeCompetence;
import views.html.cvView.competence.ajouterFormulaireCompetence;
import views.html.cvView.competence.competenceView;
import views.html.cvView.langue.formulaireModifierLangue;
import views.html.cvView.langue.listeLangue;
import views.html.cvView.langue.ajouterFormulaireLangue;
import views.html.cvView.langue.langueView;
import views.html.cvView.loisir.formulaireModifierLoisir;
import views.html.cvView.loisir.listeLoisir;
import views.html.cvView.loisir.ajouterFormulaireLoisir;
import views.html.cvView.loisir.loisirView;
import views.html.membrePakage.monProfil;
import views.html.membrePakage.suggession;

import javax.inject.Inject;
import java.util.Date;


/**
 * Created by julio on 18/06/2016.
 */
public class ProfilController extends Controller {
    @Inject
    FormFactory formFactory;
    public Result formation(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(formationView.render(m));
    }

    public Result ajouterFormulaireFormation(){
        return ok(ajouterFormulaireFormation.render());
    }

    public Result listeFormation(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(listeFormation.render(m));
    }

    public Result validerFormation(){
        final Form<FormulaireFormation> form =formFactory.form(FormulaireFormation.class).bindFromRequest();
        Membre m= Membre.byEmail(session("membre"));
        Formation.ajouterNewFormation(m.getParticulier(),form.get().getType(),form.get().getAnneeDebut(),form.get().getAnneeFin(),form.get().getEtablissement(),form.get().getDiplome(),form.get().getResultat());
        return redirect(controllers.routes.ProfilController.listeFormation());
    }

    public Result validerModifierFormation(Long id){
        final Form<FormulaireFormation> form =formFactory.form(FormulaireFormation.class).bindFromRequest();
        Formation.modifierFormation(id,form.get().getType(),form.get().getAnneeDebut(),form.get().getAnneeFin(),form.get().getEtablissement(),form.get().getDiplome(),form.get().getResultat());
        return redirect(controllers.routes.ProfilController.listeFormation());
    }

    public Result modifierFormation(Long id){
        return ok(formulaireModifierFormation.render(Formation.getFormation(id)));
    }

    public Result experience(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(experienceView.render(m));
    }

    public Result ajouterFormulaireExperience(){
        return ok(ajouterFormulaireExperience.render());
    }

    public Result listeExperience(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(listeExperience.render(m));
    }

    public Result validerExperience(){
        final Form<FormulaireExperience> form =formFactory.form(FormulaireExperience.class).bindFromRequest();
        Membre m= Membre.byEmail(session("membre"));
        Experience.ajouterNewExperience(form.get().getEntreprise(),form.get().getLieu(),form.get().getTitre(),form.get().getMoiDebut(),form.get().getMoiFin(),form.get().getAnneeDebut(),form.get().getAnneeFin(),m,form.get().getEtat());
        return redirect(controllers.routes.ProfilController.listeExperience());
    }

    public Result validerModifierExperience(Long id){
        final Form<FormulaireExperience> form =formFactory.form(FormulaireExperience.class).bindFromRequest();
        Experience.modifierExperience(id,form.get().getEntreprise(),form.get().getLieu(),form.get().getTitre(),form.get().getMoiDebut(),form.get().getMoiFin(),form.get().getAnneeDebut(),form.get().getAnneeFin(),form.get().getEtat());
        return redirect(controllers.routes.ProfilController.listeExperience());
    }

    public Result modifierExperience(Long id){
        return ok(formulaireModifierExperience.render(Experience.getExperience(id)));
    }

    public Result competence(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(competenceView.render(m));
    }

    public Result ajouterFormulaireCompetence(){
        return ok(ajouterFormulaireCompetence.render());
    }

    public Result listeCompetence(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(listeCompetence.render(m));
    }

    public Result validerCompetence(){
        final Form<Competence> form =formFactory.form(Competence.class).bindFromRequest();
        Membre m= Membre.byEmail(session("membre"));
        Competence.ajouterNewCompetence(form.get().getDescription(),m);
        return redirect(controllers.routes.ProfilController.listeCompetence());
    }

    public Result validerModifierCompetence(Long id){
        final Form<Competence> form =formFactory.form(Competence.class).bindFromRequest();
        Competence.modifierCompetence(id,form.get().getDescription());
        return redirect(controllers.routes.ProfilController.listeCompetence());
    }

    public Result modifierCompetence(Long id){
        return ok(formulaireModifierCompetence.render(Competence.getCompetence(id)));
    }

    public Result langue(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(langueView.render(m));
    }

    public Result ajouterFormulaireLangue(){
        return ok(ajouterFormulaireLangue.render());
    }

    public Result listeLangue(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(listeLangue.render(m));
    }

    public Result validerLangue(){
        final Form<Langue> form =formFactory.form(Langue.class).bindFromRequest();
        Membre m= Membre.byEmail(session("membre"));
        Langue.ajouterNewLangue(form.get().getLibele(),m.getParticulier());
        return redirect(controllers.routes.ProfilController.listeLangue());
    }

    public Result validerModifierLangue(Long id){
        final Form<Langue> form =formFactory.form(Langue.class).bindFromRequest();
        Langue.modifierLangue(id,form.get().getLibele());
        return redirect(controllers.routes.ProfilController.listeLangue());
    }

    public Result modifierLangue(Long id){
        return ok(formulaireModifierLangue.render(Langue.getLangue(id)));
    }

    public Result loisir(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(loisirView.render(m));
    }

    public Result ajouterFormulaireLoisir(){
        return ok(ajouterFormulaireLoisir.render());
    }

    public Result listeLoisir(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(listeLoisir.render(m));
    }

    public Result validerLoisir(){
        final Form<Loisir> form =formFactory.form(Loisir.class).bindFromRequest();
        Membre m= Membre.byEmail(session("membre"));
        Loisir.ajouterNewLoisir(form.get().getLibele(),m.getParticulier());
        return redirect(controllers.routes.ProfilController.listeLoisir());
    }

    public Result validerModifierLoisir(Long id){
        final Form<Loisir> form =formFactory.form(Loisir.class).bindFromRequest();
        Loisir.modifierLoisir(id,form.get().getLibele());
        return redirect(controllers.routes.ProfilController.listeLoisir());
    }

    public Result modifierLoisir(Long id){
        return ok(formulaireModifierLoisir.render(Loisir.getLoisir(id)));
    }

    public Result monProfil(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(monProfil.render(m));
    }

    public Result suggessions(){
        Membre m= Membre.byEmail(session("membre"));
        return ok(suggession.render(m));
    }

}
