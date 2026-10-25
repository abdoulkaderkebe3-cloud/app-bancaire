# Application bancaire

API REST de type mobile money (inspirée de Wave) en Spring Boot : inscription et connexion par JWT, comptes avec IBAN, transactions (dépôts et transferts), contacts, tableau de bord.

**C'est un projet d'apprentissage.** Le code de départ vient d'un tutoriel. Je m'en sers pour apprendre à lire, auditer et corriger du code que je n'ai pas écrit : suivre une requête HTTP jusqu'à la base, prédire le SQL généré par Hibernate, puis le vérifier.

## Ce que j'y ai corrigé

- **Règle de sécurité** : le `permitAll` visait `/authenticate/**` alors que les routes d'authentification sont sous `/auth`. Personne ne pouvait ni s'inscrire ni se connecter. La règle couvre maintenant `/auth/**`.
- **Compatibilité JDK récent** : `jjwt 0.9.1` dépend de `javax.xml.bind.DatatypeConverter`, retiré du JDK depuis Java 11. Ajout de `jaxb-api` en attendant de passer à `jjwt 0.12`.
- **Secrets** : le mot de passe de la base et la clé de signature JWT étaient en dur dans le code. Ils sont lus depuis un fichier `.env` non versionné.

## Stack

Java 17, Spring Boot 4, Spring Data JPA (Hibernate), PostgreSQL, Spring Security + JWT, Lombok, Maven.

## Lancer le projet

1. Créer une base PostgreSQL `banque_db`.
2. Copier `.env.example` en `.env` et remplir `DB_PASSWORD` et `JWT_SECRET`.
3. `./mvnw spring-boot:run`

L'API écoute sur `http://localhost:8080`. Inscription : `POST /auth/register`, connexion : `POST /auth/authenticate`.
