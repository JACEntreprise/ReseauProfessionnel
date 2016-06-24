package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.validation.*;
import org.mindrot.jbcrypt.BCrypt;
import repository.MembreRepository;


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
     * Relation d'héritage entre Membe et Administrateur
     */
    @OneToOne(mappedBy = "membre", cascade = CascadeType.ALL)
    public Administrateur administrateur;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Particulier getParticulier() {
        return particulier;
    }

    public void setParticulier(Particulier particulier) {
        this.particulier = particulier;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public List<Message> getMessagesEpediteurs() {
        return messagesEpediteurs;
    }

    public void setMessagesEpediteurs(List<Message> messagesEpediteurs) {
        this.messagesEpediteurs = messagesEpediteurs;
    }

    public List<Message> getMessagesDestinataires() {
        return messagesDestinataires;
    }

    public void setMessagesDestinataires(List<Message> messagesDestinataires) {
        this.messagesDestinataires = messagesDestinataires;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Groupe> getGroupeAppartenances() {
        return groupeAppartenances;
    }

    public void setGroupeAppartenances(List<Groupe> groupeAppartenances) {
        this.groupeAppartenances = groupeAppartenances;
    }

    public List<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(List<Groupe> groupes) {
        this.groupes = groupes;
    }

    public List<Amitie> getAmities() {
        return amities;
    }

    public void setAmities(List<Amitie> amities) {
        this.amities = amities;
    }

    public List<Amitie> getDemandeAmities() {
        return demandeAmities;
    }

    public void setDemandeAmities(List<Amitie> demandeAmities) {
        this.demandeAmities = demandeAmities;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public List<VuePublication> getVuePublications() {
        return vuePublications;
    }

    public void setVuePublications(List<VuePublication> vuePublications) {
        this.vuePublications = vuePublications;
    }

    public List<VueCommentaire> getVueCommentaires() {
        return vueCommentaires;
    }

    public void setVueCommentaires(List<VueCommentaire> vueCommentaires) {
        this.vueCommentaires = vueCommentaires;
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<String, Membre> find = new Finder<String,Membre>(Membre.class);

    /**
     * ajouter un membre dans la base de données
     */
    public void ajouter(){
        MembreRepository repository = MembreRepository.instance;
        motDePasse = repository.hash(motDePasse);
        salt = repository.getSalt();
        /**
         * On enregistre ce membre dans la base
         */
        this.save();
    }
}
