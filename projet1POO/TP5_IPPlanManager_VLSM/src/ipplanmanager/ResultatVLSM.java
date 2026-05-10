package ipplanmanager;

public class ResultatVLSM {

    private String nomBesoin;
    private String adresseReseau;
    private int cidr;
    private String masqueDecimal;
    private int capacite;

    // Nouveaux attributs
    private String premiereAdresseUtilisable;
    private String derniereAdresseUtilisable;

    // Constructeur complet
    public ResultatVLSM(String nomBesoin,
                        String adresseReseau,
                        int cidr,
                        String masqueDecimal,
                        int capacite,
                        String premiereAdresseUtilisable,
                        String derniereAdresseUtilisable) {

        this.nomBesoin = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.cidr = cidr;
        this.masqueDecimal = masqueDecimal;
        this.capacite = capacite;

        this.premiereAdresseUtilisable =
                premiereAdresseUtilisable;

        this.derniereAdresseUtilisable =
                derniereAdresseUtilisable;
    }

    // Getters

    public String getNomBesoin() {
        return nomBesoin;
    }

    public String getAdresseReseau() {
        return adresseReseau;
    }

    public int getCidr() {
        return cidr;
    }

    public String getMasqueDecimal() {
        return masqueDecimal;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getPremiereAdresseUtilisable() {
        return premiereAdresseUtilisable;
    }

    public String getDerniereAdresseUtilisable() {
        return derniereAdresseUtilisable;
    }

    // Méthode d'affichage

    public void afficher() {

        System.out.println(
                nomBesoin +
                " -> " +
                adresseReseau +
                "/" +
                cidr +
                " | Plage : " +
                premiereAdresseUtilisable +
                " - " +
                derniereAdresseUtilisable +
                " | Masque : " +
                masqueDecimal +
                " | Capacite : " +
                capacite +
                " hotes"
        );
    }
}