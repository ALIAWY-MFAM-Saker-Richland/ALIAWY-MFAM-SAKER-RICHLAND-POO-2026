package ipplanmanager;

public class RecommandationGrandVLAN implements RegleRecommandation {

    @Override
    public Recommandation analyser(VLAN vlan) {

        if (vlan.getReseauAssocie() != null
                && vlan.getReseauAssocie().getCapacite() > 200) {

            return new Recommandation(
                    "VLAN de grande taille",
                    "MOYENNE",
                    "Le VLAN " + vlan.getNom()
                    + " possede une grande capacite. Il faut surveiller les broadcasts."
            );
        }

        return null;
    }
}