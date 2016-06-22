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
public class Publication extends Model{
    /**
     * Identifiant de publication
     */
    @Id
    public Long id;

    /**
     * Le titre de la publication
     */

    public String titre;

    /**
     * le contenu de la publication
     */
    @Constraints.Required
    public String contenu;

    /**
     * La date de la publication
     */
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date dueDate = new Date();

    /**
     * L'image associée à la publication
     */
    public String urlImage;

    /**
     * Relation entre Publication et Commentaire
     * Une publication est associée à plusieurs commentaires
     */
    @OneToMany(mappedBy = "publication")
    public List<Commentaire> commentaires;

    /**
     * Relation entre Publication et Membre
     * plusieurs publications sont associées à un seul membre
     */
    @ManyToOne
    public Membre membre;

    /**
     * Relation entre Publication et VuePublication
     * Une publication peut etre vue par plusieurs membres
     */
    @OneToMany(mappedBy = "publication")
    public List<VuePublication> vuePublications;

    /**
     * Constructeur par defaut
     */
    public Publication() {
    }

    /**
     * Publications non lues par un membre
     * @param m
     * @return
     */
    public static List<Publication> publicationsNonLues(Membre m){
        List<Publication> publications= new ArrayList<Publication>();
        List<Long> idAmis= new ArrayList<Long>();

        for(Amitie macible:m.amities){
            if(macible.accepte==true){
                idAmis.add(macible.membreCible.id);
            }
        }

        for(Amitie masource:m.demandeAmities){
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

        for(Amitie macible:m.amities){
            if(macible.accepte==true){
                idAmis.add(macible.membreCible.id);
            }
        }

        for(Amitie masource:m.demandeAmities){
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
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Model.Finder<Long, Publication> find = new Model.Finder<Long,Publication>(Publication.class);

}
