package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entité Amitié
 */

@Entity
public class VueCommentaire extends Model {
    @Id
    public long id;

    /**
     * plusieurs vues pour un membre
     */
    @ManyToOne
    public Membre membre;


    /**
     * plusieurs vues pour pour plusieurs commentaires
     */
    @ManyToOne
    public Commentaire commentaire;

    /**
     * Attribut qui permet de connaitre les amis acceptés
     */
    public boolean vue;


    public VueCommentaire() {
        this.vue=false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Commentaire getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
    }

    public boolean isVue() {
        return vue;
    }

    public void setVue(boolean vue) {
        this.vue = vue;
    }

    public static Finder<Long, VueCommentaire> find = new Finder<Long, VueCommentaire>(VueCommentaire.class);
}
