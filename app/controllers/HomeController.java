package controllers;

import controllers.action.Secured;
import models.Membre;
import models.Particulier;
import org.mindrot.jbcrypt.BCrypt;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import repository.MembreRepository;
import views.html.*;

import javax.inject.Inject;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    private final MembreRepository repository;

    public HomeController() {
        repository = MembreRepository.instance;
    }

    @Inject
    FormFactory formFactory;
    public Result index() {
        return ok(index.render(
                formFactory.form(FormulaireConnexion.class),
                formFactory.form(FormulaireInscriptionParticulier.class),
                formFactory.form(FormulaireInscriptionEntreprise.class)
        ));
    }

}
