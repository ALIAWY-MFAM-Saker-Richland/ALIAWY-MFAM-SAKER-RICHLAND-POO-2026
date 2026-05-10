NOM: ALIAWY MFAM SAKER RICHLAND 

# TP6 - VLAN et segmentation logique

## Objectif
L'objectif de ce TP est de mettre en place la gestion des VLANs (Virtual Local Area Networks) dans l'application IPPlan-Manager. Il s'agit d'associer automatiquement les sous-réseaux générés par le moteur VLSM à des segments logiques pour organiser l'infrastructure et isoler les flux de communication.

## Notions étudiées
*   **Segmentation logique** : Découpage d'un réseau physique en plusieurs réseaux virtuels indépendants.
*   **VLAN (Virtual LAN)** : Utilisation des identifiants (IDs) pour marquer et isoler le trafic.
*   **Gestionnaires métier** : Création de la classe `GestionnaireVLAN` pour centraliser les opérations de gestion.
*   **Associations entre objets** : Liaison directe entre un objet `VLAN` et un objet `ResultatVLSM`.
*   **Architecture logicielle** : Séparation des responsabilités entre le calcul (VLSM) et l'organisation (VLAN).

## Scénarios testés
1.  **Scénario Entreprise** : Association de VLANs 10, 20, 30 et 40 aux services TECHNIQUE, WIFI, ADMINISTRATION et SERVEURS.
2.  **Scénario Université (Travail demandé)** : Création de VLANs pour ETUDIANTS (500 hôtes), ENSEIGNANTS (120 hôtes), LABORATOIRES (60 hôtes), WIFI_PUBLIC (200 hôtes) et SERVEURS (30 hôtes).

## Résultats obtenus
L'application génère désormais une liste de VLANs où chaque entrée contient :
- Un ID unique (ex: 10, 20...).
- Le nom du service associé.
- Le détail du sous-réseau calculé (Adresse, Masque, Capacité).
Le système permet également d'identifier les "VLANs critiques" (capacité > 100 hôtes) via le travail supplémentaire.

## Difficultés rencontrées
*   La gestion de la relation entre les listes : s'assurer que chaque résultat du moteur VLSM est correctement passé au constructeur de la classe VLAN.
*   Le respect des limites normatives pour les IDs de VLAN (1 à 4094).

## Réponses aux questions (Section 17)

**1. Pourquoi les VLANs sont-ils importants dans les réseaux modernes ?**
Ils permettent de réduire les domaines de broadcast (pour améliorer les performances) et d'organiser le réseau de façon logique sans tenir compte de la position géographique des équipements.

**2. Pourquoi un VLAN est-il souvent associé à un sous-réseau spécifique ?**
Cette association (généralement 1 VLAN = 1 sous-réseau) facilite le routage entre les segments, la gestion des passerelles par défaut et l'application de règles de sécurité (ACLs) spécifiques à chaque département.

**3. Pourquoi la séparation logique améliore-t-elle la sécurité ?**
Elle empêche une machine d'un VLAN (ex: WIFI_PUBLIC) d'accéder directement aux ressources d'un autre VLAN (ex: SERVEURS) sans passer par un routeur ou un pare-feu, limitant ainsi la propagation d'attaques.

**4. Quel est le rôle de la classe GestionnaireVLAN ?**
C'est une classe de service qui centralise la collection de VLANs. Elle offre des méthodes spécialisées pour ajouter, afficher, dénombrer ou rechercher un VLAN spécifique dans l'infrastructure.

**5. Pourquoi la classe VLAN contient-elle un objet ResultatVLSM ?**
C'est un principe de composition. Cela permet de lier la couche "Organisation" (le VLAN) à la couche "Adressage" (le résultat du calcul VLSM), rendant l'objet VLAN complet et autonome.

**6. Pourquoi utilise-t-on encore ArrayList dans ce TP ?**
Parce que le nombre de VLANs dans une entreprise est variable. L'ArrayList offre la flexibilité nécessaire pour ajouter ou supprimer des segments logiques dynamiquement.

**7. Pourquoi les responsabilités des classes doivent-elles être séparées ?**
Pour rendre le code plus maintenable et évolutif. Si on souhaite modifier le mode de calcul (VLSM), on n'a pas besoin de modifier la gestion des VLANs. C'est le principe de responsabilité unique.

**8. Pourquoi le projet commence-t-il maintenant à ressembler à une véritable application professionnelle ?**
Parce qu'il ne se contente plus de stocker des variables simples ; il commence à modéliser des concepts complexes de l'ingénierie réseau et à proposer une architecture logicielle structurée et cohérente.
