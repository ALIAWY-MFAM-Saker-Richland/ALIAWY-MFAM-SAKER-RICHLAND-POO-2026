package ipplanmanager;

/**
 * Classe InterfaceReseau
 * Représente une interface réseau d'un équipement.
 * Contient un nom (ex: eth0), une adresse IP et un état actif/inactif.
 * Reprise du TP4 sans modification.
 */
public class InterfaceReseau {

    private String     nom;
    private AdresseIP  adresseIP;
    private boolean    active;

    // ── Constructeur ──────────────────────────────────────────────────────
    public InterfaceReseau(String nom, AdresseIP adresseIP) {
        setNom(nom);
        this.adresseIP = adresseIP;
        this.active    = false;
    }

    // ── Getters / Setters ─────────────────────────────────────────────────
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = (nom == null || nom.isEmpty()) ? "interface_inconnue" : nom;
    }

    public AdresseIP getAdresseIP() {
        return adresseIP;
    }

    public void setAdresseIP(AdresseIP adresseIP) {
        this.adresseIP = adresseIP;
    }

    public boolean isActive() {
        return active;
    }

    // ── Activation / Désactivation ────────────────────────────────────────
    public void activer() {
        this.active = true;
        System.out.println("Interface " + nom + " activée.");
    }

    public void desactiver() {
        this.active = false;
        System.out.println("Interface " + nom + " désactivée.");
    }

    // ── Affichage ─────────────────────────────────────────────────────────
    public void afficher() {
        System.out.println("    Interface : " + nom);
        System.out.println("    État      : " + (active ? "Active" : "Inactive"));
        if (adresseIP != null) {
            System.out.print("    ");
            adresseIP.afficher();
        }
    }
}
