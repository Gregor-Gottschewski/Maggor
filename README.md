# Maggor - Share Beautiful Moments

> A platform to share beautiful moments with the people you ❤️ the most.

## Installation with Docker

Maggor is perfectly optimized to work with Docker.
If you haven't Docker installed, follow the Docker installation steps for your operating system.

Afterward, clone this repository and edit the configuration file `/conf/conf.prop` for your needs.
See the chapter [configuration file](#configuration-file) for more information.
Run `docker-compose up` in the projects directory.
Visit `http://<your_ip or localhost>:8080/maggor-0.0.1/login.xhtml`.

## Configuration file

Create a configuration file named `conf.prop` in Maggor's application directory.
It has to contain the following configurations:

`user.password`: Password of the application user  
`login.title`: Title shown on the login page  
`login.subtitle`: Subtitle shown on the login page  
`login.footer`: Footer on the login page

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

| Environment Variable         | Description                              | Used In  | Default / Value |
|------------------------------|------------------------------------------|----------|-----------------|
| `DB_USERNAME`                | Username for connecting to MongoDB       | `maggor` | `maggor`        |
| `DB_PASSWORD`                | Password for connecting to MongoDB       | `maggor` | `secure123`     |
| `MONGO_INITDB_ROOT_USERNAME` | Root user to initialize MongoDB          | `mongo`  | `maggor`        |
| `MONGO_INITDB_ROOT_PASSWORD` | Root password to initialize MongoDB      | `mongo`  | `secure123`     |
| `MONGO_INITDB_DATABASE`      | Database to create during initialization | `mongo`  | `admin`         |

