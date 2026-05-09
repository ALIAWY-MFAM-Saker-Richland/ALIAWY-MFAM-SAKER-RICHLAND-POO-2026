package ipplanmanager;

import java.util.ArrayList;

public class InfrastructureReseau {
    private String nom;
    private ArrayList<SousReseau> sousReseaux;

    public InfrastructureReseau(String nom) {
        this.nom = nom;
        sousReseaux = new ArrayList<>();
    }

    public void ajouterSousReseau(SousReseau sousReseau) {
        sousReseaux.add(sousReseau);
    }

    public void afficher() {
        System.out.println("Infrastructure : " + nom);
        System.out.println();

        for (SousReseau sousReseau : sousReseaux) {
            sousReseau.afficher();
            System.out.println();
        }
    }
}