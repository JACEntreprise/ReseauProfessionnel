package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import play.data.validation.Constraints;

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

    private Date dateCreation;

    /**
     * Relation d'heritage entre Administrateur et Membre
     * Un administrateur est un membre
     */
    @Column(nullable = false)
    @OneToOne
    private Membre membre;

    //le finder de la classe
    public static Model.Finder<Long,Administrateur> find = new Model.Finder<>(Administrateur.class);

    //constructeur par défaut
    public Administrateur(){
        dateCreation = new Date();//la date systme
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }


    /**
     * vérifie s'il existe un administrateur dans la base de données
     * @return
     */
    public static List<Administrateur> listAdministrateurs(){

        return Ebean.find(Administrateur.class).findList();
    }

}
