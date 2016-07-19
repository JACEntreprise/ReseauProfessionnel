package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

/**
 * Entite Langue
 */

@Entity
public class Langue extends Model {
    /**
     * Identifiant de Langue
     */
    @Id
    private long id;

    /**
     * le libelle de la langue
     */
    @Constraints.Required
    private String libele;

    /**
     * Relation entre Langue et particulier
     * plusieurs langues peuvent être associées à plusieurs personnes
     */
    @ManyToMany(mappedBy = "langues")
    private List<Particulier> particuliers;

    /**
     * Constructeur par defaut
     */
    public Langue() {
    }

    public Langue(String libele) {
        this.libele = libele;
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

    public List<Particulier> getParticuliers() {
        return particuliers;
    }

    public void setParticuliers(List<Particulier> particuliers) {
        this.particuliers = particuliers;
    }

    public static void ajouterNewLangue(String libele, Particulier p){
        Langue langue= new Langue(libele);
        langue.getParticuliers().add(p);
        langue.save();
    }

    public static void modifierLangue(Long id, String libele){
        Langue langue= Langue.getLangue(id);
        langue.setLibele(libele);
        langue.update();
    }

    public static Langue getLangue(Long id){
        return Langue.find.byId(id);
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Langue> find = new Finder<Long,Langue>(Langue.class);
}
