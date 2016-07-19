package controllers.cv;

/**
 * Created by brick on 29/06/2016.
 */
public class FormulaireFormation {
    private String type;
    private String diplome;
    private String etablissement;
    private String resultat;
    private Long anneeDebut;
    private Long anneeFin;

    public FormulaireFormation() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
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
}
