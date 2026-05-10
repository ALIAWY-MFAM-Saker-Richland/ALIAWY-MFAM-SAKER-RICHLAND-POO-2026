package ipplanmanager;

public class AdresseIP {

    private String adresse;

    public AdresseIP(String adresse) {
        setAdresse(adresse);
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {

        if (adresse == null || adresse.isEmpty()) {
            this.adresse = "0.0.0.0";
        } else {
            this.adresse = adresse;
        }
    }

    public void afficher() {
        System.out.println("Adresse IP : " + adresse);
    }
}