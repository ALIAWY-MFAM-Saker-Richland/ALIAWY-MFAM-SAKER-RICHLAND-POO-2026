package ipplanmanager;

public class BesoinReseau {

    private String nom;
    private int nombreHotes;

    public BesoinReseau(String nom, int nombreHotes) {
        setNom(nom);
        setNombreHotes(nombreHotes);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            this.nom = "besoin_inconnu";
        } else {
            this.nom = nom;
        }
    }

    public int getNombreHotes() {
        return nombreHotes;
    }

    public void setNombreHotes(int nombreHotes) {
        if (nombreHotes <= 0) {
            this.nombreHotes = 1;
        } else {
            this.nombreHotes = nombreHotes;
        }
    }

    public void afficher() {
        System.out.println("Besoin : " + nom +
                " | Hotes demandes : " + nombreHotes);
    }
}