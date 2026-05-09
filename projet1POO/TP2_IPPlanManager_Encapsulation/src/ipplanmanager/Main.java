package ipplanmanager;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== TP2 : Encapsulation =====");

        // Création des adresses IP
        AdresseIP ip1 = new AdresseIP("192.168.1.1");
        AdresseIP ip2 = new AdresseIP("");
        AdresseIP ip3 = new AdresseIP(null);
        AdresseIP ip4 = new AdresseIP("10.0.0.1");

        // Création des interfaces réseau
        InterfaceReseau interface1 =
                new InterfaceReseau("eth0", ip1);

        InterfaceReseau interface2 =
                new InterfaceReseau("", ip2);

        InterfaceReseau interface3 =
                new InterfaceReseau("wlan0", ip4);

        // Activation d'une interface
        interface1.activer();

        // Création des équipements
        Equipement routeur =
                new Equipement(
                        "R1_EDGE",
                        "Routeur",
                        interface1
                );

        Equipement serveur =
                new Equipement(
                        "",
                        "",
                        interface2
                );

        Equipement pc =
                new Equipement(
                        "PC_ADMIN",
                        "Ordinateur",
                        interface3
                );

        // Création des réseaux
        ReseauIP reseau1 =
                new ReseauIP(
                        "192.168.1.0",
                        24,
                        "Réseau principal"
                );

        ReseauIP reseau2 =
                new ReseauIP(
                        "",
                        55,
                        ""
                );

        // Affichage des réseaux
        System.out.println();
        System.out.println("----- Réseau 1 -----");
        reseau1.afficher();

        System.out.println();
        System.out.println("----- Réseau 2 -----");
        reseau2.afficher();

        // Affichage des équipements
        System.out.println();
        System.out.println("----- Équipement 1 -----");
        routeur.afficher();

        System.out.println();
        System.out.println("----- Équipement 2 -----");
        serveur.afficher();

        System.out.println();
        System.out.println("----- Équipement 3 -----");
        pc.afficher();

        // Test des setters
        System.out.println();
        System.out.println("===== Tests des setters =====");

        ip1.setValeur("");

        reseau1.setMasqueCidr(45);

        routeur.setNom("");

        // Affichage après modifications
        System.out.println();
        System.out.println("----- Routeur après modification -----");
        routeur.afficher();

        System.out.println();
        System.out.println("----- Réseau 1 après modification -----");
        reseau1.afficher();

        // Test méthode estAdresseLocale()
        System.out.println();
        System.out.println("===== Test des adresses locales =====");

        System.out.println(
                ip1.getValeur()
                + " est locale ? "
                + ip1.estAdresseLocale()
        );

        System.out.println(
                ip4.getValeur()
                + " est locale ? "
                + ip4.estAdresseLocale()
        );

        System.out.println(
                ip3.getValeur()
                + " est locale ? "
                + ip3.estAdresseLocale()
        );

        System.out.println();
        System.out.println("===== Fin du programme =====");
    }
}