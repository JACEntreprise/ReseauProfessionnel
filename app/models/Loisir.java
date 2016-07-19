package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

/**
 * Entite Loisir
 */

@Entity
public class Loisir extends Model {
    /**
     * Indentifiant de Loisir
     */
    @Id
    private long id;

    /**
     * le libelle du loisir
     */
    @Constraints.Required
    private String libele;

    /**
     * Relation entre Loisir et Personnes
     * plusieurs loisirs sont associes a plusieurs personnes
     */
    @ManyToMany(mappedBy = "loisirs")
    private List<Particulier> particuliers;

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

    public List<Particulier> getParticuliers() {
        return particuliers;
    }

    public void setParticuliers(List<Particulier> particuliers) {
        this.particuliers = particuliers;
    }

    public Loisir(String libele) {
        this.libele = libele;
    }

    public static void ajouterNewLoisir(String libele, Particulier p){
        Loisir loisir= new Loisir(libele);
        loisir.getParticuliers().add(p);
        loisir.save();
    }

    public static void modifierLoisir(Long id, String libele){
        Loisir loisir= Loisir.getLoisir(id);
        loisir.setLibele(libele);
        loisir.update();
    }

    public static Loisir getLoisir(Long id){
        return Loisir.find.byId(id);
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Loisir> find = new Finder<Long,Loisir>(Loisir.class);
}
