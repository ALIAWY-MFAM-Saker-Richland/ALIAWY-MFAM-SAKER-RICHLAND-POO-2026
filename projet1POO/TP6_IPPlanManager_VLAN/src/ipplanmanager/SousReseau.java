package ipplanmanager;

public class SousReseau {

    private String nom;
    private ReseauIP reseau;

    public SousReseau(String nom, ReseauIP reseau) {

        setNom(nom);
        this.reseau = reseau;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {

        if (nom == null || nom.isEmpty()) {
            this.nom = "Sous-Reseau";
        } else {
            this.nom = nom;
        }
    }

    public ReseauIP getReseau() {
        return reseau;
    }

    public void afficher() {

        System.out.println(
                "Sous-réseau : " + nom);

        reseau.afficher();
    }
}