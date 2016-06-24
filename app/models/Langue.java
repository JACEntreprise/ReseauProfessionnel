package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * Entite Langue
 */

@Entity
public class Langue extends Model {
    /**
     * Identifiant de Langue
     */
    @Id
    public long id;

    /**
     * le libelle de la langue
     */
    @Constraints.Required
    public String libele;

    /**
     * Relation entre Langue et Profil
     * plusieurs langues sont associees à un profil
     */
    @ManyToOne
    public Profil profil;

    /**
     * Constructeur par defaut
     */
    public Langue() {
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
    public static Finder<Long, Langue> find = new Finder<Long,Langue>(Langue.class);
}
