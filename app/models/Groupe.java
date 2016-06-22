package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Entité Groupe
 */

@Entity
public class Groupe extends Model {
    /**
     * Identifiant de groupe
     */
    @Id
    public long id;

    /**
     * le nom du groupe
     */
    @Constraints.Required
    public String nomGroupe;

    /**
     * la date de creation
     */
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date dateCreation = new Date();

    /**
     * Relation entre Groupe et Membre
     * la liste des tous les membres du groupe
     */
    @ManyToMany(mappedBy = "groupeAppartenances")
    public List<Membre> membres;

    /**
     * Relation entre Groupe et Membre
     * le membre qui a crée le groupe
     */
    @ManyToOne
    public Membre createur;

    /**
     * Constructeur par defaut
     */
    public Groupe() {
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Groupe> find = new Finder<Long,Groupe>(Groupe.class);
}
