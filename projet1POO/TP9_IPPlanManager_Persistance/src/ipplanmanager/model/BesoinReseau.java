package ipplanmanager.model;

public class BesoinReseau {

    private String nom;
    private int nombreHotes;

    public BesoinReseau(String nom, int nombreHotes) {
        this.nom = nom;
        this.nombreHotes = nombreHotes;
    }

    public String getNom() {
        return nom;
    }

    public int getNombreHotes() {
        return nombreHotes;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNombreHotes(int nombreHotes) {
        this.nombreHotes = nombreHotes;
    }

    @Override
    public String toString() {
        return nom + " (" + nombreHotes + " hôtes)";
    }
}