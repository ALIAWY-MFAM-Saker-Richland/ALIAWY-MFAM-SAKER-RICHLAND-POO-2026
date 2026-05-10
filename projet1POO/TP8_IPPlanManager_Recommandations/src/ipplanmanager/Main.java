package ipplanmanager;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                "===== IPPlan-Manager : TP8-Recommandations ALIAWY MFAM SAKER RICHLAND====="
        );

        /*
         * ==========================
         * SCÉNARIO OFFICIEL TP
         * ==========================
         */

        ArrayList<BesoinReseau> besoins =
                new ArrayList<>();

        besoins.add(
                new BesoinReseau(
                        "ETUDIANTS",
                        500
                )
        );

        besoins.add(
                new BesoinReseau(
                        "WIFI_INVITES",
                        200
                )
        );

        besoins.add(
                new BesoinReseau(
                        "ENSEIGNANTS",
                        120
                )
        );

        besoins.add(
                new BesoinReseau(
                        "LABORATOIRES",
                        60
                )
        );

        besoins.add(
                new BesoinReseau(
                        "SERVEURS",
                        30
                )
        );

        MoteurVLSM moteurVLSM =
                new MoteurVLSM();

        ArrayList<ResultatVLSM> resultats =
                moteurVLSM.genererPlan(
                        "10.10.0.0",
                        besoins
                );

        GestionnaireVLAN gestionnaireVLAN =
                new GestionnaireVLAN();

        int numeroVLAN = 10;

        try {

            for (ResultatVLSM resultat : resultats) {

                VLAN vlan =
                        new VLAN(
                                numeroVLAN,
                                resultat.getNomBesoin(),
                                resultat,
                                "VLAN "
                                + resultat.getNomBesoin()
                        );

                gestionnaireVLAN
                        .ajouterVLAN(vlan);

                numeroVLAN += 10;
            }

        } catch (ConflitVLANException e) {

            System.out.println(
                    "Erreur VLAN : "
                    + e.getMessage()
            );
        }

        System.out.println();

        System.out.println(
                "Plan VLAN genere :"
        );

        gestionnaireVLAN
                .afficherTousLesVLANs();

        /*
         * ==========================
         * MOTEUR DE RECOMMANDATION
         * ==========================
         */

        MoteurRecommandation
                moteurRecommandation =
                new MoteurRecommandation();

        /*
         * POLYMORPHISME :
         * Toutes les règles sont manipulées
         * via l'interface
         * RegleRecommandation
         */

        moteurRecommandation
                .ajouterRegle(
                        new RecommandationWifiInvite()
                );

        moteurRecommandation
                .ajouterRegle(
                        new RecommandationServeurs()
                );

        moteurRecommandation
                .ajouterRegle(
                        new RecommandationGrandVLAN()
                );

        /*
         * TRAVAIL DEMANDÉ
         */

        moteurRecommandation
                .ajouterRegle(
                        new RecommandationAdministration()
                );

        /*
         * TRAVAIL SUPPLÉMENTAIRE
         */

        moteurRecommandation
                .ajouterRegle(
                        new RecommandationMargeAdresse()
                );

        ArrayList<Recommandation>
                recommandations =
                moteurRecommandation
                        .analyserVLANs(
                                gestionnaireVLAN
                                        .getVlans()
                        );

        System.out.println();

        System.out.println(
                "Recommandations proposees :"
        );

        moteurRecommandation
                .afficherRecommandations(
                        recommandations
                );

        /*
         * ==========================
         * SCÉNARIO SUPPLÉMENTAIRE
         * PARTIE 18
         * ==========================
         */

        System.out.println();
        System.out.println(
                "===== SCENARIO SUPPLEMENTAIRE ====="
        );

        ArrayList<BesoinReseau>
                besoinsSupplementaires =
                new ArrayList<>();

        besoinsSupplementaires.add(
                new BesoinReseau(
                        "ADMINISTRATION",
                        50
                )
        );

        besoinsSupplementaires.add(
                new BesoinReseau(
                        "WIFI_INVITES",
                        120
                )
        );

        besoinsSupplementaires.add(
                new BesoinReseau(
                        "SERVEURS",
                        20
                )
        );

        besoinsSupplementaires.add(
                new BesoinReseau(
                        "CAMERAS",
                        80
                )
        );

        besoinsSupplementaires.add(
                new BesoinReseau(
                        "VOIP",
                        60
                )
        );

        ArrayList<ResultatVLSM>
                resultatsSupplementaires =
                moteurVLSM.genererPlan(
                        "10.20.0.0",
                        besoinsSupplementaires
                );

        GestionnaireVLAN
                gestionnaireSupplementaire =
                new GestionnaireVLAN();

        numeroVLAN = 100;

        try {

            for (ResultatVLSM resultat
                    : resultatsSupplementaires) {

                VLAN vlan =
                        new VLAN(
                                numeroVLAN,
                                resultat.getNomBesoin(),
                                resultat,
                                "VLAN "
                                + resultat.getNomBesoin()
                        );

                gestionnaireSupplementaire
                        .ajouterVLAN(vlan);

                numeroVLAN += 10;
            }

        } catch (ConflitVLANException e) {

            System.out.println(
                    "Erreur VLAN : "
                    + e.getMessage()
            );
        }

        System.out.println();

        gestionnaireSupplementaire
                .afficherTousLesVLANs();

        ArrayList<Recommandation>
                recommandationsSupplementaires =
                moteurRecommandation
                        .analyserVLANs(
                                gestionnaireSupplementaire
                                        .getVlans()
                        );

        System.out.println();

        moteurRecommandation
                .afficherRecommandations(
                        recommandationsSupplementaires
                );
    }
}