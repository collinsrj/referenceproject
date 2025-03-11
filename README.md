# Reference Project

[![Java CI with Maven](https://github.com/collinsrj/referenceproject/actions/workflows/build.yml/badge.svg)](https://github.com/collinsrj/referenceproject/actions/workflows/build.yml)
[![CodeQL](https://github.com/collinsrj/referenceproject/actions/workflows/codeql.yml/badge.svg)](https://github.com/collinsrj/referenceproject/actions/workflows/codeql.yml)

A reference Spring Boot project demonstrating:
- Reactive Web with WebFlux
- Security with JWT authentication
- Dependency scanning with OWASP
- Code quality with SpotBugs
- Code coverage with JaCoCo
- Automated dependency updates with Dependabot
- Code analysis with CodeQL

## Building

```bash
mvn clean verify
```

## Security

The project uses Basic Authentication with the following default credentials:
- User: username=user, password=password
- Admin: username=admin, password=admin

### Security Scanning
The project includes multiple security scanning tools:
- OWASP Dependency Check for vulnerable dependencies
- SpotBugs with FindSecBugs for static analysis
- CodeQL for advanced security analysis and vulnerability detection
- JaCoCo for code coverage

## Reports

After building, the following reports are available:
- JaCoCo Coverage: `target/site/jacoco/index.html`
- OWASP Dependency Check: `target/dependency-check-report.html`
- SpotBugs: `target/spotbugs/spotbugs.html`
- CodeQL: Available in GitHub Security tab

## Dependency Management

This project uses GitHub's Dependabot to keep dependencies up to date. Dependabot will:

- Check Maven dependencies weekly (every Monday at 9:00 AM IST)
- Check GitHub Actions weekly (every Monday at 9:00 AM IST)
- Create pull requests for updates with appropriate labels
- Ignore patch updates for Spring Boot dependencies
- Include scope information in commit messages

Pull requests from Dependabot will be labeled with either `dependencies` for Maven updates or `github-actions` for workflow updates.
