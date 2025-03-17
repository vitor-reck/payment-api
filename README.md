# payment-api-java
RESTful API for transactions between customers and shopkeepers, integrating with mock services for tranfer authorization and notification

## Architecture
- Service Layer with Repository Pattern
- Declarative code style approach wherever it's possible
- Integration: https://util.devi.tools/api/v2/authorize and https://util.devi.tools/api/v1/notify

## Versions
- Java 21
- Spring Boot 3.4.3
- Flyway 10.20.1
- PostgreSQL 15

## Prerequisites
Cloning this API and running will need:
- Git
- Docker
- Docker-compose

## How to clone this repository
Run this command
```
git clone https://github.com/vitor-reck/payment-api.git
```
## How to run this API
Using docker-compose to pull/create images and running them
```
docker-compose up -d
```
Stopping and removing containers
```
docker-compose down
```
Optional: removing volume created by PostgreSQL container
```
docker-compose down --volumes
```
## Postman
This repository contains one postman collection for the requests on the API
