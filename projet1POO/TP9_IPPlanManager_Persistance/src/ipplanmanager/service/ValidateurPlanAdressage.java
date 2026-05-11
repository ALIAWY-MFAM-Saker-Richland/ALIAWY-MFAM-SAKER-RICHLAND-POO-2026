package ipplanmanager.service;

public class ValidateurPlanAdressage {

    public boolean validerAdresseIP(String adresse) {

        String[] parties = adresse.split("\\.");

        if (parties.length != 4) {
            return false;
        }

        try {

            for (String partie : parties) {

                int valeur = Integer.parseInt(partie);

                if (valeur < 0 || valeur > 255) {
                    return false;
                }
            }

        } catch (NumberFormatException e) {

            return false;
        }

        return true;
    }
}