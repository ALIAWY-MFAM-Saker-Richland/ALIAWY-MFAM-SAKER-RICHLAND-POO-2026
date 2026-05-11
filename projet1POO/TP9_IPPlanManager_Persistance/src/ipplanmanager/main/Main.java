package ipplanmanager.main;

import ipplanmanager.exception.ConflitVLANException;

import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;

import ipplanmanager.repository.BesoinRepository;
import ipplanmanager.repository.FichierPlanRepository;

import ipplanmanager.service.GestionnaireVLAN;
import ipplanmanager.service.MoteurRecommandation;
import ipplanmanager.service.MoteurVLSM;
import ipplanmanager.service.RapportService;

import ipplanmanager.service.RecommandationGrandVLAN;
import ipplanmanager.service.RecommandationServeurs;
import ipplanmanager.service.RecommandationWifiInvite;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                "===== IPPlan-Manager : TP9-Persistance ALIAWY MFAM SAKER RICHLAND  ====="
        );

        /*
        =====================================================
                    CHOIX DU SCÉNARIO
        =====================================================
        */

        // ===============================
        // SCÉNARIO UNIVERSITÉ
        // ===============================
/*
        String fichierBesoins =
                "exports/besoins.csv";

        String fichierRapport =
                "exports/rapport_complet.txt";

  */
        // ===============================
        // SCÉNARIO PME
        // ===============================

        
        String fichierBesoins =
                "exports/besoins_pme.csv";

        String fichierRapport =
                "exports/rapport_pme.txt";
      

        // ===============================
        // FICHIERS COMMUNS
        // ===============================

        String fichierPlan =
                "exports/plan_adressage.csv";

        String fichierVlans =
                "exports/vlans.csv";

        String fichierRecommandations =
                "exports/recommandations.txt";

        BesoinRepository besoinRepository =
                new BesoinRepository();

        FichierPlanRepository fichierPlanRepository =
                new FichierPlanRepository();

        RapportService rapportService =
                new RapportService();

        try {

            // ===============================
            // CHARGEMENT DES BESOINS
            // ===============================

            ArrayList<BesoinReseau> besoins =

                    besoinRepository.chargerBesoins(
                            fichierBesoins
                    );

            // ===============================
            // GÉNÉRATION VLSM
            // ===============================

            MoteurVLSM moteurVLSM =
                    new MoteurVLSM();

            ArrayList<ResultatVLSM> resultats =

                    moteurVLSM.genererPlan(
                            "10.10.0.0",
                            besoins
                    );

            // ===============================
            // CRÉATION DES VLANs
            // ===============================

            GestionnaireVLAN gestionnaireVLAN =
                    new GestionnaireVLAN();

            int numeroVLAN = 10;

            for (ResultatVLSM resultat : resultats) {

                VLAN vlan =
                        new VLAN(
                                numeroVLAN,
                                resultat.getNomBesoin(),
                                resultat,
                                "VLAN "
                                        + resultat.getNomBesoin()
                        );

                gestionnaireVLAN.ajouterVLAN(vlan);

                numeroVLAN += 10;
            }

            // ===============================
            // RECOMMANDATIONS
            // ===============================

            MoteurRecommandation moteurRecommandation =
                    new MoteurRecommandation();

            moteurRecommandation.ajouterRegle(
                    new RecommandationWifiInvite()
            );

            moteurRecommandation.ajouterRegle(
                    new RecommandationServeurs()
            );

            moteurRecommandation.ajouterRegle(
                    new RecommandationGrandVLAN()
            );

            ArrayList<Recommandation> recommandations =

                    moteurRecommandation.analyserVLANs(
                            gestionnaireVLAN.getVlans()
                    );

            // ===============================
            // SAUVEGARDE CSV
            // ===============================

            fichierPlanRepository.sauvegarderPlanCSV(
                    resultats,
                    fichierPlan
            );

            fichierPlanRepository.sauvegarderVLANsCSV(
                    gestionnaireVLAN.getVlans(),
                    fichierVlans
            );

            fichierPlanRepository
                    .sauvegarderRecommandations(
                            recommandations,
                            fichierRecommandations
                    );

            // ===============================
            // GÉNÉRATION DU RAPPORT
            // ===============================

            rapportService.genererRapportComplet(

                    besoins,

                    resultats,

                    gestionnaireVLAN.getVlans(),

                    recommandations,

                    fichierRapport
            );

            // ===============================
            // AFFICHAGE FINAL
            // ===============================

            System.out.println(
                    "Traitement termine avec succes."
            );

            System.out.println(
                    "Fichiers generes dans le dossier exports."
            );

        } catch (IOException e) {

            System.out.println(
                    "Erreur de fichier : "
                            + e.getMessage()
            );

        } catch (ConflitVLANException e) {

            System.out.println(
                    "Erreur VLAN : "
                            + e.getMessage()
            );
        }
    }
}