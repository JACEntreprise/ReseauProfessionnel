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
    public int vue;

    /**
     * Attribut qui permet de connaitre les amis acceptés
     */
    public int jaime;


    public VuePublication() {
        this.vue=0;
        this.jaime=0;
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

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public int getVue() {
        return vue;
    }

    public void setVue(int vue) {
        this.vue = vue;
    }

    public int getJaime() {
        return jaime;
    }

    public void setJaime(int jaime) {
        this.jaime = jaime;
    }

    public VuePublication(Membre membre, Publication publication) {
        this.membre = membre;
        this.publication = publication;
        this.vue = 0;
        this.jaime = 0;
    }

    public static void CreerNewVuePulication(Membre m, Publication publication){
        VuePublication vp= new VuePublication(m,publication);
        vp.save();
    }
    public static Finder<Long, VuePublication> find = new Finder<Long, VuePublication>(VuePublication.class);
}
