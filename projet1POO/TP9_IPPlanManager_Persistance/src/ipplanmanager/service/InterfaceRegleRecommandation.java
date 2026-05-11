package ipplanmanager.service;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.VLAN;

public interface InterfaceRegleRecommandation {

    Recommandation analyser(VLAN vlan);
}