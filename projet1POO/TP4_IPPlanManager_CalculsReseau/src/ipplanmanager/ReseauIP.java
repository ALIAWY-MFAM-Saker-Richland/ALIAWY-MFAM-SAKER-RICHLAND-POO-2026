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

    public String getAdresseReseau() {
        return adresseReseau;
    }

    public void setAdresseReseau(String adresseReseau) {
        if (adresseReseau == null || adresseReseau.isEmpty()) {
            this.adresseReseau = "0.0.0.0";
        } else {
            this.adresseReseau = adresseReseau;
        }
    }

    public int getMasqueCidr() {
        return masqueCidr;
    }

    public void setMasqueCidr(int masqueCidr) {
        if (masqueCidr < 0 || masqueCidr > 32) {
            this.masqueCidr = 24;
        } else {
            this.masqueCidr = masqueCidr;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            this.description = "Aucune description";
        } else {
            this.description = description;
        }
    }

    public void afficher() {
        System.out.println("Réseau : " + adresseReseau + "/" + masqueCidr);
        System.out.println("Description : " + description);

        System.out.println("Classe réseau : "
                + CalculateurReseau.obtenirClasseReseau(adresseReseau));

        System.out.println("Masque décimal : "
                + CalculateurReseau.obtenirMasqueDecimal(masqueCidr));

        System.out.println("Capacité maximale : "
                + CalculateurReseau.calculerNombreHotes(masqueCidr)
                + " hôtes");

        System.out.println("Réseau privé : "
                + CalculateurReseau.estReseauPrive(adresseReseau));
    }
}