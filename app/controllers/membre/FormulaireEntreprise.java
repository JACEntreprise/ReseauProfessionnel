package controllers.membre;

import controllers.FormulaireInscription;
import models.Membre;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brick on 06/06/2016.
 */
public class FormulaireEntreprise extends Formulaire {
    private String domaine;
    private String jour;
    private String moi;
    private String annee;

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getMoi() {
        return moi;
    }

    public void setMoi(String moi) {
        this.moi = moi;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
}
