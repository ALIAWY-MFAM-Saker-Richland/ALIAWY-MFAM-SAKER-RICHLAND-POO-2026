package ipplanmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Classe MoteurVLSM
 * NOUVELLE CLASSE TP5 — Premier vrai SERVICE MÉTIER du projet.
 *
 * Reçoit : une adresse réseau de départ + une liste de besoins utilisateur.
 * Produit : une liste de résultats VLSM avec les sous-réseaux calculés.
 *
 * ALGORITHME (3 étapes) :
 *   1. Trier les besoins par nombre d'hôtes DÉCROISSANT
 *      (plus grand besoin en premier pour éviter la fragmentation).
 *   2. Pour chaque besoin, calculer le plus petit CIDR suffisant.
 *   3. Attribuer l'adresse courante, puis avancer de la taille du bloc.
 *
 * DIFFÉRENCE IMPORTANTE :
 *   calculerTailleBloc(cidr)  = 2^(32-cidr)     ← taille totale (avec broadcast)
 *   calculerNombreHotes(cidr) = 2^(32-cidr) - 2 ← hôtes utilisables seulement
 *   On utilise calculerTailleBloc pour faire avancer l'adresse courante.
 */
public class MoteurVLSM {

    /**
     * Génère automatiquement un plan d'adressage VLSM.
     *
     * @param adresseDepart L'adresse du réseau de départ (ex : "192.168.1.0")
     * @param besoins       La liste des besoins utilisateur (modifiée par le tri)
     * @return              La liste des résultats VLSM dans l'ordre de traitement
     */
    public ArrayList<ResultatVLSM> genererPlan(String adresseDepart,
                                               ArrayList<BesoinReseau> besoins) {

        ArrayList<ResultatVLSM> resultats = new ArrayList<>();

        // ── ÉTAPE 1 : tri décroissant par nombre d'hôtes ──────────────────
        // b2 - b1 donne l'ordre décroissant (plus grand en premier).
        // IMPORTANT : ce tri modifie la liste besoins passée en paramètre.
        Collections.sort(besoins, new Comparator<BesoinReseau>() {
            @Override
            public int compare(BesoinReseau b1, BesoinReseau b2) {
                return b2.getNombreHotes() - b1.getNombreHotes();
            }
        });

        // ── ÉTAPE 2 & 3 : calcul et attribution des sous-réseaux ──────────
        int adresseCourante = CalculateurReseau.convertirIpEnEntier(adresseDepart);

        for (BesoinReseau besoin : besoins) {

            // Trouver le plus petit CIDR dont la capacité >= besoin
            int cidr     = CalculateurReseau.calculerCidrPourHotes(besoin.getNombreHotes());
            int capacite = CalculateurReseau.calculerNombreHotes(cidr);
            String masque  = CalculateurReseau.obtenirMasqueDecimal(cidr);
            String adresse = CalculateurReseau.convertirEntierEnIp(adresseCourante);

            // Créer le résultat (les plages §16 sont calculées dans le constructeur)
            ResultatVLSM resultat = new ResultatVLSM(
                    besoin.getNom(), adresse, cidr, masque, capacite);
            resultats.add(resultat);

            // Avancer l'adresse courante au sous-réseau suivant
            // calculerTailleBloc = 2^(32-cidr) : taille totale, broadcast inclus
            int tailleBloc = CalculateurReseau.calculerTailleBloc(cidr);
            adresseCourante = adresseCourante + tailleBloc;
        }

        return resultats;
    }
}
