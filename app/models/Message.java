package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * Entite Message
 */

@Entity
public class Message extends Model {
    /**
     * Identifiant de message
     */
    @Id
    public long id;

    /**
     * le contenu du message
     */
    @Constraints.Required
    public String contenu;

    /**
     * Relation entre Message et Membre(envoi)
     * plusieurs messages sont envoyés par un membre
     */
    @ManyToOne
    public Membre expediteur;

    /**
     * Relation entre Message et Membre(reception)
     * plusieurs messages sont reçus par un membre
     */
    @ManyToOne
    public Membre destinataire;

    /**
     * Constructeur par defaut
     */
    public Message() {
    }

    /**
     * finder permettant d'accedant aux donnees de l'entite
     */
    public static Finder<Long, Message> find = new Finder<Long,Message>(Message.class);
}
