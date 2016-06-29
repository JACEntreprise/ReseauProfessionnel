package controllers.cv;

/**
 * Created by brick on 29/06/2016.
 */
public class FormulaireExperience {
    private String entreprise;
    private String lieu;
    private String titre;
    private String moiDebut;
    private String moiFin;
    private String anneDebut;
    private String anneFin;
    private Boolean etat;

    public FormulaireExperience() {
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMoiDebut() {
        return moiDebut;
    }

    public void setMoiDebut(String moiDebut) {
        this.moiDebut = moiDebut;
    }

    public String getMoiFin() {
        return moiFin;
    }

    public void setMoiFin(String moiFin) {
        this.moiFin = moiFin;
    }

    public String getAnneDebut() {
        return anneDebut;
    }

    public void setAnneDebut(String anneDebut) {
        this.anneDebut = anneDebut;
    }

    public String getAnneFin() {
        return anneFin;
    }

    public void setAnneFin(String anneFin) {
        this.anneFin = anneFin;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
}
