package models;

import java.util.*;
import javax.persistence.*;
import javax.persistence.Query;
import javax.xml.transform.Result;

import com.avaje.ebean.*;
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
    protected Long id;

    /**
     * Email d'un membre qui est obligatoire
     */
    @Constraints.Required
    @Constraints.Email
    protected String email;

    /**
     * Mot de passe d'un membre qui est obligatoire
     */
    @Constraints.Required
    protected String motDePasse;

    /**
     * adresse du membre qui est obligatoire
     */
    protected String adresse;

    /**
     * Numéro de téléphone
     */
    protected String telephone;

    /**
     * Site web s'il y'en a
     */
    protected String siteweb;

    /**
        * Etat du membre
    */
    protected int etat;

    /**
     * cle pour hachage mot de passe
    */
    protected String salt;

    /**
     * la date de création du membre
     */
    protected Date dateCreation;

    @Constraints.Required
    private String type;

    /**
     * Relation d'héritage entre Membe et Particulier
     */
    @OneToOne(mappedBy = "membre", cascade = CascadeType.ALL)
    private Particulier particulier;

    /**
     * Relation d'héritage entre Membe et Administrateur
     */
    @OneToOne(mappedBy = "membre", cascade = CascadeType.ALL)
    private Administrateur administrateur;

    /**
     * Relation d'héritage entre Membe et Entreprise
     */
    @OneToOne(mappedBy = "membre")
    private Entreprise entreprise;


    /**
     * Relation entre Membe et Message dans un sens(envoi)
     * Un membre peut envoyer plusieurs messages
     */
    @Column(nullable = true)
    @OneToMany(mappedBy = "expediteur")
    private List<Message> messagesEpediteurs;

    /**
     * Relation entre Membe et Message dans l'autre sens(reception)
     * Un membre peut recevoir plusieurs messages
     */
    @Column(nullable = true)
    @OneToMany(mappedBy = "destinataire")
    private List<Message> messagesDestinataires;

    /**
     * Relation entre Membe et Image
     * Un membre peut avoir plusieurs photo de profil
     */
    @OneToMany(mappedBy = "membre")
    private List<Image> images;

    /**
     * Relation entre Membe et Groupe(appartenance)
     * Plusieurs peuvent appartenir à un Groupe
     */
    @ManyToMany
    private List<Groupe> groupeAppartenances;

    /**
     * Relation de création entre Membe et Groupe
     * Un membre peut creer plusieurs Groupe
     */
    @OneToMany(mappedBy = "createur")
    private List<Groupe> groupes;

    /**
     * La liste d'amis demandés par ce membre
     */
    @OneToMany(mappedBy = "membreSource")
    private List<Amitie> amities;

    /**
     * La liste d'amis qui ont demandé une relation d'amitie à ce membre
     */
    @OneToMany(mappedBy = "membreCible")
    private List<Amitie> demandeAmities;

    /**
     * Relation entre Membre et Publication
     * Un membre peut faire plusieurs publications
     */
    @OneToMany(mappedBy = "membre")
    private List<Publication> publications;
    /**
     * Concernant le profil: relation entre membre et compétence
     * Un membre a plusieurs compétences, plusieurs membres peuvent partager une même compétence
     */
    @ManyToMany
    private List<Competence> competences;

    /**
     * Concernant toujours le profil: relation entre membre et expériences
     * Un membre peut avoir plusieurs expériences, plusieurs membre peuvent avoir vécus les mêmes expériences
     */
    @OneToMany(mappedBy = "membre")
    private List<Experience> experiences;

    /**
     * Relation entre Membre et Commentaire
     * Un membre peut faire plusieurs commentaires
     */
    @OneToMany(mappedBy = "membre")
    private List<Commentaire> commentaires;

    /**
     * Relation entre Membre et VuePublication
     * Un membre peut voir plusieurs publications
     */
    @OneToMany(mappedBy = "membre")
    private List<VuePublication> vuePublications;

    /**
     * Relation entre Membre et VueCommentaire
     * Un membre peut voir plusieurs commentaires
     */
    @OneToMany(mappedBy = "membre")
    private List<VueCommentaire> vueCommentaires;


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
        dateCreation = new Date(); //date systeme
        type="membre";
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Particulier getParticulier() {
        return particulier;
    }

    public void setParticulier(Particulier particulier) {
        this.particulier = particulier;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
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

    public List<Competence> getCompetences() {
        return competences;
    }

    public void addCompetence(Competence competence) {
        this.getCompetences().add(competence);
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<String, Membre> find = new Finder<>(Membre.class);

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

    /**
     * Obtenir une liste de suggessions d'amis
     * @return
     */
    public List<Membre> suggessionAmis(){
        String sql
                = "SELECT DISTINCT id " +
                "FROM membre m " +
                "WHERE m.id<>:id " +
                "AND (((m.id IN " +
                        "(SELECT a.membre_source_id " +
                        "FROM amitie a " +
                        "WHERE a.membre_cible_id IN " +
                            "(SELECT m1.id FROM membre m1 " +
                            "WHERE m1.id IN" +
                                "(SELECT a1.membre_cible_id FROM amitie a1 WHERE a1.membre_source_id=:id) " +
                            "OR m1.id IN " +
                                "(SELECT a1.membre_source_id FROM amitie a1 WHERE a1.membre_cible_id=:id)" +
                            ")" +
                        ")" +
                    "OR m.id IN "+
                        "(SELECT a.membre_cible_id " +
                        "FROM amitie a " +
                        "WHERE a.membre_source_id IN " +
                            "(SELECT m1.id FROM membre m1 " +
                            "WHERE m1.id IN" +
                                "(SELECT a1.membre_cible_id FROM amitie a1 WHERE a1.membre_source_id=:id) " +
                            "OR m1.id IN " +
                                "(SELECT a1.membre_source_id FROM amitie a1 WHERE a1.membre_cible_id=:id)" +
                            ")" +
                        ")" +
                    ")" +
                "AND (m.id NOT IN" +
                        "(SELECT a1.membre_cible_id FROM amitie a1 WHERE a1.membre_source_id=:id) " +
                    "AND m.id NOT IN " +
                        "(SELECT a1.membre_source_id FROM amitie a1 WHERE a1.membre_cible_id=:id)" +
                    ")" +
                ") " +
                "OR (m.id IN " +
                        "(SELECT p.membre_id " +
                        "FROM particulier p " +
                        "WHERE p.id IN " +
                            "(SELECT f.particulier_id " +
                            "FROM formation f " +
                            "WHERE f.id IN " +
                                "(SELECT f1.id " +
                                "FROM formation f1 " +
                                "WHERE f1.etablissement IN " +
                                    "(SELECT f2.etablissement " +
                                    "FROM formation f2 " +
                                    "WHERE f2.id IN " +
                                        "(SELECT f3.id " +
                                        "FROM formation f3 " +
                                        "WHERE f3.particulier_id IN " +
                                            "(SELECT p.id " +
                                            "FROM particulier p " +
                                            "WHERE p.membre_id=:id" +
                                            ")" +
                                        ")" +
                                    ")" +
                                ")" +
                            ")" +
                        ") " +
                    "AND (m.id NOT IN" +
                            "(SELECT a1.membre_cible_id FROM amitie a1 WHERE a1.membre_source_id=:id) " +
                        "AND m.id NOT IN " +
                            "(SELECT a1.membre_source_id FROM amitie a1 WHERE a1.membre_cible_id=:id)" +
                        ")" +
                    ") " +
                "OR (m.id IN " +
                        "(SELECT e.membre_id " +
                        "FROM experience e " +
                        "WHERE LOWER(e.entreprise) IN " +
                            "(SELECT LOWER(e1.entreprise) " +
                            "FROM experience e1 " +
                            "WHERE e1.id IN " +
                                "(SELECT e2.id FROM experience e2 WHERE e2.membre_id=:id)" +
                            ") " +
                            "OR LOWER(e.lieu) IN " +
                                "(SELECT LOWER(e1.lieu) " +
                                "FROM experience e1 " +
                                "WHERE e1.id IN " +
                                    "(SELECT e2.id FROM experience e2 WHERE e2.membre_id=:id)" +
                                ")" +
                        ") " +
                    "AND (m.id NOT IN" +
                            "(SELECT a1.membre_cible_id FROM amitie a1 WHERE a1.membre_source_id=:id) " +
                        "AND m.id NOT IN " +
                            "(SELECT a1.membre_source_id FROM amitie a1 WHERE a1.membre_cible_id=:id)" +
                        ") " +
                    ") " +
                ")" +
                " ORDER BY RAND() LIMIT 10"
        ;

        RawSql rawSql = RawSqlBuilder.parse(sql)
                .create();
        List<Membre> membres = Ebean.find(Membre.class)
                .setRawSql(rawSql)
                .setParameter("id", id)
                .findList();

        return membres;
    }

    /**
     * recuper le status de ce membre
     * @return
     */
    public String getStatus(){
        String status="pas de titre professionnel";
        if(this.getParticulier()!=null){
            Experience exp=Ebean.find(Experience.class).where().eq("membre.id",id).eq("etat",true).findUnique();
            if(exp!=null){
                status=exp.getTitre()+" chez "+exp.getEntreprise();
            }
            else{
                String sql="SELECT type, etablissement FROM formation f WHERE f.particulier_id=:pID AND f.annee_fin =(SELECT MAX(f1.annee_fin) AS annee_fin FROM formation f1 WHERE f1.particulier_id=:pID)";
                RawSql rawSql = RawSqlBuilder.parse(sql)
                        .create();
                List<Formation> formations = Ebean.find(Formation.class)
                        .setRawSql(rawSql)
                        .setParameter("pID", particulier.getId())
                        .findList();
                for(Formation f:formations){
                    status=f.getType()+" "+f.getEtablissement();
                }

            }

        }
        else{
            if(this.getEntreprise().getDomaine()!=null){
                status="Entreprise "+this.getEntreprise().getDomaine();
            }
        }
        return status;
    }

    /**
     * recuperer le nom de profil
     * @return
     */
    public String getNomProfil(){
        String nomProfil="";
        if(this.particulier==null){
            nomProfil=this.getEntreprise().getRaisonSocial();
        }else{
            nomProfil=this.getParticulier().getPrenom()+" "+this.getParticulier().getNom();
        }
        return nomProfil;
    }

    public Image getImageProfil(){
        if(this.getImages().size()!=0){
            for(Image profil:this.getImages()){
                if(profil.getProfil()==true){
                    return profil;
                }
            }
        }
        return null;
    }

    /**
     * Recuperer tous les membres qui ont demandé d'etre mon ami et que je n'ai pas encore accepté
     * @return
     */
    public List<Membre> demandes(){
        String sql="SELECT id FROM membre m WHERE m.id IN (SELECT a.membre_source_id FROM amitie a WHERE a.membre_cible_id=:id AND a.accepte=false) ORDER BY RAND() LIMIT 10";
        RawSql rawSql = RawSqlBuilder.parse(sql)
                .create();
        List<Membre> membres = Ebean.find(Membre.class)
                .setRawSql(rawSql)
                .setParameter("id", id)
                .findList();
        return membres;
    }

    /**
     * envoyer une demande d'ami
     * @param id
     */
    public void envoyerDemande(Long id){
        Membre membre=Ebean.find(Membre.class).where().eq("id",id).findUnique();
        Amitie demande= new Amitie(this,membre);
        demande.save();
    }

    /**
     * Accepter une demande d'ami
     * @param id
     */
    public void accepterDemande(Long id){
        Amitie demande= Ebean.find(Amitie.class).where().eq("membreCible.id",this.id).eq("membreSource.id",id).findUnique() ;
        demande.setAccepte(true);
        demande.update();
    }

    /**
     * Refuser une demande d'ami
     * @param id
     */
    public void refuserDemande(Long id){

        Amitie demande= Ebean.find(Amitie.class).where().eq("mebreSource.id",this.id).eq("mebreSource.id",id).findUnique() ;
        demande.delete();
    }

    /**
     * Recuperons les publications de ces amis qui ne sont ps encore lus
     * @return
     */
    public List<Publication> publicationsNonLues(){
        List<Publication> publications= new ArrayList<Publication>();
        List<Long> idAmis= new ArrayList<Long>();

        for(Amitie macible:this.getAmities()){
            if(macible.isAccepte()){
                idAmis.add(macible.getMembreCible().getId());
            }
        }

        for(Amitie masource:this.getDemandeAmities()){
            if(masource.isAccepte()){
                idAmis.add(masource.getMembreSource().getId());
            }

        }
        publications= Ebean.find(Publication.class)
                .where()
                .or(Expr.eq("membre.id",this.id), Expr.in("membre.id",idAmis))
                .eq("vuePublications.membre.id",this.id)
                .eq("vuePublications.vue",0)
                .orderBy("id desc")
                .findList();
        for(Publication pub:publications){
            VuePublication vp=Ebean.find(VuePublication.class)
                    .where().eq("publication.id",pub.getId())
                    .eq("membre.id",this.id)
                    .findUnique();
            vp.setVue(-1);
            vp.update();
            VueCommentaire.commentaireNonLues(pub,this);
        }
        return publications;
    }

    /**
     * Rrecuperons toutes les publications de ces amis
     * et les marquées toutes lues
     * @return
     */
    public List<Publication> publicationsLues(){
        List<Publication> publications= new ArrayList<Publication>();
        List<Long> idAmis= new ArrayList<Long>();

        for(Amitie macible:this.getAmities()){
            if(macible.isAccepte()){
                idAmis.add(macible.getMembreCible().getId());
            }
        }

        for(Amitie masource:this.getDemandeAmities()){
            if(masource.isAccepte()){
                idAmis.add(masource.getMembreSource().getId());
            }

        }

        publications= Ebean.find(Publication.class)
                .where()
                .or(Expr.eq("membre.id",this.id), Expr.in("membre.id",idAmis))
                .orderBy("id desc")
                .findList();
        for(Publication pub:publications){
            VueCommentaire.commentaireNonLues(pub,this);
        }
        return publications;
    }

    /**
     * Recuperer tous les commentaires de mes amis que je n'ai pas encore vu
     * @return
     */
    public List<Commentaire> commentairesNonLues(){
        List<Commentaire> commentaires= new ArrayList<Commentaire>();
        List<Long> idAmis= new ArrayList<Long>();

        /**
         * Recuperons mes amis
         */
        for(Amitie macible: this.getAmities()){
            if(macible.isAccepte()){
                idAmis.add(macible.getMembreCible().getId());
            }
        }

        for(Amitie masource:this.getDemandeAmities()){
            if(masource.isAccepte()){
                idAmis.add(masource.getMembreSource().getId());
            }

        }
        /**
         * Je recupere les commentaires non vus par ce membre
         */
        commentaires= Ebean.find(Commentaire.class)
                .where()
                .or(Expr.eq("publication.membre.id",this.id), Expr.in("publication.membre.id",idAmis))
                .eq("vueCommentaires.membre.id",this.id)
                .eq("vueCommentaires.vue",0)
                .orderBy("id desc")
                .findList();
        /**
         * On change la vue pour montrer que ce membre a vu ces commentaires
         * mais il ne l'est pas encore lus
         */
        for(Commentaire commentaire:commentaires){
            VueCommentaire vp=Ebean.find(VueCommentaire.class)
                    .where().eq("commentaire.id",commentaire.id)
                    .eq("membre.id",this.getId())
                    .findUnique();
            vp.setVue(-1);
            vp.update();
        }
        return commentaires;
    }

    /**
     *Recuperer mes
     * @return
     */
    public List<Publication> publicationNonVueReelle(){
        return Ebean.find(Publication.class)
                .where()
                .eq("membre.id",this.id)
                .eq("vuePublications.membre.id",this.id)
                .eq("vuePublications.vue",-1)
                .orderBy("id desc")
                .findList();
    }

    /**
     * Recuperons toutes les membres
     * @return
     */
    public static List<Membre> listMembres(){

        return Membre.find.all();
    }

    /**
     * Recuprons les publications qui ont été vu par ce membre et
     * qui ne sont pas encore lues
     * @return
     */
    public List<Publication> publicationNonLueReelle(){
        return Ebean.find(Publication.class)
                .where()
                .eq("vuePublications.membre.id",this.id)
                .eq("vuePublications.vue",-1)
                .orderBy("id desc")
                .findList();
    }

    /**
     * Recupererons les commentaires qui ne sont pas encore lus par ce membre
     * @return
     */
    public List<Commentaire> commentaireNonLueReelle(){
        return Ebean.find(Commentaire.class)
                .where()
                .eq("vueCommentaires.membre.id",this.id)
                .or(Expr.eq("vueCommentaires.vue",-1),Expr.eq("vueCommentaires.vue",0))
                .orderBy("id desc")
                .findList();
    }

    /**
     * Recuperer les membres qui ont demandé d'etre mon amie
     * et ceux qui ont accepté de l'etre
     * @return
     */
    public List<Amitie> demandesEtDemandeAccepter(){
        List<Amitie> amities = Ebean.find(Amitie.class)
                .where()
                .or(Expr.and(Expr.eq("membreSource.id",id),Expr.eq("accepte",true)),Expr.and(Expr.eq("membreCible.id",id),Expr.eq("accepte",false)))
                .findList();
        return amities;
    }
}
