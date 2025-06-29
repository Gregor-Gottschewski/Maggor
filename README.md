# Maggor - Share Beautiful Moments

> A platform to share beautiful moments with the people you ❤️ the most.

## Installation with Docker

Maggor is perfectly optimized to work with Docker.
An installation without docker is possible but not recommended.

1. If you haven't Docker installed, [follow the Docker installation steps](https://docs.docker.com/engine/install/) for
   your operating system.
2. Create a `docker-compose.yaml` file with the following content

```yaml
services:
  maggor:
    image: gregorgott/maggor:latest
    container_name: maggor
    ports:
      - "8080:8080"
    volumes:
      - ~/maggor/conf:/maggor
    environment:
      - DB_USERNAME=${MONGO_INITDB_ROOT_USERNAME}
      - DB_PASSWORD=${MONGO_INITDB_ROOT_PASSWORD}
      - DB_PORT=27017
      - DB_NAME=mongo
    networks:
      - external
      - internal
  database:
    image: mongo
    container_name: mongo
    environment:
      - MONGO_INITDB_ROOT_USERNAME=${MONGO_INITDB_ROOT_USERNAME}
      - MONGO_INITDB_ROOT_PASSWORD=${MONGO_INITDB_ROOT_PASSWORD}
      - MONGO_INITDB_DATABASE=${MONGO_INITDB_DATABASE}
    volumes:
      - ~/maggor/data/mongo:/data/db
    networks:
      - internal
networks:
  external:
  internal:
```

3. Create a `.env`-file where you specify environmental variables. Change the database root password to a secure
   password.

```dotenv
MONGO_INITDB_ROOT_USERNAME=maggor
MONGO_INITDB_ROOT_PASSWORD=secure123
MONGO_INITDB_DATABASE=admin
```

4. Create a directory `maggor/conf` in your user home directory.
5. (**optional**) Create a file `conf.prop` with the following content (see
   chapter [configuration file](#configuration-file)):

```properties
user.password=<secure_user_password>
login.title=Here is the title
login.subtitle=Here is your subtitle
login.footer=Here is your footer
```

6. Run `docker compose up`
7. Visit `http://<ip>:8080/` and login with the user password defined in the `conf.prop`. If you haven't created a
   `conf.prop`, the login password is `maggor`.

## Configuration file

The configuration file in `config` has to contain the following configurations:

`user.password`: Password of the application user  
`login.title`: Title shown on the login page  
`login.subtitle`: Subtitle shown on the login page  
`login.footer`: Footer on the login page

If the values aren't set, default values are used.

### Example file

```properties
user.password=<user_password_here>
login.title=Here is the title
login.subtitle=Here is the subtitle
login.footer=This is the footer
```

## Environment-Variables

Change the environment variables in `.env` to your needs.
The only configuration that *has to be changed* is `DB_PASSWORD`.

| Environment Variable         | Description                              | Used In  | Default / Value |
|------------------------------|------------------------------------------|----------|-----------------|
| `DB_USERNAME`                | Username for connecting to MongoDB       | `maggor` | `maggor`        |
| `DB_PASSWORD`                | Password for connecting to MongoDB       | `maggor` | `secure123`     |
| `MONGO_INITDB_ROOT_USERNAME` | Root user to initialize MongoDB          | `mongo`  | `maggor`        |
| `MONGO_INITDB_ROOT_PASSWORD` | Root password to initialize MongoDB      | `mongo`  | `secure123`     |
| `MONGO_INITDB_DATABASE`      | Database to create during initialization | `mongo`  | `admin`         |

