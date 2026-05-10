package ipplanmanager;

public class RecommandationAdministration implements RegleRecommandation {

    @Override
    public Recommandation analyser(VLAN vlan) {

        String nom = vlan.getNom().toUpperCase();

        if (nom.contains("ADMIN")
                || nom.contains("ADMINISTRATION")) {

            return new Recommandation(
                    "Securisation du VLAN Administration",
                    "ELEVEE",
                    "Le VLAN " + vlan.getNom()
                    + " doit etre accessible uniquement aux administrateurs reseau."
            );
        }

        return null;
    }
}