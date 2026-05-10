package ipplanmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MoteurVLSM {

    private int ipCourante = 0; // compteur global

    public ArrayList<ResultatVLSM> genererPlan(
            String reseauDepart,
            ArrayList<BesoinReseau> besoins) {

        ArrayList<ResultatVLSM> resultats =
                new ArrayList<>();

        // 1. TRI décroissant
        Collections.sort(besoins,
                Comparator.comparingInt(
                        BesoinReseau::getNombreHotes
                ).reversed());

        ipCourante = 0;

        for (BesoinReseau besoin : besoins) {

            int hostDemandes = besoin.getNombreHotes();

            // 2. calcul bits hôtes
            int bits = 0;
            while ((Math.pow(2, bits) - 2) < hostDemandes) {
                bits++;
            }

            int prefixe = 32 - bits;
            int tailleBloc = (int) Math.pow(2, bits);

            int capacite = tailleBloc - 2;

            // 3. ALIGNEMENT VLSM
            ipCourante = aligner(ipCourante, tailleBloc);

            String adresse = convertirIP(ipCourante);

            String masque = calculerMasque(prefixe);

            ResultatVLSM res = new ResultatVLSM(
                    besoin.getNom(),
                    adresse,
                    masque,
                    prefixe,
                    capacite,
                    hostDemandes
            );

            resultats.add(res);

            // 4. avancer IP correctement
            ipCourante += tailleBloc;
        }

        return resultats;
    }

    // ALIGNEMENT VLSM
    private int aligner(int ip, int bloc) {
        return (ip + bloc - 1) / bloc * bloc;
    }

    // conversion IP simple (10.10.x.0)
    private String convertirIP(int ip) {

        int octet3 = (ip / 256) % 256;
        int octet4 = ip % 256;

        return "10.10." + octet3 + "." + octet4;
    }

    private String calculerMasque(int prefixe) {

        int masque = 0xffffffff << (32 - prefixe);

        return ((masque >>> 24) & 255) + "."
                + ((masque >>> 16) & 255) + "."
                + ((masque >>> 8) & 255) + "."
                + (masque & 255);
    }
}