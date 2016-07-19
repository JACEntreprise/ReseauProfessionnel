package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Entite Formation
 */

@Entity
public class Formation extends Model {
    /**
     * Indentifiant de formation
     */
    @Id
    private long id;

    /**
     * le type de formation
     */
    @Constraints.Required
    private String type;

    @Constraints.Required
    private String etablissement;

    /**
     * la date de debut
     */
    private Long anneeDebut;

    /**
     * la date de fin
     */
    private Long anneeFin ;

    /**
     * diplome
     */
    private String diplome ;

    /**
     * resultat
     */
    private String resultat ;

    /**
     * Relation entre Formation et Particulier
     * plusieurs formations sont associees à plusieurs personnes
     */
    @ManyToOne
    private Particulier particulier;

    public Formation() {
    }

    public Formation(String type, String etablissement, Long anneeDebut, Long anneeFin, Particulier particulier, String diplome, String resultat) {
        this.type = type;
        this.etablissement = etablissement;
        this.anneeDebut = anneeDebut;
        this.anneeFin = anneeFin;
        this.particulier = particulier;
        this.diplome = diplome;
        this.resultat = resultat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public Long getAnneeDebut() {
        return anneeDebut;
    }

    public void setAnneeDebut(Long anneeDebut) {
        this.anneeDebut = anneeDebut;
    }

    public Long getAnneeFin() {
        return anneeFin;
    }

    public void setAnneeFin(Long anneeFin) {
        this.anneeFin = anneeFin;
    }

    public Particulier getParticulier() {
        return particulier;
    }

    public void setParticulier(Particulier particulier) {
        this.particulier = particulier;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public static void ajouterNewFormation(Particulier p, String type, Long anneeDebut, Long anneeFin, String etablissement, String diplome, String resultat){
        Formation formation= new Formation(type,etablissement,anneeDebut,anneeFin,p,diplome,resultat);
        formation.save();
    }

    public static void modifierFormation(Long id, String type, Long anneeDebut, Long anneeFin, String etablissement, String diplome, String resultat){
        Formation formation= Formation.getFormation(id);
        formation.setType(type);
        formation.setAnneeDebut(anneeDebut);
        formation.setAnneeFin(anneeFin);
        formation.setEtablissement(etablissement);
        formation.setDiplome(diplome);
        formation.setResultat(resultat);
        formation.update();
    }

    public static Formation getFormation(Long id){
        return Formation.find.byId(id);
    }
    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Formation> find = new Finder<Long,Formation>(Formation.class);
}
