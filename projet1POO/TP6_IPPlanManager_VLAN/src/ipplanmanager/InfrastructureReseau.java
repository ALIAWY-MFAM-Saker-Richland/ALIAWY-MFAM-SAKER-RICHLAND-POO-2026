package ipplanmanager;

import java.util.ArrayList;

public class InfrastructureReseau {

    private String nom;
    private ArrayList<SousReseau> sousReseaux;

    public InfrastructureReseau(String nom) {

        this.nom = nom;
        sousReseaux = new ArrayList<>();
    }

    public void ajouterSousReseau(
            SousReseau sousReseau) {

        sousReseaux.add(sousReseau);
    }

    public void afficherInfrastructure() {

        System.out.println(
                "Infrastructure : " + nom);

        for (SousReseau sr : sousReseaux) {

            System.out.println("----------------");

            sr.afficher();
        }
    }
}