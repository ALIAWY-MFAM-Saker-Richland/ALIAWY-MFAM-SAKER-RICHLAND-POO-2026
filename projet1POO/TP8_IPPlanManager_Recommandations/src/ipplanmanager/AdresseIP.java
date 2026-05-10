package ipplanmanager;

public class AdresseIP {

    private String adresse;

    public AdresseIP(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return adresse;
    }
}