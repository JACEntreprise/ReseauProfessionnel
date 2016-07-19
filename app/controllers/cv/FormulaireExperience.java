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
    private Long anneeDebut;
    private Long anneeFin;
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

    public Long getAnneeDebut() {
        return anneeDebut;
    }

    public void setAnneeDebut(Long anneeDebut) {
        this.anneeDebut = anneeDebut;
    }

    public Long getAnneeFin() {
        return anneeFin;
    }

    public void setAnneeFin(Long anneeFin) {
        this.anneeFin = anneeFin;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
}
