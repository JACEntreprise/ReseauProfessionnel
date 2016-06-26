package models;

import com.avaje.ebean.Ebean;

import javax.persistence.*;
import java.util.List;

/**
 * Created by julio on 25/06/2016.
 */
@Entity
@DiscriminatorValue("article")
public class Article extends Publication {
    @ManyToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    private List<Domaine> domaines;

    private boolean publie;
    private boolean etat;

    public Article(){
        etat = false;
        publie = false;
    }

    public List<Domaine> getDomaines() {
        return domaines;
    }

    public void setDomaines(List<Domaine> domaines) {
        this.domaines = domaines;
    }

    public boolean isPublie() {
        return publie;
    }

    public void setPublie(boolean publie) {
        this.publie = publie;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    /**
     * fonction qui permet de lister tous les articles
     */
    public static List<Article> listArticles(){
        List<Article> listArticles;
        listArticles = Ebean.find(Article.class).where().eq("etat",false).findList();
        return listArticles;
    }

    /**
     * fonction qui permet de lister tous les articles supprimes
     */
    public static List<Article> articleSupprime(){
        List<Article> listArticles;
        listArticles = Ebean.find(Article.class).where().eq("etat",true).findList();
        return listArticles;
    }

    /**
     * fonction qui permet d'ajouter un nouvel article
     */
    public void ajouter(){
        this.save();
    }

    /**
     * fonction qui permet de modifier un article
     */
    public void modifier(Article article){
        super.modifier(article);
        this.domaines = article.getDomaines();
    }

    /**
     * supprimer un article
     */
    public void supprimer(){
        etat = true;
    }

    /**
     * recupèrer un article par son id
     * @param id
     */
    public static Article getArticle(Long id){
        Article article;
        article = Ebean.find(Article.class).where().eq("id",id).findUnique();
        return article;
    }

    /**
     * recupérer l'ensemble des articles publiés
     */
    public static List<Article> articlePublie(){
        List<Article> listArticles;
        listArticles = Ebean.find(Article.class).where().eq("publie",true).findList();
        return listArticles;
    }

    /**
     * recupérer l'ensemble des articles non publiés
     */
    public static List<Article> articleNonPublie(){
        List<Article> listArticles;
        listArticles = Ebean.find(Article.class).where().eq("publie",false).findList();
        return listArticles;
    }

    /**
     * valider un article
     */
    public String validate(){
        return null;
    }
}
