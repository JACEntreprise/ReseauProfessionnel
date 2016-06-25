package controllers;

import models.Administrateur;
import models.Membre;

/**
 * Created by brick on 02/06/2016.
 */
public class FormulaireConnexionAdmin extends FormulaireConnexion {

    public FormulaireConnexionAdmin() {
        super();
    }

    public String validate(){
        Membre membre = Administrateur.byEmail(this.email);
        if(membre != null){
            if(Membre.authenticate(email,motDePasse)==null){
                return "Email ou mot de passe incorrect";
            }
            return null;
        }
        return "Vous devez être administrateur pour vous connecter!";
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
}
