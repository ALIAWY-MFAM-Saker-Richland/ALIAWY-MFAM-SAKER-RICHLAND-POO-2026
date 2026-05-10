package ipplanmanager;

import java.util.ArrayList;

/**
 * Classe Main — Point d'entrée du programme TP5.
 *
 * Contient 3 scénarios de test VLSM :
 *   Scénario 1 : Bureau (fourni dans le TP §11)
 *   Scénario 2 : Petite entreprise (§15 travail demandé)
 *   Scénario 3 : Campus universitaire (§15 travail demandé)
 *
 * ATTENTION : créer une NOUVELLE ArrayList pour chaque scénario.
 * Ne pas réutiliser la même liste car Collections.sort() modifie
 * la liste en place → le scénario suivant serait déjà trié.
 */
public class Main {

    public static void main(String[] args) {

        MoteurVLSM moteur = new MoteurVLSM();

        // ══════════════════════════════════════════════════════════════════
        // SCÉNARIO 1 — Bureau (fourni dans le TP §11)
        // Réseau de départ : 192.168.1.0
        // ══════════════════════════════════════════════════════════════════
        System.out.println("===== IPPlan-Manager : TP5 - Moteur VLSM =====");
        System.out.println();
        System.out.println("===== SCÉNARIO 1 : Bureau =====");
        System.out.println("Réseau de départ : 192.168.1.0");

        // Nouvelle ArrayList dédiée à ce scénario
        ArrayList<BesoinReseau> besoins1 = new ArrayList<>();
        besoins1.add(new BesoinReseau("TECHNIQUE",      120));
        besoins1.add(new BesoinReseau("WIFI",            80));
        besoins1.add(new BesoinReseau("ADMINISTRATION",  50));
        besoins1.add(new BesoinReseau("SERVEURS",        20));
        besoins1.add(new BesoinReseau("DIRECTION",       10));

        System.out.println();
        System.out.println("Besoins exprimés par l'utilisateur :");
        for (BesoinReseau b : besoins1) {
            b.afficher();
        }

        ArrayList<ResultatVLSM> resultats1 = moteur.genererPlan("192.168.1.0", besoins1);

        System.out.println();
        System.out.println("Plan d'adressage proposé :");
        for (ResultatVLSM r : resultats1) {
            r.afficher();
        }

        // Résultat attendu :
        // TECHNIQUE      -> 192.168.1.0/25   Plage: 192.168.1.1  - 192.168.1.126  Capacité: 126
        // WIFI           -> 192.168.1.128/25  Plage: 192.168.1.129- 192.168.1.254  Capacité: 126
        // ADMINISTRATION -> 192.168.2.0/26   Plage: 192.168.2.1  - 192.168.2.62   Capacité: 62
        // SERVEURS       -> 192.168.2.64/27  Plage: 192.168.2.65 - 192.168.2.94   Capacité: 30
        // DIRECTION      -> 192.168.2.96/28  Plage: 192.168.2.97 - 192.168.2.110  Capacité: 14

        // ══════════════════════════════════════════════════════════════════
        // SCÉNARIO 2 — Petite entreprise (§15 travail demandé)
        // Réseau de départ : 10.0.0.0
        // ══════════════════════════════════════════════════════════════════
        System.out.println();
        System.out.println("===== SCÉNARIO 2 : Petite entreprise =====");
        System.out.println("Réseau de départ : 10.0.0.0");

        // Nouvelle ArrayList — NE PAS réutiliser besoins1
        ArrayList<BesoinReseau> besoins2 = new ArrayList<>();
        besoins2.add(new BesoinReseau("ADMIN",         25));
        besoins2.add(new BesoinReseau("COMPTABILITE",  12));
        besoins2.add(new BesoinReseau("WIFI_INVITES",  40));
        besoins2.add(new BesoinReseau("SERVEURS",       8));

        System.out.println();
        System.out.println("Besoins exprimés par l'utilisateur :");
        for (BesoinReseau b : besoins2) {
            b.afficher();
        }

        ArrayList<ResultatVLSM> resultats2 = moteur.genererPlan("10.0.0.0", besoins2);

        System.out.println();
        System.out.println("Plan d'adressage proposé :");
        for (ResultatVLSM r : resultats2) {
            r.afficher();
        }

        // Résultat attendu (après tri décroissant : WIFI_INVITES=40, ADMIN=25,
        //                   COMPTABILITE=12, SERVEURS=8) :
        // WIFI_INVITES  -> 10.0.0.0/26  Plage: 10.0.0.1  - 10.0.0.62  Capacité: 62
        // ADMIN         -> 10.0.0.64/27 Plage: 10.0.0.65 - 10.0.0.94  Capacité: 30
        // COMPTABILITE  -> 10.0.0.96/28 Plage: 10.0.0.97 - 10.0.0.110 Capacité: 14
        // SERVEURS      -> 10.0.0.112/28 Plage: 10.0.0.113- 10.0.0.126 Capacité: 14

        // ══════════════════════════════════════════════════════════════════
        // SCÉNARIO 3 — Campus universitaire (§15 travail demandé)
        // Réseau de départ : 172.16.0.0
        // ══════════════════════════════════════════════════════════════════
        System.out.println();
        System.out.println("===== SCÉNARIO 3 : Campus universitaire =====");
        System.out.println("Réseau de départ : 172.16.0.0");

        // Nouvelle ArrayList — NE PAS réutiliser besoins2
        ArrayList<BesoinReseau> besoins3 = new ArrayList<>();
        besoins3.add(new BesoinReseau("ETUDIANTS",      500));
        besoins3.add(new BesoinReseau("WIFI_PUBLIC",    200));
        besoins3.add(new BesoinReseau("PERSONNEL",      120));
        besoins3.add(new BesoinReseau("LABORATOIRE",     60));
        besoins3.add(new BesoinReseau("ADMINISTRATION",  40));

        System.out.println();
        System.out.println("Besoins exprimés par l'utilisateur :");
        for (BesoinReseau b : besoins3) {
            b.afficher();
        }

        ArrayList<ResultatVLSM> resultats3 = moteur.genererPlan("172.16.0.0", besoins3);

        System.out.println();
        System.out.println("Plan d'adressage proposé :");
        for (ResultatVLSM r : resultats3) {
            r.afficher();
        }

        // Résultat attendu (après tri décroissant : ETUDIANTS=500, WIFI_PUBLIC=200,
        //                   PERSONNEL=120, LABORATOIRE=60, ADMINISTRATION=40) :
        // ETUDIANTS      -> 172.16.0.0/23  Plage: 172.16.0.1 - 172.16.1.254 Capacité: 510
        // WIFI_PUBLIC    -> 172.16.2.0/24  Plage: 172.16.2.1 - 172.16.2.254 Capacité: 254
        // PERSONNEL      -> 172.16.3.0/25  Plage: 172.16.3.1 - 172.16.3.126 Capacité: 126
        // LABORATOIRE    -> 172.16.3.128/26 Plage: 172.16.3.129-172.16.3.190 Capacité: 62
        // ADMINISTRATION -> 172.16.3.192/26 Plage: 172.16.3.193-172.16.3.254 Capacité: 62

        System.out.println();
        System.out.println("===== Fin du programme TP5 =====");
    }
}
