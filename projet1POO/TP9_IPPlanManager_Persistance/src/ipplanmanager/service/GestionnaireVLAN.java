package ipplanmanager.service;

import ipplanmanager.exception.ConflitVLANException;
import ipplanmanager.model.VLAN;

import java.util.ArrayList;

public class GestionnaireVLAN {

    private ArrayList<VLAN> vlans;

    public GestionnaireVLAN() {
        vlans = new ArrayList<>();
    }

    public void ajouterVLAN(VLAN vlan)
            throws ConflitVLANException {

        for (VLAN v : vlans) {

            if (v.getId() == vlan.getId()) {

                throw new ConflitVLANException(
                        "Conflit VLAN : ID "
                                + vlan.getId()
                                + " déjà utilisé."
                );
            }
        }

        vlans.add(vlan);
    }

    public ArrayList<VLAN> getVlans() {
        return vlans;
    }
}