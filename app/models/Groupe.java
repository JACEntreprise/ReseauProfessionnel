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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<Membre> getMembres() {
        return membres;
    }

    public void setMembres(List<Membre> membres) {
        this.membres = membres;
    }

    public Membre getCreateur() {
        return createur;
    }

    public void setCreateur(Membre createur) {
        this.createur = createur;
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Groupe> find = new Finder<Long,Groupe>(Groupe.class);
}
