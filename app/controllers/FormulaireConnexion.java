package controllers;

import models.Membre;
import org.mindrot.jbcrypt.BCrypt;
import repository.MembreRepository;

/**
 * Created by brick on 02/06/2016.
 */
public class FormulaireConnexion {
    private String email;
    private String motDePasse;
    private final MembreRepository repository;

    public FormulaireConnexion() {
            repository = MembreRepository.instance;
    }

    public String validate(){
        if(Membre.authenticate(email,motDePasse)==null){
            return "Email ou mot de passe incorrect";
        }
        return null;
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
