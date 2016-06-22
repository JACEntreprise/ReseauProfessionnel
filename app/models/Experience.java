package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * Entité Experience
 */
@Entity
public class Experience extends Model {
    /**
     * Identifiant de l'experience
     */
    @Id
    public long id;

    /**
     * le libelle de l'experience
     */
    @Constraints.Required
    public String libele;

    /**
     * l'annee d'acquisition de l'experience
     */
    @Constraints.Required
    public String annee;

    /**
     * Relation entre Experience et Profil
     * Plusieurs experiences sont associees à un profil
     */
    @ManyToOne
    public Profil profil;

    public Experience() {
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Experience> find = new Finder<Long,Experience>(Experience.class);
}
