# Maggor - Share Beautiful Moments

> A platform to share beautiful moments with the people you ❤️ the most.

## Installation with Docker

Maggor is perfectly optimized to work with Docker. If you haven't Docker installed, follow the Docker installation steps
for your operating system.

Afterward, clone this repository and edit the configuration file `/conf/conf.prop` for your needs. See the
chapter [configuration file](#configuration-file) for more information.
Run `docker-compose up` and the software starts. Visit `http://<your_ip or localhost>:8080/maggor-0.0.1/login.xhtml`

## Configuration file

Create a configuration file named `conf.prop` in Maggor's application directory.
It has to contain the following configurations:

`user.password`: Password of the application user  
`login.title`: Title shown on the login page  
`login.subtitle`: Subtitle shown on the login page  
`login.footer`: Footer on the login page  
`db.name`: Database name (has to be the same name as in the `docker-compose.yaml`)  
`db.port`: Port to the database (has to be the same name as in the `docker-compose.yaml`)  
`db.root.username`: Username of the root user of the database (has to be the same name as in the
`docker-compose.yaml`)  
`db.root.password`: Password of the root user of the database (has to be the same name as in the `docker-compose.yaml`)

### Example file

```properties
user.password=<user_password_here>
# UI
login.title=Here is the title
login.subtitle=Here is the subtitle
login.footer=This is the footer
# database
db.name=mongo
db.port=27017
db.root.username=maggor
db.root.password=<db_password_here>
```
