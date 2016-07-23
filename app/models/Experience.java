package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Entité Experience
 */
@Entity
public class Experience extends Model {
    /**
     * Identifiant de l'experience
     */
    @Id
    private long id;

    /**
     * le libelle de l'experience
     */
    @Constraints.Required
    private String entreprise;

    /**
     * le libelle de l'experience
     */
    @Constraints.Required
    private String lieu;

    /**
     * le libelle de l'experience
     */
    @Constraints.Required
    private String titre;

    /**
     * le libelle de l'experience
     */
    @Constraints.Required
    private String moiDebut;

    /**
     * le libelle de l'experience
     */
    @Constraints.Required
    private String moiFin;

    /**
     * l'annee d'acquisition de l'experience
     */
    private Long anneeDebut;

    /**
     * l'annee d'acquisition de l'experience
     */
    private Long anneeFin;

    private Boolean etat;

    /**
     * Relation entre Ex un profilperience et membre
     * Plusieurs experiences peuvent être associées à plusieurs membres
     */
    @ManyToOne
    private Membre membre;

    public Experience() {
    }

    public Experience(String entreprise, String lieu, String titre, String moiDebut, String moiFin, Long anneeDebut, Long anneeFin, Membre membre) {
        this.entreprise = entreprise;
        this.lieu = lieu;
        this.titre = titre;
        this.moiDebut = moiDebut;
        this.moiFin = moiFin;
        this.anneeDebut = anneeDebut;
        this.anneeFin = anneeFin;
        this.etat = false;
        this.membre = membre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMoiDebut() {
        return moiDebut;
    }

    public void setMoiDebut(String moiDebut) {
        this.moiDebut = moiDebut;
    }

    public String getMoiFin() {
        return moiFin;
    }

    public void setMoiFin(String moiFin) {
        this.moiFin = moiFin;
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

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public static void ajouterNewExperience(String entreprise, String lieu, String titre, String moiDebut, String moiFin,Long anneeDebut,Long anneeFin, Membre m, Boolean etat){
        if(etat){
            Experience e= Ebean.find(Experience.class).where().eq("membre.id",m.id).eq("etat",true).findUnique();
            if(e!=null){
                e.setEtat(false);
                e.update();
            }
        }
        Experience experience= new Experience(entreprise,lieu,titre,moiDebut,moiFin,anneeDebut,anneeFin,m);
        experience.setEtat(etat);
        experience.save();
    }

    public static void modifierExperience(Long id, String entreprise, String lieu, String titre, String moiDebut, String moiFin,Long anneeDebut,Long anneeFin, Boolean etat){
        Experience experience= Experience.getExperience(id);
        if(etat==true){
            Experience e= Ebean.find(Experience.class).where().eq("membre.id",experience.membre.id).eq("etat",true).findUnique();
            if(e!=null){
                e.setEtat(false);
                e.update();
            }
        }
        experience.setEntreprise(entreprise);
        experience.setLieu(lieu);
        experience.setTitre(titre);
        experience.setMoiDebut(moiDebut);
        experience.setMoiFin(moiFin);
        experience.setAnneeDebut(anneeDebut);
        experience.setAnneeFin(anneeFin);
        experience.setEtat(etat);
        experience.update();
    }

    public static Experience getExperience(Long id){
        return Experience.find.byId(id);
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Experience> find = new Finder<Long,Experience>(Experience.class);
}
