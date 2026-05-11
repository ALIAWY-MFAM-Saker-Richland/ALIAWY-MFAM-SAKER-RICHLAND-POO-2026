package ipplanmanager.model;

public class Recommandation {

    private String priorite;
    private String titre;
    private String message;

    public Recommandation(String priorite,
                          String titre,
                          String message) {

        this.priorite = priorite;
        this.titre = titre;
        this.message = message;
    }

    public String getPriorite() {
        return priorite;
    }

    public String getTitre() {
        return titre;
    }

    public String getMessage() {
        return message;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}