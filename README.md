# Sistema de gestion ambiental (Java)

## Apis 📋 

#### app-user

Este microservicio se encarga de la crear, consultar, actualizar y eliminar usuarios

#### app-waste

El microservicio se encarga de gestionar toda la informacion referente al manejo de residuos

## Pre-requisitos 📋

- Tener una version instalada de Jdk 17
- Una instalacion de docker

### Instalación 🔧

- Clonar el respositorio

```
https://github.com/ElFierro/sga_manejo_de_residuos.git
```
- Instalar maven

**Ejemplo:**

Dentro del proyecto en una terminal ejecute el siguiente comando:

```
mvn install
```
- Configure el archivo settings.xml 

Para accerder al core debe agregar la ruta y token de acceso en el archivo settings.xml que esta ubicado en la carpeta .../.m2 (Solicitar estas credenciales al administrador del proyecto)

**Ejemplo:**
```
 <servers>
    <server>
      <id>github</id>
      <username>nombre_del_usuario_en_minusculas</username>
      <password>token</password>
    </server>
  </servers>
```

- Crear la imagen del microservicio

**Ejemplo:**

En la ruta ...\Apis\user-app ejecutamos en la consola el siguiente comando:

```
docker build -t spring-userapp-image:1.0 .
```

Ejecute el archivo docker-compose

**Ejemplo:**

En la ruta ...\Apis ejecutamos en la consola del cmd el siguiente comando:

```
Docker-compose up
```

## Construido con 🛠️

- Spring boot - version 3.2.5

#### Entorno de desarrollo

- JDK version 17

#### Manejador de dependencias

- Maven

#### Dependencias

- Spring BootDevTools
- Springdoc (swagger) - 2.3.0
- Springboot Data MongoDB
- Spring web
- Lombok
- Core 1.0
- Spring Security

#### Empaquetado

- Jar

## Autores ✒️

* **Cristian Fierro** 
* **Jayler Castillo** 
* **Jhon Machado** 
