package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Formats.DateTime(pattern="dd/MM/yyyy")
    private Date datePublication = new Date();

    /**
     * L'image associée à la publication
     */
    private String urlImage;

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

    /**
     * Publications non lues par un membre
     * @param m
     * @return
     */
    public static List<Publication> publicationsNonLues(Membre m){
        List<Publication> publications= new ArrayList<Publication>();
        List<Long> idAmis= new ArrayList<Long>();

        for(Amitie macible:m.getAmities()){
            if(macible.accepte==true){
                idAmis.add(macible.membreCible.id);
            }
        }

        for(Amitie masource:m.getDemandeAmities()){
            if(masource.accepte==true){
                idAmis.add(masource.membreSource.id);
            }

        }
        publications= Ebean.find(Publication.class)
                .where()
                .or(Expr.eq("membre.id",m.id), Expr.in("membre.id",idAmis))
                .eq("vuePublications.membre.id",m.id)
                .eq("vuePublications.vue",false)
                .orderBy("id desc")
                .findList();
        for(Publication pub:publications){
            VuePublication vp=Ebean.find(VuePublication.class)
                    .where().eq("publication.id",pub.id)
                    .eq("membre.id",m.id)
                    .findUnique();
            vp.vue=true;
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
            if(macible.accepte==true){
                idAmis.add(macible.membreCible.id);
            }
        }

        for(Amitie masource:m.getDemandeAmities()){
            if(masource.accepte==true){
                idAmis.add(masource.membreSource.id);
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
     * @param publication
     */
    public static void creerNewPublication(Publication publication){
        publication.save();
        List<Membre> membres=Membre.find.all();
        for(Membre m:membres){
            VuePublication.CreerNewVuePulication(m,publication);
        }
        VuePublication vp=Ebean.find(VuePublication.class)
                .where().eq("publication.id",publication.id)
                .eq("membre.id",publication.membre.id)
                .findUnique();
        vp.vue=true;
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
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Model.Finder<Long, Publication> find = new Model.Finder<Long,Publication>(Publication.class);

}
