package ipplanmanager;

public class ResultatVLSM {

    private String nomBesoin;
    private String adresseReseau;
    private String masque;
    private int prefixe;
    private int capacite;
    private int nombreHotesDemandes;

    public ResultatVLSM(
            String nomBesoin,
            String adresseReseau,
            String masque,
            int prefixe,
            int capacite,
            int nombreHotesDemandes) {

        this.nomBesoin = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.masque = masque;
        this.prefixe = prefixe;
        this.capacite = capacite;
        this.nombreHotesDemandes = nombreHotesDemandes;
    }

    public String getNomBesoin() {
        return nomBesoin;
    }

    public String getAdresseReseau() {
        return adresseReseau;
    }

    public String getMasque() {
        return masque;
    }

    public int getPrefixe() {
        return prefixe;
    }

    public int getCapacite() {
        return capacite;
    }

    public int getNombreHotesDemandes() {
        return nombreHotesDemandes;
    }

    public void afficher() {

        System.out.println(
                nomBesoin
                + " -> "
                + adresseReseau
                + "/"
                + prefixe
                + " | Masque : "
                + masque
                + " | Capacite : "
                + capacite
                + " hotes"
        );
    }
}