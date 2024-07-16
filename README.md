# foro-hub
challenge #3 - backend one

### Dependencies
- Lombok
- Spring Web
- Spring Boot DevTools
- Spring Data JPA
- Flyway Migration
- MySQL Driver
- Validation
- Spring Security
- Java JWT
- Springdoc

#### Credentials
- We're working with MySQL, so you must create a local db. Create a file `application.properties` following the structure in `application.properties.template` and set:
  - DB_HOST: host and port of your database
  - DB_name: name of your db
  - DB_usr: db username
  - DB_psw: db password
  - ${JWT_SECRET:123456}

#### Documentation
After running the application, go to `http://localhost:8080/swagger-ui.html` in your browser to check the available endpoints









