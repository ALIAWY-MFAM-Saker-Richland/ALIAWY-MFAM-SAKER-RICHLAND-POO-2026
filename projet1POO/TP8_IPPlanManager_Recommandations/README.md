ALIAWY MFAM SAKER RICHLAND 

# TP8 - Moteur de recommandations intelligentes

## 1. Objectif du TP
L'objectif de ce TP est d'apporter une dimension "intelligente" à l'application **IPPlan-Manager**. Au-delà de la simple validation technique, le logiciel devient un assistant capable d'analyser un plan d'adressage pour proposer des conseils de sécurité et d'optimisation basés sur les bonnes pratiques de l'ingénierie réseau.

## 2. Notions étudiées
*   **Interfaces Java** : Création d'un contrat de service avec `RegleRecommandation`.
*   **Polymorphisme** : Capacité du moteur à exécuter diverses règles via une interface commune.
*   **Architecture Extensible** : Séparation de la logique de recommandation du reste du code pour faciliter l'ajout de nouvelles règles.
*   **Règles Métier** : Traduction de principes de sécurité (Isolation WiFi, Protection Serveur) en algorithmes.
*   **Gestion des Collections** : Manipulation de listes de recommandations pour un affichage centralisé.

## 3. Travail réalisé (Section 18 & 19)
Conformément aux consignes, les tâches suivantes ont été accomplies :
- [x] **Nouvelle règle Administration** : Détection des VLANs "ADMIN" avec recommandation d'accès restreint.
- [x] **Scénario de test complexe** : Test incluant ADMINISTRATION, WIFI_INVITES, SERVEURS, CAMERAS et VOIP.
- [x] **Règle de Marge d'adresse** : Analyse de la capacité restante pour prévenir la saturation future des sous-réseaux.
- [x] **Affichage structuré** : Présentation des conseils par niveau de priorité (ÉLEVÉE, MOYENNE, NORMALE).

## 4. Réponses aux questions de compréhension (Section 20)

**Q1. Rôle du moteur de recommandations ?**  
Il transforme l'outil IPAM en un assistant d'aide à la décision, guidant l'utilisateur vers des choix de configuration plus sûrs et performants.

**Q2. Pourquoi utiliser une interface ?**  
Pour imposer la méthode `analyser()` à toutes les règles, permettant au moteur de les traiter de manière uniforme sans connaître leur logique interne.

**Q3. Différence entre classe concrète et interface ?**  
Une classe concrète contient du code prêt à l'emploi. Une interface est un contrat qui liste des méthodes sans les coder, laissant les classes s'en charger.

**Q4. Pourquoi la méthode analyser() peut-elle retourner null ?**  
Elle retourne `null` lorsqu'un VLAN ne présente aucune anomalie par rapport à la règle testée (aucune recommandation n'est alors nécessaire).

**Q5. Pourquoi ce moteur illustre-t-il le polymorphisme ?**  
Le moteur appelle la même méthode `analyser()` sur une liste d'objets différents, et chaque objet réagit différemment selon sa propre nature (règle WiFi, règle Serveur, etc.).

**Q6. Pourquoi une classe par règle plutôt que tout mettre dans Main ?**  
Cela rend le code modulaire, facile à lire et permet d'ajouter ou de supprimer des règles sans risquer de casser le programme principal.

**Q7. Pourquoi un VLAN WiFi invité doit-il être isolé ?**  
Pour éviter que des utilisateurs externes n'accèdent aux ressources critiques (serveurs, bases de données) du réseau interne de l'entreprise.

**Q8. Pourquoi surveiller les VLANs de grande taille ?**  
À cause du trafic de "broadcast" (diffusion) qui peut saturer la bande passante et ralentir tous les équipements du segment.

---
*Projet réalisé sous NetBeans - Promotion 2026*
