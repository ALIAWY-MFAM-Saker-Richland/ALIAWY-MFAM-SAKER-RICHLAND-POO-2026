#IPPlan-Manager : Gestionnaire de Plan d'Adressage IP
**Projet :** TP1 - Découverte de la POO en Java  
**Auteur :ALIAWY MFAM SAKER RICHLAND 
**Promotion :** 2026

---

## 1. Description du projet
IPPlan-Manager est une application console développée en Java permettant de simuler la gestion d'un parc réseau. L'objectif est de manipuler les concepts fondamentaux de la Programmation Orientée Objet (POO) à travers la modélisation d'équipements réseaux, d'interfaces et d'adresses IP.

## 2. Structure du code (Classes)
Le projet est organisé autour de 4 classes principales dans le package `ipplanmanager` :
*   **AdresseIP** : Gère la valeur textuelle de l'adresse.
*   **ReseauIP** : Définit un sous-réseau (adresse, masque CIDR et description).
*   **InterfaceReseau** : Représente une carte réseau (ex: eth0) avec son état (active/inactive) et son association à une `AdresseIP`.
*   **Equipement** : Modélise un appareil (Switch, PC, Routeur) possédant une interface.

## 3. Travail Réalisé (Section 13)
Conformément aux consignes du TP, les éléments suivants ont été implémentés dans la classe `Main` :
- [x] Création de deux réseaux distincts (Admin et Public).
- [x] Instanciation d'un **Switch** (`SW-CORE-01`).
- [x] Instanciation d'un **Point d'accès WiFi** (`AP-HALL-A`).
- [x] Instanciation d'un **Poste client supplémentaire**.
- [x] Test d'une **interface inactive** (le poste client).
- [x] Test d'une **interface sans adresse IP** (serveur de debug).

## 4. Réponses aux questions de compréhension (Section 14)

**Q1. Intérêt d'une classe AdresseIP ?**  
L'utilisation d'une classe permet d'encapsuler la donnée. On pourra plus tard ajouter des méthodes de validation (vérifier le format X.X.X.X) sans modifier le reste du programme.

**Q2. Différence entre Classe et Objet ?**  
La Classe est le modèle (le moule), tandis que l'Objet est l'instance concrète créée en mémoire (l'équipement réel).

**Q3. Pourquoi l'adresse IP peut-elle être nulle ?**  
Dans un réseau réel, une interface physique peut exister sans avoir de configuration IP (ex: interface en attente de DHCP ou dédiée au monitoring).

**Q4. Rôle du constructeur ?**  
Il permet d'initialiser les attributs de l'objet dès sa naissance pour garantir qu'il est dans un état valide.

**Q5. Rôle de la méthode afficher() ?**  
Elle assure la visualisation des données de l'objet de manière formatée dans la console.

**Q6. Pourquoi la POO facilite-t-elle l'évolution ?**  
Grâce à la modularité : on peut modifier le comportement d'une classe (ex: ajouter une adresse MAC dans `InterfaceReseau`) sans impacter les autres classes.

**Q7. Lien entre Equipement et InterfaceReseau ?**  
C'est une relation de **composition/agrégation** : un objet `Equipement` contient une référence vers un objet `InterfaceReseau`.



