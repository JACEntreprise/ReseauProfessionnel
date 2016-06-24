package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * Entite Loisir
 */

@Entity
public class Loisir extends Model {
    /**
     * Indentifiant de Loisir
     */
    @Id
    public long id;

    /**
     * le libelle du loisir
     */
    @Constraints.Required
    public String libele;

    /**
     * Relation entre Loisir et Profil
     * plusieurs loisirs sont associes a un profil
     */
    @ManyToOne
    public Profil profil;

    /**
     * Constructeur par defaut
     */
    public Loisir() {
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
    public static Finder<Long, Loisir> find = new Finder<Long,Loisir>(Loisir.class);
}
