package controllers;

import com.google.inject.Inject;
import controllers.action.SecuredAdmin;
import models.Article;
import models.Domaine;
import models.Membre;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import views.html.administrateur.article.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by julio on 25/06/2016.
 */
@Security.Authenticated(SecuredAdmin.class)
public class ArticleController extends Controller {
    @Inject
    FormFactory formFactory;
    /**
     * action qui permet de lister l'ensemble des articles
     * @return
     */
    public Result lister(){
        //on recupère le membre connecté
        Membre admin = AdministrateurController.adminConnecte();

        //la liste de tous les domaines
        List<Domaine> listDomaines;
        listDomaines = Domaine.listeDomaine();

        List<Article> listArticles = Article.listArticles();
        return ok(list_article.render(listArticles,listDomaines,admin));

        //gestion des requetes ajax
    }

    /**
     * action qui permet d'afficher le formulaire d'ajout
     * @return
     */
    public Result ajouterform(){
        //on recupère le membre connecté
        Membre membre = AdministrateurController.adminConnecte();
        //la liste de tous les domaines
        List<Domaine> listDomaines = Domaine.listeDomaine();
        return ok(ajouter_article.render(formFactory.form(Article.class),membre,listDomaines));
    }

    /**
     * ajouter un nouvel article
     */
    public Result ajouter(){
        //recupération des données du formulaire
        final Form<Article> articleForm = formFactory.form(Article.class).bindFromRequest();

        //on crée l'article
        Article article = new Article();
        article.setTitre(articleForm.get().getTitre());
        article.setContenu(articleForm.get().getContenu());
        article.setPublie(articleForm.get().isPublie());

        //le domaine
        List<Domaine> domaines = articleForm.get().getDomaines();
        List<Domaine> nvDomaines = new ArrayList<>();
        for(int i=0;i<domaines.size();i++){
            Domaine d = domaines.get(i);
            d = d.byLibelle(d.getLibelle());
            nvDomaines.add(d);
        }
        article.setDomaines(nvDomaines);
        article.setMembre(AdministrateurController.adminConnecte());

        //upload de l'image
        Http.MultipartFormData body = request().body().asMultipartFormData();

        //ask the multipart to be form url encoded...

        //which should not impact such call
        Http.MultipartFormData.FilePart<File> image = body.getFile("image-article");
        if(image != null){
            File file = image.getFile();
            String nom = image.getFilename();
            //extension du fichier
            String ext = getFileExtension(nom);
            //on génère un nom unique pour l'image
            nom = genererNom(article.getDatePublication());

            nom = String.format("%s.%s",nom,ext);

            article.setUrlImage(nom);

            file.renameTo(new File("public/images/articles",nom));
        }

        //on ajoute l'article dans la base
        article.save();

        //on redirige vers la liste des articles
        return redirect(routes.ArticleController.lister());

    }

    /**
     * action qui permet de supprimer un article
     * @return
     */
    public Result supprimer(Long id){
        Article article = Article.getArticle(id);
        article.supprimer();
        return redirect(routes.ArticleController.lister());
    }

    /**
     * action qui permet de modifier un article
     * @return
     */
    public Result modifier(Long id){
        return ok("test");
    }

    /**
     * action qui permet de visualiser un article
     * @return
     */
    public Result voir(Long id){
        Article article = Article.getArticle(id);
        //membre connecté
        Membre membre = AdministrateurController.adminConnecte();
        return ok(voir.render(article,membre));
    }


    public static String getFileExtension(String NomFichier) {
        File tmpFichier = new File(NomFichier);
        tmpFichier.getName();
        int posPoint = tmpFichier.getName().lastIndexOf('.');
        if (0 < posPoint && posPoint <= tmpFichier.getName().length() - 2 ) {
            return tmpFichier.getName().substring(posPoint + 1);
        }
        return "";
    }

    public static String  genererNom(Date date) {
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy_HHmmss");
        String format1 = dt1.format(date);

        return format1;
    }
}
