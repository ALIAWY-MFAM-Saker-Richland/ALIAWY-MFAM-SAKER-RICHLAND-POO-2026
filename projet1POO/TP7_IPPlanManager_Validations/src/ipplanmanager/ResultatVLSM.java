package ipplanmanager;

public class ResultatVLSM {

    private String nomBesoin;
    private String adresseReseau;
    private int cidr;

    public ResultatVLSM(String nomBesoin, String adresseReseau, int cidr) {
        this.nomBesoin = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.cidr = cidr;
    }

    public String getNomBesoin() { return nomBesoin; }
    public String getAdresseReseau() { return adresseReseau; }
    public int getCidr() { return cidr; }

    public void afficher() {
        System.out.println(nomBesoin + " -> " + adresseReseau + "/" + cidr);
    }
}