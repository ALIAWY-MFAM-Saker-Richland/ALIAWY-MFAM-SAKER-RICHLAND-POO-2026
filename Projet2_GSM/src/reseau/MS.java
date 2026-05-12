package reseau;
import java.util.ArrayList;

public abstract class MS implements Connectable {
    protected String nom, prenom, msisdn, imsi;
    protected ArrayList<String> historiqueAppels = new ArrayList<>();

    public MS(String nom, String prenom, String mdp, String msisdn, String imsi) {
        this.nom = nom; this.prenom = prenom; this.msisdn = msisdn; this.imsi = imsi;
    }

    public String getMsisdn() { return msisdn; }
    public String getNomComplet() { return prenom + " " + nom; }
    public abstract void afficherTypeAppareil();

    public void passerAppel(MS destinataire) {
        System.out.println("\n[TRAFIC] " + this.getNomComplet() + " appelle " + destinataire.getNomComplet() + "...");
        destinataire.recevoirAppel("Appel de " + this.getNomComplet() + " (" + this.msisdn + ")");
    }

    public void recevoirAppel(String details) {
        this.historiqueAppels.add(details);
        System.out.println("[ALERTE] " + this.getNomComplet() + " : Bip Bip... Appel reçu !");
    }

    public void afficherHistorique() {
        System.out.println("\n--- Journal d'appels de " + getNomComplet() + " ---");
        if (historiqueAppels.isEmpty()) System.out.println("Aucun appel.");
        else for (String s : historiqueAppels) System.out.println(" -> " + s);
    }
}
