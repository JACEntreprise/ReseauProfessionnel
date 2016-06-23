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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }

    public List<Langue> getLangues() {
        return langues;
    }

    public void setLangues(List<Langue> langues) {
        this.langues = langues;
    }

    public List<Loisir> getLoisir() {
        return loisir;
    }

    public void setLoisir(List<Loisir> loisir) {
        this.loisir = loisir;
    }


    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Profil> find = new Finder<Long,Profil>(Profil.class);
}
