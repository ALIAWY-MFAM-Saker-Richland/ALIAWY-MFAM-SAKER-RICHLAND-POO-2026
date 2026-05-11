package ipplanmanager.service;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.VLAN;

import java.util.ArrayList;

public class MoteurRecommandation {

    private ArrayList<InterfaceRegleRecommandation> regles;

    public MoteurRecommandation() {

        regles = new ArrayList<>();
    }

    public void ajouterRegle(
            InterfaceRegleRecommandation regle) {

        regles.add(regle);
    }

    public ArrayList<Recommandation> analyserVLANs(
            ArrayList<VLAN> vlans) {

        ArrayList<Recommandation> recommandations =
                new ArrayList<>();

        for (VLAN vlan : vlans) {

            for (InterfaceRegleRecommandation regle : regles) {

                Recommandation recommendation =
                        regle.analyser(vlan);

                if (recommendation != null) {

                    recommandations.add(recommendation);
                }
            }
        }

        return recommandations;
    }
}