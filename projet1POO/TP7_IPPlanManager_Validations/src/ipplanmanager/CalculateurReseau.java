package ipplanmanager;

public class CalculateurReseau {

    // Vérification IP valide
    public static void verifierAdresseIP(String ip)
            throws AdresseIPInvalideException {

        if (!estAdresseIPValide(ip)) {
            throw new AdresseIPInvalideException("Adresse IP invalide : " + ip);
        }
    }

    public static boolean estAdresseIPValide(String ip) {

        if (ip == null) return false;

        String[] parts = ip.split("\\.");

        if (parts.length != 4) return false;

        for (String p : parts) {
            try {
                int v = Integer.parseInt(p);
                if (v < 0 || v > 255) return false;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    // Conversion IP → entier
    public static int ipToInt(String ip) {
        String[] p = ip.split("\\.");
        return (Integer.parseInt(p[0]) << 24)
             + (Integer.parseInt(p[1]) << 16)
             + (Integer.parseInt(p[2]) << 8)
             + Integer.parseInt(p[3]);
    }

    // Taille bloc réseau
    public static int tailleBloc(int cidr) {
        return (int) Math.pow(2, 32 - cidr);
    }

    // Fin réseau
    public static int calculerFin(String ip, int cidr) {
        return ipToInt(ip) + tailleBloc(cidr) - 1;
    }

    // Chevauchement réseaux
    public static boolean reseauxSeChevauchent(
            String ip1, int cidr1,
            String ip2, int cidr2) {

        int d1 = ipToInt(ip1);
        int f1 = calculerFin(ip1, cidr1);

        int d2 = ipToInt(ip2);
        int f2 = calculerFin(ip2, cidr2);

        return d1 <= f2 && d2 <= f1;
    }
}