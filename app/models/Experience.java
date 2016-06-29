package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

/**
 * Entité Experience
 */
@Entity
public class Experience extends Model {
    /**
     * Identifiant de l'experience
     */
    @Id
    private long id;

    /**
     * nom de l'entreprise
     */
    @Constraints.Required
    private String entreprise;

    /**
     * le titre de l'experience
     */
    @Constraints.Required
    private String titre;

    /**
     * lieu
     */
    @Constraints.Required
    private String lieu;

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
     * etat
     */
    private Boolean etat;

    /**
     * Relation entre Experience et Profil
     * Plusieurs experiences sont associees à un profil
     */
    @ManyToOne
    private Profil profil;

    public Experience() {
        etat=false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
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

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Experience> find = new Finder<Long,Experience>(Experience.class);
}
