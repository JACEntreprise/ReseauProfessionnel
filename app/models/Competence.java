package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * Entite Competence
 */
@Entity
public class Competence extends Model {
    /**
     * Identifiant de competence
     */
    @Id
    private long id;

    /**
     * La description de la competence
     */
    @Constraints.Required
    private String libele;

    /**
     * Relation entre Competence et Profil
     * Plusieurs competences sont associées à un profil
     */
    @ManyToOne
    private Profil profil;

    public Competence() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Competence> find = new Finder<Long,Competence>(Competence.class);

}
