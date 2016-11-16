package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Model;
import org.jetbrains.annotations.Contract;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * L'entité Publication
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="type")
@DiscriminatorValue("publication")
public class Publication extends Model{
    /**
     * Identifiant de publication
     */
    @Id
    private Long id;

    /**
     * Le titre de la publication
     */

    private String titre;

    /**
     * le contenu de la publication
     */
    @Constraints.Required
    @Lob
    private String contenu;

    /**
     * La date de la publication
     */
    @Formats.DateTime(pattern="dd.MM.yyyy HH:mm:ss")
    private Date datePublication;

    /**
     * L'image associée à la publication
     */
    private String urlImage;

    /**
     * L'image associée à la publication
     */
    private String nomImage;

    /**
     * Relation entre publication et Commentaire
     * Une privateation est associée à plusieurs commentaires
     */
    @OneToMany(mappedBy = "publication")
    private List<Commentaire> commentaires;

    /**
     * Relation entre publication et Membre
     * plusieurs publications sont associées à un seul membre
     */
    @ManyToOne
    private Membre membre;

    /**
     * Relation entre publication et VuePublication
     * Une publication peut etre vue par plusieurs membres
     */
    @OneToMany(mappedBy = "publication")
    private List<VuePublication> vuePublications;

    /**
     * Constructeur par defaut
     */
    public Publication() {
        datePublication = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String nvd = dt.format(datePublication);
        try {
            datePublication = dt.parse(nvd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Publication(String titre, String contenu, String urlImage,String nomImage, Membre membre) {
        this.titre = titre;
        this.contenu = contenu;
        this.urlImage = urlImage;
        this.membre = membre;
        this.nomImage = nomImage;
        this.datePublication = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String nvd = dt.format(this.datePublication);
        try {
            this.datePublication = dt.parse(nvd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public List<VuePublication> getVuePublications() {
        return vuePublications;
    }

    public void setVuePublications(List<VuePublication> vuePublications) {
        this.vuePublications = vuePublications;
    }

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    /**
     * Publications non lues par un membre
     * @param m
     * @return
     */
    public static List<Publication> publicationsNonLues(Membre m){
        List<Publication> publications= new ArrayList<Publication>();
        List<Long> idAmis= new ArrayList<Long>();

        for(Amitie macible:m.getAmities()){
            if(macible.isAccepte()){
                idAmis.add(macible.getMembreCible().getId());
            }
        }

        for(Amitie masource:m.getDemandeAmities()){
            if(masource.isAccepte()){
                idAmis.add(masource.getMembreSource().getId());
            }

        }
        publications= Ebean.find(Publication.class)
                .where()
                .or(Expr.eq("membre.id",m.id), Expr.in("membre.id",idAmis))
                .eq("vuePublications.membre.id",m.id)
                .eq("vuePublications.vue",0)
                .orderBy("id desc")
                .findList();
        for(Publication pub:publications){
            VuePublication vp=Ebean.find(VuePublication.class)
                    .where().eq("publication.id",pub.id)
                    .eq("membre.id",m.id)
                    .findUnique();
            vp.setVue(-1);
            vp.update();
        }
        return publications;
    }

    /**
     * Publications lues par un membre
     * @param m
     * @return
     */
    public static List<Publication> publicationsLues(Membre m){
        List<Publication> publications= new ArrayList<Publication>();
        List<Long> idAmis= new ArrayList<Long>();

        for(Amitie macible:m.getAmities()){
            if(macible.isAccepte()){
                idAmis.add(macible.getMembreCible().getId());
            }
        }

        for(Amitie masource:m.getDemandeAmities()){
            if(masource.isAccepte()){
                idAmis.add(masource.getMembreSource().getId());
            }

        }

        publications= Ebean.find(Publication.class)
                .where()
                .or(Expr.eq("membre.id",m.id), Expr.in("membre.id",idAmis))
                .orderBy("id desc")
                .findList();
        return publications;
    }

    /**
     * creer une nouvelle publication
     * @param
     */
    public static void creerNewPublication(String titre, String contenu, String urlImage,String nomImage, Membre membre){
        Publication publication= new Publication(titre,contenu,urlImage,nomImage,membre);
        publication.save();
        List<Membre> membres=Membre.find.all();
        for(Membre m:membres){
            VuePublication.CreerNewVuePulication(m,publication);
        }
        VuePublication vp= Ebean.find(VuePublication.class).where().eq("membre.id",membre.getId()).eq("publication.id",publication.getId()).findUnique();
        vp.setVue(1);
        vp.update();
    }

    /**
     * modifier une publication
     * @param publication
     */
    public void modifier(Publication publication){
        this.contenu = publication.getContenu();
        this.titre = publication.getTitre();
        this.urlImage = publication.getUrlImage();
    }

    /**
     * Recuperons la duree de la publication
     * @return
     */
    public Long datePubEnJour(){
        Date now=new Date();
        return (now.getTime()-this.datePublication.getTime())/1000/60/60/24;
    }

    /**
     * Recuperer la duree de la publication
     * @return
     */
    public String getDatePub(){
        String pubDate="";
        /**
         * On recupere la date d'aujourd'hui
         */
        Date now=new Date();

        /**
         * On recupere la duree en jour
         */
        Long dureeEnSeconde=(now.getTime()-this.datePublication.getTime())/1000/60/60/24;
        if(dureeEnSeconde/360>=1){
            pubDate="publié il y a "+ dureeEnSeconde/360 + " an(s)";
        }
        else{
            if(dureeEnSeconde/30>=1){
                pubDate="publié il y a "+ dureeEnSeconde/30 + " moi(s)";
            }
            else{
                if(dureeEnSeconde>=2){
                    pubDate="publié il y a "+ dureeEnSeconde + " jours";
                }
                else{
                    if(dureeEnSeconde==1){
                        pubDate="publié hier";
                    }
                    else{
                        pubDate="publié aujourd'hui";
                    }
                }
            }
        }
        return pubDate;
    }

    /**
     * Recuperer une publication connaissant son identifiant
     * @param id
     * @return
     */
    public static Publication getPublication(Long id){
        if(id==null || id==0){
            return null;
        }
        return Publication.find.byId(id);
    }

    /**
     * Recuperer les 5 derniers commentaires d'une publication
     * @return
     */
    public List<Commentaire> listeCommentaires(){
         List<Commentaire> commentaires=Ebean.find(Commentaire.class)
                .where()
                .eq("publication.id",id)
                .orderBy("id desc")
                .setMaxRows(5)
                .findList();
        Collections.reverse(commentaires);
        return commentaires;
    }

    /**
     * recuperer les commentaires d'une publication non vus par ce membre
     * @param m
     * @return
     */
    public List<VueCommentaire> listeCommentairesNonDeLaPub(Membre m){
        List<VueCommentaire> commentaires=Ebean.find(VueCommentaire.class)
                .where()
                .eq("commentaire.publication.id",id)
                .eq("membre.id",m.getId())
                .eq("vue",0)
                .findList();
        return commentaires;
    }

    /**
     * lire toutes les commentaires d'une publication que ce membre n'a pas encore lu
     * @param m
     */
    public void lueToutesLesCommentairesNonLueDeLaPub(Membre m){
        /**
         * recuperos toutes les commentaires de la publication non vu par ce membre
         */
        List<VueCommentaire> commentaires=Ebean.find(VueCommentaire.class)
                .where()
                .eq("commentaire.publication.id",id)
                .eq("membre.id",m.getId())
                .or(Expr.eq("vue",-1),Expr.eq("vue",0))
                .findList();
        /**
         * maintenant on les parcourt tous et on change leurs etats
         */
        for(VueCommentaire vc:commentaires){
            vc.setVue(1);
            vc.update();
        }
    }

    /**
     * Resumer d'une publication
     * @return
     */
    public String getContenuPub(){
        if(this.getContenu().length()>400){
            return this.getContenu().substring(0,399);
        }
        return this.getContenu();
    }


    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Model.Finder<Long, Publication> find = new Model.Finder<Long,Publication>(Publication.class);

}
