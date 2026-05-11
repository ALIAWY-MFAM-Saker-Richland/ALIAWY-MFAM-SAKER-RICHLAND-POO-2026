package ipplanmanager.model;

public class ResultatVLSM {

    private String nomBesoin;
    private String adresseReseau;
    private int cidr;
    private int capacite;

    public ResultatVLSM(String nomBesoin,
                        String adresseReseau,
                        int cidr,
                        int capacite) {

        this.nomBesoin = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.cidr = cidr;
        this.capacite = capacite;
    }

    public String getNomBesoin() {
        return nomBesoin;
    }

    public String getAdresseReseau() {
        return adresseReseau;
    }

    public int getCidr() {
        return cidr;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setNomBesoin(String nomBesoin) {
        this.nomBesoin = nomBesoin;
    }

    public void setAdresseReseau(String adresseReseau) {
        this.adresseReseau = adresseReseau;
    }

    public void setCidr(int cidr) {
        this.cidr = cidr;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return nomBesoin + " -> "
                + adresseReseau
                + "/"
                + cidr
                + " | "
                + capacite
                + " hôtes";
    }
}