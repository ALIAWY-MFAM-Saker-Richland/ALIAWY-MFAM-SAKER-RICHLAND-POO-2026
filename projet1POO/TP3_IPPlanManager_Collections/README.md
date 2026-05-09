NOM: ALIAWY MFAM SAKER RICHLAND 

# TP3 - Collections et composition

## Objectif

Introduction des collections et des relations entre objets dans le projet
IPPlan-Manager. L'application évolue pour gérer des ensembles structurés de
réseaux, de sous-réseaux et d'équipements.

---

## Notions étudiées

Composition, ArrayList, collections, parcours de listes (boucle for-each),
relations entre objets.

---

## Structure du projet

```
TP3_IPPlanManager_Collections/
├── build/
│   └── classes/ipplanmanager/   ← fichiers .class compilés
├── nbproject/
│   ├── build-impl.xml
│   ├── project.properties
│   └── project.xml
├── src/
│   └── ipplanmanager/
│       ├── AdresseIP.java
│       ├── InterfaceReseau.java
│       ├── ReseauIP.java
│       ├── Equipement.java
│       ├── SousReseau.java
│       ├── InfrastructureReseau.java
│       └── Main.java
├── build.xml
├── manifest.mf
└── README.md
```

---

## Tests réalisés

| Test | Résultat attendu | Résultat obtenu |
|------|-----------------|-----------------|
| Création de 3 sous-réseaux (ADMIN, TECH, WIFI) | Affichage correct des adresses et masques | OK |
| Routeur R1_EDGE avec 2 interfaces | 2 interfaces listées, toutes actives | OK |
| Switch SW1_CORE avec 3 interfaces GigabitEthernet | 3 interfaces listées et actives | OK |
| Serveur SRV_WEB avec 2 interfaces | eth0 active, eth1 inactive | OK |
| Point d'accès AP1_WIFI avec wlan0 + eth0 | 2 interfaces, toutes actives | OK |
| `rechercherEquipement("SW1_CORE")` | Affichage complet du switch | OK |
| `rechercherEquipement("SRV_DNS")` | "Équipement introuvable." | OK |

---

## Difficultés rencontrées

- Comprendre que `new ArrayList<>()` doit être initialisé dans le constructeur
  pour éviter un NullPointerException lors du premier `add()`.
- S'assurer que la boucle for-each ne provoque pas d'erreur sur une liste vide
  (elle ne fait simplement aucune itération, ce qui est correct).
- Utiliser `equalsIgnoreCase` dans `rechercherEquipement` pour rendre la
  recherche insensible aux majuscules/minuscules.

---

## Réponses aux questions

### 1. Qu'est-ce qu'une composition en Programmation Orientée Objet ?

La **composition** est une relation entre objets où un objet contient d'autres
objets comme attributs. L'objet conteneur est responsable de ses composants.
Exemple : un `Equipement` contient une `ArrayList<InterfaceReseau>`. Si
l'équipement est supprimé, ses interfaces n'ont plus de sens.

### 2. Pourquoi utilise-t-on ArrayList dans ce TP ?

`ArrayList` permet de gérer un **nombre variable** d'objets de façon dynamique.
Contrairement à un tableau classique (taille fixe déclarée à l'avance), une
ArrayList grandit automatiquement. On peut y ajouter, supprimer ou parcourir
des éléments facilement, ce qui est indispensable pour les interfaces, les
équipements et les sous-réseaux dont le nombre n'est pas connu à l'avance.

### 3. Quelle différence existe entre une variable simple et une collection ?

Une variable simple contient **un seul objet ou une seule valeur**, de taille
fixe. Une collection (ArrayList) contient **plusieurs objets**, avec une taille
dynamique, et offre des méthodes pour ajouter, supprimer, parcourir et
rechercher des éléments.

### 4. Pourquoi un équipement possède-t-il plusieurs interfaces ?

Dans la réalité, un routeur ou un serveur est connecté à **plusieurs réseaux**
simultanément via des interfaces distinctes (eth0, eth1, wlan0…). Plusieurs
interfaces permettent le routage entre différents sous-réseaux, la redondance
et la séparation des flux réseau.

### 5. Pourquoi une infrastructure réseau contient-elle plusieurs sous-réseaux ?

Un réseau d'entreprise est découpé en sous-réseaux pour des raisons de
**sécurité** (isolation des flux), de **performance** (réduction des domaines
de diffusion) et d'**organisation logique** (ADMIN, TECH, WIFI…). Une
infrastructure réelle gère toujours plusieurs sous-réseaux.

### 6. Quel est le rôle de la boucle for-each ?

La boucle **for-each** (`for (Type element : collection)`) parcourt
automatiquement tous les éléments d'une collection un par un, sans gérer
d'indice. Elle est plus lisible et évite les erreurs d'index
(IndexOutOfBoundsException).

### 7. Pourquoi la classe InfrastructureReseau devient-elle importante dans le projet ?

`InfrastructureReseau` est le **point d'entrée unique** qui centralise tous les
équipements et sous-réseaux. Elle propose des méthodes de haut niveau
(afficher, ajouter, rechercher) qui masquent la complexité interne. Sans elle,
le `Main` devrait gérer de nombreuses listes séparées, rendant le code
difficile à maintenir.

### 8. Pourquoi les collections sont-elles indispensables dans les applications professionnelles ?

Dans une vraie application, les données sont **nombreuses, variables et
inconnues à l'avance**. Les collections fournissent des structures flexibles,
optimisées et dotées d'une API riche (tri, recherche, filtrage). Sans elles,
coder chaque structure manuellement serait coûteux, peu fiable et impossible
à maintenir à grande échelle.

---

