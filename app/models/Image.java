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
    private Long id;

    /**
     * nom de l'image
     */
    @Constraints.Required
    private String nom;

    /**
     * chemin vers l'image
     */
    @Constraints.Required
    private String chemin;

    private Boolean profil;

    /**
     * Relation entre Membe et Image
     * Un membre peut avoir plusieurs photo de profil
     */
    @ManyToOne
    private Membre membre;

    /**
     * Constructeur par defaut
     */
    public Image() {
        this.profil=true;
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

    public Boolean getProfil() {
        return profil;
    }

    public void setProfil(Boolean profil) {
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
