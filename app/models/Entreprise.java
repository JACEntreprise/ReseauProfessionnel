package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
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

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Entreprise> find = new Finder<Long,Entreprise>(Entreprise.class);
}
