package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;


/**
 * Creation de l'Entité Membre
 */
@Entity
public class Image extends Model {

    /**
     * id de l'entité
     */
    @Id
    public Long id;

    /**
     * nom de l'image
     */
    @Constraints.Required
    public String nom;

    /**
     * chemin vers l'image
     */
    @Constraints.Required
    public String chemin;

    /**
     * Relation entre Image et Profil
     * image profile actuelle
     */
    @OneToOne
    public Profil profil;

    /**
     * Relation entre Membe et Image
     * Un membre peut avoir plusieurs photo de profil
     */
    @ManyToOne
    public Membre membre;

    /**
     * Constructeur par defaut
     */
    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
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
    public static Finder<String, Image> find = new Finder<String, Image>(Image.class);
}
