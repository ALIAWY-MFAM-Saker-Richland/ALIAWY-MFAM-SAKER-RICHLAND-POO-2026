package ipplanmanager;

public class VLAN {

    private int id;
    private String nom;
    private ResultatVLSM reseau;
    private String description;

    public VLAN(int id, String nom, ResultatVLSM reseau, String description) {
        this.id = id;
        this.nom = nom;
        this.reseau = reseau;
        this.description = description;
    }

    public int getId() {
        return id;
    }
}