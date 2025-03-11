# Reference Project

[![Java CI with Maven](https://github.com/collinsrj/referenceproject/actions/workflows/build.yml/badge.svg)](https://github.com/collinsrj/referenceproject/actions/workflows/build.yml)

A reference Spring Boot project demonstrating:
- Spring WebFlux
- Spring Security
- Unit Testing
- Code Quality Tools (SpotBugs, JaCoCo, OWASP Dependency Check)
- GitHub Actions CI/CD

## Building

```bash
mvn clean verify
```

## Security

The project uses Basic Authentication with the following default credentials:
- User: username=user, password=password
- Admin: username=admin, password=admin

## Reports

After building, the following reports are available:
- JaCoCo Coverage: `target/site/jacoco/index.html`
- OWASP Dependency Check: `target/dependency-check-report.html`
- SpotBugs: `target/spotbugs/spotbugs.html` 