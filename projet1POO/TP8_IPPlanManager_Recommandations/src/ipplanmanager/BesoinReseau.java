package ipplanmanager;

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
}