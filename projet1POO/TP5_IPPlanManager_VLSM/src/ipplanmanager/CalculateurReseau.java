package ipplanmanager;

/**
 * Classe CalculateurReseau
 * Classe utilitaire contenant toutes les méthodes statiques de calcul réseau.
 * VERSION ENRICHIE TP5 : ajout de calculerCidrPourHotes, convertirIpEnEntier,
 * convertirEntierEnIp, calculerTailleBloc, calculerPremiereAdresseUtilisable,
 * calculerDerniereAdresseUtilisable (§16 travail supplémentaire).
 *
 * IMPORTANT : utiliser >>> (décalage non signé) et jamais >> pour les IP,
 * sinon les adresses supérieures à 127.x.x.x deviennent négatives.
 */
public class CalculateurReseau {

    // ════════════════════════════════════════════════════════════════════
    // MÉTHODES REPRISES ET AMÉLIORÉES DU TP4
    // ════════════════════════════════════════════════════════════════════

    /**
     * Calcule le nombre maximal d'hôtes utilisables pour un masque CIDR.
     * Formule : 2^(32 - cidr) - 2
     * Cas spécial /32 : retourne 1 (adresse hôte unique).
     */
    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) {
            return 0;
        }
        int bitsHotes = 32 - cidr;
        if (bitsHotes == 0) {
            return 1;
        }
        return (int) Math.pow(2, bitsHotes) - 2;
    }

    /**
     * Convertit un CIDR en masque décimal pointé.
     * Utilise des opérations bit-à-bit (fonctionne pour TOUS les CIDR,
     * contrairement au switch du TP4 qui ne couvrait que /8 /16 /24...).
     * Exemple : /26 → 255.255.255.192
     */
    public static String obtenirMasqueDecimal(int cidr) {
        int masque = 0xffffffff << (32 - cidr);
        int o1 = (masque >>> 24) & 255;
        int o2 = (masque >>> 16) & 255;
        int o3 = (masque >>>  8) & 255;
        int o4 =  masque         & 255;
        return o1 + "." + o2 + "." + o3 + "." + o4;
    }

    /**
     * Détecte la classe réseau (A, B, C) selon le premier octet.
     */
    public static String obtenirClasseReseau(String adresseIP) {
        String[] parties = adresseIP.split("\\.");
        int premierOctet = Integer.parseInt(parties[0]);
        if (premierOctet >= 1   && premierOctet <= 126) return "Classe A";
        if (premierOctet >= 128 && premierOctet <= 191) return "Classe B";
        if (premierOctet >= 192 && premierOctet <= 223) return "Classe C";
        return "Classe inconnue";
    }

    /**
     * Détecte si une adresse IP appartient à une plage privée RFC 1918.
     * Plages : 10.x.x.x / 172.16-31.x.x / 192.168.x.x
     */
    public static boolean estReseauPrive(String adresseIP) {
        String[] parties = adresseIP.split("\\.");
        if (parties.length != 4) return false;
        int o1 = Integer.parseInt(parties[0]);
        int o2 = Integer.parseInt(parties[1]);
        if (o1 == 10)                          return true;
        if (o1 == 172 && o2 >= 16 && o2 <= 31) return true;
        if (o1 == 192 && o2 == 168)            return true;
        return false;
    }

    // ════════════════════════════════════════════════════════════════════
    // NOUVELLES MÉTHODES TP5
    // ════════════════════════════════════════════════════════════════════

    /**
     * Trouve automatiquement le plus petit CIDR capable d'accueillir
     * le nombre d'hôtes demandé.
     * Algorithme : parcourt de /32 vers /0 et retourne le premier CIDR
     * dont la capacité >= nombreHotes.
     * Exemple : 50 hôtes → /26 (capacité 62)
     */
    public static int calculerCidrPourHotes(int nombreHotes) {
        for (int cidr = 32; cidr >= 0; cidr--) {
            int capacite = calculerNombreHotes(cidr);
            if (capacite >= nombreHotes) {
                return cidr;
            }
        }
        return -1; // ne devrait jamais arriver
    }

    /**
     * Convertit une adresse IP en entier 32 bits.
     * Permet de faire des calculs arithmétiques sur les adresses.
     * Exemple : "192.168.1.0" → 3232235776
     */
    public static int convertirIpEnEntier(String ip) {
        String[] parties = ip.split("\\.");
        int resultat = 0;
        for (int i = 0; i < 4; i++) {
            resultat = resultat * 256 + Integer.parseInt(parties[i]);
        }
        return resultat;
    }

    /**
     * Convertit un entier 32 bits en adresse IP décimale pointée.
     * Opération inverse de convertirIpEnEntier.
     * IMPORTANT : utiliser >>> (non signé) pour éviter les adresses négatives.
     */
    public static String convertirEntierEnIp(int valeur) {
        int o1 = (valeur >>> 24) & 255;
        int o2 = (valeur >>> 16) & 255;
        int o3 = (valeur >>>  8) & 255;
        int o4 =  valeur         & 255;
        return o1 + "." + o2 + "." + o3 + "." + o4;
    }

    /**
     * Calcule le nombre TOTAL d'adresses dans un bloc (broadcast inclus).
     * Formule : 2^(32 - cidr)  ← sans le -2 car on compte TOUTES les adresses
     * Utilisé par MoteurVLSM pour faire avancer l'adresse courante.
     * Exemple : /25 → 128 adresses au total (126 utilisables + réseau + broadcast)
     */
    public static int calculerTailleBloc(int cidr) {
        return (int) Math.pow(2, 32 - cidr);
    }

    // ════════════════════════════════════════════════════════════════════
    // §16 TRAVAIL SUPPLÉMENTAIRE : plages utilisables
    // ════════════════════════════════════════════════════════════════════

    /**
     * Retourne la première adresse utilisable d'un sous-réseau.
     * Principe : adresse réseau + 1
     * Exemple : 192.168.2.0 → 192.168.2.1
     */
    public static String calculerPremiereAdresseUtilisable(String adresseReseau) {
        int entier = convertirIpEnEntier(adresseReseau);
        return convertirEntierEnIp(entier + 1);
    }

    /**
     * Retourne la dernière adresse utilisable d'un sous-réseau.
     * Principe : adresse broadcast - 1
     * Broadcast = adresse réseau + taille du bloc - 1
     * Exemple : 192.168.2.0/26 → broadcast 192.168.2.63 → dernière utile 192.168.2.62
     */
    public static String calculerDerniereAdresseUtilisable(String adresseReseau, int cidr) {
        int entier    = convertirIpEnEntier(adresseReseau);
        int broadcast = entier + calculerTailleBloc(cidr) - 1;
        return convertirEntierEnIp(broadcast - 1);
    }
}
