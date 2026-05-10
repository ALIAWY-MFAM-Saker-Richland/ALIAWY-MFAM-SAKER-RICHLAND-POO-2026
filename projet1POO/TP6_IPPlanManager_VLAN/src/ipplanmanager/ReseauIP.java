package ipplanmanager;

public class ReseauIP {

    private AdresseIP adresseReseau;
    private int prefixe;

    public ReseauIP(String adresse, int prefixe) {

        this.adresseReseau = new AdresseIP(adresse);
        setPrefixe(prefixe);
    }

    public AdresseIP getAdresseReseau() {
        return adresseReseau;
    }

    public int getPrefixe() {
        return prefixe;
    }

    public void setPrefixe(int prefixe) {

        if (prefixe < 0 || prefixe > 32) {
            this.prefixe = 24;
        } else {
            this.prefixe = prefixe;
        }
    }

    public String getMasque() {

        int masque = 0xffffffff << (32 - prefixe);

        int octet1 = (masque >>> 24) & 255;
        int octet2 = (masque >>> 16) & 255;
        int octet3 = (masque >>> 8) & 255;
        int octet4 = masque & 255;

        return octet1 + "."
                + octet2 + "."
                + octet3 + "."
                + octet4;
    }

    public void afficher() {

        System.out.println(
                "Réseau : "
                + adresseReseau.getAdresse()
                + "/"
                + prefixe);

        System.out.println(
                "Masque : "
                + getMasque());
    }
}