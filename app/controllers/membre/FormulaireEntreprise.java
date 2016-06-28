package controllers.membre;

import controllers.FormulaireInscription;
import models.Membre;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brick on 06/06/2016.
 */
public class FormulaireEntreprise extends FormulaireInscription {
    private String raisonSocial;

    public FormulaireEntreprise() {
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

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }
}
