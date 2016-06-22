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



    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Loisir> find = new Finder<Long,Loisir>(Loisir.class);
}
