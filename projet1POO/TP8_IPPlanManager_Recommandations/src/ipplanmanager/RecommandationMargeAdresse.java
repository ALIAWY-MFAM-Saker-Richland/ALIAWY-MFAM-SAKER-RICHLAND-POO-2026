package ipplanmanager;

public class RecommandationMargeAdresse
        implements RegleRecommandation {

    @Override
    public Recommandation analyser(VLAN vlan) {

        if (vlan.getReseauAssocie() != null) {

            int capacite =
                    vlan.getReseauAssocie()
                            .getCapacite();

            int besoin =
                    vlan.getReseauAssocie()
                            .getNombreHotesDemandes();

            int marge =
                    capacite - besoin;

            if (marge <= 10) {

                return new Recommandation(
                        "Marge d'adresses faible",
                        "MOYENNE",
                        "Le VLAN "
                        + vlan.getNom()
                        + " possede une faible marge "
                        + "d'adresses disponibles."
                );
            }
        }

        return null;
    }
}