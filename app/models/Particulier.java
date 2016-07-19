package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Entite Particulier
 */
@Entity
public class Particulier extends Model{
    /**
     * Identifiant de Particulier
     */
    @Id
    private long id;
    /**
     * Le nom du particulier
     */
    @Constraints.Required
    private String nom;

    /**
     * Le prenom du particulier
     */
    @Constraints.Required
    private String prenom;

    /**
     * La date de naissance du particulier
     */
    @Formats.DateTime(pattern="dd/MM/yyyy")
    private Date dateDeNaissance = new Date();

    /**
     * Le lieu de naissance du particulier
     */
    private String lieuDeNaissance;

    /**
     * Relation d'heritage entre Particulier et Membre
     * Un particulier est un membre
     */
    @Column(nullable = false)
    @OneToOne
    private Membre membre;

    /**
     * Relation entre particulier et Loisir
     */
    @ManyToMany
    private List<Loisir> loisirs;

    /**
     * Relation entre particulier et langue
     * Une personne peut parlet plusieurs langues
     */
    @ManyToMany
    private List<Langue> langues;
    /**
     * Relation entre personne et formation
     * Une personne peut avoir plusieurs formation
     */
    @OneToMany(mappedBy = "particulier")
    private List<Formation> formations;

    /**
     * Construceteur par defaut
     */
    public Particulier() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public List<Loisir> getLoisirs() {
        return loisirs;
    }

    public void setLoisirs(List<Loisir> loisirs) {
        this.loisirs = loisirs;
    }

    public List<Langue> getLangues() {
        return langues;
    }

    public void setLangues(List<Langue> langues) {
        this.langues = langues;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }


    public static void completeProfil(Membre m,String adresse, String telephone, String siteweb, String lieuDeNaissane,String dateDeNaissance) throws ParseException {
        m.setAdresse(adresse);
        m.setEtat(-1);
        m.setTelephone(telephone);
        m.setSiteweb(siteweb);
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        m.getParticulier().setLieuDeNaissance(lieuDeNaissane);
        m.getParticulier().setDateDeNaissance(f.parse(dateDeNaissance));
        m.update();
    }
    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Particulier> find = new Finder<Long,Particulier>(Particulier.class);
}
