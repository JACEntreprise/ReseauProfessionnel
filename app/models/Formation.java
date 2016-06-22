package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

/**
 * Entite Formation
 */

@Entity
public class Formation extends Model {
    /**
     * Indentifiant de formation
     */
    @Id
    public long id;

    /**
     * le type de formation
     */
    @Constraints.Required
    public String type;

    /**
     * la date de debut
     */
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date dateDebut = new Date();

    /**
     * la date de fin
     */
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date dateFin = new Date();

    /**
     * Relation entre Formation et Profil
     * plusieurs formations sont associees à un profil
     */
    @ManyToOne
    public Profil profil;

    public Formation() {
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Formation> find = new Finder<Long,Formation>(Formation.class);
}
