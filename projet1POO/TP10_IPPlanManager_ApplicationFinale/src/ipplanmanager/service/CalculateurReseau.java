package ipplanmanager.service;

public class CalculateurReseau {

    public static int convertirIpEnEntier(String ip) {
        String[] parties = ip.split("\\.");
        int resultat = 0;
        for (int i = 0; i < 4; i++) {
            resultat = resultat * 256 + Integer.parseInt(parties[i]);
        }
        return resultat;
    }

    public static String convertirEntierEnIp(int entier) {
        return ((entier >> 24) & 0xFF) + "."
             + ((entier >> 16) & 0xFF) + "."
             + ((entier >> 8)  & 0xFF) + "."
             + ( entier        & 0xFF);
    }

    public static int calculerCidrPourHotes(int hotes) {
        int bits = 0;
        while ((Math.pow(2, bits) - 2) < hotes) {
            bits++;
        }
        return 32 - bits;
    }

    public static int calculerNombreHotes(int cidr) {
        return (int) Math.pow(2, 32 - cidr) - 2;
    }

    public static String obtenirMasqueDecimal(int cidr) {
        int masque = cidr == 0 ? 0 : (0xFFFFFFFF << (32 - cidr));
        return ((masque >> 24) & 0xFF) + "."
             + ((masque >> 16) & 0xFF) + "."
             + ((masque >> 8)  & 0xFF) + "."
             + ( masque        & 0xFF);
    }

    public static int calculerTailleBloc(int cidr) {
        return (int) Math.pow(2, 32 - cidr);
    }
}