ALIAWY MFAM SAKER RICHLAND 

# TP7 - Validations avancées et détection des conflits

## Objectif
L'objectif de ce TP est de transformer **IPPlan-Manager** en un outil de planification professionnel et robuste. Nous avons implémenté des mécanismes de validation avancés pour détecter automatiquement les erreurs humaines, les chevauchements de réseaux et les conflits d'identifiants VLAN avant qu'ils ne provoquent des pannes réelles.

## Notions étudiées
*   **Exceptions personnalisées** : Création de classes héritant de `Exception` pour signaler des erreurs métier spécifiques (`ConflitVLANException`, `AdresseIPInvalideException`, etc.).
*   **Mécanisme Try/Catch** : Capture et gestion des erreurs pour empêcher l'arrêt brutal du programme.
*   **Mot-clé Throw** : Déclenchement volontaire d'exceptions lorsqu'une règle réseau n'est pas respectée.
*   **Validation algorithmique** : Comparaison des bornes numériques (début/fin) des sous-réseaux pour détecter les chevauchements.
*   **Robustesse logicielle** : Séparation de la logique de calcul de la logique de vérification.

## Scénarios testés
1.  **Cas Nominal** : Génération d'un plan VLSM valide et vérification par le `ValidateurPlanAdressage` (succès attendu).
2.  **Conflit VLAN** : Tentative d'ajout de deux VLANs avec le même ID (ID 10) dans le `GestionnaireVLAN`.
3.  **Adresse IP Invalide** : Test de l'adresse de départ `192.168.300.0` (hors plage 0-255).
4.  **Chevauchement de réseaux** : Comparaison manuelle de `192.168.1.0/25` et `192.168.1.64/26` pour vérifier la détection de conflit.
5.  **Ressources insuffisantes (Bonus)** : Test d'un plan dont les besoins cumulés dépassent la capacité du réseau parent.

## Résultats obtenus
*   Le plan valide s'affiche avec le message : *"Validation terminée : aucun conflit critique détecté."*
*   Les erreurs sont interceptées proprement et affichent des messages explicites comme : *"Erreur VLAN : Conflit VLAN : l'identifiant 10 est déjà utilisé."*
*   L'application ne plante plus, elle informe l'utilisateur du problème rencontré.

## Difficultés rencontrées
*   Mise au point de l'algorithme de détection de chevauchement (`debut1 <= fin2 && debut2 <= fin1`).
*   Gestion des blocs `try/catch` multiples pour capturer différentes exceptions dans le même scénario de test.

## Réponses aux questions (Section 19)

**1. Pourquoi les validations avancées sont-elles indispensables dans un outil IPAM ?**  
Elles évitent des erreurs de configuration qui pourraient causer des conflits d'adresses ou des problèmes de routage critiques dans une infrastructure réelle.

**2. Quelle est la différence entre une erreur simple et une exception en Java ?**  
Une erreur simple (comme un message `println`) n'interrompt pas le flux du programme. Une exception est un objet qui interrompt le flux et force le développeur à gérer le problème (via `catch`).

**3. Pourquoi crée-t-on des exceptions personnalisées ?**  
Pour rendre le code plus lisible et précis. On sait immédiatement si l'erreur concerne une IP, un VLAN ou un chevauchement, plutôt que d'avoir une erreur générique.

**4. Quel est le rôle du bloc try/catch ?**  
`try` contient le code "risqué" susceptible de générer une erreur, et `catch` contient le code à exécuter si l'erreur survient, permettant ainsi de gérer le problème sans arrêter le logiciel.

**5. Pourquoi deux VLANs ne doivent-ils pas avoir le même identifiant ?**  
L'ID de VLAN (1-4094) sert à marquer les trames Ethernet. Si deux réseaux ont le même ID, leurs trafics seront mélangés, annulant tout l'intérêt de l'isolation logique.

**6. Pourquoi deux sous-réseaux ne doivent-ils pas se chevaucher ?**  
Parce qu'une adresse IP ne peut appartenir qu'à un seul réseau à la fois. Si deux réseaux se chevauchent, les routeurs ne sauront pas vers quel segment envoyer les paquets.

**7. Pourquoi transforme-t-on les adresses IP en entiers pour comparer des plages ?**  
Parce qu'il est mathématiquement très simple de comparer des nombres (est-ce que X est entre A et B ?) alors qu'il est très complexe de le faire avec des chaînes de caractères pointées.

**8. Pourquoi la classe ValidateurPlanAdressage doit-elle être séparée du moteur VLSM ?**  
C'est le principe de responsabilité unique. Le moteur calcule, le validateur vérifie. Cela permet de tester ou de modifier l'un sans perturber l'autre.
