ALIAWY MFAM SAKER RICHLAND 

# TP5 - Moteur VLSM

## Objectif

Développer un moteur VLSM (Variable Length Subnet Mask) capable de proposer
automatiquement un plan d'adressage IP complet à partir des besoins exprimés
par l'utilisateur, en attribuant à chaque sous-réseau le masque le plus adapté.

---

## Notions étudiées

VLSM, tri de collections (`Collections.sort`), comparateur (`Comparator`),
classe de service métier, calcul CIDR automatique (`calculerCidrPourHotes`),
conversion IP↔entier (`convertirIpEnEntier`, `convertirEntierEnIp`),
génération automatique de sous-réseaux, séparation entrée/sortie
(`BesoinReseau` vs `ResultatVLSM`), plages d'adresses utilisables (§16).

---

## Structure du projet

```
TP5_IPPlanManager_VLSM/
├── build/
│   └── classes/ipplanmanager/
├── nbproject/
│   ├── build-impl.xml
│   ├── project.properties
│   └── project.xml
├── src/
│   └── ipplanmanager/
│       ├── AdresseIP.java              (reprise TP4)
│       ├── CalculateurReseau.java      (enrichi TP5 : 8 méthodes)
│       ├── ReseauIP.java               (reprise TP4)
│       ├── InterfaceReseau.java        (reprise TP4)
│       ├── Equipement.java             (reprise TP4)
│       ├── SousReseau.java             (reprise TP4)
│       ├── InfrastructureReseau.java   (reprise TP4)
│       ├── BesoinReseau.java           ← NOUVELLE classe TP5
│       ├── ResultatVLSM.java           ← NOUVELLE classe TP5 (+ §16)
│       ├── MoteurVLSM.java             ← NOUVELLE classe TP5 (service métier)
│       └── Main.java                   (3 scénarios §11 + §15)
├── build.xml
├── manifest.mf
└── README.md
```

---

## Scénarios testés

### Scénario 1 — Bureau (192.168.1.0) — fourni dans le TP §11

| Besoin | Hôtes demandés | CIDR proposé | Plage | Capacité |
|--------|---------------|-------------|-------|----------|
| TECHNIQUE | 120 | /25 | 192.168.1.1 – 192.168.1.126 | 126 |
| WIFI | 80 | /25 | 192.168.1.129 – 192.168.1.254 | 126 |
| ADMINISTRATION | 50 | /26 | 192.168.2.1 – 192.168.2.62 | 62 |
| SERVEURS | 20 | /27 | 192.168.2.65 – 192.168.2.94 | 30 |
| DIRECTION | 10 | /28 | 192.168.2.97 – 192.168.2.110 | 14 |

### Scénario 2 — Petite entreprise (10.0.0.0) — §15

| Besoin | Hôtes demandés | CIDR proposé | Plage | Capacité |
|--------|---------------|-------------|-------|----------|
| WIFI_INVITES | 40 | /26 | 10.0.0.1 – 10.0.0.62 | 62 |
| ADMIN | 25 | /27 | 10.0.0.65 – 10.0.0.94 | 30 |
| COMPTABILITE | 12 | /28 | 10.0.0.97 – 10.0.0.110 | 14 |
| SERVEURS | 8 | /28 | 10.0.0.113 – 10.0.0.126 | 14 |

### Scénario 3 — Campus universitaire (172.16.0.0) — §15

| Besoin | Hôtes demandés | CIDR proposé | Plage | Capacité |
|--------|---------------|-------------|-------|----------|
| ETUDIANTS | 500 | /23 | 172.16.0.1 – 172.16.1.254 | 510 |
| WIFI_PUBLIC | 200 | /24 | 172.16.2.1 – 172.16.2.254 | 254 |
| PERSONNEL | 120 | /25 | 172.16.3.1 – 172.16.3.126 | 126 |
| LABORATOIRE | 60 | /26 | 172.16.3.129 – 172.16.3.190 | 62 |
| ADMINISTRATION | 40 | /26 | 172.16.3.193 – 172.16.3.254 | 62 |

---

## Difficultés rencontrées

- Utiliser `>>>` (décalage non signé) au lieu de `>>` dans `convertirEntierEnIp`
  et `obtenirMasqueDecimal` : sans cela, les adresses > 127.x.x.x produisent
  des valeurs négatives car Java traite les int en complément à deux.
- Distinguer `calculerTailleBloc(cidr) = 2^(32-cidr)` (taille totale,
  utilisée pour faire avancer l'adresse) de `calculerNombreHotes(cidr) =
  2^(32-cidr) - 2` (hôtes utilisables seulement).
- Créer une nouvelle `ArrayList` pour chaque scénario dans `Main` : si on
  réutilise la même liste, `Collections.sort` du scénario 1 réordonne la
  liste avant le scénario 2 et fausse les résultats.
- Respecter l'ordre de création des classes : `CalculateurReseau` doit
  exister avant `ReseauIP` et avant `ResultatVLSM`.

---

## Réponses aux questions

### 1. Pourquoi le VLSM permet-il d'économiser les adresses IP ?

Le VLSM attribue à chaque sous-réseau **exactement la taille dont il a
besoin**, ni plus, ni moins. Sans VLSM (méthode classique), tous les
sous-réseaux reçoivent le même masque, même si certains n'utilisent que 5
hôtes sur 254. Le VLSM récupère cet espace gaspillé et le redistribue à
d'autres sous-réseaux, ce qui est crucial dans un espace IPv4 limité.

### 2. Pourquoi faut-il traiter les plus grands besoins en premier ?

Si l'on commence par de petits sous-réseaux, on fragmente l'espace d'adressage
en petits blocs discontinus. Lorsqu'un grand sous-réseau doit ensuite être
placé, aucun bloc contigu suffisamment grand n'est disponible. En traitant les
plus grands besoins d'abord, on garantit que les blocs importants sont alloués
en premier dans des zones contiguës, et que les blocs restants (plus petits)
suffisent aux petits besoins.

### 3. Quelle est la différence entre un besoin réseau et un résultat VLSM ?

Un `BesoinReseau` représente ce que l'**utilisateur demande** : un nom et un
nombre d'hôtes. C'est une entrée, sans aucun calcul. Un `ResultatVLSM`
représente ce que l'**application propose** : une adresse réseau, un CIDR,
un masque décimal, une plage utilisable et une capacité. C'est une sortie,
entièrement calculée. Séparer entrée et sortie est un principe fondamental de
la conception logicielle professionnelle.

### 4. Pourquoi la classe MoteurVLSM est-elle une classe de service métier ?

`MoteurVLSM` n'est pas une classe de données (elle ne stocke rien de façon
permanente) et n'est pas non plus une classe utilitaire pure. Elle applique une
**logique métier complexe** : tri, algorithme de sélection du CIDR, gestion de
l'adresse courante. C'est ce qu'on appelle un service : une classe dont le rôle
est de transformer des données d'entrée en données de sortie selon des règles
métier.

### 5. Pourquoi transforme-t-on une adresse IP en entier pour certains calculs ?

Une adresse IP sous forme de chaîne ("192.168.1.128") ne peut pas être
additionnée ou comparée directement. En la convertissant en entier
(3232235904), on peut faire de l'arithmétique : ajouter la taille d'un bloc
pour obtenir l'adresse suivante, comparer deux adresses, vérifier qu'une
adresse appartient à une plage. C'est la base de tous les calculs réseau.

### 6. Quel est le rôle de la méthode `calculerCidrPourHotes()` ?

Cette méthode automatise le choix du masque. Elle parcourt tous les CIDR de
/32 vers /0 et retourne le premier CIDR dont la capacité est supérieure ou
égale au nombre d'hôtes demandé. Sans elle, l'administrateur devrait calculer
manuellement le bon masque pour chaque besoin, ce qui est source d'erreurs et
très lent sur de grandes infrastructures.

### 7. Pourquoi une adresse de réseau et une adresse de broadcast ne sont-elles pas attribuées aux machines ?

L'**adresse réseau** (tous les bits hôtes à 0) identifie le sous-réseau lui-
même dans les tables de routage. L'**adresse broadcast** (tous les bits hôtes
à 1) est utilisée pour envoyer un paquet à toutes les machines du sous-réseau
simultanément. Ces deux adresses ont des rôles techniques réservés par le
protocole IP — les attribuer à des machines créerait des conflits et
perturberait le routage et la diffusion réseau.

### 8. Pourquoi le moteur VLSM représente-t-il une étape importante dans le projet IPPlan-Manager ?

Jusqu'au TP4, l'application se contentait d'afficher des informations calculées
sur des réseaux définis manuellement. Avec le moteur VLSM, l'application
**génère elle-même un plan d'adressage complet** à partir d'un simple besoin
utilisateur. C'est la différence entre un outil d'affichage et un vrai outil
de planification. Le moteur VLSM est le cœur fonctionnel du projet IPPlan-
Manager.

