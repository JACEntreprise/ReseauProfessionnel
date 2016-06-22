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

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<String, Image> find = new Finder<String, Image>(Image.class);
}
