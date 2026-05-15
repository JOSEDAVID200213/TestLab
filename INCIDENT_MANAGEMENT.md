# 📋 Incident & Service Management (ITIL v4 Framework)

Este proyecto no solo es una pieza técnica, sino que implementa las mejores prácticas de **ITIL v4** para asegurar la estabilidad del servicio y la satisfacción del usuario.

## 🏛️ Alineación con Prácticas ITIL v4

| Práctica ITIL v4 | Implementación en TestLab |
|------------------|---------------------------|
| **Incident Management** | Uso de [Structured Issue Templates](./.github/ISSUE_TEMPLATE/) para captura de datos crítica. |
| **Service Level Management** | Definición de **SLAs** de respuesta basados en severidad en [SECURITY.md](./SECURITY.md). |
| **Change Enablement** | Pipeline de CI/CD con **Quality Gates** que validan cambios antes de producción. |
| **Release Management** | Workflow automatizado de [Releases](./.github/workflows/release.yml) con versionamiento semántico. |
| **Information Security Management** | Escaneos preventivos de vulnerabilidades (**SCA** y **Container Scanning**). |
| **Problem Management** | Análisis de tendencias de fallos mediante el [QA Dashboard](http://localhost:4200). |

---

## 🛠️ Flujo de Gestión de Incidencias (Lifecycle)

Seguimos un flujo estándar de la industria para resolver fallos técnicos:

1.  **Identificación:** El incidente se detecta vía tests automáticos (CI) o reporte de usuario (GitHub Issues).
2.  **Registro:** Se utiliza el template de **Bug Report** para asegurar que se capture la severidad, el componente y los pasos de reproducción.
3.  **Clasificación:** Los incidentes se etiquetan automáticamente (`bug`, `security`, `triage`) para priorizar según el impacto.
4.  **Diagnóstico:** Uso de los reportes de **JaCoCo**, **Playwright** y **k6** generados en el CI para identificar la causa raíz.
5.  **Resolución:** Creación de un Fix, validación por el pipeline de CI/CD y despliegue mediante una nueva **Release**.
6.  **Cierre:** Verificación de la solución por tests E2E y cierre formal del Issue.

---

## ⏱️ Service Level Agreements (SLAs)

Para cumplir con la gestión de niveles de servicio, TestLab define los siguientes objetivos de respuesta inicial:

| Urgencia / Impacto | Prioridad | SLA (Respuesta Inicial) | SLA (Resolución Objetivo) |
|--------------------|-----------|--------------------------|---------------------------|
| **Crítica** (🔴)   | P1        | < 24 Horas               | < 48 Horas                |
| **Alta** (🟠)      | P2        | < 48 Horas               | < 1 Semana                |
| **Media** (🟡)     | P3        | < 1 Semana               | < 2 Semanas               |
| **Baja** (🟢)      | P4        | Best Effort              | Próximo Ciclo             |

---

## 📈 Mejora Continua (Continual Service Improvement)

El **QA Dashboard** integrado en Angular permite visualizar:
- Tasa de éxito de los despliegues.
- Evolución de la cobertura de tests.
- Tendencias de performance.

Estos datos se utilizan para identificar áreas de mejora en el servicio, alineándose con el principio de **Mejora Continua** de ITIL.
