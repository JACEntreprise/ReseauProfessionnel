package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

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
    private String description;

    /**
     * Relation entre Competence et membre
     * Plusieurs compétences peuvent être partager par un groupes de membre
     */

    @ManyToMany(mappedBy = "competences")
    private List<Membre> membres;

    public Competence() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Membre> getMembres() {
        return membres;
    }

    public void setMembres(List<Membre> membres) {
        this.membres = membres;
    }

    public Competence(String description) {
        this.description = description;
    }

    public static void ajouterNewCompetence(String description, Membre m){
        Competence competence= new Competence(description);
        competence.getMembres().add(m);
        competence.save();
    }

    public static void modifierCompetence(Long id, String descrption){
        Competence competence= Competence.getCompetence(id);
        competence.setDescription(descrption);
        competence.update();
    }

    public static Competence getCompetence(Long id){
        return Competence.find.byId(id);
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Competence> find = new Finder<Long,Competence>(Competence.class);

}
