# Security Policy

## 🔒 Supported Versions

| Version | Supported          |
|---------|--------------------|
| 1.x.x   | ✅ Active support   |
| 0.x.x   | ⚠️ Best effort only |

## 🛡️ Security Practices

This project implements multiple layers of security scanning as part of its DevSecOps pipeline:

### Automated Security Checks (CI/CD)

| Tool | What it Scans | Trigger |
|------|---------------|---------|
| **OWASP Dependency-Check** | Java/Maven dependencies for known CVEs | Every push & PR |
| **Trivy** | Docker images for OS and library vulnerabilities | Every push & PR |
| **JaCoCo** | Code coverage (security-relevant code must be tested) | Every push & PR |
| **ArchUnit** | Architecture rules preventing unsafe patterns | Every push & PR |

### Security Thresholds

- **OWASP Dependency-Check:** Build fails if any dependency has a CVSS score ≥ 7.0
- **Trivy:** Build fails if any CRITICAL or HIGH vulnerability is found in Docker images
- **Code Coverage:** Minimum 80% line coverage enforced

## 📋 Reporting a Vulnerability

If you discover a security vulnerability, please follow responsible disclosure:

### For Non-Critical Issues
1. Open a [Security Vulnerability Issue](../../issues/new?template=security_vulnerability.yml)
2. Fill in the severity, CVE ID (if applicable), and impact assessment
3. The maintainer will triage and respond within **48 hours**

### For Critical Issues
1. **Do NOT open a public issue**
2. Send an email to: **j.munoz6@utp.edu.co**
3. Include:
   - Description of the vulnerability
   - Steps to reproduce
   - Potential impact
   - Suggested remediation (if any)
4. You will receive a response within **24 hours**

## 🔄 Vulnerability Response Process

```
Discovery → Triage → Assessment → Remediation → Verification → Disclosure
   │           │          │            │              │             │
   └─ Report   └─ 24-48h  └─ CVSS      └─ Fix + PR    └─ CI passes  └─ Release
      received    response    scoring      created        all scans     notes
```

### Response Timeline

| Severity | Initial Response | Fix Target | Disclosure |
|----------|-----------------|------------|------------|
| 🔴 Critical | 24 hours | 48 hours | After fix is deployed |
| 🟠 High | 48 hours | 1 week | After fix is deployed |
| 🟡 Medium | 1 week | 2 weeks | Next release |
| 🟢 Low | 2 weeks | Next release | Next release |

## 🔍 Security Scanning Locally

You can run the same security checks locally before pushing:

### Dependency Vulnerability Scan
```bash
cd backend
mvn dependency-check:check
# Report: target/dependency-check/dependency-check-report.html
```

### Docker Image Scan
```bash
# Install Trivy: https://aquasecurity.github.io/trivy
docker build -t testlab-backend:local ./backend
trivy image testlab-backend:local

docker build -t testlab-frontend:local ./frontend
trivy image testlab-frontend:local
```

## 📚 References

- [OWASP Dependency-Check](https://owasp.org/www-project-dependency-check/)
- [Trivy Container Scanner](https://aquasecurity.github.io/trivy)
- [NVD — National Vulnerability Database](https://nvd.nist.gov/)
- [CVSS Calculator](https://www.first.org/cvss/calculator/3.1)
