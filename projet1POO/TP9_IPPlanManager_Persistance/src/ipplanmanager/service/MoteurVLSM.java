package ipplanmanager.service;

import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.ResultatVLSM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MoteurVLSM {

    public ArrayList<ResultatVLSM> genererPlan(
            String adresseBase,
            ArrayList<BesoinReseau> besoins) {

        ArrayList<ResultatVLSM> resultats =
                new ArrayList<>();

        Collections.sort(
                besoins,
                Comparator.comparingInt(
                        BesoinReseau::getNombreHotes
                ).reversed()
        );

        String adresseCourante = adresseBase;

        CalculateurReseau calculateur =
                new CalculateurReseau();

        for (BesoinReseau besoin : besoins) {

            int cidr =
                    calculateur.calculerCIDR(
                            besoin.getNombreHotes()
                    );

            int capacite =
                    calculateur.calculerCapacite(cidr);

            ResultatVLSM resultat =
                    new ResultatVLSM(
                            besoin.getNom(),
                            adresseCourante,
                            cidr,
                            capacite
                    );

            resultats.add(resultat);

            adresseCourante =
                    incrementerAdresse(
                            adresseCourante,
                            capacite + 2
                    );
        }

        return resultats;
    }

    private String incrementerAdresse(
            String adresse,
            int increment) {

        String[] parties = adresse.split("\\.");

        int a = Integer.parseInt(parties[0]);
        int b = Integer.parseInt(parties[1]);
        int c = Integer.parseInt(parties[2]);
        int d = Integer.parseInt(parties[3]);

        int valeur =
                (a << 24)
                        | (b << 16)
                        | (c << 8)
                        | d;

        valeur += increment;

        a = (valeur >> 24) & 255;
        b = (valeur >> 16) & 255;
        c = (valeur >> 8) & 255;
        d = valeur & 255;

        return a + "." + b + "." + c + "." + d;
    }
}