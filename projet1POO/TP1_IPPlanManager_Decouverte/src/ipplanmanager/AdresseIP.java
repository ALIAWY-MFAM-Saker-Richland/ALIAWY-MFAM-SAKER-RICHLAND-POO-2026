package ipplanmanager;

public class AdresseIP {
    String valeur;

    public AdresseIP(String valeur) {
        this.valeur = valeur;
    }

    public void afficher() {
        System.out.println("Adresse IP : " + valeur);
    }
}
