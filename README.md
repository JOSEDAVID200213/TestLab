# 🧪 TestLab — Professional QA & DevSecOps Platform

![CI Pipeline](https://github.com/JOSEDAVID200213/testlab-qa/actions/workflows/ci.yml/badge.svg)
![Release](https://github.com/JOSEDAVID200213/testlab-qa/actions/workflows/release.yml/badge.svg)
![Coverage](https://img.shields.io/badge/coverage-80%25-green?style=flat-square)
![Security](https://img.shields.io/badge/security-OWASP%20%2B%20Trivy-blue?style=flat-square)
![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.2-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Angular](https://img.shields.io/badge/Angular_17+-DD0031?style=for-the-badge&logo=angular&logoColor=white)
![Playwright](https://img.shields.io/badge/Playwright-45ba4b?style=for-the-badge&logo=playwright&logoColor=white)
![k6](https://img.shields.io/badge/k6-7D64FF?style=for-the-badge&logo=k6&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![OWASP](https://img.shields.io/badge/OWASP-000000?style=for-the-badge&logo=owasp&logoColor=white)
![Trivy](https://img.shields.io/badge/Trivy-1904DA?style=for-the-badge&logo=aqua&logoColor=white)
![ITIL v4](https://img.shields.io/badge/ITIL_v4-002E5F?style=for-the-badge&logo=axelos&logoColor=white)

A full-stack project demonstrating **professional-grade software testing, DevSecOps, and ITIL v4 Service Management**
across 5 testing levels plus automated security scanning and release management. Built on a real
inventory management API in Java + Spring Boot, with a live QA dashboard in Angular.

> This project is not about the inventory system — it is about demonstrating
> every layer of a **production testing + security strategy** that most developers skip.

![TestLab Dashboard Preview](./docs/preview.png)

---

## 🎯 Testing Philosophy

Most projects have unit tests. Few have all of this working together:

| Level | Tool | What it proves |
|---|---|---|
| 1 — Unit | JUnit 5 + Mockito | Business logic works in isolation |
| 2 — Integration | Testcontainers | API works with a real PostgreSQL database |
| 3 — Architecture | ArchUnit | Code structure rules are enforced automatically |
| 4 — E2E | Playwright (TypeScript) | Full user flows work across 3 browsers |
| 5 — Performance | k6 | API holds up under load, stress and spike traffic |

Every level runs automatically on every push via **GitHub Actions**.

---

## 🔒 Security & DevSecOps

Security is not an afterthought — it's embedded in every stage of the pipeline.

### Automated Security Scanning

| Tool | What it Scans | Fail Criteria |
|---|---|---|
| **OWASP Dependency-Check** | Java/Maven dependencies for known CVEs | CVSS score ≥ 7.0 |
| **Trivy** | Docker images (backend + frontend) | Any CRITICAL or HIGH vulnerability |
| **Spring Security** | Runtime API protection | Unauthorized access blocked |
| **ArchUnit** | Code patterns that could introduce vulnerabilities | Violations fail the build |

### Vulnerability Management Workflow

```
 ┌──────────────┐    ┌──────────────┐    ┌──────────────┐    ┌──────────────┐
 │  Discovery   │───▶│   Triage     │───▶│ Remediation  │───▶│  Verified    │
 │              │    │              │    │              │    │              │
 │ OWASP/Trivy  │    │ CVSS Scoring │    │ Fix + PR     │    │ CI re-scan   │
 │ finds a CVE  │    │ Impact check │    │ Suppression? │    │ All green ✅ │
 └──────────────┘    └──────────────┘    └──────────────┘    └──────────────┘
```

### Issue Templates

The project includes structured templates for **incident management** (similar to Jira workflows):

- 🐛 **Bug Report** — Severity, component, steps to reproduce, environment
- 🔒 **Security Vulnerability** — CVE ID, CVSS score, impact assessment, remediation plan

### Running Security Scans Locally

```bash
# Scan dependencies for known CVEs
cd backend
mvn dependency-check:check
open target/dependency-check/dependency-check-report.html

# Scan Docker images
docker build -t testlab-backend:local ./backend
trivy image testlab-backend:local
```

See [SECURITY.md](./SECURITY.md) for the full security policy, response SLAs, and reporting guidelines.

---

## 🏗️ Architecture

```


TESTING PYRAMID
                    
                        /\
                       /  \
                      / k6 \          Level 5 — Performance
                     /______\         smoke, load, stress, spike
                    /        \
                   / Playwright\      Level 4 — E2E
                  /____________\      3 browsers, Page Object Model
                 /              \
                /   ArchUnit     \    Level 3 — Architecture
               /________________\    structural rule enforcement
              /                  \
             / Testcontainers     \   Level 2 — Integration
            /____________________\   real PostgreSQL via Docker
           /                      \
          /   JUnit 5 + Mockito    \  Level 1 — Unit
         /________________________\  isolated, fast, no I/O

```
---

## 🛠️ Tech Stack

**Backend (System Under Test)**

| Technology | Purpose |
|---|---|
| Java 21 | Main language |
| Spring Boot 3.2+ | REST API framework |
| Spring Data JPA + Hibernate | ORM and database access |
| PostgreSQL 15 | Relational database |
| Spring Security + JWT | Authentication |
| Lombok | Boilerplate reduction |
| MapStruct | DTO mapping |
| Maven | Build tool |

**Testing**

| Technology | Level | Purpose |
|---|---|---|
| JUnit 5 (Jupiter) | Unit | Test runner |
| Mockito | Unit | Dependency mocking |
| AssertJ | Unit + Integration | Fluent assertions |
| Testcontainers | Integration | Real PostgreSQL in tests |
| MockMvc | Integration | HTTP layer testing |
| JaCoCo | Coverage | Enforce 80% minimum |
| ArchUnit | Architecture | Structural rule validation |
| Playwright + TypeScript | E2E | Browser automation, 3 engines |
| k6 | Performance | Load, stress, spike testing |

**Security & DevSecOps**

| Technology | Purpose |
|---|---|
| OWASP Dependency-Check | CVE scanning for Java dependencies |
| Trivy | Container image vulnerability scanning |
| Spring Security | API authentication and authorization |
| GitHub Issue Templates | Structured incident & vulnerability management |

**Frontend & CI/CD**

| Technology | Purpose |
|---|---|
| Angular 17+ + Signals | QA metrics dashboard |
| Chart.js + ng2-charts | Coverage and history charts |
| TailwindCSS | Styling |
| GitHub Actions | CI/CD pipeline (6 stages + quality gate) |
| Docker Compose | Local environment |
| Semantic Versioning | Automated release management |

---

## 🚀 Getting Started

### Prerequisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Java 21](https://adoptium.net/)
- [Maven 3.9+](https://maven.apache.org/)
- [Node.js 20+](https://nodejs.org/)
- [k6](https://k6.io/docs/getting-started/installation/) (for performance tests)
- [Trivy](https://aquasecurity.github.io/trivy) (for local security scans)

### 1. Clone the repository

```bash
git clone https://github.com/JOSEDAVID200213/testlab-qa.git
cd testlab-qa
```

### 2. Start the environment

```bash
docker-compose up --build
```

| Service | URL |
|---|---|
| QA Dashboard (Angular) | http://localhost:4200 |
| API (Spring Boot) | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| PostgreSQL | localhost:5432 |

---

## 🧪 Running the Tests

### Level 1 — Unit Tests

Fast, no Docker needed. Tests business logic in complete isolation.

```bash
cd backend
mvn test -Dgroups="unit"
```

Expected output: ~25 tests, all passing, under 5 seconds.

### Level 2 — Integration Tests

Requires Docker running (Testcontainers spins up PostgreSQL automatically).

```bash
cd backend
mvn test -Dgroups="integration"
```

Expected output: ~20 tests against a real database, under 60 seconds.

### Level 3 — Architecture Tests

Validates that the codebase follows structural rules. Runs with unit tests.

```bash
cd backend
mvn test -Dtest=ArchitectureRulesTest
```

Rules enforced:
- Controllers never access Repositories directly
- Services never depend on Controllers  
- All DTOs are free of JPA annotations
- Entities never appear as Controller method parameters

### Level 4 — E2E Tests (Playwright)

Requires the full stack running (`docker-compose up` first).

```bash
cd e2e
npm install
npx playwright install
npx playwright test
```

Run on a single browser:
```bash
npx playwright test --project=chromium
```

View the HTML report after a run:
```bash
npx playwright show-report
```

Tests run against **Chromium, Firefox and WebKit** simultaneously.
Screenshots and videos are saved automatically on failure.

### Level 5 — Performance Tests (k6)

Requires the backend running. Each script has a different load profile.

```bash
# Smoke — 1 VU, 2 min — is the API alive and responding?
k6 run performance/scripts/smoke-test.js

# Load — ramp to 50 VUs over 5 min — normal expected traffic
k6 run performance/scripts/load-test.js

# Stress — ramp to 200 VUs — find where degradation starts
k6 run performance/scripts/stress-test.js

# Spike — sudden 500 VUs for 30s — simulate a traffic burst
k6 run performance/scripts/spike-test.js
```

Pass/fail thresholds (build fails if not met):
- p(95) response time under 500ms
- Error rate below 1%
- Failed requests below 1%

---

## 📊 Code Coverage

JaCoCo enforces minimum coverage on every build. **The build fails if thresholds are not met.**

| Layer | Minimum Line Coverage |
|---|---|
| Service layer | 90% |
| Overall project | 80% |
| Overall branches | 70% |

View the HTML report locally after running tests:

```bash
cd backend
mvn test
open target/site/jacoco/index.html   # macOS
# Windows: start target/site/jacoco/index.html
```

Excluded from coverage measurement: DTOs, config classes,
`TestLabApplication.java`, MapStruct-generated mappers.

---

## 🔄 CI/CD Pipeline

Every push to `main` and every pull request triggers this pipeline:


```

push to main / PR
│
▼
┌─────────────────┐
│  build + unit   │  mvn test -Dgroups="unit" + JaCoCo
│     tests       │
└────────┬────────┘
         │ needs: build
    ┌────┴────────────────────┐
    ▼                         ▼
┌──────────────┐    ┌────────────────────┐
│ integration  │    │ security scans     │
│   tests      │    │                    │
│              │    │ ├─ OWASP Dep-Check │
│ Testcontainers│   │ └─ Trivy (Docker)  │
└──────┬───────┘    └────────┬───────────┘
       │                     │
  ┌────┴────┐                │
  ▼         ▼                │
┌────────┐ ┌──────────┐      │
│  e2e   │ │ perf     │      │
│ tests  │ │ smoke    │      │
│        │ │          │      │
│Playwright│ │k6       │     │
└────┬───┘ └────┬─────┘      │
     │          │             │
     └──────────┴─────────────┘
                │
                ▼
        ┌───────────────┐
        │ Quality Gate  │  All stages must pass ✅
        │               │
        │ Ready for     │
        │ release       │
        └───────────────┘

```

### Release Pipeline

When a version tag is pushed (`v1.0.0`), a separate release workflow:

1. ✅ Builds and packages the `.jar`
2. ✅ Builds Docker images with version tags
3. ✅ Generates a changelog from git commits
4. ✅ Creates a **GitHub Release** with the `.jar` as downloadable asset

```bash
# Create a release:
git tag v1.0.0
git push origin v1.0.0
```

### Artifacts per CI run:
- JaCoCo HTML coverage report
- OWASP Dependency-Check HTML report
- Playwright HTML report + failure screenshots + videos
- k6 summary JSON

---

## 📁 Project Structure


```
testlab/
├── backend/                          Java + Spring Boot API
│   └── src/
│       ├── main/java/com/testlab/
│       │   ├── controller/           REST endpoints
│       │   ├── service/              Business logic (unit tested)
│       │   ├── repository/           JPA repositories
│       │   ├── model/                JPA entities
│       │   ├── dto/                  Request/Response objects
│       │   ├── mapper/               MapStruct mappers
│       │   ├── exception/            Custom exceptions + handler
│       │   └── testresults/          Endpoints for QA dashboard
│       └── test/java/com/testlab/
│           ├── unit/                 JUnit 5 + Mockito tests
│           ├── integration/          Testcontainers IT tests
│           └── architecture/         ArchUnit rules
│
├── e2e/                              Playwright E2E tests
│   ├── tests/                        Test specs
│   └── pages/                        Page Object Models
│
├── performance/                      k6 scripts
│   └── scripts/
│       ├── smoke-test.js
│       ├── load-test.js
│       ├── stress-test.js
│       └── spike-test.js
│
├── frontend/                         Angular QA Dashboard
│   └── src/app/
│       ├── features/
│       │   ├── dashboard/            Overview metrics
│       │   ├── test-runs/            Build history
│       │   ├── coverage/             JaCoCo charts
│       │   └── performance/          k6 results
│       └── core/services/
│
├── .github/
│   ├── workflows/
│   │   ├── ci.yml                    CI pipeline (6 stages + quality gate)
│   │   └── release.yml               Automated release on version tags
│   └── ISSUE_TEMPLATE/
│       ├── bug_report.yml            Structured bug reporting
│       ├── security_vulnerability.yml CVE/vulnerability tracking
│       └── config.yml                Template configuration
│
├── docker-compose.yml                Dev environment
├── docker-compose.test.yml           Test environment
├── SECURITY.md                       Security policy & SLAs
└── INCIDENT_MANAGEMENT.md            ITIL v4 Incident Framework
```

---

## 📋 API Reference

**Products**

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/products` | List all (paginated, filterable) |
| GET | `/api/products/{id}` | Get by ID |
| POST | `/api/products` | Create new product |
| PUT | `/api/products/{id}` | Update product |
| DELETE | `/api/products/{id}` | Soft delete |
| GET | `/api/products/{id}/movements` | Stock history |

**Inventory**

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/inventory/in` | Add stock |
| POST | `/api/inventory/out` | Remove stock (fails if insufficient) |
| GET | `/api/inventory/low-stock` | Products below threshold |

Full interactive docs available at `http://localhost:8080/swagger-ui.html`.

---

## 📈 Reading the QA Dashboard

Open `http://localhost:4200` after starting the environment.

**Overview cards** — Pass rate, total tests, coverage %, last run time.
Color coding: green above 80%, yellow 60-80%, red below 60%.

**Build history chart** — Last 30 CI runs plotted as a line.
Click any point to expand that run's full test breakdown.

**Coverage breakdown** — Horizontal bars per package.
Lets you identify which layer needs more test coverage at a glance.

**Performance metrics** — p50/p90/p95/p99 response times from the
last k6 run. Anything above 500ms at p95 signals a problem.

**Recent failures** — Table of failing tests with error messages.
Click any row to see the full stack trace in a modal.

---

## 👤 Author

**Jose David Muñoz Riascos**
Systems Engineer · AI Master's Student

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/jose-david-munoz-riascos-23a811342)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/JOSEDAVID200213)
[![Email](https://img.shields.io/badge/Email-EA4335?style=for-the-badge&logo=gmail&logoColor=white)](mailto:j.munoz6@utp.edu.co)

---

## 📄 License

This project is open source and available under the
[MIT License](./LICENSE).