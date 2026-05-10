package ipplanmanager;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                "===== IPPlan-Manager : TP6-VLANs KEMENE TAKO JAMES NELSON =====");

        /*
         =========================================
         SCENARIO PRINCIPAL DU TP
         =========================================
         */

        ArrayList<BesoinReseau> besoins =
                new ArrayList<>();

        besoins.add(
                new BesoinReseau(
                        "TECHNIQUE",
                        120));

        besoins.add(
                new BesoinReseau(
                        "WIFI",
                        80));

        besoins.add(
                new BesoinReseau(
                        "ADMINISTRATION",
                        50));

        besoins.add(
                new BesoinReseau(
                        "SERVEURS",
                        20));

        /*
         MOTEUR VLSM
         */
        MoteurVLSM moteur =
                new MoteurVLSM();

        ArrayList<ResultatVLSM> resultats =
                moteur.genererPlan(
                        "192.168.1.0",
                        besoins);

        /*
         GESTIONNAIRE VLAN
         */
        GestionnaireVLAN gestionnaire =
                new GestionnaireVLAN();

        int numeroVLAN = 10;

        /*
         CREATION DES VLANS
         */
        for (ResultatVLSM resultat
                : resultats) {

            VLAN vlan =
                    new VLAN(
                            numeroVLAN,
                            resultat.getNomBesoin(),
                            resultat,
                            "VLAN du service "
                            + resultat.getNomBesoin());

            gestionnaire
                    .ajouterVLAN(vlan);

            numeroVLAN += 10;
        }

        /*
         AFFICHAGE VLANS
         */
        System.out.println();

        System.out.println(
                "===== VLANS GENERES =====");

        gestionnaire
                .afficherTousLesVLANs();

        /*
         TEST RECHERCHE VLAN
         */
        System.out.println();

        System.out.println(
                "===== TEST DE RECHERCHE VLAN =====");

        VLAN vlanRecherche =
                gestionnaire
                .rechercherVLAN(20);

        if (vlanRecherche != null) {

            vlanRecherche.afficher();

        } else {

            System.out.println(
                    "VLAN introuvable.");
        }

        /*
         =========================================
         TRAVAIL DEMANDE A L'ETUDIANT
         =========================================
         */

        System.out.println();

        System.out.println(
                "===== SCENARIO UNIVERSITE =====");

        ArrayList<BesoinReseau>
                besoinsUniversite =
                new ArrayList<>();

        besoinsUniversite.add(
                new BesoinReseau(
                        "ETUDIANTS",
                        500));

        besoinsUniversite.add(
                new BesoinReseau(
                        "ENSEIGNANTS",
                        120));

        besoinsUniversite.add(
                new BesoinReseau(
                        "LABORATOIRES",
                        60));

        besoinsUniversite.add(
                new BesoinReseau(
                        "WIFI_PUBLIC",
                        200));

        besoinsUniversite.add(
                new BesoinReseau(
                        "SERVEURS",
                        30));

        /*
         GENERATION PLAN VLSM
         */
        ArrayList<ResultatVLSM>
                resultatsUniversite =
                moteur.genererPlan(
                        "10.0.0.0",
                        besoinsUniversite);

        /*
         GESTIONNAIRE VLAN
         */
        GestionnaireVLAN
                gestionnaireUniversite =
                new GestionnaireVLAN();

        numeroVLAN = 10;

        /*
         CREATION VLANS
         */
        for (ResultatVLSM resultat
                : resultatsUniversite) {

            VLAN vlan =
                    new VLAN(
                            numeroVLAN,
                            resultat.getNomBesoin(),
                            resultat,
                            "VLAN du service "
                            + resultat.getNomBesoin());

            gestionnaireUniversite
                    .ajouterVLAN(vlan);

            numeroVLAN += 10;
        }

        /*
         AFFICHAGE VLANS UNIVERSITE
         */
        System.out.println();

        System.out.println(
                "===== VLANS UNIVERSITE =====");

        gestionnaireUniversite
                .afficherTousLesVLANs();

        /*
         NOMBRE TOTAL VLANS
         */
        System.out.println();

        System.out.println(
                "Nombre total de VLANs : "
                + gestionnaireUniversite
                .obtenirNombreVLANs());

        /*
         VLANS > 100 HOTES
         */
        System.out.println();

        System.out.println(
                "===== VLANS CRITIQUES =====");

        gestionnaireUniversite
                .afficherVLANsCritiques();

        /*
         VLAN PLUS GRANDE CAPACITE
         */
        System.out.println();

        System.out.println(
                "===== PLUS GRAND VLAN =====");

        VLAN plusGrand =
                gestionnaireUniversite
                .obtenirPlusGrandVLAN();

        if (plusGrand != null) {

            plusGrand.afficher();
        }
    }
}