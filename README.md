# 🧪 Proyecto de Práctica de Testing en Spring Boot

Este repositorio contiene ejercicios prácticos enfocados en testing para proyectos Java backend usando **Spring Boot**, **JUnit 5** y **Mockito**. Está diseñado como una bitácora de aprendizaje y evolución, con ejemplos simples, claros y profesionales.

---

## 🎯 Objetivos del proyecto

- ✅ Dominar testing con **JUnit 5** en proyectos Spring Boot  
- ✅ Aprender **Mockito** para simular dependencias  
- ✅ Aplicar principios de **TDD (Test Driven Development)**  
- ✅ Simular escenarios reales de validaciones y excepciones  
- ✅ Practicar buenas prácticas para entrevistas de trabajo y proyectos reales  

---

## 🧰 Tecnologías usadas

| Herramienta              | Versión     |
|--------------------------|-------------|
| Java                     | 17          |
| Spring Boot              | 3.2.5       |
| Spring Data JPA          | 3.x         |
| JUnit Jupiter (JUnit 5)  | 5.10.2      |
| Mockito                  | 5.7.0       |
| H2 Database              | 2.2.224     |
| Lombok                   | ✅          |
| Maven                    | ✅          |
| IntelliJ IDEA / Eclipse  | Compatible  |

---

## 📁 Estructura del repositorio

```bash
.
├── src
│   ├── main
│   │   └── java
│   │       └── com.api.rest
│   │           ├── model
│   │           │   └── Empleado.java
│   │           ├── repository
│   │           │   └── EmpleadoRepository.java
│   │           ├── service
│   │           │   └── EmpleadoService.java
│   │           ├── service.impl
│   │           │   └── EmpleadoServiceImpl.java
│   │           ├── controller
│   │           │   └── EmpleadoController.java
│   │           └── exception
│   │               └── ResourceNotFoundException.java
│   └── test
│       └── java
│           └── com.api.rest
│               ├── service
│               │   └── EmpleadoServiceImplTest.java
│               ├── controller
│               │   └── EmpleadoControllerTest.java
│               └── exception
│                   └── ResourceNotFoundExceptionTest.java
├── pom.xml
└── README.md

⚙️ Configuración del proyecto
application.properties
properties
Copiar
Editar
# Configuración de H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Mostrar la consola web de H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Mostrar las consultas SQL
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
🚀 Cómo ejecutar
Cloná el repositorio:

bash
Copiar
Editar
git clone https://github.com/tuusuario/springboot-testing-practice.git
cd empresasolar-test-practice
Abrí el proyecto con tu IDE (IntelliJ IDEA o Eclipse).

Corré la clase principal: springboot-testing-practice.

Accedé a los endpoints desde Postman o el navegador.

Consola de H2: http://localhost:8080/h2-console

✅ Tests incluidos
Pruebas unitarias del springboot-testing-practice con Mockito

Pruebas de integración del EmpleadoController

Validación de excepciones con ResourceNotFoundException

📚 Buenas prácticas aplicadas
Separación de capas (Controller, Service, Repository)

Pruebas unitarias con mocks

Principios de TDD

Manejo de errores centralizado

🙌 Autor
Fabian Martínez
💻 Electricista & Java Backend Developer
📍 Buenos Aires, Argentina
🔗 En práctica constante y construcción de portafolio para oportunidades profesionales

📌 Estado del proyecto
✅ En desarrollo continuo — cada clase o concepto de testing aprendido se sube con commits claros.
