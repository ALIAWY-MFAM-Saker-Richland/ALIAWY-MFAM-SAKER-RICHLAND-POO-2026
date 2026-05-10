package ipplanmanager;

import java.util.ArrayList;

public class GestionnaireVLAN {

    private ArrayList<VLAN> vlans = new ArrayList<>();

    public void ajouterVLAN(VLAN vlan) throws ConflitVLANException {

        for (VLAN v : vlans) {
            if (v.getId() == vlan.getId()) {
                throw new ConflitVLANException(
                        "Conflit VLAN : l'identifiant " + vlan.getId() + " est déjà utilisé."
                );
            }
        }

        vlans.add(vlan);
    }
}