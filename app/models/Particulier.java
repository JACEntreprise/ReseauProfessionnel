package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

/**
 * Entite Particulier
 */
@Entity
public class Particulier extends Model{
    /**
     * Identifiant de Particulier
     */
    @Id
    public long id;
    /**
     * Le nom du particulier
     */
    @Constraints.Required
    public String nom;

    /**
     * Le prenom du particulier
     */
    @Constraints.Required
    public String prenom;

    /**
     * La date de naissance du particulier
     */
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date dateDeNaissance = new Date();

    /**
     * Le lieu de naissance du particulier
     */
    public String lieuDeNaissance;

    /**
     * Relation d'heritage entre Particulier et Membre
     * Un particulier est un membre
     */
    @Column(nullable = false)
    @OneToOne
    public Membre membre;

    /**
     * Construceteur par defaut
     */
    public Particulier() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Particulier> find = new Finder<Long,Particulier>(Particulier.class);
}
