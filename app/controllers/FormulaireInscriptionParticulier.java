package controllers;

import models.Membre;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brick on 06/06/2016.
 */
public class FormulaireInscriptionParticulier extends FormulaireInscription {
    private String nom;
    private String prenom;
    private String dateDeNaissance;
    private String lieuDeNaissance;

    public FormulaireInscriptionParticulier() {
    }

    /**
     * Validation formulaire
     * @return
     */
    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<ValidationError>();
        if(Membre.byEmail(super.getEmail())!=null){
            errors.add(new ValidationError("email", "cet email est dejà utilisé."));
        }
        if(super.getMotDePasse().length()<8){
            errors.add(new ValidationError("motDePasse", "le mot de passe doit contenir au moins 8 caracteres."));
        }
        if(!super.getMotDePasse().equals(super.getConfirmation())){
            errors.add(new ValidationError("confirmation", "mot de passe invalide."));
        }
        return errors.isEmpty() ? null : errors;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }
}
