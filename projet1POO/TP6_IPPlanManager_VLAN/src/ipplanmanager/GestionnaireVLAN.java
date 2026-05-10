package ipplanmanager;

import java.util.ArrayList;

public class GestionnaireVLAN {

    private ArrayList<VLAN> vlans;

    public GestionnaireVLAN() {

        vlans = new ArrayList<>();
    }

    /*
     AJOUT VLAN
     */
    public void ajouterVLAN(
            VLAN vlan) {

        vlans.add(vlan);
    }

    /*
     AFFICHAGE COMPLET
     */
    public void afficherTousLesVLANs() {

        for (VLAN vlan : vlans) {

            vlan.afficher();

            System.out.println();
        }
    }

    /*
     RECHERCHE VLAN
     */
    public VLAN rechercherVLAN(
            int id) {

        for (VLAN vlan : vlans) {

            if (vlan.getId() == id) {

                return vlan;
            }
        }

        return null;
    }

    /*
     NOMBRE TOTAL
     */
    public int obtenirNombreVLANs() {

        return vlans.size();
    }

    /*
     TRAVAIL SUPPLEMENTAIRE
     */
    public void afficherVLANsCritiques() {

        for (VLAN vlan : vlans) {

            if (vlan.getReseauAssocie()
                    .getCapacite() > 100) {

                System.out.println(
                        "VLAN critique detecte :");

                System.out.println(
                        "VLAN "
                        + vlan.getId()
                        + " - "
                        + vlan.getNom()
                        + " - "
                        + vlan.getReseauAssocie()
                                .getCapacite()
                        + " hotes");

                System.out.println();
            }
        }
    }

    /*
     VLAN PLUS GRANDE CAPACITE
     */
    public VLAN obtenirPlusGrandVLAN() {

        if (vlans.isEmpty()) {

            return null;
        }

        VLAN plusGrand =
                vlans.get(0);

        for (VLAN vlan : vlans) {

            if (vlan.getReseauAssocie()
                    .getCapacite()
                    > plusGrand
                    .getReseauAssocie()
                    .getCapacite()) {

                plusGrand = vlan;
            }
        }

        return plusGrand;
    }
}