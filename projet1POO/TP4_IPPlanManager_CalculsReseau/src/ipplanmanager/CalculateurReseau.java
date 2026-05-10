package ipplanmanager;

public class CalculateurReseau {

    // ── Calcul du nombre d'hôtes (formule : 2^(32-CIDR) - 2) ──────────────
    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) {
            return 0;
        }
        int bitsHotes = 32 - cidr;
        return (int) Math.pow(2, bitsHotes) - 2;
    }

    // ── Détection de la classe réseau ──────────────────────────────────────
    public static String obtenirClasseReseau(String adresseIP) {
        String[] parties = adresseIP.split("\\.");
        int premierOctet = Integer.parseInt(parties[0]);

        if (premierOctet >= 1 && premierOctet <= 126) {
            return "Classe A";
        }
        if (premierOctet >= 128 && premierOctet <= 191) {
            return "Classe B";
        }
        if (premierOctet >= 192 && premierOctet <= 223) {
            return "Classe C";
        }
        return "Classe inconnue";
    }

    // ── Conversion CIDR → masque décimal ──────────────────────────────────
    public static String obtenirMasqueDecimal(int cidr) {
        switch (cidr) {
            case 8:  return "255.0.0.0";
            case 16: return "255.255.0.0";
            case 24: return "255.255.255.0";
            case 25: return "255.255.255.128";
            case 26: return "255.255.255.192";
            case 27: return "255.255.255.224";
            case 28: return "255.255.255.240";
            default: return "Masque non disponible";
        }
    }

    // ── Section 15 : détection des plages privées ─────────────────────────
    /**
     * Retourne true si l'adresse IP appartient à une plage privée RFC 1918 :
     *   10.0.0.0/8       → premier octet = 10
     *   172.16.0.0/12    → premier octet = 172, deuxième octet 16..31
     *   192.168.0.0/16   → premiers octets = 192.168
     */
    public static boolean estReseauPrive(String adresseIP) {
        String[] parties = adresseIP.split("\\.");
        if (parties.length != 4) {
            return false;
        }
        int o1 = Integer.parseInt(parties[0]);
        int o2 = Integer.parseInt(parties[1]);

        // 10.x.x.x
        if (o1 == 10) {
            return true;
        }
        // 172.16.x.x à 172.31.x.x
        if (o1 == 172 && o2 >= 16 && o2 <= 31) {
            return true;
        }
        // 192.168.x.x
        if (o1 == 192 && o2 == 168) {
            return true;
        }
        return false;
    }
}
