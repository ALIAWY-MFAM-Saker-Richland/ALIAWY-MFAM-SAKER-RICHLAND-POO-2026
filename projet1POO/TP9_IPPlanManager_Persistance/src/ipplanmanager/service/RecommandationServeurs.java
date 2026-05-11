package ipplanmanager.service;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.VLAN;

public class RecommandationServeurs
        implements InterfaceRegleRecommandation {

    @Override
    public Recommandation analyser(VLAN vlan) {

        if (vlan.getNom()
                .toUpperCase()
                .contains("SERVEUR")) {

            return new Recommandation(
                    "HAUTE",
                    "Protection Serveurs",
                    "Mettre en place des ACL et un pare-feu."
            );
        }

        return null;
    }
}