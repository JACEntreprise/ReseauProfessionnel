package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * Entité Amitié
 */

@Entity
public class VueCommentaire extends Model {
    @Id
    public long id;

    /**
     * plusieurs vues pour un membre
     */
    @ManyToOne
    public Membre membre;


    /**
     * plusieurs vues pour pour plusieurs commentaires
     */
    @ManyToOne
    public Commentaire commentaire;

    /**
     * Attribut qui permet de connaitre les amis acceptés
     */
    public int vue;

    /**
     * Attribut qui permet de connaitre les amis acceptés
     */
    public int jaime;


    public VueCommentaire() {
        this.vue=0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Commentaire getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
    }

    public int getVue() {
        return vue;
    }

    public void setVue(int vue) {
        this.vue = vue;
    }

    public int getJaime() {
        return jaime;
    }

    public void setJaime(int jaime) {
        this.jaime = jaime;
    }

    public VueCommentaire(Membre membre, Commentaire commentaire) {
        this.membre = membre;
        this.commentaire = commentaire;
        this.vue=0;
        this.jaime=0;
    }

    public static void creerNewVueCommentaire(Membre m, Commentaire commentaire){
        VueCommentaire vc= new VueCommentaire(m,commentaire);
        vc.save();
    }

    public static void commentaireNonLues(Publication publication, Membre membre){
        List<VueCommentaire> vueCommentaires= Ebean.find(VueCommentaire.class)
                .where()
                .eq("commentaire.publication.id",publication.getId())
                .eq("membre.id",membre.getId())
                .eq("vue",0)
                .findList();
        for(VueCommentaire vueCommentaire:vueCommentaires){
            vueCommentaire.setVue(-1);
            vueCommentaire.update();
        }
    }

    public static Finder<Long, VueCommentaire> find= new Finder<Long, VueCommentaire>(VueCommentaire.class);
}
