package ipplanmanager;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== IPPlan-Manager : TP7 - Validations avancees ALIAWY MFAM SAKER RICHLAND =====\n");

        // =========================================================
        // 1. GENERATION DU PLAN D'ADRESSAGE
        // =========================================================
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("TECHNIQUE", 120));
        besoins.add(new BesoinReseau("WIFI", 80));
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("SERVEURS", 20));

        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats = moteur.genererPlan("192.168.1.0", besoins);

        System.out.println("Plan genere :");
        for (ResultatVLSM resultat : resultats) {
            resultat.afficher();
        }

        System.out.println();

        // =========================================================
        // 2. VALIDATION DU PLAN
        // =========================================================
        ValidateurPlanAdressage validateur = new ValidateurPlanAdressage();

        try {
            validateur.verifierAdresses(resultats);
            validateur.verifierChevauchements(resultats);
            System.out.println("Validation terminee : aucun conflit critique detecte.");
        } catch (AdresseIPInvalideException | ChevauchementReseauException e) {
            System.out.println("Erreur de validation : " + e.getMessage());
        }

        System.out.println();

        // =========================================================
        // 3. TEST DE CONFLIT VLAN
        // =========================================================
        System.out.println("Test de conflit VLAN :");

        GestionnaireVLAN gestionnaire = new GestionnaireVLAN();

        try {
            VLAN vlan1 = new VLAN(10, "TECHNIQUE", resultats.get(0), "VLAN Technique");
            VLAN vlan2 = new VLAN(20, "WIFI", resultats.get(1), "VLAN Wifi");
            VLAN vlan3 = new VLAN(10, "ADMINISTRATION", resultats.get(2), "VLAN Administration"); // ID déjà utilisé

            gestionnaire.ajouterVLAN(vlan1);
            gestionnaire.ajouterVLAN(vlan2);
            gestionnaire.ajouterVLAN(vlan3);

        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }

        System.out.println();

        // =========================================================
        // 4. TEST D'UNE ADRESSE IP INVALIDE
        // =========================================================
        System.out.println("Test d'adresse IP invalide :");

        try {
            CalculateurReseau.verifierAdresseIP("192.168.300.0");
            System.out.println("Adresse IP valide.");
        } catch (AdresseIPInvalideException e) {
            System.out.println("Erreur adresse IP : " + e.getMessage());
        }

        System.out.println();

        // =========================================================
        // 5. TEST DE CHEVAUCHEMENT DE RESEAUX
        // =========================================================
        System.out.println("Test de chevauchement de reseaux :");

        ArrayList<ResultatVLSM> resultatsChevauchement = new ArrayList<>();
        resultatsChevauchement.add(new ResultatVLSM("RESEAU_1", "192.168.1.0", 25));
        resultatsChevauchement.add(new ResultatVLSM("RESEAU_2", "192.168.1.64", 26));

        try {
            validateur.verifierChevauchements(resultatsChevauchement);
            System.out.println("Aucun chevauchement detecte.");
        } catch (ChevauchementReseauException e) {
            System.out.println("Erreur de validation : " + e.getMessage());
        }

        System.out.println();

        // =========================================================
        // 6. TEST DE TROIS VLANs VALIDES + UN QUATRIEME EN DOUBLON
        // =========================================================
        System.out.println("Test de creation de VLANs :");

        GestionnaireVLAN gestionnaire2 = new GestionnaireVLAN();

        try {
            VLAN v1 = new VLAN(1, "VLAN1", resultats.get(0), "VLAN 1");
            VLAN v2 = new VLAN(2, "VLAN2", resultats.get(1), "VLAN 2");
            VLAN v3 = new VLAN(3, "VLAN3", resultats.get(2), "VLAN 3");
            VLAN v4 = new VLAN(2, "VLAN4", resultats.get(3), "VLAN 4 en doublon");

            gestionnaire2.ajouterVLAN(v1);
            gestionnaire2.ajouterVLAN(v2);
            gestionnaire2.ajouterVLAN(v3);
            gestionnaire2.ajouterVLAN(v4);

        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }
    }
}