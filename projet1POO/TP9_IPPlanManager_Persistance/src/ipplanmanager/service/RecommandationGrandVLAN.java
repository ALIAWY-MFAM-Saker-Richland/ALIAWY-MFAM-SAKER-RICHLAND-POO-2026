package ipplanmanager.service;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.VLAN;

public class RecommandationGrandVLAN
        implements InterfaceRegleRecommandation {

    @Override
    public Recommandation analyser(VLAN vlan) {

        if (vlan.getReseauAssocie() != null
                && vlan.getReseauAssocie().getCapacite() > 250) {

            return new Recommandation(
                    "MOYENNE",
                    "Grand VLAN",
                    "Prévoir une supervision réseau renforcée."
            );
        }

        return null;
    }
}