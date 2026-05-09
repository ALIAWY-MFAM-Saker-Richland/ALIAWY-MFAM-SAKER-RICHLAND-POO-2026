NOM: ALIAWY MFAM SAKER RICHLAND 
# TP2 - Encapsulation

## Objectif

Ce TP a pour objectif d’introduire l’encapsulation dans un projet Java orienté réseau et télécommunications.  
Il permet de protéger les données des objets, de contrôler les modifications grâce aux getters et setters et de mettre en place des validations afin d’éviter les incohérences dans les configurations réseau.

---

## Notions étudiées

- Encapsulation
- Attributs private
- Getters
- Setters
- Validation des données
- Mot-clé this
- Constructeurs
- Programmation Orientée Objet (POO)

---

## Classes créées

### AdresseIP

Cette classe représente une adresse IP.  
Elle permet :
- de stocker une adresse IP ;
- de vérifier qu’elle n’est pas vide ;
- d’utiliser une valeur par défaut en cas d’erreur ;
- de vérifier si l’adresse est locale.

### ReseauIP

Cette classe représente un réseau IP.  
Elle contient :
- l’adresse réseau ;
- le masque CIDR ;
- une description.

Elle vérifie également :
- la validité du masque CIDR ;
- la présence des informations nécessaires.

### InterfaceReseau

Cette classe représente une interface réseau d’un équipement.

Elle permet :
- d’associer une adresse IP ;
- d’activer ou désactiver une interface ;
- de contrôler le nom de l’interface.

### Equipement

Cette classe représente un équipement réseau.

Elle contient :
- le nom ;
- le type ;
- l’interface réseau principale.

Des validations empêchent les valeurs invalides.

### Main

Cette classe permet de tester :
- les validations ;
- les setters ;
- les objets créés ;
- les méthodes d’affichage.

---

## Tests réalisés

Les tests suivants ont été réalisés :

- création d’une adresse IP valide ;
- création d’une adresse IP vide ;
- création d’une adresse IP null ;
- création d’un réseau avec un masque valide ;
- création d’un réseau avec un masque CIDR invalide ;
- création d’une interface sans nom ;
- création d’un équipement sans nom ;
- modification des valeurs avec les setters ;
- activation d’une interface réseau ;
- test de la méthode estAdresseLocale().

---

## Résultats obtenus

Le programme affiche correctement :
- les validations ;
- les messages d’erreur ;
- les valeurs par défaut ;
- les équipements ;
- les réseaux ;
- les états des interfaces réseau.

Les validations empêchent les incohérences dans les données réseau.

---

## Difficultés rencontrées

Les principales difficultés rencontrées étaient :

- la compréhension du fonctionnement des setters ;
- l’utilisation correcte du mot-clé this ;
- la gestion des validations ;
- l’organisation des classes dans le package ;
- la compréhension de l’encapsulation.

---

## Réponses aux questions

### 1. Pourquoi utilise-t-on private dans les classes ?

Le mot-clé private permet de protéger les données d’un objet et d’empêcher leur modification directe depuis l’extérieur de la classe.

### 2. Quelle différence existe entre un attribut public et un attribut privé ?

Un attribut public peut être modifié directement par n’importe quelle classe, tandis qu’un attribut privé est protégé et accessible uniquement à travers des méthodes contrôlées.

### 3. Pourquoi utilise-t-on des getters et setters ?

Les getters et setters permettent de lire et modifier les données de manière sécurisée tout en ajoutant des validations.

### 4. Pourquoi les validations sont-elles importantes dans un logiciel réseau ?

Les validations évitent les erreurs de configuration réseau, les incohérences et les comportements imprévus dans le système.

### 5. Quel est le rôle du mot-clé this ?

Le mot-clé this désigne l’objet courant et permet de différencier les attributs de la classe des paramètres des méthodes.

### 6. Pourquoi le constructeur appelle-t-il les setters ?

Le constructeur appelle les setters afin de réutiliser les validations déjà définies dans les méthodes de modification.

### 7. Pourquoi la validation du masque CIDR est-elle importante ?

Elle permet d’éviter l’utilisation de masques réseau invalides qui pourraient provoquer des erreurs de configuration.

### 8. Pourquoi l’encapsulation améliore-t-elle la sécurité logicielle ?

L’encapsulation protège les données sensibles et empêche les modifications non contrôlées des objets.

---

## Conclusion

Ce TP a permis de comprendre l’importance de l’encapsulation dans le développement orienté objet.  
Les validations mises en place rendent le programme plus robuste, plus sécurisé et plus fiable pour la gestion des données réseau.