package reseau;

public class Tablette extends MS {
    public Tablette(String nom, String prenom, String mdp, String msisdn, String imsi) {
        super(nom, prenom, mdp, msisdn, imsi);
    }
    @Override
    public void afficherTypeAppareil() { System.out.print("Tablette"); }
    @Override
    public boolean verifierAttachement(BTS bts) {
        return !bts.estSaturee() && bts.getTypeMilieu().equalsIgnoreCase("Urbaine");
    }
}
