# Backend - Sistema de Gestión de Tienda

## Descripción del proyecto

Backend de un sistema de gestión de tienda desarrollado con **Java y Spring Boot**. Proporciona servicios REST para administrar la información del negocio y permite la comunicación con el frontend desarrollado en React.

El sistema contempla la gestión de usuarios, clientes, productos y ventas.

## Tecnologías utilizadas

- **Java:** Lenguaje de programación.
- **Spring Boot:** Framework para el desarrollo del backend.
- **Spring Data JPA:** Persistencia y acceso a datos.
- **Hibernate:** ORM para el mapeo de entidades.
- **Maven:** Gestión de dependencias y construcción del proyecto.
- **Base de datos:** [Indicar motor de base de datos].
- **REST API:** Comunicación mediante servicios HTTP.
- **MapStruct:** Mapeo entre entidades y DTO.
- **Lombok:** Reducción de código repetitivo.

## Módulos del sistema

- **Usuarios:** Registro, consulta, actualización, autenticación y gestión del estado de usuarios.
- **Clientes:** Administración de la información de los clientes.
- **Productos:** Gestión de productos del negocio.
- **Ventas:** Registro y consulta de ventas.

## Arquitectura del proyecto

El backend utiliza una organización por capas para separar las responsabilidades:

```text
src/main/java/
└── com/tienda/
    ├── controller/
    ├── service/
    ├── repository/
    ├── model/
    ├── dto/
    ├── mapper/
    └── exception/
```

- **Controller:** Recibe las solicitudes HTTP y expone los endpoints REST.
- **Service:** Contiene la lógica de negocio.
- **Repository:** Gestiona las operaciones de acceso a datos.
- **Model:** Contiene las entidades del sistema.
- **DTO:** Define los objetos de transferencia de datos.
- **Mapper:** Convierte entidades y DTO.
- **Exception:** Gestiona las excepciones de la aplicación.

## Requisitos previos

Antes de ejecutar el proyecto, es necesario tener instalado:

- JDK de Java (versión compatible con el proyecto).
- Apache Maven.
- Motor de base de datos configurado.
- IDE como IntelliJ IDEA o Eclipse.

## Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone URL_DEL_REPOSITORIO
```

### 2. Ingresar a la carpeta del proyecto

```bash
cd tienda-backend
```

### 3. Configurar la base de datos

Modificar el archivo `application.properties` o `application.yml` con las credenciales correspondientes.

### 4. Instalar dependencias

```bash
mvn clean install
```

### 5. Ejecutar el proyecto

```bash
mvn spring-boot:run
```

El servidor estará disponible en:

```text
http://localhost:8080
```

## Endpoints principales

Los servicios REST están organizados por módulos:

| Módulo | Operaciones |
|---|---|
| Usuarios | Registro, listado, actualización y autenticación |
| Clientes | Registro, listado, actualización y eliminación |
| Productos | Registro, listado, actualización y eliminación |
| Ventas | Registro y consulta de ventas |

## Integración con el frontend

El backend proporciona servicios REST que son consumidos por el frontend desarrollado en React mediante solicitudes HTTP.

URL base del backend:

```text
http://localhost:8080
```

## Autor

Desarrollado como proyecto de aprendizaje y aplicación práctica de desarrollo backend con Java y Spring Boot.
