package reseau;
import java.util.ArrayList;

public class BTS {
    private int numero;
    private String typeMilieu; // Urbaine ou Rural
    private int maxUtilisateurs;
    private ArrayList<MS> utilisateurs = new ArrayList<>();

    public BTS(int num, String lieu, double h, String milieu, double r, double p, int max) {
        this.numero = num; 
        this.typeMilieu = milieu; 
        this.maxUtilisateurs = max;
    }

    // CETTE MÉTHODE EST INDISPENSABLE POUR LA TABLETTE
    public String getTypeMilieu() { 
        return typeMilieu; 
    }

    public boolean estSaturee() { 
        return utilisateurs.size() >= maxUtilisateurs; 
    }

    public void attacherMS(MS ms) throws BTSException {
        if (!ms.verifierAttachement(this)) throw new BTSException("Critères non respectés");
        if (estSaturee()) throw new BTSException("BTS n°" + numero + " saturée !");
        utilisateurs.add(ms);
        System.out.println("MS " + ms.getNomComplet() + " rattaché à la BTS n°" + numero);
    }

    public MS rechercherMS(String msisdn) {
        for (MS ms : utilisateurs) if (ms.getMsisdn().equals(msisdn)) return ms;
        return null;
    }
}
