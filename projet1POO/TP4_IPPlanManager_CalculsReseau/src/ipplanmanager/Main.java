package ipplanmanager;

public class Main {

    public static void main(String[] args) {

        // ════════════════════════════════════════════════════════
        //  1. INFRASTRUCTURE
        // ════════════════════════════════════════════════════════
        InfrastructureReseau infrastructure =
                new InfrastructureReseau("Infrastructure YFY");

        // ════════════════════════════════════════════════════════
        //  2. RÉSEAUX DE BASE (fournis dans le TP)
        // ════════════════════════════════════════════════════════
        ReseauIP reseauAdmin = new ReseauIP(
                "192.168.1.0", 24, "Réseau Administration");

        ReseauIP reseauTechnique = new ReseauIP(
                "172.16.0.0", 16, "Réseau Technique");

        ReseauIP reseauWifi = new ReseauIP(
                "10.0.0.0", 8, "Réseau WiFi");

        SousReseau admin = new SousReseau("ADMIN", reseauAdmin);
        SousReseau tech  = new SousReseau("TECH",  reseauTechnique);
        SousReseau wifi  = new SousReseau("WIFI",  reseauWifi);

        infrastructure.ajouterSousReseau(admin);
        infrastructure.ajouterSousReseau(tech);
        infrastructure.ajouterSousReseau(wifi);

        // ════════════════════════════════════════════════════════
        //  3. RÉSEAUX SUPPLÉMENTAIRES (§14 — CIDR variés)
        // ════════════════════════════════════════════════════════

        // /25 → 126 hôtes  (sous-réseau de 128 adresses)
        ReseauIP reseauServeurs = new ReseauIP(
                "192.168.1.128", 25, "Réseau Serveurs DMZ");

        // /26 → 62 hôtes
        ReseauIP reseauVoip = new ReseauIP(
                "192.168.2.0", 26, "Réseau VoIP");

        // /27 → 30 hôtes
        ReseauIP reseauManagement = new ReseauIP(
                "192.168.2.64", 27, "Réseau Management");

        // /28 → 14 hôtes  (petite salle de conférence)
        ReseauIP reseauConference = new ReseauIP(
                "192.168.2.96", 28, "Réseau Salle de conférence");

        // Adresse publique (non privée — pour tester §15)
        ReseauIP reseauPublic = new ReseauIP(
                "8.8.0.0", 16, "Réseau Public (DNS Google)");

        infrastructure.ajouterSousReseau(new SousReseau("DMZ",         reseauServeurs));
        infrastructure.ajouterSousReseau(new SousReseau("VOIP",        reseauVoip));
        infrastructure.ajouterSousReseau(new SousReseau("MANAGEMENT",  reseauManagement));
        infrastructure.ajouterSousReseau(new SousReseau("CONFERENCE",  reseauConference));
        infrastructure.ajouterSousReseau(new SousReseau("PUBLIC",      reseauPublic));

        // ════════════════════════════════════════════════════════
        //  4. AFFICHAGE COMPLET (calculs automatiques inclus)
        // ════════════════════════════════════════════════════════
        infrastructure.afficher();

        // ════════════════════════════════════════════════════════
        //  5. TESTS DIRECTS DU CALCULATEUR (§14)
        // ════════════════════════════════════════════════════════
        System.out.println("============ TESTS CALCULATEUR ============");
        int[] cidrTests = {8, 16, 24, 25, 26, 27, 28};
        for (int cidr : cidrTests) {
            System.out.println("/" + cidr
                    + " → masque : " + CalculateurReseau.obtenirMasqueDecimal(cidr)
                    + "  |  hôtes : " + CalculateurReseau.calculerNombreHotes(cidr));
        }
        System.out.println();

        System.out.println("======= DÉTECTION CLASSE RÉSEAU =======");
        String[] adressesTest = {"10.0.0.1", "172.16.5.1", "192.168.1.1", "8.8.8.8"};
        for (String ip : adressesTest) {
            System.out.println(ip + " → " + CalculateurReseau.obtenirClasseReseau(ip));
        }
        System.out.println();

        // ════════════════════════════════════════════════════════
        //  6. TESTS estReseauPrive (§15)
        // ════════════════════════════════════════════════════════
        System.out.println("========= TEST estReseauPrive (§15) =========");
        String[] ipsTest = {
            "10.0.0.1",       // privé  – plage 10.x
            "172.16.5.10",    // privé  – plage 172.16–31
            "172.32.0.1",     // public – hors plage 172
            "192.168.1.1",    // privé  – plage 192.168
            "8.8.8.8",        // public – DNS Google
            "203.0.113.5"     // public – documentation
        };
        for (String ip : ipsTest) {
            System.out.println(ip + " → privé : "
                    + CalculateurReseau.estReseauPrive(ip));
        }
        System.out.println("=============================================");
    }
}
