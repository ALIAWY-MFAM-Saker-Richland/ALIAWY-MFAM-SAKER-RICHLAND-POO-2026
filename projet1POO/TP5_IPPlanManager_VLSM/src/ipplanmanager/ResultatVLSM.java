package ipplanmanager;

/**
 * Classe ResultatVLSM
 * NOUVELLE CLASSE TP5
 *
 * Représente le résultat produit par le moteur VLSM pour un besoin donné.
 * Exemple : pour ADMIN (50 hôtes) → 192.168.1.128/26, masque 255.255.255.192,
 * plage 192.168.1.129 - 192.168.1.190, capacité 62 hôtes.
 *
 * Cette classe contient les SORTIES calculées automatiquement.
 * Elle ne doit pas être confondue avec BesoinReseau (entrée utilisateur).
 *
 * §16 TRAVAIL SUPPLÉMENTAIRE :
 * Les attributs premiereAdresseUtilisable et derniereAdresseUtilisable
 * sont calculés automatiquement dans le constructeur via CalculateurReseau.
 */
public class ResultatVLSM {

    private String nomBesoin;
    private String adresseReseau;
    private int    cidr;
    private String masqueDecimal;
    private int    capacite;

    // §16 : plage d'adresses utilisables
    private String premiereAdresseUtilisable;
    private String derniereAdresseUtilisable;

    // ── Constructeur ──────────────────────────────────────────────────────
    /**
     * Construit un résultat VLSM complet.
     * Les plages utilisables (§16) sont calculées automatiquement.
     *
     * @param nomBesoin     Nom du service (ex : "ADMINISTRATION")
     * @param adresseReseau Adresse du sous-réseau proposé (ex : "192.168.2.0")
     * @param cidr          Masque CIDR proposé (ex : 26)
     * @param masqueDecimal Masque décimal calculé (ex : "255.255.255.192")
     * @param capacite      Nombre d'hôtes utilisables (ex : 62)
     */
    public ResultatVLSM(String nomBesoin, String adresseReseau,
                        int cidr, String masqueDecimal, int capacite) {
        this.nomBesoin     = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.cidr          = cidr;
        this.masqueDecimal = masqueDecimal;
        this.capacite      = capacite;

        // Calcul automatique des plages utilisables (§16)
        this.premiereAdresseUtilisable =
                CalculateurReseau.calculerPremiereAdresseUtilisable(adresseReseau);
        this.derniereAdresseUtilisable =
                CalculateurReseau.calculerDerniereAdresseUtilisable(adresseReseau, cidr);
    }

    // ── Getters ───────────────────────────────────────────────────────────
    public String getNomBesoin()              { return nomBesoin; }
    public String getAdresseReseau()          { return adresseReseau; }
    public int    getCidr()                   { return cidr; }
    public int    getCapacite()               { return capacite; }
    public String getMasqueDecimal()          { return masqueDecimal; }
    public String getPremiereAdresseUtilisable() { return premiereAdresseUtilisable; }
    public String getDerniereAdresseUtilisable() { return derniereAdresseUtilisable; }

    // ── Affichage (format §16 avec plage) ─────────────────────────────────
    public void afficher() {
        System.out.println(
            nomBesoin
            + " -> " + adresseReseau + "/" + cidr
            + " | Plage : " + premiereAdresseUtilisable
            + " - "         + derniereAdresseUtilisable
            + " | Masque : " + masqueDecimal
            + " | Capacité : " + capacite + " hôtes"
        );
    }
}
