package reseau;
import java.util.ArrayList;

public class Reseau {
    private String nom;
    private ArrayList<BTS> listeBTS = new ArrayList<>();

    public Reseau(String n, String ul, String dl, String acc, double dup, double ddown, int delai) {
        this.nom = n;
    }

    public void ajouterBTS(BTS bts) { listeBTS.add(bts); }

    public String localiserUtilisateur(String msisdn) {
        for (BTS bts : listeBTS) {
            if (bts.rechercherMS(msisdn) != null) return "BTS n°" + bts.rechercherMS(msisdn).getMsisdn();
        }
        return "Inconnu";
    }
}
