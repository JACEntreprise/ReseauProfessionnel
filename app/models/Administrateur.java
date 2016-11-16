package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import play.data.validation.Constraints;
import repository.MembreRepository;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by julio on 19/06/2016.
 */
@Entity
public class Administrateur extends Model{
    @Id
    private Long id;

    @Constraints.Required
    private String prenom;

    @Constraints.Required
    private String nom;

    //le finder de la classe
    public static Model.Finder<Long,Administrateur> find = new Model.Finder<>(Administrateur.class);

    //constructeur par défaut
    public Administrateur(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    /**
     * Relation d'heritage entre Administrateur et Membre
     * Un administrateur est un membre
     */
    @Column(nullable = false)
    @OneToOne
    private Membre membre;

    /**
     * liste de tous les administrateurs
     * @return
     */
    public static List<Administrateur> listAdministrateurs(){

        return Ebean.find(Administrateur.class).findList();
    }

    /**
     * ajouter un nouvel administrateur
     */
    public void ajouter(){
        membre.ajouter();
        this.save();
    }

    /**
     * trouver un administrateur par son email
     */
    public static Administrateur byEmail(String email){
        Membre membre;
        Administrateur admin;
        membre = Membre.byEmail(email);

        if(membre == null){
            return null;
        }

        String req = "Select id from administrateur a where a.membre_id =:id";

        RawSql rawSql = RawSqlBuilder.parse(req).create();
        admin = Ebean.find(Administrateur.class)
                .setRawSql(rawSql)
                .setParameter("id", membre.getId())
                .findUnique();

        return admin;
    }
}
