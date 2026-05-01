# TestLab 🧪

[![Build Status](https://github.com/user/testlab/actions/workflows/ci.yml/badge.svg)](https://github.com/user/testlab/actions)
[![Coverage](https://img.shields.io/badge/coverage-85%25-brightgreen.svg)](backend/target/site/jacoco/index.html)
[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-6db33f.svg)](https://spring.io/projects/spring-boot)

TestLab is a professional-grade testing demonstration project showcasing multi-level testing strategies for a Spring Boot inventory management system.

## 🏗️ Architecture

```text
┌───────────────────────────────────────────────────────────┐
│                    QA Dashboard (Angular)                 │
└─────────────────────────────┬─────────────────────────────┘
                              │ REST API
┌─────────────────────────────▼─────────────────────────────┐
│                 Spring Boot Backend (Java 21)             │
├──────────────┬──────────────┬──────────────┬──────────────┤
│  Unit Tests  │ Integr. Tests│ Arch. Tests  │  Perf. Tests │
│   (JUnit 5)  │ (Testcont.)  │  (ArchUnit)  │     (k6)     │
└──────────────┴──────────────┴──────────────┴──────────────┘
                              │
               ┌──────────────▼──────────────┐
               │    PostgreSQL Database      │
               └─────────────────────────────┘
```

## 🧪 Testing Levels

1.  **Level 1: Unit Testing**
    *   **Tools**: JUnit 5, Mockito, AssertJ.
    *   **Scope**: All business logic in Service layer.
    *   **Run**: `mvn test -Dgroups="unit"`

2.  **Level 2: Integration Testing**
    *   **Tools**: Testcontainers, MockMvc.
    *   **Scope**: Database persistence and HTTP endpoints.
    *   **Run**: `mvn test -Dgroups="integration"`

3.  **Level 3: Architecture Testing**
    *   **Tools**: ArchUnit.
    *   **Scope**: Dependency rules and package structure enforcement.

4.  **Level 4: End-to-End (E2E) Testing**
    *   **Tools**: Playwright, TypeScript.
    *   **Scope**: Full user flows (Product CRUD, Inventory movements).
    *   **Run**: `cd e2e && npx playwright test`

5.  **Level 5: Performance Testing**
    *   **Tools**: k6.
    *   **Scope**: Smoke, Load, Stress, and Spike tests.
    *   **Run**: `k6 run performance/scripts/load-test.js`

## 📊 Quality Dashboard

The Angular dashboard provides real-time visibility into:
*   **Build Status**: Historical pass/fail rates.
*   **Code Coverage**: JaCoCo reports per module.
*   **Performance Metrics**: Latency trends from k6 runs.

## 🚀 Getting Started

1.  **Local Environment**:
    ```bash
    docker-compose up -d
    ```
2.  **Run Full Test Suite**:
    ```bash
    docker-compose -f docker-compose.test.yml up --exit-code-from backend-test
    ```

## 📜 License
MIT
