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
    private long id;

    /**
     * le type de formation
     */
    @Constraints.Required
    private String type;

    /**
     * diplome obtenu
     */
    @Constraints.Required
    private String diplome;

    /**
     * l'etablissement qu'il a passé la formation
     */
    @Constraints.Required
    private String etablissement;

    /**
     * resultat de la formation
     */
    @Constraints.Required
    private String resultat;

    /**
     * description de la formation
     */
    @Constraints.Required
    @Lob
    private String description;

    /**
     * la date de debut
     */
    @Formats.DateTime(pattern="dd/MM/yyyy")
    private Date dateDebut = new Date();

    /**
     * la date de fin
     */
    @Formats.DateTime(pattern="dd/MM/yyyy")
    private Date dateFin = new Date();

    /**
     * Relation entre Formation et Profil
     * plusieurs formations sont associees à un profil
     */
    @ManyToOne
    private Profil profil;

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

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Formation> find = new Finder<Long,Formation>(Formation.class);
}
