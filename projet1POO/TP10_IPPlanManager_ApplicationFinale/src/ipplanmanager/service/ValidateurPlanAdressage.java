package ipplanmanager.service;

import ipplanmanager.exception.AdresseIPInvalideException;
import ipplanmanager.exception.ChevauchementReseauException;
import ipplanmanager.model.ResultatVLSM;
import java.util.ArrayList;

public class ValidateurPlanAdressage {

    public void validerAdresseIP(String adresse)
            throws AdresseIPInvalideException {

        String[] parties = adresse.split("\\.");
        if (parties.length != 4) {
            throw new AdresseIPInvalideException(
                "Adresse IP invalide : " + adresse + " (doit contenir 4 parties)."
            );
        }
        try {
            for (String partie : parties) {
                int valeur = Integer.parseInt(partie);
                if (valeur < 0 || valeur > 255) {
                    throw new AdresseIPInvalideException(
                        "Adresse IP invalide : valeur hors limite (" + valeur + ")."
                    );
                }
            }
        } catch (NumberFormatException e) {
            throw new AdresseIPInvalideException(
                "Adresse IP invalide : caractères non numériques détectés."
            );
        }
    }

    public void validerPlan(ArrayList<ResultatVLSM> resultats)
            throws ChevauchementReseauException {
        for (int i = 0; i < resultats.size(); i++) {
            for (int j = i + 1; j < resultats.size(); j++) {
                ResultatVLSM r1 = resultats.get(i);
                ResultatVLSM r2 = resultats.get(j);
                if (r1.getAdresseReseau().equals(r2.getAdresseReseau())) {
                    throw new ChevauchementReseauException(
                        "Chevauchement entre " + r1.getNomBesoin()
                        + " et " + r2.getNomBesoin()
                        + " sur " + r1.getAdresseReseau()
                    );
                }
            }
        }
    }
}