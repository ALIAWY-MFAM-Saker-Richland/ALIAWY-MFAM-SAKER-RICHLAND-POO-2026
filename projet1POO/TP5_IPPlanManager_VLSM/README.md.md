NOM: ALIAWY MFAM SAKER RICHLAND 
# TP5 - Moteur VLSM

## Objectif
Développer un moteur VLSM capable de proposer automatiquement un plan d'adressage IP optimisé à partir des besoins en nombre d'hôtes exprimés par l'utilisateur.

## Notions étudiées
*   **VLSM (Variable Length Subnet Mask)** : Segmentation de réseau de taille variable.
*   **Tri de collections** : Utilisation de `Comparator` pour traiter les besoins par ordre décroissant.
*   **Classe de service métier** : Création d'une logique algorithmique avec `MoteurVLSM`.
*   **Calcul CIDR automatique** : Transformation d'un besoin d'hôtes en masque réseau adapté.
*   **Conversion IP-entier** : Manipulation mathématique des adresses pour automatiser l'incrémentation des blocs réseau.

## Scénarios testés
1.  **Scénario Entreprise (Initial)** : TECHNIQUE (120), WIFI (80), ADMINISTRATION (50), SERVEURS (20), DIRECTION (10).
2.  **Scénario Petite Entreprise (Travail demandé)** : WIFI_INVITES (40), ADMIN (25), COMPTABILITE (12), SERVEURS (8).
3.  **Scénario Campus (Travail demandé)** : ETUDIANTS (500), WIFI_PUBLIC (200), PERSONNEL (120), LABORATOIRE (60), ADMINISTRATION (40).

## Résultats obtenus
Les plans d'adressage générés respectent la hiérarchie des tailles. Par exemple, pour le scénario Campus :
- ETUDIANTS reçoit un /23 (510 hôtes).
- WIFI_PUBLIC reçoit un /24 (254 hôtes).
- Le moteur décale l'adresse réseau suivante en fonction de la taille du bloc précédent pour éviter tout chevauchement.

## Difficultés rencontrées
*   La conversion des adresses IP en entiers `int` (32 bits) pour effectuer l'addition des tailles de blocs.
*   La mise en place du `Comparator` pour le tri décroissant des besoins, essentiel pour ne pas fragmenter l'espace d'adressage.

## Réponses aux questions (Section 17)

**1. Pourquoi le VLSM permet-il d’économiser les adresses IP ?**
Le VLSM permet d'adapter la taille du masque au besoin réel de chaque service. Au lieu d'utiliser un masque fixe (ex: /24) pour tout le monde, on peut attribuer un /29 pour 4 hôtes et un /26 pour 50 hôtes, limitant ainsi le nombre d'adresses inutilisées.

**2. Pourquoi faut-il traiter les plus grands besoins en premier ?**
Pour éviter la fragmentation de l'espace d'adressage. En plaçant les grands blocs au début, on s'assure qu'il reste des segments contigus suffisamment larges pour les accueillir. Commencer par les petits besoins pourrait "casser" les plages disponibles.

**3. Quelle est la différence entre un besoin réseau et un résultat VLSM ?**
Le **besoin** est la donnée d'entrée (ex: "Administration veut 50 hôtes"). Le **résultat** est la donnée calculée par le moteur (ex: "Administration reçoit 192.168.1.0/26").

**4. Pourquoi la classe MoteurVLSM est-elle une classe de service métier ?**
Contrairement aux classes comme `AdresseIP` qui ne font que stocker des données, `MoteurVLSM` contient une logique algorithmique complexe qui transforme des besoins en un plan d'adressage complet.

**5. Pourquoi transforme-t-on une adresse IP en entier pour certains calculs ?**
Une adresse IP sous forme de texte ("192.168.1.0") est impossible à additionner. En la convertissant en un nombre entier unique, on peut simplement ajouter la taille d'un bloc (ex: +128) pour trouver instantanément l'adresse du sous-réseau suivant.

**6. Quel est le rôle de la méthode calculerCidrPourHotes() ?**
Elle automatise le choix du masque. Elle cherche par itération le plus petit CIDR capable de contenir le nombre d'hôtes demandé (2^n - 2 >= besoins).

**7. Pourquoi une adresse de réseau et une adresse de broadcast ne sont-elles pas attribuées aux machines ?**
L'adresse réseau sert d'identifiant pour la route dans les tables de routage, et l'adresse de broadcast est réservée pour envoyer un message à tous les hôtes du segment simultanément.

**8. Pourquoi le moteur VLSM représente-t-il une étape importante dans le projet IPPlan-Manager ?**
C'est le passage d'une application de stockage de données à une application "intelligente" capable d'aider réellement un administrateur système dans ses tâches de planification.
