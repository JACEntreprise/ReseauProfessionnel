package controllers;

import repository.MembreRepository;

/**
 * Created by brick on 06/06/2016.
 */
public class FormulaireInscription {
    private String email;
    private String motDePasse;
    private String confirmation;
    private final MembreRepository repository;

    public FormulaireInscription() {
        repository = MembreRepository.instance;
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

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

}
