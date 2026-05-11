package ipplanmanager.model;

public class VLAN {
    private int numero;
    private String nom;
    private String description;

    public VLAN(int numero, String nom, String description) {
        this.numero = numero;
        this.nom = nom;
        this.description = description;
    }

    public int getNumero()        { return numero; }
    public String getNom()        { return nom; }
    public String getDescription(){ return description; }

    public void setNumero(int numero)          { this.numero = numero; }
    public void setNom(String nom)             { this.nom = nom; }
    public void setDescription(String desc)    { this.description = desc; }

    @Override
    public String toString() {
        return "VLAN " + numero + " - " + nom + " (" + description + ")";
    }
}