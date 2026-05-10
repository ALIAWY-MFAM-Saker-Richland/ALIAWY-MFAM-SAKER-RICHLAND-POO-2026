# TP4 - Calculs réseau

## Objectif

Introduction des calculs automatiques réseau dans le projet IPPlan-Manager.
L'application sait désormais calculer automatiquement le nombre d'hôtes,
le masque décimal, la classe réseau et détecter les plages privées.

---

## Notions étudiées

Méthodes statiques, calculs réseau, CIDR, logique algorithmique, classes
utilitaires, `Math.pow()`, `split()`, `switch`.

---

## Structure du projet

```
TP4_IPPlanManager_CalculsReseau/
├── build/
│   └── classes/ipplanmanager/
├── nbproject/
│   ├── build-impl.xml
│   ├── project.properties
│   └── project.xml
├── src/
│   └── ipplanmanager/
│       ├── AdresseIP.java
│       ├── InterfaceReseau.java
│       ├── ReseauIP.java          ← afficher() enrichi (calculs auto)
│       ├── Equipement.java
│       ├── SousReseau.java
│       ├── InfrastructureReseau.java
│       ├── CalculateurReseau.java ← NOUVELLE classe utilitaire
│       └── Main.java              ← tests §14 + §15
├── build.xml
├── manifest.mf
└── README.md
```

---

## Tests réalisés

| Test | Résultat attendu | Résultat obtenu |
|------|-----------------|-----------------|
| `/24` → nombre d'hôtes | 254 | OK |
| `/16` → nombre d'hôtes | 65534 | OK |
| `/8`  → nombre d'hôtes | 16777214 | OK |
| `/25` → masque décimal | 255.255.255.128 | OK |
| `/28` → masque décimal | 255.255.255.240 | OK |
| `192.168.1.0` → classe | Classe C | OK |
| `172.16.0.0`  → classe | Classe B | OK |
| `10.0.0.0`    → classe | Classe A | OK |
| `10.0.0.1` → privé | true | OK |
| `172.16.5.10` → privé | true | OK |
| `172.32.0.1`  → privé | false | OK |
| `192.168.1.1` → privé | true | OK |
| `8.8.8.8`     → privé | false | OK |

---

## Difficultés rencontrées

- Comprendre que `Math.pow()` retourne un `double` ; il faut le caster en
  `int` pour obtenir le nombre d'hôtes sous forme entière.
- La méthode `split("\\.")` nécessite un double antislash car le point est
  un caractère spécial dans les expressions régulières Java.
- Pour `estReseauPrive`, la plage `172.16–172.31` demande de vérifier à la
  fois le premier octet (172) et le deuxième octet (entre 16 et 31).

---

## Réponses aux questions

### 1. Pourquoi a-t-on créé une classe utilitaire ?

Certains traitements (calcul d'hôtes, conversion de masque, détection de
classe) ne représentent pas des objets réels mais des **opérations techniques
pures**. Les regrouper dans `CalculateurReseau` permet d'organiser le code,
d'éviter les répétitions et de faciliter la maintenance : si une formule
change, on ne la corrige qu'à un seul endroit.

### 2. Quel est le rôle du mot-clé `static` ?

`static` indique qu'une méthode (ou un attribut) appartient à la **classe
elle-même** et non à une instance. On peut donc l'appeler directement :
`CalculateurReseau.calculerNombreHotes(24)` sans créer d'objet. C'est la
pratique habituelle pour les classes utilitaires dont les méthodes n'ont pas
besoin d'un état interne.

### 3. Pourquoi les calculs réseau sont-ils importants dans un outil IPAM ?

Un outil IPAM (**IP Address Management**) doit éviter les erreurs de
configuration réseau. Des calculs automatiques garantissent que les masques,
les capacités et les plages d'adresses sont toujours corrects, accélèrent les
déploiements et permettent à des techniciens moins expérimentés de travailler
sans risque de conflit d'adresses.

### 4. Quelle est l'utilité du CIDR ?

Le **CIDR** (Classless Inter-Domain Routing, ex. `/24`) est une notation
compacte qui indique combien de bits sont réservés à la partie réseau d'une
adresse IP. Il remplace les classes fixes (A/B/C) et permet un découpage
**flexible** des réseaux, réduisant le gaspillage d'adresses IP.

### 5. Pourquoi le nombre d'hôtes dépend-il du masque réseau ?

Plus le masque est grand (CIDR élevé), moins il reste de bits pour les hôtes.
La formule `2^(32 - CIDR) - 2` montre cette relation directe : un `/24`
laisse 8 bits pour les hôtes (254 adresses utiles), tandis qu'un `/28` n'en
laisse que 4 (14 adresses utiles). Le `-2` correspond aux adresses réservées
pour le réseau et le broadcast.

### 6. Pourquoi certaines adresses IP sont-elles privées ?

Les plages privées (10.x, 172.16–31.x, 192.168.x) sont définies par la
RFC 1918 pour une utilisation **interne** uniquement. Elles ne sont pas
routables sur Internet, ce qui permet à des milliers d'entreprises de réutiliser
les mêmes adresses localement sans conflit, tout en économisant l'espace
d'adressage public (IPv4 limité à ~4,3 milliards d'adresses).

### 7. Pourquoi la séparation entre logique métier et logique de calcul améliore-t-elle le projet ?

La **séparation des responsabilités** (principe SRP en POO) rend le code plus
lisible, plus testable et plus maintenable. `ReseauIP` modélise un objet
métier ; `CalculateurReseau` contient la logique technique. Si l'algorithme
de calcul doit changer, on ne touche qu'à la classe utilitaire sans risquer
de casser les classes métier.

### 8. Pourquoi les outils de planification réseau doivent-ils automatiser les calculs ?

Dans une infrastructure réelle, un administrateur peut gérer des centaines de
sous-réseaux. Calculer manuellement chaque masque, plage ou capacité est
lent, sujet aux erreurs et non reproductible. L'automatisation garantit la
**cohérence**, la **rapidité** et la **fiabilité** des plans d'adressage, et
libère les techniciens pour des tâches à plus forte valeur ajoutée.

---

## Commandes Git

```bash
git add TP4_IPPlanManager_CalculsReseau
git commit -m "Ajout du TP4 calculs réseau automatiques"
git push origin main
```
