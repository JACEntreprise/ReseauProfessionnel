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


    public static Finder<Long, VueCommentaire> find = new Finder<Long, VueCommentaire>(VueCommentaire.class);
}
