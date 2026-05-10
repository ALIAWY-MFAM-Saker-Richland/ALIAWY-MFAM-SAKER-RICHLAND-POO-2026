package ipplanmanager;

/**
 * Classe AdresseIP
 * Représente une adresse IPv4 avec validation des octets.
 * Reprise du TP4 sans modification.
 */
public class AdresseIP {

    private String adresse;

    // ── Constructeur ──────────────────────────────────────────────────────
    public AdresseIP(String adresse) {
        setAdresse(adresse);
    }

    // ── Getter / Setter ───────────────────────────────────────────────────
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        if (adresse == null || adresse.isEmpty()) {
            this.adresse = "0.0.0.0";
        } else if (estValide(adresse)) {
            this.adresse = adresse;
        } else {
            this.adresse = "0.0.0.0";
            System.out.println("Adresse IP invalide : " + adresse
                    + " → valeur par défaut appliquée.");
        }
    }

    // ── Validation ────────────────────────────────────────────────────────
    private boolean estValide(String adresse) {
        String[] octets = adresse.split("\\.");
        if (octets.length != 4) {
            return false;
        }
        for (String octet : octets) {
            try {
                int valeur = Integer.parseInt(octet);
                if (valeur < 0 || valeur > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    // ── Affichage ─────────────────────────────────────────────────────────
    public void afficher() {
        System.out.println("Adresse IP : " + adresse);
    }
}
