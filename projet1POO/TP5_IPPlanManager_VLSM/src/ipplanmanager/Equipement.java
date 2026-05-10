package ipplanmanager;

import java.util.ArrayList;

/**
 * Classe Equipement
 * Représente un équipement réseau (routeur, switch, serveur...).
 * Contient une liste dynamique d'interfaces réseau (composition TP3).
 * Reprise du TP4 sans modification.
 */
public class Equipement {

    private String                     nom;
    private String                     type;
    private ArrayList<InterfaceReseau> interfaces;

    // ── Constructeur ──────────────────────────────────────────────────────
    public Equipement(String nom, String type) {
        setNom(nom);
        setType(type);
        interfaces = new ArrayList<>();
    }

    // ── Getters / Setters ─────────────────────────────────────────────────
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = (nom == null || nom.isEmpty()) ? "equipement_inconnu" : nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = (type == null || type.isEmpty()) ? "type_inconnu" : type;
    }

    // ── Gestion des interfaces ────────────────────────────────────────────
    public void ajouterInterface(InterfaceReseau interfaceReseau) {
        interfaces.add(interfaceReseau);
    }

    public void afficherInterfaces() {
        for (InterfaceReseau ir : interfaces) {
            ir.afficher();
            System.out.println();
        }
    }

    // ── Affichage ─────────────────────────────────────────────────────────
    public void afficher() {
        System.out.println("  Nom                  : " + nom);
        System.out.println("  Type                 : " + type);
        System.out.println("  Nombre d'interfaces  : " + interfaces.size());
        afficherInterfaces();
    }
}
