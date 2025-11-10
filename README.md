# Hospital Registration Queue

Simple Spring Boot REST API to manage hospital patient registration queue.

## Tech stack
- Java 17
- Spring Boot 3.2.x
- Spring Data JPA (Hibernate)
- PostgreSQL (configured)
- Lombok
- OpenAPI / Swagger (springdoc)

## Setup (quick)
### Option A — run with local PostgreSQL
1. Create database:
```sql
CREATE DATABASE hospitaldb;
-- Make sure PostgreSQL is running and accessible at localhost:5432
```
2. Update `src/main/resources/application.properties` if needed (currently uses `postgre` / `admin`).
3. Run:
```bash
mvn spring-boot:run
```
Open Swagger UI: `http://localhost:8080/swagger-ui/index.html`

### Option B — run with Docker Compose (recommended)
If you have Docker, a `docker-compose.yml` is included. Run:
```bash
docker compose up -d
# wait a few seconds for Postgres to be ready
mvn spring-boot:run
```

## Endpoints (summary)
- `GET /api/queue` — list queue entries
- `POST /api/queue` — create new entry, e.g.:
  {
    "patientName":"Budi",
    "department":"Cardiology"
  }
- `GET /api/queue/{id}` — get entry
- `PUT /api/queue/{id}/status` — update status, e.g.:
  { "status": "SERVING" }
- `DELETE /api/queue/{id}` — delete entry

## Notes
- Uses `spring.jpa.hibernate.ddl-auto=update` for simplicity. For production, use migrations (Flyway/Liquibase).
- Lombok is used; enable annotation processing in your IDE.
