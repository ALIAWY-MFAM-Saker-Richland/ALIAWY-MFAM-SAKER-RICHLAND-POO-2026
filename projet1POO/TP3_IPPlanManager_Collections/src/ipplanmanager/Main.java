package ipplanmanager;

public class Main {

    public static void main(String[] args) {

        InfrastructureReseau infrastructure =
                new InfrastructureReseau(
                        "Infrastructure YFY"
                );

        // Réseaux
        ReseauIP reseauAdmin =
                new ReseauIP(
                        "192.168.1.0",
                        24,
                        "Réseau administration"
                );

        ReseauIP reseauTech =
                new ReseauIP(
                        "192.168.2.0",
                        24,
                        "Réseau technique"
                );

        ReseauIP reseauWifi =
                new ReseauIP(
                        "192.168.3.0",
                        24,
                        "Réseau WiFi"
                );

        // Sous-réseaux
        SousReseau admin =
                new SousReseau(
                        "ADMIN",
                        reseauAdmin
                );

        SousReseau tech =
                new SousReseau(
                        "TECH",
                        reseauTech
                );

        SousReseau wifi =
                new SousReseau(
                        "WIFI",
                        reseauWifi
                );

        infrastructure.ajouterSousReseau(admin);
        infrastructure.ajouterSousReseau(tech);
        infrastructure.ajouterSousReseau(wifi);

        // Adresses IP
        AdresseIP ip1 =
                new AdresseIP("192.168.1.1");

        AdresseIP ip2 =
                new AdresseIP("10.0.0.1");

        AdresseIP ip3 =
                new AdresseIP("192.168.2.1");

        AdresseIP ip4 =
                new AdresseIP("192.168.3.1");

        // Interfaces
        InterfaceReseau eth0 =
                new InterfaceReseau("eth0", ip1);

        InterfaceReseau eth1 =
                new InterfaceReseau("eth1", ip2);

        InterfaceReseau wlan0 =
                new InterfaceReseau("wlan0", ip3);

        InterfaceReseau gig0 =
                new InterfaceReseau("Gig0/0", ip4);

        eth0.activer();
        eth1.activer();
        wlan0.activer();
        gig0.activer();

        // Routeur
        Equipement routeur =
                new Equipement(
                        "R1_EDGE",
                        "Routeur"
                );

        routeur.ajouterInterface(eth0);
        routeur.ajouterInterface(eth1);

        // Switch
        Equipement switch1 =
                new Equipement(
                        "SW_CORE",
                        "Switch"
                );

        switch1.ajouterInterface(wlan0);

        // Serveur
        Equipement serveur =
                new Equipement(
                        "SRV_WEB",
                        "Serveur"
                );

        serveur.ajouterInterface(gig0);

        infrastructure.ajouterEquipement(routeur);
        infrastructure.ajouterEquipement(switch1);
        infrastructure.ajouterEquipement(serveur);

        // Affichage
        infrastructure.afficher();

        // Recherche
        System.out.println();

        infrastructure.rechercherEquipement(
                "SRV_WEB"
        );

        System.out.println();

        infrastructure.rechercherEquipement(
                "PC_ADMIN"
        );
    }
}