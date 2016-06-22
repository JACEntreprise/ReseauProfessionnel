package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Entité Amitié
 */

@Entity
public class Amitie extends Model {
    @Id
    public long id;

    /**
     * Le membre qui demande l'amitié
     */
    @ManyToOne
    public Membre membreSource;


    /**
     * Le membre à qui l'amitié a été demandée
     */
    @ManyToOne
    public Membre membreCible;

    /**
     * Attribut qui permet de connaitre les amis acceptés
     */
    public boolean accepte;


    public Amitie() {
        this.accepte=false;
    }
    public static Finder<Long, Amitie> find = new Finder<Long,Amitie>(Amitie.class);
}
