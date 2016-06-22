package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * Entite Competence
 */
@Entity
public class Competence extends Model {
    /**
     * Identifiant de competence
     */
    @Id
    public long id;

    /**
     * La description de la competence
     */
    @Constraints.Required
    public String description;

    /**
     * Relation entre Competence et Profil
     * Plusieurs competences sont associées à un profil
     */
    @ManyToOne
    public Profil profil;

    public Competence() {
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Competence> find = new Finder<Long,Competence>(Competence.class);

}
