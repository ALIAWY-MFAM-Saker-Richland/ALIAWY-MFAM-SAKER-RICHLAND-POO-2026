package ipplanmanager.service;

import ipplanmanager.console.ConsoleService;
import ipplanmanager.exception.AdresseIPInvalideException;
import ipplanmanager.exception.ChevauchementReseauException;
import ipplanmanager.exception.ConflitVLANException;
import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import ipplanmanager.repository.BesoinRepository;
import ipplanmanager.repository.FichierPlanRepository;
import java.io.IOException;
import java.util.ArrayList;

public class ApplicationIPPlanManager {

    private ConsoleService          console;
    private MoteurVLSM              moteurVLSM;
    private GestionnaireVLAN        gestionnaireVLAN;
    private ValidateurPlanAdressage validateur;
    private MoteurRecommandation    moteurRecommandation;
    private FichierPlanRepository   fichierRepository;
    private RapportService          rapportService;

    public ApplicationIPPlanManager() {
        console              = new ConsoleService();
        moteurVLSM           = new MoteurVLSM();
        gestionnaireVLAN     = new GestionnaireVLAN();
        validateur           = new ValidateurPlanAdressage();
        moteurRecommandation = new MoteurRecommandation();
        fichierRepository    = new FichierPlanRepository();
        rapportService       = new RapportService();
    }

    public void demarrer() {
        boolean continuer = true;
        while (continuer) {
            console.afficherMenu();
            int choix = console.saisirEntier("Choix : ");
            switch (choix) {
                case 1: executerGenerationComplete(); break;
                case 2: chargerDepuisCSV();           break;
                case 3:
                    System.out.println("Au revoir !");
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void executerGenerationComplete() {
        try {
            String nomProjet     = console.saisirTexte("Nom du projet réseau : ");
            String adresseDepart = console.saisirTexte("Adresse réseau de départ : ");
            validateur.validerAdresseIP(adresseDepart);

            ArrayList<BesoinReseau> besoins   = console.saisirBesoins();
            ArrayList<ResultatVLSM> resultats = moteurVLSM.genererPlan(adresseDepart, besoins);
            validateur.validerPlan(resultats);

            gestionnaireVLAN = new GestionnaireVLAN();
            genererVLANs(resultats);
            ArrayList<VLAN> vlans = gestionnaireVLAN.getVlans();

            ArrayList<Recommandation> recommandations =
                moteurRecommandation.genererRecommandations(resultats, vlans);

            afficherResultats(resultats, vlans, recommandations);
            sauvegarderResultats(nomProjet, besoins, resultats, vlans, recommandations);

        } catch (AdresseIPInvalideException e)  { System.out.println("Erreur IP : "           + e.getMessage()); }
          catch (ChevauchementReseauException e) { System.out.println("Erreur chevauchement : "+ e.getMessage()); }
          catch (ConflitVLANException e)         { System.out.println("Erreur VLAN : "         + e.getMessage()); }
          catch (IOException e)                  { System.out.println("Erreur fichier : "      + e.getMessage()); }
    }

    private void chargerDepuisCSV() {
        try {
            String chemin = console.saisirTexte("Chemin du fichier CSV : ");
            ArrayList<BesoinReseau> besoins = BesoinRepository.charger(chemin);

            if (besoins.isEmpty()) {
                System.out.println("Aucun besoin trouvé dans le fichier.");
                return;
            }
            System.out.println(besoins.size() + " besoins chargés.");

            String nomProjet     = console.saisirTexte("Nom du projet réseau : ");
            String adresseDepart = console.saisirTexte("Adresse réseau de départ : ");
            validateur.validerAdresseIP(adresseDepart);

            ArrayList<ResultatVLSM> resultats = moteurVLSM.genererPlan(adresseDepart, besoins);
            validateur.validerPlan(resultats);

            gestionnaireVLAN = new GestionnaireVLAN();
            genererVLANs(resultats);
            ArrayList<VLAN> vlans = gestionnaireVLAN.getVlans();

            ArrayList<Recommandation> recommandations =
                moteurRecommandation.genererRecommandations(resultats, vlans);

            afficherResultats(resultats, vlans, recommandations);
            sauvegarderResultats(nomProjet, besoins, resultats, vlans, recommandations);

        } catch (AdresseIPInvalideException e)  { System.out.println("Erreur IP : "           + e.getMessage()); }
          catch (ChevauchementReseauException e) { System.out.println("Erreur chevauchement : "+ e.getMessage()); }
          catch (ConflitVLANException e)         { System.out.println("Erreur VLAN : "         + e.getMessage()); }
          catch (IOException e)                  { System.out.println("Erreur fichier : "      + e.getMessage()); }
    }

    private void genererVLANs(ArrayList<ResultatVLSM> resultats)
            throws ConflitVLANException {
        int numeroVLAN = 10;
        for (ResultatVLSM resultat : resultats) {
            gestionnaireVLAN.ajouterVLAN(new VLAN(
                numeroVLAN,
                resultat.getNomBesoin(),
                resultat.getAdresseReseau()
            ));
            numeroVLAN += 10;
        }
    }

    private void afficherResultats(
            ArrayList<ResultatVLSM> resultats,
            ArrayList<VLAN> vlans,
            ArrayList<Recommandation> recommandations) {

        System.out.println("\n=== Plan d'adressage proposé ===");
        for (ResultatVLSM r : resultats) { r.afficher(); }

        System.out.println("\n=== VLANs créés ===");
        for (VLAN v : vlans) {
            System.out.println("VLAN " + v.getNumero()
                + " - " + v.getNom()
                + " (" + v.getDescription() + ")");
        }

        System.out.println("\n=== Recommandations ===");
        for (Recommandation rec : recommandations) {
            System.out.println("[" + rec.getPriorite() + "] "
                + rec.getTitre() + " : " + rec.getMessage());
        }
    }

    private void sauvegarderResultats(
            String nomProjet,
            ArrayList<BesoinReseau> besoins,
            ArrayList<ResultatVLSM> resultats,
            ArrayList<VLAN> vlans,
            ArrayList<Recommandation> recommandations) throws IOException {

        String prefixe = "exports/" + nomProjet.replace(" ", "_");

        fichierRepository.sauvegarderPlanCSV(resultats, prefixe + "_plan.csv");
        System.out.println("Plan sauvegardé : " + prefixe + "_plan.csv");

        fichierRepository.sauvegarderVLANsCSV(vlans, prefixe + "_vlans.csv");
        System.out.println("VLANs sauvegardés : " + prefixe + "_vlans.csv");

        fichierRepository.sauvegarderRecommandations(
            recommandations, prefixe + "_recommandations.txt");
        System.out.println("Recommandations sauvegardées.");

        rapportService.genererRapport(
            nomProjet, besoins, resultats, vlans,
            recommandations, prefixe + "_rapport.txt");
    }
}