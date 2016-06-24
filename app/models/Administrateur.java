package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by julio on 19/06/2016.
 */
@Entity
public class Administrateur extends Model{
    @Id
    public Long id;

    @Constraints.Required
    public String prenom;

    @Constraints.Required
    public String nom;

    public Date dateCreation;

    /**
     * Relation d'heritage entre Administrateur et Membre
     * Un administrateur est un membre
     */
    @Column(nullable = false)
    @OneToOne
    public Membre membre;

    //le finder de la classe
    public static Finder<String, Administrateur> find = new Finder<String,Administrateur>(Administrateur.class);

    //constructeur par défaut
    public Administrateur(){
        dateCreation = new Date();//la date systme
    }

}
