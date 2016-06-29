package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Entite Entreprise
 */
@Entity
public class Entreprise extends Model{
    /**
     * Identifiant d'entreprise
     */
    @Id
    public long id;

    /**
     * La raison sociale de l'entreprise
     */
    @Constraints.Required
    public String raisonSocial;

    /**
     * La date de creation de l'entreprise
     */
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date dateCreation = new Date();

    /**
     * Le domaine d'activité de l'entreprise
     */
    @Constraints.Required
    public String domaine;

    /**
     * Relation d'héritage entre Entreprise et Membre
     * Une entreprise est un membre
     */
    @OneToOne
    public Membre membre;

    public Entreprise() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public static void completeProfil(Membre m,String adresse, String telephone, String siteweb, String domaine,String dateCreation) throws ParseException {
        m.setAdresse(adresse);
        m.setEtat(-1);
        m.setTelephone(telephone);
        m.setSiteweb(siteweb);
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        m.getEntreprise().setDomaine(domaine);
        m.getEntreprise().setDateCreation(f.parse(dateCreation));
        m.update();
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Entreprise> find = new Finder<Long,Entreprise>(Entreprise.class);
}
