package ipplanmanager;

import java.util.ArrayList;

public class MoteurVLSM {

    public ArrayList<ResultatVLSM> genererPlan(String adresseBase,
                                               ArrayList<BesoinReseau> besoins) {

        ArrayList<ResultatVLSM> resultats = new ArrayList<>();

        int base = CalculateurReseau.ipToInt(adresseBase);
        int offset = 0;

        for (BesoinReseau b : besoins) {

            int cidr = calculerCIDR(b.getHotes());
            String ip = convertir(base + offset);

            resultats.add(new ResultatVLSM(
                    b.getNom(),
                    ip,
                    cidr
            ));

            offset += CalculateurReseau.tailleBloc(cidr);
        }

        return resultats;
    }

    // Calcul CIDR selon nombre d'hôtes
    private int calculerCIDR(int hotes) {
        int bits = 0;

        while (Math.pow(2, bits) - 2 < hotes) {
            bits++;
        }

        return 32 - bits;
    }

    // Conversion entier → IP
    private String convertir(int val) {

        return ((val >> 24) & 255) + "." +
               ((val >> 16) & 255) + "." +
               ((val >> 8) & 255) + "." +
               (val & 255);
    }
}