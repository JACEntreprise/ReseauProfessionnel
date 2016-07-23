package controllers.membre;

import repository.MembreRepository;

/**
 * Created by brick on 06/06/2016.
 */
public class Formulaire {
    private String adresse;
    private String telephone;
    private String siteweb;

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }
}
