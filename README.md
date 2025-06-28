# Maggor - Share Beautiful Moments

> A platform to share beautiful moments with the people you ❤️ the most.

## Installation with Docker

Maggor is perfectly optimized to work with Docker.
An installation without docker is possible but not recommended.

1. If you haven't Docker installed, [follow the Docker installation steps](https://docs.docker.com/engine/install/) for
   your operating system.
2. Clone this repository with `git clone https://github.com/Gregor-Gottschewski/Maggor.git` and edit the configuration
   file `/conf/conf.prop` for your needs. See the chapter [configuration file](#configuration-file) for more
   information.
3. Change the database password stored in `.env` before running Maggor. This password is used to initialize the MongoDB
   database for the first time.
4. Run `docker-compose up` in the project's directory.
5. Visit `http://<your_ip or localhost>:8080/login.xhtml`.

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
# UI
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

