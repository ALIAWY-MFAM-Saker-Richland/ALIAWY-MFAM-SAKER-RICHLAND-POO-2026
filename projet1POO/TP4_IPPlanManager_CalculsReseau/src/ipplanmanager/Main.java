package ipplanmanager;

public class Main {
    public static void main(String[] args) {

        InfrastructureReseau infrastructure =
                new InfrastructureReseau("Infrastructure YFY");

        ReseauIP reseauAdmin =
                new ReseauIP(
                        "192.168.1.0",
                        24,
                        "Réseau administration"
                );

        ReseauIP reseauTechnique =
                new ReseauIP(
                        "172.16.0.0",
                        16,
                        "Réseau technique"
                );

        ReseauIP reseauWifi =
                new ReseauIP(
                        "10.0.0.0",
                        8,
                        "Réseau WIFI"
                );

        SousReseau admin =
                new SousReseau(
                        "ADMIN",
                        reseauAdmin
                );

        SousReseau tech =
                new SousReseau(
                        "TECH",
                        reseauTechnique
                );

        SousReseau wifi =
                new SousReseau(
                        "WIFI",
                        reseauWifi
                );

        infrastructure.ajouterSousReseau(admin);
        infrastructure.ajouterSousReseau(tech);
        infrastructure.ajouterSousReseau(wifi);

        infrastructure.afficher();
    }
}