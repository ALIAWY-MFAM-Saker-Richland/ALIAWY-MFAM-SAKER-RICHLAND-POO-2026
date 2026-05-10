package ipplanmanager;

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
                        "Le VLAN "
                        + vlan.getId()
                        + " existe deja."
                );
            }
        }

        vlans.add(vlan);
    }

    public ArrayList<VLAN> getVlans() {
        return vlans;
    }

    public void afficherTousLesVLANs() {

        for (VLAN vlan : vlans) {
            vlan.afficher();
        }
    }
}