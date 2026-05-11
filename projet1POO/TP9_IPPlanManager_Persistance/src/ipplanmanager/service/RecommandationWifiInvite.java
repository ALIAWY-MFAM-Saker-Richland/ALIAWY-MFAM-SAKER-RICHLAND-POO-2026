package ipplanmanager.service;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.VLAN;

public class RecommandationWifiInvite
        implements InterfaceRegleRecommandation {

    @Override
    public Recommandation analyser(VLAN vlan) {

        if (vlan.getNom()
                .toUpperCase()
                .contains("WIFI")) {

            return new Recommandation(
                    "MOYENNE",
                    "Isolation WIFI",
                    "Configurer une isolation stricte du réseau invité."
            );
        }

        return null;
    }
}