package ipplanmanager;

import java.util.ArrayList;

public class ValidateurPlanAdressage {

    public void verifierAdresses(ArrayList<ResultatVLSM> resultats)
            throws AdresseIPInvalideException {

        for (ResultatVLSM r : resultats) {
            CalculateurReseau.verifierAdresseIP(r.getAdresseReseau());
        }
    }

    public void verifierChevauchements(ArrayList<ResultatVLSM> resultats)
            throws ChevauchementReseauException {

        for (int i = 0; i < resultats.size(); i++) {
            for (int j = i + 1; j < resultats.size(); j++) {

                ResultatVLSM r1 = resultats.get(i);
                ResultatVLSM r2 = resultats.get(j);

                boolean conflit = CalculateurReseau.reseauxSeChevauchent(
                        r1.getAdresseReseau(), r1.getCidr(),
                        r2.getAdresseReseau(), r2.getCidr()
                );

                if (conflit) {
                    throw new ChevauchementReseauException(
                            "Chevauchement entre " +
                            r1.getNomBesoin() + " et " +
                            r2.getNomBesoin()
                    );
                }
            }
        }
    }
}