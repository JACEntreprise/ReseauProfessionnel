package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.Constraint;
import java.util.List;

/**
 * Created by julio on 25/06/2016.
 */
@Entity
public class Domaine extends Model {
    @Id
    private Long id;

    @Constraints.Required
    private String libelle;

    private boolean etat;

    @ManyToMany
    private List<Article> articles;

    public Domaine(){etat = false;}

    public Domaine(String domaine){
        libelle = domaine;
        etat = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    /**
     * fonction qui permet de lister les domaines existants
     */
    public static List<Domaine> listeDomaine(){
        List<Domaine> listDomaines;
        listDomaines = Ebean.find(Domaine.class).where().eq("etat",false).findList();
        return listDomaines;
    }

    /**
     * fonction qui permet de lister les domaines supprimés
     */
    public static List<Domaine> domaineSupprime(){
        List<Domaine> listDomaines;
        listDomaines = Ebean.find(Domaine.class).where().eq("etat",true).findList();
        return listDomaines;
    }

    /**
     * fonction qui permet d'ajouter un nouveau domaine
     */
    public void ajouter(){
        this.save();
    }

    /**
     * fonction qui permet de modifier un domaine
     */
    public void modifier(Domaine domaine){
        this.libelle = domaine.getLibelle();
    }

    /**
     * fonction qui permet de supprimer un domaine
     */
    public void supprimer(){
        etat = true;
    }

    /**
     * fonction qui permet de recupérer un domaine par son id
     */
    public static Domaine getDomaine(Long id){
        Domaine domaine;
        domaine = Ebean.find(Domaine.class).where().eq("id",id).findUnique();
        return domaine;
    }

    /**
     * trouver le domaine correspondant à un libellé donné
     */
    public static Domaine byLibelle(String libelle){
        Domaine domaine;
        domaine = Ebean.find(Domaine.class).where().eq("libelle",libelle).findUnique();

        return domaine;
    }
}
