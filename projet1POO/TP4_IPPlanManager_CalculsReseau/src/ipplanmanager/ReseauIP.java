package ipplanmanager;

public class ReseauIP {

    private String adresseReseau;
    private int masqueCidr;
    private String description;

    public ReseauIP(String adresseReseau, int masqueCidr, String description) {
        setAdresseReseau(adresseReseau);
        setMasqueCidr(masqueCidr);
        setDescription(description);
    }

    public String getAdresseReseau() { return adresseReseau; }

    public void setAdresseReseau(String adresseReseau) {
        this.adresseReseau = (adresseReseau == null || adresseReseau.isEmpty())
                ? "0.0.0.0" : adresseReseau;
    }

    public int getMasqueCidr() { return masqueCidr; }

    public void setMasqueCidr(int masqueCidr) {
        if (masqueCidr < 0 || masqueCidr > 32) {
            this.masqueCidr = 24;
            System.out.println("Masque invalide → valeur par défaut /24 appliquée.");
        } else {
            this.masqueCidr = masqueCidr;
        }
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = (description == null || description.isEmpty())
                ? "Réseau sans description" : description;
    }

    // ── Méthode afficher() enrichie (section 10) ──────────────────────────
    public void afficher() {
        System.out.println("  Réseau          : " + adresseReseau + "/" + masqueCidr);
        System.out.println("  Description     : " + description);
        System.out.println("  Classe réseau   : "
                + CalculateurReseau.obtenirClasseReseau(adresseReseau));
        System.out.println("  Masque décimal  : "
                + CalculateurReseau.obtenirMasqueDecimal(masqueCidr));
        System.out.println("  Capacité max.   : "
                + CalculateurReseau.calculerNombreHotes(masqueCidr) + " hôtes");
        System.out.println("  Réseau privé    : "
                + (CalculateurReseau.estReseauPrive(adresseReseau) ? "Oui" : "Non"));
    }
}
