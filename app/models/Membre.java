package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;
import org.mindrot.jbcrypt.BCrypt;


/**
 * Creation de l'Entité Membre
 */
@Entity
public class Membre extends Model {

    /**
     * id de l'entité
     */
    @Id
    public Long id;

    /**
     * Email d'un membre qui est obligatoire
     */
    @Constraints.Required
    @Constraints.Email
    public String email;

    /**
     * Mot de passe d'un membre qui est obligatoire
     */
    @Constraints.Required
    public String motDePasse;

    /**
     * adresse du membre qui est obligatoire
     */
    public String adresse;

    /**
     * Numéro de téléphone
     */
    public String telephone;

    /**
     * Site web s'il y'en a
     */
    public String siteweb;

    /**
        * Etat du membre
    */
    public int etat;

    /**
     * cle pour hachage mot de passe
    */
    public String salt;

    /**
     * Relation d'héritage entre Membe et Particulier
     */
    @OneToOne(mappedBy = "membre", cascade = CascadeType.ALL)
    public Particulier particulier;

    /**
     * Relation d'héritage entre Membe et Entreprise
     */
    @OneToOne(mappedBy = "membre")
    public Entreprise entreprise;


    /**
     * Relation entre Membre et Profil
     * Un membre est associé à un seul Profil
     */
    @OneToOne
    public Profil profil;


    /**
     * Relation entre Membe et Message dans un sens(envoi)
     * Un membre peut envoyer plusieurs messages
     */
    @Column(nullable = true)
    @OneToMany(mappedBy = "expediteur")
    public List<Message> messagesEpediteurs;

    /**
     * Relation entre Membe et Message dans l'autre sens(reception)
     * Un membre peut recevoir plusieurs messages
     */
    @Column(nullable = true)
    @OneToMany(mappedBy = "destinataire")
    public List<Message> messagesDestinataires;

    /**
     * Relation entre Membe et Image
     * Un membre peut avoir plusieurs photo de profil
     */
    @OneToMany(mappedBy = "membre")
    public List<Image> images;

    /**
     * Relation entre Membe et Groupe(appartenance)
     * Plusieurs peuvent appartenir à un Groupe
     */
    @ManyToMany
    public List<Groupe> groupeAppartenances;

    /**
     * Relation de création entre Membe et Groupe
     * Un membre peut creer plusieurs Groupe
     */
    @OneToMany(mappedBy = "createur")
    public List<Groupe> groupes;

    /**
     * La liste d'amis demandés par ce membre
     */
    @OneToMany(mappedBy = "membreSource")
    public List<Amitie> amities;

    /**
     * La liste d'amis qui ont demandé une relation d'amitie à ce membre
     */
    @OneToMany(mappedBy = "membreCible")
    public List<Amitie> demandeAmities;

    /**
     * Relation entre Membre et Publication
     * Un membre peut faire plusieurs publications
     */
    @OneToMany(mappedBy = "membre")
    public List<Publication> publications;

    /**
     * Relation entre Membre et Commentaire
     * Un membre peut faire plusieurs commentaires
     */
    @OneToMany(mappedBy = "membre")
    public List<Commentaire> commentaires;

    /**
     * Relation entre Membre et VuePublication
     * Un membre peut voir plusieurs publications
     */
    @OneToMany(mappedBy = "membre")
    public List<VuePublication> vuePublications;

    /**
     * Relation entre Membre et VueCommentaire
     * Un membre peut voir plusieurs commentaires
     */
    @OneToMany(mappedBy = "membre")
    public List<VueCommentaire> vueCommentaires;


    /**
     *  Recuperer un membre par son email
     * @param email
     * @return Membre
     */

    public static Membre byEmail(String email){
        List<Membre> membres= Membre.find.all();
        for (Membre m:membres){
            if(m.email.equals(email)){
                return m;
            }
        }
        return null;
    }

    public static Membre authenticate(String email, String motDePasse){
        List<Membre> membres= Membre.find.all();
        for (Membre m:membres){
            if(m.email.equals(email)){
                String hashPassword= BCrypt.hashpw(motDePasse, m.salt);
                if((m.motDePasse).equals(hashPassword)){
                    return m;
                }
                return  null;
            }
        }
        return null;
    }

    /**
     * la liste de ses amis
     * @param membre
     * @return
     */
    public static List<Membre> listeDAmisAccepte(Membre membre){
        List<Membre> amis=new ArrayList<Membre>();

        return amis;
    }

    /**
     * Demandes qui ne sont pas acceptées
     * @param membre
     * @return
     */
    public static List<Membre> listeDemadeAmiNonAccepte(Membre membre){
        List<Membre> amisNonAccepte=new ArrayList<Membre>();

        return amisNonAccepte;
    }

    /**
     * Demandes qu'il n'a pas encore acceptéés
     * @param membre
     * @return
     */
    public static List<Membre> listeDemadeAmiNonAccepteMe(Membre membre){
        List<Membre> amisNonAccepte=new ArrayList<Membre>();

        return amisNonAccepte;
    }

    /**
     * Liste d'amis a suggeré
     * @param membre
     * @return
     */
    public static List<Membre> listeNonAmi(Membre membre){
        List<Membre> amisNon=new ArrayList<Membre>();

        return amisNon;
    }


    /**
     * Constructeur par defaut
     */
    public Membre() {
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<String, Membre> find = new Finder<String,Membre>(Membre.class);
}
