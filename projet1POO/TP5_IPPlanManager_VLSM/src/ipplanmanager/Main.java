package ipplanmanager;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                "===== IPPlan-Manager : TP5-Moteur_VLSM KEMENE TAKO JAMES NELSON=====");

        /*
        =========================
        SCENARIO 1
        =========================
         */

        ArrayList<BesoinReseau> besoins1 =
                new ArrayList<>();

        besoins1.add(new BesoinReseau("TECHNIQUE", 120));
        besoins1.add(new BesoinReseau("WIFI", 80));
        besoins1.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins1.add(new BesoinReseau("SERVEURS", 20));
        besoins1.add(new BesoinReseau("DIRECTION", 10));

        System.out.println("\n===== SCENARIO 1 =====");

        for (BesoinReseau besoin : besoins1) {
            besoin.afficher();
        }

        MoteurVLSM moteur = new MoteurVLSM();

        ArrayList<ResultatVLSM> resultats1 =
                moteur.genererPlan(
                        "192.168.1.0",
                        besoins1);

        System.out.println("\nPlan d'adressage :");

        for (ResultatVLSM resultat : resultats1) {
            resultat.afficher();
        }

        /*
        =========================
        SCENARIO 2
        =========================
         */

        ArrayList<BesoinReseau> besoins2 =
                new ArrayList<>();

        besoins2.add(new BesoinReseau("ADMIN", 25));
        besoins2.add(new BesoinReseau("COMPTABILITE", 12));
        besoins2.add(new BesoinReseau("WIFI_INVITES", 40));
        besoins2.add(new BesoinReseau("SERVEURS", 8));

        System.out.println("\n===== SCENARIO 2 =====");

        for (BesoinReseau besoin : besoins2) {
            besoin.afficher();
        }

        ArrayList<ResultatVLSM> resultats2 =
                moteur.genererPlan(
                        "10.0.0.0",
                        besoins2);

        System.out.println("\nPlan d'adressage :");

        for (ResultatVLSM resultat : resultats2) {
            resultat.afficher();
        }

        /*
        =========================
        SCENARIO 3
        =========================
         */

        ArrayList<BesoinReseau> besoins3 =
                new ArrayList<>();

        besoins3.add(new BesoinReseau("ETUDIANTS", 500));
        besoins3.add(new BesoinReseau("PERSONNEL", 120));
        besoins3.add(new BesoinReseau("LABORATOIRE", 60));
        besoins3.add(new BesoinReseau("ADMINISTRATION", 40));
        besoins3.add(new BesoinReseau("WIFI_PUBLIC", 200));

        System.out.println("\n===== SCENARIO 3 =====");

        for (BesoinReseau besoin : besoins3) {
            besoin.afficher();
        }

        ArrayList<ResultatVLSM> resultats3 =
                moteur.genererPlan(
                        "172.16.0.0",
                        besoins3);

        System.out.println("\nPlan d'adressage :");

        for (ResultatVLSM resultat : resultats3) {
            resultat.afficher();
        }
    }
}