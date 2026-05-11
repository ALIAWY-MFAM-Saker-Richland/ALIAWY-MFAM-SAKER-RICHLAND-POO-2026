package ipplanmanager.service;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.VLAN;

public class RecommandationGrandVLAN
        implements InterfaceRegleRecommandation {
    @Override
    public Recommandation analyser(VLAN vlan) {
        String nom = vlan.getNom().toUpperCase();
        if (nom.contains("ETUDIANT") || nom.contains("INVIT")) {
            return new Recommandation(
                "MOYENNE",
                "Grand VLAN",
                "Prévoir une supervision réseau renforcée pour le VLAN "
                    + vlan.getNom() + "."
            );
        }
        return null;
    }
}