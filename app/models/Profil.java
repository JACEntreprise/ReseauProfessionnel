package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.*;

/**
 * Entite Profil
 */
@Entity
public class Profil extends Model {
    /**
     * Identifiant de profil
     */
    @Id
    public long id;

    /**
     * Relation entre Profil et Membre
     * un membre a un seul profil
     */
    @OneToOne(mappedBy = "profil")
    public Membre membre;

    /**
     * Relation entre Profil et Image
     */
    @OneToOne(mappedBy = "profil")
    public Image image;

    /**
     * Relation entre Profil et Competence
     * Un profil associe à plusieurs competences
     */
    @OneToMany(mappedBy = "profil")
    public List<Competence> competences;

    /**
     * Relation entre Profil et Experience
     * Un profil associe à plusieurs experience
     */
    @OneToMany(mappedBy = "profil")
    public List<Experience> experiences;

    /**
     * Relation entre Profil et Formation
     * Un profil associe à plusieurs formations
     */
    @OneToMany(mappedBy = "profil")
    public List<Formation> formations;

    /**
     * Relation entre Profil et Langue
     * Un profil associe à plusieurs langues
     */
    @OneToMany(mappedBy = "profil")
    public List<Langue> langues;

    /**
     * Relation entre Profil et Loisir
     * Un profil associe à plusieurs loisir
     */
    @OneToMany(mappedBy = "profil")
    public List<Loisir> loisir;

    /**
     * Constructeur par defaut
     */
    public Profil() {
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Profil> find = new Finder<Long,Profil>(Profil.class);
}
