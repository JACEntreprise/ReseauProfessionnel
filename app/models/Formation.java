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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Formation> find = new Finder<Long,Formation>(Formation.class);
}
