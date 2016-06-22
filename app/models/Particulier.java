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

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Particulier> find = new Finder<Long,Particulier>(Particulier.class);
}
