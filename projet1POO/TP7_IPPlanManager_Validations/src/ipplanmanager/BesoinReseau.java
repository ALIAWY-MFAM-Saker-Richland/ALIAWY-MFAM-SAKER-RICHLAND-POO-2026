package ipplanmanager;

public class BesoinReseau {
    private String nom;
    private int hotes;

    public BesoinReseau(String nom, int hotes) {
        this.nom = nom;
        this.hotes = hotes;
    }

    public String getNom() { return nom; }
    public int getHotes() { return hotes; }
}