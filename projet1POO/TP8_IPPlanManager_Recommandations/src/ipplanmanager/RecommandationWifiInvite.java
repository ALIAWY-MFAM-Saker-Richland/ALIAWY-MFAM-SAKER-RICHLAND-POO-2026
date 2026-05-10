package ipplanmanager;

public class RecommandationWifiInvite implements RegleRecommandation {

    @Override
    public Recommandation analyser(VLAN vlan) {

        if (vlan.getNom().toUpperCase().contains("WIFI")) {

            return new Recommandation(
                    "Isolation du WiFi",
                    "ELEVEE",
                    "Le VLAN " + vlan.getNom()
                    + " doit etre isole des VLANs internes sensibles."
            );
        }

        return null;
    }
}