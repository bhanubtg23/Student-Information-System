# Student Information System

Microservices for managing students, faculty, and gate passes.

**Services**
- discovery (Eureka)
- config-server
- gateway
- auth-service
- student-service
- faculty-service
- pass-service

## Run
```bash
mvn -q -DskipTests package
docker compose up --build
```

- Gateway: http://localhost:8080  
- Eureka: http://localhost:8761  
- Config Server: http://localhost:8888  
- Auth: http://localhost:8081/swagger-ui.html  
- Students: http://localhost:8082/swagger-ui.html  
- Passes: http://localhost:8083/swagger-ui.html  
- Faculty: http://localhost:8084/swagger-ui.html  

**Notes**
- JWT roles: `ROLE_STUDENT`, `ROLE_FACULTY`, `ROLE_ADMIN`
- Database: PostgreSQL (`sis` / `sisuser` / `sispass`)
