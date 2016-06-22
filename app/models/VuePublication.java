package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entité Amitié
 */

@Entity
public class VuePublication extends Model {
    @Id
    public long id;

    /**
     * plusieurs vues pour un membre
     */
    @ManyToOne
    public Membre membre;


    /**
     * plusieurs vues pour pour plusieurs publications
     */
    @ManyToOne
    public Publication publication;

    /**
     * Attribut qui permet de connaitre les amis acceptés
     */
    public boolean vue;


    public VuePublication() {
        this.vue=false;
    }

    public static void CreerNewVuePulication(Membre m,Publication publication){
        VuePublication vp= new VuePublication();
        vp.membre=m;
        vp.publication=publication;
        vp.save();
    }
    public static Finder<Long, VuePublication> find = new Finder<Long, VuePublication>(VuePublication.class);
}
