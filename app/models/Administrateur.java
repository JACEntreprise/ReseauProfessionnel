package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import play.data.validation.Constraints;
import repository.MembreRepository;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by julio on 19/06/2016.
 */
@Entity
@DiscriminatorValue("admin")
public class Administrateur extends Membre{
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
        MembreRepository repository = MembreRepository.instance;
        motDePasse = repository.hash(motDePasse);
        salt = repository.getSalt();

        this.save();

    }

    /**
     * trouver un administrateur par son email
     */
    public static Administrateur byEmail(String email){
        Administrateur admin;
        admin = Ebean.find(Administrateur.class).where().eq("email",email).findUnique();

        return admin;
    }
}