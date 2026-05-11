package ipplanmanager.model;

public class VLAN {

    private int id;
    private String nom;
    private ResultatVLSM reseauAssocie;
    private String description;

    public VLAN(int id,
                String nom,
                ResultatVLSM reseauAssocie,
                String description) {

        this.id = id;
        this.nom = nom;
        this.reseauAssocie = reseauAssocie;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public ResultatVLSM getReseauAssocie() {
        return reseauAssocie;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setReseauAssocie(ResultatVLSM reseauAssocie) {
        this.reseauAssocie = reseauAssocie;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}