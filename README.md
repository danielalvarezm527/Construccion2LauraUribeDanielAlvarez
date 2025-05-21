# Sistema de Gestión de Veterinaria

## Descripción
Este sistema permite la gestión de una clínica veterinaria, incluyendo el registro de mascotas, dueños, historias clínicas, órdenes médicas y facturación. El proyecto soporta múltiples roles (Administrador, Veterinario, Vendedor) con funcionalidades específicas para cada uno.

## Tecnologías Utilizadas
- Spring Boot
- Spring Data JPA
- Lombok
- MySQL Database
- Maven

## Configuración y Ejecución

### Paso 1: Clonar el Repositorio
```bash
git clone https://github.com/LauraUribeFranco/Construccion2LauraUribeDanielAlvarez
cd Construccion2LauraUribeDanielAlvarez
```

### Paso 2: Configurar la Base de Datos
Editar el archivo `src/main/resources/application.properties` para configurar la conexión a la base de datos:
en caso de ser un puerto diferente al 3306
- la base de datos se crea automaticamente al ejecutar el proyecto

### Paso 3: Compilar el Proyecto (deberia ser automatico al abriri en algun ide)

### Paso 4: Ejecutar la Aplicación
La aplicación se iniciará y automáticamente creará un usuario administrador predeterminado:
- Usuario: `admin`
- Contraseña: `admin`

## Modos de Uso

### Interfaz de Consola
Al iniciar la aplicación, se mostrará un menú de consola con las siguientes opciones:
1. Iniciar sesión (con credenciales de administrador, veterinario o vendedor)
2. Registrarse como dueño de mascota
3. Salir

### API REST
La aplicación también expone una API REST para integración con otros sistemas:

#### Endpoints Principales:

- **EN LA RAIZ DEL PROYECTO ESTA EL ARCHIVO PARA IMPORTAR EN POSTMAN**

- **Autenticación**:
  - `POST /users/login` - Iniciar sesión

- **Gestión de Usuarios**:
  - `POST /users` - Registrar nuevo usuario (veterinario/vendedor)

- **Gestión de Dueños**:
  - `POST /owners` - Registrar dueño
  - `GET /owners/{document}` - Consultar dueño por documento

- **Gestión de Mascotas**:
  - `POST /pets` - Registrar mascota
  - `GET /pets/{id}` - Consultar mascota por ID

- **Gestión de Órdenes Médicas**:
  - `POST /orders` - Crear orden médica
  - `GET /orders/{id}` - Consultar orden por ID
  - `POST /orders/{id}/cancel` - Anular orden médica

- **Gestión de Historias Clínicas**:
  - `POST /medical-records` - Crear historia clínica
  - `GET /medical-records/pet/{petId}` - Consultar historias por mascota
  - `GET /medical-records/{id}` - Consultar historia por ID

- **Facturación**:
  - `POST /bills` - Crear factura
  - `GET /bills/{id}` - Consultar factura por ID
  - `GET /bills/pet/{petId}` - Consultar facturas por mascota
  - `GET /bills/order/{orderId}` - Consultar facturas por orden


## Funcionalidades

### Administrador
- Gestión de usuarios (veterinarios y vendedores)

### Veterinario
- Registro de mascotas
- Creación y consulta de historias clínicas
- Creación y anulación de órdenes médicas

### Vendedor
- Venta de medicamentos por orden médica
- Venta de otros productos
- Consulta de órdenes médicas

## Estado del Proyecto
El proyecto se encuentra en desarrollo. V 1.0

## Autores
- Laura Uribe
- Daniel Álvarez