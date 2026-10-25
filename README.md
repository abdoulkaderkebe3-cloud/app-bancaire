# App bancaire

API REST de mobile money, inspirée de Wave, écrite en Spring Boot. Un utilisateur s'inscrit, son compte est validé et reçoit un IBAN, puis il effectue des dépôts et des transferts et suit son solde depuis un tableau de bord.

## Fonctionnalités

- **Authentification JWT** : inscription, connexion, routes protégées, sessions sans état
- **Comptes bancaires** : un compte par utilisateur, créé à la validation, avec un IBAN unique généré par `iban4j`
- **Transactions** : dépôts et transferts vers un IBAN de destination
- **Solde calculé** : aucun champ solde stocké. Le solde est la somme des transactions, un transfert est enregistré en négatif et un dépôt en positif
- **Tableau de bord** : solde, plus gros transfert, plus gros dépôt, sommes par jour sur une période
- **Contacts et adresses** rattachés à l'utilisateur
- **Validation des entrées** avec Bean Validation et gestion centralisée des erreurs (`@RestControllerAdvice`)
- **Audit automatique** : date de création et de mise à jour sur chaque entité

## Stack

| Couche | Technologie |
|---|---|
| Langage | Java 17 |
| Framework | Spring Boot 4, Spring Web |
| Persistance | Spring Data JPA, Hibernate, PostgreSQL |
| Sécurité | Spring Security, JWT (`jjwt`), BCrypt |
| Outils | Lombok, `iban4j`, Maven |

## Architecture

Découpage en couches, une requête traverse `Controller → Service → Repository → PostgreSQL` :

```
src/main/java/Kebe/application_bancaire/
├── Controllers/   # routes REST
├── services/      # interfaces métier et implémentations (impl/)
├── repository/    # accès aux données, Spring Data JPA
├── models/        # entités JPA (Mere = superclasse auditée : id et dates)
├── dto/           # objets d'entrée et de sortie, conversion toEntity / fromEntity
├── config/        # sécurité, filtre et utilitaires JWT
├── auth/          # chargement de l'utilisateur pour Spring Security
├── exceptions/    # validation et exceptions métier
└── handlers/      # gestion centralisée des erreurs
```

Modèle de données : `utilisateurs`, `account`, `transaction`, `contact`, `address`, `role`.

## Installation

Prérequis : Java 17 ou plus, PostgreSQL.

```bash
# 1. Créer la base
createdb banque_db

# 2. Configurer les secrets
cp .env.example .env
# puis renseigner DB_PASSWORD et JWT_SECRET dans .env

# 3. Lancer
./mvnw spring-boot:run
```

L'API écoute sur `http://localhost:8080`. Les tables sont créées au premier démarrage.

## Routes

Toutes les routes sauf `/auth/**` demandent l'en-tête `Authorization: Bearer <token>`.

| Méthode | Route | Rôle |
|---|---|---|
| POST | `/auth/register` | Inscription, renvoie un token |
| POST | `/auth/authenticate` | Connexion, renvoie un token |
| GET | `/users` · `/users/{id}` | Lister, consulter |
| PATCH | `/users/validate/{id}` | Activer l'utilisateur et créer son compte |
| PATCH | `/users/invalidate/{id}` | Désactiver l'utilisateur |
| POST | `/transactions/` | Créer un dépôt ou un transfert |
| GET | `/transactions` · `/transactions/{id}` · `/transactions/users/{id}` | Consulter les transactions |
| GET | `/accounts` · `/accounts/{id}` | Consulter les comptes |
| POST / GET | `/contacts/` · `/contacts/users/{id}` | Gérer les contacts |
| POST / GET | `/adresses/` · `/adresses/{id}` | Gérer les adresses |
| GET | `/dashboards/account-balance/{id}` | Solde |
| GET | `/dashboards/higest-transfert/{id}` · `/dashboards/higestDeposit/{id}` | Plus gros transfert, plus gros dépôt |
| GET | `/dashboards/sum-by-date/{id}?start-date=…&end-date=…` | Sommes par jour |

## Exemple

```bash
# Inscription
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"firstname":"Kader","lastname":"Kebe","email":"kader@test.com","password":"Test1234"}'

# Transfert de 5 000 (enregistré à -5000)
curl -X POST http://localhost:8080/transactions/ \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"amount":5000,"type":"TRANSFERT","destinationIban":"CI0000000000000000000000","userId":1}'
```

## Pistes d'évolution

- Passer à `jjwt` 0.12
- Migrations versionnées avec Flyway à la place de `ddl-auto`
- Tests d'intégration sur les services
- Conteneurisation avec Docker Compose

## Auteur

Kebe Abdoul Kader, développeur full stack à Abidjan.
