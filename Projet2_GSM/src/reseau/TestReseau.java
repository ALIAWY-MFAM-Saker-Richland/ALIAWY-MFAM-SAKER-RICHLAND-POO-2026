package reseau;

public class TestReseau {
    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("   PROJET 2 POO : GSM - ALIAWY RICHLAND");
        System.out.println("=============================================\n");

        try {
            Reseau orange = new Reseau("Orange CM", "900", "1800", "TDMA", 0.3, 0.3, 10);
            BTS bts1 = new BTS(101, "Douala", 35.0, "Urbaine", 2.0, 45.0, 2);
            orange.ajouterBTS(bts1);

            MS u1 = new Smartphone("ALIAWY", "Richland", "123", "677000001", "IMSI001");
            MS u2 = new Smartphone("Kevin", "Junior", "456", "677000002", "IMSI002");
            MS u3 = new Tablette("Talla", "Alice", "789", "655000003", "IMSI003");

            System.out.println("--- Connexions ---");
            bts1.attacherMS(u1);
            bts1.attacherMS(u2);
            
            System.out.println("\n--- Test Saturation ---");
            bts1.attacherMS(u3); // Va déclencher l'exception

        } catch (BTSException e) {
            System.out.println("Exception capturee : " + e.getMessage());
        }

        System.out.println("\n--- Simulation Appel ---");
        // On simule un appel entre les deux connectés
        MS s1 = new Smartphone("ALIAWY", "Richland", "", "677000001", "");
        MS s2 = new Smartphone("Kevin", "Junior", "", "677000002", "");
        s1.passerAppel(s2);
        s2.afficherHistorique();
    }
}
