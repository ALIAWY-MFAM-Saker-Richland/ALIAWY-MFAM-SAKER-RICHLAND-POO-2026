NOM: ALIAWY MFAM SAKER RICHLAND 

# TP10 - Application finale IPPlan-Manager

## Objectif
L'objectif de ce TP ultime est d'assembler toutes les fonctionnalités développées progressivement durant les TPs précédents. Il s'agit de produire une **application console complète, cohérente et professionnelle** dédiée à la planification d'adressage IP. L'outil permet désormais de passer de la saisie des besoins à la génération d'un rapport technique complet en une seule chaîne de traitement.

## Fonctionnalités réalisées
- **Interface Utilisateur** : Menu console interactif pour la saisie des exigences.
- **Moteur VLSM** : Génération automatisée et optimisée du plan d'adressage.
- **Segmentation Logique** : Création automatique des VLANs correspondants.
- **Moteur de Recommandations** : Analyse intelligente et conseils techniques.
- **Validation** : Détection des erreurs (conflits IP, chevauchements, erreurs VLAN).
- **Persistance** : Sauvegarde des résultats en formats CSV et génération d'un rapport technique final.
- **Extensibilité** : Option de chargement des besoins via un fichier CSV externe.

## Organisation du projet
Le projet est structuré en **6 packages** pour respecter une architecture logicielle professionnelle :
*   **ipplanmanager.model** : Objets métier (Besoin, Resultat, VLAN, Recommandation).
*   **ipplanmanager.service** : Logique de traitement (Moteur VLSM, Application principale).
*   **ipplanmanager.repository** : Accès aux données et persistance (Lecture/Écriture).
*   **ipplanmanager.exception** : Exceptions personnalisées pour la gestion des erreurs.
*   **ipplanmanager.console** : Gestion des interactions utilisateur (ConsoleService).
*   **ipplanmanager.main** : Point d'entrée unique de l'application.

## Scénarios testés
1.  **Campus IRT** : Gestion de 5 besoins (Etudiants, Wifi, Enseignants, Laboratoires, Serveurs) pour un total de près de 1000 hôtes.
2.  **PME** : Planification pour 5 départements (Admin, Compta, Wifi, Serveurs, VOIP).
3.  **Entreprise Multi-services** : Test de robustesse avec des services variés (Technique, Direction, Caméras, Support, Invités).

## Fichiers générés
Tous les résultats sont exportés dans le dossier **`exports/`** :
*   `[NomDuProjet]_plan.csv`
*   `[NomDuProjet]_vlans.csv`
*   `[NomDuProjet]_recommandations.txt`
*   `[NomDuProjet]_rapport.txt`

## Difficultés rencontrées
- L'orchestration globale de toutes les classes dans `ApplicationIPPlanManager` pour garantir un flux sans erreur.
- La gestion rigoureuse des types de données lors des calculs de masques et de marges pour les grands réseaux.

## Réponses aux questions (Section 18)
1. **Pourquoi le TP10 est-il plus complet ?** Parce qu'il n'est plus un test de fonctionnalités isolées mais une solution intégrée prête à l'emploi.
2. **Rôle de ApplicationIPPlanManager ?** C'est l'orchestrateur qui coordonne les saisies, les calculs, les validations et les sauvegardes.
3. **Pourquoi la classe Main doit-elle rester courte ?** Pour séparer le lancement de l'application de sa logique interne, facilitant ainsi la maintenance.
4. **Pourquoi séparer les packages ?** Pour assurer la modularité : on peut modifier l'affichage sans toucher au moteur de calcul.
5. **Pourquoi ConsoleService pour la saisie ?** Pour centraliser les interactions `Scanner` et éviter de dupliquer du code de saisie partout.
6. **Pourquoi valider l'adresse réseau au départ ?** Pour s'assurer que toute la chaîne de calcul VLSM reposera sur une base saine.
7. **Pourquoi les recommandations après les VLANs ?** Car certaines règles dépendent de l'existence des VLANs pour être analysées.
8. **Pourquoi la sauvegarde rend l'appli exploitable ?** Car elle permet de transmettre les résultats aux techniciens via des fichiers standards.
9. **Importance du rapport technique ?** Il sert de documentation officielle de référence pour l'infrastructure réseau.
10. **Améliorations futures ?** Une interface graphique (GUI), la gestion de l'IPv6, ou une connexion à une base de données SQL.

## Conclusion personnelle
Ce projet m'a permis de comprendre que la Programmation Orientée Objet (POO) est un outil puissant pour modéliser des problèmes complexes. En divisant les responsabilités entre différents objets, on obtient une application robuste, facile à faire évoluer et réellement utile pour un ingénieur réseau.

---
*Projet réalisé sous NetBeans - Par Saker Richland - Promotion 2026*

