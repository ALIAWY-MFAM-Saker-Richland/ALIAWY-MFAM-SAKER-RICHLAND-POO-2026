package reseau;

public class Smartphone extends MS {
    public Smartphone(String nom, String prenom, String mdp, String msisdn, String imsi) {
        super(nom, prenom, mdp, msisdn, imsi);
    }
    @Override
    public void afficherTypeAppareil() { System.out.print("Smartphone"); }
    @Override
    public boolean verifierAttachement(BTS bts) { return !bts.estSaturee(); }
}
