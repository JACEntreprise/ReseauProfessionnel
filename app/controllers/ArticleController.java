package controllers;

import controllers.action.SecuredAdmin;
import models.Administrateur;
import models.Article;
import models.Domaine;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.administrateur.*;

import java.util.List;

/**
 * Created by julio on 25/06/2016.
 */
@Security.Authenticated(SecuredAdmin.class)
public class ArticleController extends Controller {
    /**
     * action qui permet de lister l'ensemble des articles
     * @return
     */
    public Result lister(){
        //on recupère le membre connecté
        String email = session("administrateur");
        Administrateur admin = Administrateur.byEmail(email);

        //la liste de tous les domaines
        List<Domaine> listDomaines;
        listDomaines = Domaine.listeDomaine();

        List<Article> listArticles = Article.listArticles();
        return ok(list_admin.render(listArticles,listDomaines,admin));
    }

    /**
     * action qui permet d'ajouter un article
     * @return
     */
    public Result ajouter(){
        return ok("test");
    }

    /**
     * action qui permet de supprimer un article
     * @return
     */
    public Result supprimer(){
        return ok("test");
    }

    /**
     * action qui permet de modifier un article
     * @return
     */
    public Result modifier(){
        return ok("test");
    }

    /**
     * action qui permet de visualiser un article
     * @return
     */
    public Result voir(){
        return ok("test");
    }
}
