package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * L'Entite Commentaire
 */
@Entity
public class Commentaire extends Model {
    /**
     * L'identificateur de l'entite
     */
    @Id
    public Long id;

    /**
     * Le contenu du commentaire
     */
    @Constraints.Required
    @Lob
    public String contenu;

    /**
     * Relation entre commentaire et Publication
     * plusieurs commentaires sont associés à une publication
     */
    @ManyToOne
    public Publication publication;

    /**
     * Relation entre commentaire et membre
     * plusieurs commentaires sont faits par un membre
     */
    @ManyToOne
    public Membre membre;

    /**
     * Relation entre Commentaire et VueCommentaire
     * Un commentaire peut etre vu par plusieurs membres
     */
    @OneToMany(mappedBy = "commentaire")
    public List<VueCommentaire> vueCommentaires;

    public Commentaire() {
    }

    public Commentaire(String contenu, Publication publication, Membre membre) {
        this.contenu = contenu;
        this.publication = publication;
        this.membre = membre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public List<VueCommentaire> getVueCommentaires() {
        return vueCommentaires;
    }

    public void setVueCommentaires(List<VueCommentaire> vueCommentaires) {
        this.vueCommentaires = vueCommentaires;
    }


    public static void creerNewCommentaire(String contenu, Publication publication, Membre membre){
        Commentaire commentaire= new Commentaire(contenu,publication,membre);
        commentaire.save();
        List<Membre> membres=Membre.find.all();
        for(Membre m:membres) {
            VueCommentaire.creerNewVueCommentaire(m,commentaire);
        }
        VueCommentaire vc= Ebean.find(VueCommentaire.class).where().eq("membre.id",membre.getId()).eq("commentaire.id",commentaire.getId()).findUnique();
        vc.setVue(1);
        vc.update();
    }

    public static Model.Finder<Long, Commentaire> find = new Model.Finder<Long,Commentaire>(Commentaire.class);
}
