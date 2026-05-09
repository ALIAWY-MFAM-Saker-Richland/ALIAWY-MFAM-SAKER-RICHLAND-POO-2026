package ipplanmanager;

public class InterfaceReseau {
    String nom;
    AdresseIP adresseIP; // Utilisation de la classe AdresseIP
    boolean active;

    public InterfaceReseau(String nom, AdresseIP adresseIP) {
        this.nom = nom;
        this.adresseIP = adresseIP;
        this.active = false; // Désactivée par défaut
    }

    public void activer() { active = true; }
    public void desactiver() { active = false; }

    public void afficher() {
        System.out.println("Interface : " + nom);
        if (adresseIP != null) {
            adresseIP.afficher();
        } else {
            System.out.println("Adresse IP : non configurée");
        }
        System.out.println("État : " + (active ? "active" : "inactive"));
    }
}
