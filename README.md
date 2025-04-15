# P5
Aplicación web que usa Spring JPA para persistir los datos de un API REST de gestión de usuarios.
El API permite el registro de nuevos usuarios y su identificación mediante email y password.
Una vez identificados, se emplea una cookie de sesión para autenticar las peticiones que permiten 
a los usuarios leer, modificar y borrar sus datos. También existe un endpoint para cerrar la sesión.  

## Endpoints

// TODO#1: rellena la tabla siguiente analizando el código del proyecto


| Método | Ruta                   | Descripción                                                      | Respuestas                                                   |
|--------|------------------------|------------------------------------------------------------------|--------------------------------------------------------------|
| POST   | `/api/users`           | Registra un nuevo usuario con nombre, email y contraseña        | `201 Created` si tiene éxito, `409 Conflict` si ya existe    |
| POST   | `/api/users/me/session`| Inicia sesión (login) con email y contraseña, devuelve una cookie de sesión | `201 Created` si login correcto, `401 Unauthorized` si falla |
| DELETE | `/api/users/me/session`| Cierra la sesión del usuario autenticado                        | `204 No Content`, elimina la cookie                          |
| GET    | `/api/users/me`        | Devuelve el perfil del usuario autenticado                      | `200 OK` si autenticado, `401 Unauthorized` si no            |
| PUT    | `/api/users/me`        | Actualiza el perfil del usuario autenticado                     | `200 OK` si éxito, `401 Unauthorized` si no autenticado      |
| DELETE | `/api/users/me`        | Elimina la cuenta del usuario autenticado                       | `204 No Content` si éxito, `401 Unauthorized` si no          |



## Comandos 

- Construcción: 
  ```sh
  ./mvnw clean package
  ```

- Ejecución: 
  ```sh
  ./mvnw spring-boot:run
  ```

- Tests:
  ```sh
  ./mvnw test
  ```
