# 📚 REFERENCIA RÁPIDA - OPERACIONES API

## Base URL
```
https://reqres.in/api/
```

## Endpoints Disponibles

### 1️⃣ OBTENER TODOS LOS USUARIOS
**GET** `/users`

**Respuesta:**
```json
{
  "data": [
    {
      "id": 1,
      "email": "george.bluth@reqres.in",
      "first_name": "George",
      "last_name": "Bluth",
      "avatar": "https://reqres.in/img/faces/1-image.jpg"
    }
  ],
  "page": 1,
  "per_page": 6,
  "total": 12
}
```

**Código:**
```kotlin
val response = service.getUsers()
val users = response.data
```

---

### 2️⃣ OBTENER UN USUARIO POR ID
**GET** `/users/{id}`

**Ejemplo:** `GET /users/1`

**Respuesta:**
```json
{
  "data": {
    "id": 1,
    "email": "george.bluth@reqres.in",
    "first_name": "George",
    "last_name": "Bluth",
    "avatar": "https://reqres.in/img/faces/1-image.jpg"
  }
}
```

**Código:**
```kotlin
val response = service.getUser("1")
val user = response.data.first()
```

---

### 3️⃣ CREAR NUEVO USUARIO
**POST** `/users`

**Body (JSON):**
```json
{
  "first_name": "Juan",
  "last_name": "Pérez",
  "email": "juan@example.com"
}
```

**Respuesta:**
```json
{
  "first_name": "Juan",
  "last_name": "Pérez",
  "email": "juan@example.com",
  "id": 901,
  "createdAt": "2024-04-09T12:00:00.000Z"
}
```

**Código:**
```kotlin
val newUser = User(
  first_name = "Juan",
  last_name = "Pérez",
  email = "juan@example.com"
)
val response = service.createUser(newUser)
if (response.isSuccessful) {
  val createdUser = response.body()
}
```

---

### 4️⃣ ACTUALIZAR USUARIO
**PUT** `/users/{id}`

**Ejemplo:** `PUT /users/1`

**Body (JSON):**
```json
{
  "id": 1,
  "first_name": "George",
  "last_name": "Bluth",
  "email": "george.updated@reqres.in"
}
```

**Respuesta:**
```json
{
  "id": 1,
  "first_name": "George",
  "last_name": "Bluth",
  "email": "george.updated@reqres.in",
  "updatedAt": "2024-04-09T12:00:00.000Z"
}
```

**Código:**
```kotlin
val updatedUser = User(
  id = 1,
  first_name = "George",
  last_name = "Bluth",
  email = "george.updated@reqres.in"
)
val response = service.updateUser(1, updatedUser)
if (response.isSuccessful) {
  val user = response.body()
}
```

---

### 5️⃣ ELIMINAR USUARIO
**DELETE** `/users/{id}`

**Ejemplo:** `DELETE /users/1`

**Respuesta:**
```
(vacía - Status 204)
```

**Código:**
```kotlin
val response = service.deleteUser(1)
if (response.isSuccessful) {
  // Usuario eliminado correctamente
}
```

---

## Códigos de Estado HTTP

| Código | Significado | Acción |
|--------|------------|--------|
| 200 | OK | Operación exitosa |
| 201 | Created | Recurso creado |
| 204 | No Content | Eliminado correctamente |
| 400 | Bad Request | Error en los parámetros |
| 401 | Unauthorized | Sin autorización |
| 404 | Not Found | Recurso no encontrado |
| 500 | Server Error | Error del servidor |

---

## Límites y Notas

- ⚠️ La API es de prueba (reqres.in)
- 📄 Máximo 6 usuarios por página por defecto
- ⏱️ Los datos se resetean cada cierto tiempo
- 🔒 No requiere autenticación

---

## Manejo de Errores en el Código

```kotlin
try {
    val response = service.getUsers()
    if (response.isSuccessful) {
        val users = response.body()?.data
    } else {
        Log.e("API_ERROR", "Error: ${response.code()}")
    }
} catch (e: retrofit2.HttpException) {
    Log.e("API_ERROR", "HTTP Error: ${e.code()} - ${e.message()}")
} catch (e: Exception) {
    Log.e("API_ERROR", "Error: ${e.message}")
}
```

---

## ViewModel - Métodos Disponibles

```kotlin
// Cargar todos los usuarios
userViewModel.loadUsers()

// Obtener usuario por ID
userViewModel.getUser("1")

// Crear usuario
userViewModel.createUser("Juan", "Pérez", "juan@example.com")

// Actualizar usuario
userViewModel.updateUser(1, "Juan", "Pérez", "juan@example.com")

// Eliminar usuario
userViewModel.deleteUser(1)

// Limpiar error
userViewModel.clearError()

// Cerrar detalle
userViewModel.clearSelectedUser()
```

---

## StateFlow Disponibles para Observar

```kotlin
// Lista de usuarios
val users = userViewModel.users.collectAsState()

// Usuario seleccionado
val selectedUser = userViewModel.selectedUser.collectAsState()

// Estado de carga
val isLoading = userViewModel.isLoading.collectAsState()

// Mensajes de error
val errorMessage = userViewModel.errorMessage.collectAsState()
```


