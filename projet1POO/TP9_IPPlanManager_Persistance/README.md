NOM: ALIAWY MFAM SAKER RICHLAND 

# TP9 - Persistance et organisation professionnelle

## Objectif
L'objectif de ce TP est de rendre l'application **IPPlan-Manager** capable de conserver ses données. Nous avons implémenté la lecture et l'écriture de fichiers afin de sauvegarder les besoins, les plans d'adressage, les VLANs, les recommandations techniques et de générer des rapports complets. Ce TP marque également le passage à une architecture logicielle professionnelle segmentée en packages.

## Notions étudiées
*   **Persistance des données** : Sauvegarde durable sur support physique (fichiers).
*   **Fichiers CSV** : Utilisation d'un format structuré pour l'échange de données.
*   **Lecture/Écriture de fichiers** : Utilisation des classes `FileWriter`, `BufferedReader` et `FileReader`.
*   **Architecture en packages** : Organisation du code en couches distinctes (`model`, `service`, `repository`, `exception`, `main`).
*   **Séparation des responsabilités** : Distinction claire entre le stockage des données et les traitements métiers.

## Fichiers utilisés
*   **Fichiers d'entrée** : `besoins.csv`, `besoins_pme.csv` (contenant les exigences utilisateurs).
*   **Fichiers générés (dossier exports/)** : 
    *   `plan_adressage.csv` (résultats VLSM).
    *   `vlans.csv` (liste des segments logiques).
    *   `recommandations.txt` (conseils techniques).
    *   `rapport_complet.txt` et `rapport_pme.txt` (rapports détaillés pour l'administrateur).

## Scénarios testés
1.  **Scénario de base** : Lecture du fichier `besoins.csv`, génération automatique du plan et export des résultats.
2.  **Scénario PME (Travail demandé)** : Test de flexibilité en changeant le fichier d'entrée pour `besoins_pme.csv` (Administration, Comptabilité, WiFi, etc.) et génération d'un rapport spécifique.

## Difficultés rencontrées
*   Gestion des imports suite au déplacement des classes dans les nouveaux packages.
*   Manipulation des flux de données (`IOException`) et fermeture obligatoire des fichiers pour garantir l'écriture.
*   Découpage des lignes CSV avec le séparateur `;` et conversion des types de données.

## Réponses aux questions (Section 18)

1.  **Qu'est-ce que la persistance des données ?** C'est la capacité d'une application à enregistrer ses données sur un support durable (disque dur) pour qu'elles ne disparaissent pas à la fermeture du programme.
2.  **Pourquoi une application professionnelle doit-elle sauvegarder ses résultats ?** Pour permettre l'archivage, le partage entre équipes techniques, la réutilisation ultérieure des données et la traçabilité des configurations.
3.  **Quelle est la différence entre un fichier CSV et un rapport texte ?** Le CSV est un format structuré destiné à être lu par des machines ou des tableurs (Excel). Le rapport texte est un document mis en forme destiné à être lu par des humains.
4.  **Pourquoi a-t-on créé un package repository ?** Pour isoler tout le code chargé de l'accès aux données (lecture/écriture) du reste de la logique métier.
5.  **Pourquoi a-t-on créé un package service ?** Pour regrouper les classes qui effectuent des traitements et des calculs (le "cerveau" de l'application).
6.  **Pourquoi ne faut-il pas écrire tout le code dans la classe Main ?** Cela rendrait le code illisible, impossible à tester et très difficile à maintenir ou à faire évoluer.
7.  **Pourquoi le fichier besoins.csv rend-il l’application plus flexible ?** Car il permet de changer les besoins du réseau sans avoir à modifier et recompiler le code source Java.
8.  **Pourquoi la séparation en packages améliore-t-elle la maintenabilité du projet ?** Car elle permet de localiser rapidement une erreur et de modifier une partie du système (ex: changer le format de fichier) sans impacter les autres (ex: le moteur de calcul).

---
*Projet réalisé sous NetBeans - Promotion 2026*
