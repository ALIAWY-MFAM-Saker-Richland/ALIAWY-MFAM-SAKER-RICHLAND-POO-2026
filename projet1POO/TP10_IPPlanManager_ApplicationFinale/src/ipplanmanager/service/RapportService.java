package ipplanmanager.service;

import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RapportService {

    public void genererRapport(
            String nomProjet,
            ArrayList<BesoinReseau> besoins,
            ArrayList<ResultatVLSM> resultats,
            ArrayList<VLAN> vlans,
            ArrayList<Recommandation> recommandations,
            String cheminFichier) throws IOException {

        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("==========================================\n");
        writer.write("  RAPPORT TECHNIQUE - " + nomProjet.toUpperCase() + "\n");
        writer.write("==========================================\n\n");

        writer.write("--- BESOINS SAISIS ---\n");
        for (BesoinReseau b : besoins) {
            writer.write("  " + b.getNom() + " : " + b.getNombreHotes() + " hôtes\n");
        }

        writer.write("\n--- PLAN D'ADRESSAGE VLSM ---\n");
        for (ResultatVLSM r : resultats) {
            writer.write("  " + r.getNomBesoin()
                + " -> " + r.getAdresseReseau() + "/" + r.getCidr()
                + " | Masque : " + r.getMasqueDecimal()
                + " | Demandés : " + r.getHotesDemandes()
                + " | Capacité : " + r.getCapacite()
                + " | Marge : " + r.getMarge() + "\n");
        }

        writer.write("\n--- VLANs CRÉÉS ---\n");
        for (VLAN v : vlans) {
            writer.write("  VLAN " + v.getNumero()
                + " - " + v.getNom()
                + " (" + v.getDescription() + ")\n");
        }

        writer.write("\n--- RECOMMANDATIONS ---\n");
        for (Recommandation rec : recommandations) {
            writer.write("  [" + rec.getPriorite() + "] "
                + rec.getTitre() + " : " + rec.getMessage() + "\n");
        }

        writer.write("\n==========================================\n");
        writer.write("  Fin du rapport\n");
        writer.write("==========================================\n");
        writer.close();
        System.out.println("Rapport généré : " + cheminFichier);
    }
}