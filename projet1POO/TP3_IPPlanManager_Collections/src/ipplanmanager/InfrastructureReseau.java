package ipplanmanager;

import java.util.ArrayList;

public class InfrastructureReseau {

    private final String nom;

    private final ArrayList<Equipement> equipements;

    private final ArrayList<SousReseau> sousReseaux;

    public InfrastructureReseau(String nom) {

        this.nom = nom;

        equipements = new ArrayList<>();

        sousReseaux = new ArrayList<>();
    }

    public void ajouterEquipement(
            Equipement equipement) {

        equipements.add(equipement);
    }

    public void ajouterSousReseau(
            SousReseau sousReseau) {

        sousReseaux.add(sousReseau);
    }

    public void afficherEquipements() {

        for (Equipement equipement : equipements) {

            equipement.afficher();

            System.out.println();
        }
    }

    public void afficherSousReseaux() {

        for (SousReseau sousReseau : sousReseaux) {

            sousReseau.afficher();

            System.out.println();
        }
    }

    public void rechercherEquipement(String nom) {

        boolean trouve = false;

        for (Equipement equipement : equipements) {

            if (equipement.getNom().equalsIgnoreCase(nom)) {

                System.out.println(
                        "===== Équipement trouvé ====="
                );

                equipement.afficher();

                trouve = true;
            }
        }

        if (!trouve) {

            System.out.println(
                    "Équipement introuvable."
            );
        }
    }

    public void afficher() {

        System.out.println(
                "Infrastructure : " + nom
        );

        System.out.println();

        System.out.println(
                "===== SOUS-RÉSEAUX ====="
        );

        afficherSousReseaux();

        System.out.println();

        System.out.println(
                "===== ÉQUIPEMENTS ====="
        );

        afficherEquipements();
    }
}