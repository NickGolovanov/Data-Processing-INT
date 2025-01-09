# Data-Processing-INT
Data Processing INT

## Description
This is a test for the implementation of the DBMS directly to the docker container.
Because of the constraint that the database is depenedent on the creatino of the entities by the api this version tackes the backup of the the database and uploads it on the docker container before starting the API. This allows to create triggers, views and stored procedures without waiting for the creating of the entities by the API.

## Changes to main
- Changed the docker-compose file to include the database backup
- The docker-compose file uploads all SQL files to **docker-entrypoint-initdb.d**
- Removed column preference in the Profile Entity, because it coursed some problems with creating Preferences
- For testing purposes, volumes were removed from the docker-compose file

## How to run
Before starting the docker container please remove all the volumes related to the project and build the project without cache.

```bash
docker-compose down -v
docker-compose build --no-cache
docker-compose up 
```

## Testing
Please ensure that the database docker container started properly in the docker desktop app. This can be checked if container logs show that the database created Views or Triggers.
If everything is working properly  you can try to create a new account in the database and check if it created profile and preferences.

```sql
INSERT INTO public.account (email, password, payment_method, referral_discount_id)
VALUES ('testemail@gmail.com', null, null, null);
```
