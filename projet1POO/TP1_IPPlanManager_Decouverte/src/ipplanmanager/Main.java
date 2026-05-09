package ipplanmanager;

/**
 * Classe principale pour tester le projet IPPlan-Manager (TP1)
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager : TP1 (Travail Étudiant) =====");
        System.out.println();

        // 1. Création de deux réseaux (Consigne : un deuxième réseau)
        ReseauIP reseauAdmin = new ReseauIP("192.168.1.0", 24, "Réseau Administration");
        ReseauIP reseauPublic = new ReseauIP("172.16.0.0", 16, "Réseau Public (WiFi)");

        // 2. Création des adresses IP
        AdresseIP ipSwitch = new AdresseIP("192.168.1.2");
        AdresseIP ipAP = new AdresseIP("172.16.0.1");
        AdresseIP ipPC2 = new AdresseIP("172.16.0.50");

        // 3. Création des interfaces réseau
        // Consigne : une interface inactive
        InterfaceReseau interfaceSwitch = new InterfaceReseau("vlan1", ipSwitch);
        interfaceSwitch.activer();

        InterfaceReseau interfaceAP = new InterfaceReseau("eth0", ipAP);
        interfaceAP.activer();

        InterfaceReseau interfacePC2 = new InterfaceReseau("wlan0", ipPC2);
        // Cette interface reste inactive par défaut (consigne respectée)

        // Consigne : une interface sans adresse IP
        InterfaceReseau interfaceSansIP = new InterfaceReseau("eth1", null);
        interfaceSansIP.activer();

        // 4. Création des équipements supplémentaires demandés
        // Consigne : un switch
        Equipement switchPrincipal = new Equipement("SW-CORE-01", "Switch", interfaceSwitch);

        // Consigne : un point d'accès WiFi (AP)
        Equipement apWifi = new Equipement("AP-HALL-A", "Point d'accès WiFi", interfaceAP);

        // Consigne : un poste client supplémentaire
        Equipement pcClient2 = new Equipement("PC-VISITEUR", "Poste client", interfacePC2);
        
        // Équipement avec l'interface sans IP
        Equipement serveurTest = new Equipement("SRV-DEBUG", "Serveur", interfaceSansIP);

        // 5. AFFICHAGE DES OBJETS CRÉÉS
        System.out.println("----- LISTE DES RÉSEAUX -----");
        reseauAdmin.afficher();
        System.out.println("-----------------------------");
        reseauPublic.afficher();
        System.out.println();

        System.out.println("----- LISTE DES ÉQUIPEMENTS -----");
        
        System.out.println("[Test Switch]");
        switchPrincipal.afficher();
        System.out.println();

        System.out.println("[Test Point d'accès]");
        apWifi.afficher();
        System.out.println();

        System.out.println("[Test Poste client supplémentaire - Interface Inactive]");
        pcClient2.afficher();
        System.out.println();

        System.out.println("[Test Interface sans adresse IP]");
        serveurTest.afficher();
    }
}
