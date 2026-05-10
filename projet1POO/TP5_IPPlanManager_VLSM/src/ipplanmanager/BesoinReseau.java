package ipplanmanager;

/**
 * Classe BesoinReseau
 * NOUVELLE CLASSE TP5
 *
 * Représente une demande exprimée par l'utilisateur AVANT tout calcul.
 * Exemple : le service Administration demande 50 hôtes.
 *
 * Cette classe ne contient PAS d'adresse IP calculée.
 * Elle représente uniquement l'ENTRÉE du moteur VLSM.
 * La SORTIE correspondante sera un objet ResultatVLSM.
 *
 * Séparation entrée/sortie = principe fondamental d'un logiciel professionnel.
 */
public class BesoinReseau {

    private String nom;
    private int    nombreHotes;

    // ── Constructeur ──────────────────────────────────────────────────────
    public BesoinReseau(String nom, int nombreHotes) {
        setNom(nom);
        setNombreHotes(nombreHotes);
    }

    // ── Getters / Setters ─────────────────────────────────────────────────
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            this.nom = "besoin_inconnu";
        } else {
            this.nom = nom;
        }
    }

    public int getNombreHotes() {
        return nombreHotes;
    }

    /**
     * Validation : un besoin de 0 hôtes ou négatif n'a pas de sens.
     * Valeur minimale forcée à 1.
     */
    public void setNombreHotes(int nombreHotes) {
        if (nombreHotes <= 0) {
            this.nombreHotes = 1;
        } else {
            this.nombreHotes = nombreHotes;
        }
    }

    // ── Affichage ─────────────────────────────────────────────────────────
    public void afficher() {
        System.out.println("Besoin : " + nom + " | Hôtes demandés : " + nombreHotes);
    }
}
