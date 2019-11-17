# Spring Boot App
Spring Boot Application structure with important concepts implemented.

```
git clone https://github.com/AazibSafi/SpringBootApp.git
```

#### Prerquisite
1. Java 8
2. Apache Maven 3.6.2


#### Run it!
```
mvn clean install
```
```
mvn spring-boot:run
```

### Accessing Application
```
http://localhost:8082/
```

### H2 Database
H2 in-memory database is used. Database details and records can be viewed through H2 console

[http://localhost:8082/h2-console](http://localhost:8082/h2-console)

JDBC URL:  jdbc:h2:mem:personadb


## Swagger - Rest Api Documentation
Rest services can be tested directly through Swagger UI.

[http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)


Sample Request Body

        {
        	"name": "John",
        	"lastName": "Mathew",
        	"address": "USA",
        	"phoneNumber": 3212342,
        	"hairColor": "black"
        }