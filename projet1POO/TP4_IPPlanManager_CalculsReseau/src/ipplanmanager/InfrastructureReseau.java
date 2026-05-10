package ipplanmanager;

import java.util.ArrayList;

public class InfrastructureReseau {

    private String nom;
    private ArrayList<Equipement> equipements;
    private ArrayList<SousReseau> sousReseaux;

    public InfrastructureReseau(String nom) {
        this.nom = nom;
        equipements  = new ArrayList<>();
        sousReseaux  = new ArrayList<>();
    }

    public void ajouterEquipement(Equipement equipement) {
        equipements.add(equipement);
    }

    public void ajouterSousReseau(SousReseau sousReseau) {
        sousReseaux.add(sousReseau);
    }

    public void afficherEquipements() {
        for (Equipement eq : equipements) {
            eq.afficher();
            System.out.println();
        }
    }

    public void afficherSousReseaux() {
        for (SousReseau sr : sousReseaux) {
            sr.afficher();
            System.out.println();
        }
    }

    public void afficher() {
        System.out.println("Infrastructure : " + nom);
        System.out.println();
        System.out.println("===== SOUS-RÉSEAUX =====");
        afficherSousReseaux();
        System.out.println("===== ÉQUIPEMENTS =====");
        afficherEquipements();
    }

    public void rechercherEquipement(String nom) {
        System.out.println(">>> Recherche : " + nom);
        for (Equipement eq : equipements) {
            if (eq.getNom().equalsIgnoreCase(nom)) {
                System.out.println("Équipement trouvé :");
                eq.afficher();
                return;
            }
        }
        System.out.println("Équipement introuvable.");
    }
}
