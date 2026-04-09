# 📖 GUÍA DE USO - RETROFITJC

## 🎮 Interfaz Principal

La aplicación tiene una interfaz limpia y moderna con las siguientes secciones:

### Barra Superior
- **Título:** "Gestión de Usuarios"
- **Botón "+ Crear nuevo usuario"** (verde primario)

### Lista de Usuarios
Cada usuario se muestra en una tarjeta con:
- **Avatar**: Círculo con la inicial del nombre (p.ej., "G" para George)
- **Información**:
  - Nombre completo en negrita
  - Email
  - ID (pequeño y gris)
- **Acciones**:
  - 🖊️ **Editar** (icono de lápiz, azul)
  - 🗑️ **Eliminar** (icono de papelera, rojo)

---

## 🔧 Operaciones CRUD

### 1. LISTAR USUARIOS (SELECT)
✅ **Automático** - Se cargan al iniciar la app

**Qué pasa:**
1. La app se abre
2. Se muestra un indicador de carga (spinner)
3. Se obtienen todos los usuarios de la API
4. Se muestran en la lista

**En código:**
```kotlin
LaunchedEffect(Unit) {
    userViewModel.loadUsers()
}
```

---

### 2. VER DETALLES DE UN USUARIO (SELECT ONE)
**Acción:** Click en la tarjeta del usuario o en el icono ✏️

**Qué pasa:**
1. Se abre un diálogo modal
2. Muestra:
   - Avatar grande (inicial)
   - ID del usuario
   - Nombre, Apellido, Email (en lectura)
   - Botones: Cerrar, Editar

**En código:**
```kotlin
onSelectUser = { user ->
    userViewModel.getUser(user.id.toString())
}
```

---

### 3. CREAR USUARIO (CREATE)
**Acción:** Click en botón "+ Crear nuevo usuario"

**Qué pasa:**
1. Se envía a la API:
   - Nombre: "Novo"
   - Apellido: "Usuario"
   - Email: "novo@example.com"
2. Se recarga la lista
3. El nuevo usuario aparece en la lista

**Personalizar valores:**
Edita `UserViewModel.kt`, método `createUser()`:
```kotlin
fun createUser(firstName: String, lastName: String, email: String) {
    val newUser = User(
        first_name = firstName,
        last_name = lastName,
        email = email
    )
    // ...
}
```

---

### 4. ACTUALIZAR USUARIO (UPDATE) ⭐ NUEVO
**Acción:** 
1. Click en usuario para abrir detalles
2. Click en botón "Editar"
3. Modifica los campos
4. Click "Guardar"

**Qué pasa:**
1. Los campos se habilitan para edición
2. Cambias los datos deseados
3. Al guardar, se envía PUT a la API
4. Se recarga la lista
5. El diálogo se cierra

**En código:**
```kotlin
onUpdateUser = { id, firstName, lastName, email ->
    userViewModel.updateUser(id, firstName, lastName, email)
}
```

---

### 5. ELIMINAR USUARIO (DELETE)
**Acción:** Click en icono 🗑️ en la tarjeta

**Qué pasa:**
1. Se envía DELETE a la API inmediatamente
2. El usuario se elimina de la lista
3. Se muestra indicador de carga
4. La lista se actualiza

**En código:**
```kotlin
onDeleteUser = { id ->
    userViewModel.deleteUser(id)
}
```

---

## ⚠️ Manejo de Errores

### Cuando hay un error:
1. Se muestra un **Snackbar rojo** en la parte inferior
2. Muestra el mensaje de error (ej: "Error al cargar usuarios: Network error")
3. Se cierra automáticamente después de unos segundos
4. Puedes intentar de nuevo

### Tipos de errores comunes:
- **"Network error"** - Sin conexión a internet
- **"HTTP error 404"** - Usuario no encontrado
- **"HTTP error 500"** - Error del servidor
- **"Connection timeout"** - Servidor no responde

---

## 🔄 Estados de la UI

### 1. Cargando
- Se muestra un spinner circular
- Texto "Cargando..."
- Los botones están deshabilitados

### 2. Vacío
- Mensaje "No hay usuarios"
- Botón para crear disponible

### 3. Con datos
- Lista de usuarios visible
- Acciones (editar/eliminar) disponibles

### 4. Error
- Snackbar con mensaje
- Lista mantiene último estado válido

---

## 💾 Datos Persistentes

⚠️ **Importante**: Los datos se guardan en la API de prueba (reqres.in)
- Los datos pueden resetearse ocasionalmente
- No hay persistencia local
- Cada recarga obtiene datos frescos de la API

---

## 🎨 Personalización

### Cambiar colores:
Edita `Color.kt`:
```kotlin
val Primary = Color(0xFF...)  // Color principal
val Error = Color(0xFF...)   // Color de error
```

### Cambiar tamaños:
Ajusta en los archivos de UI:
```kotlin
.size(48.dp)              // Avatar pequeño
.size(80.dp)              // Avatar grande
fontSize = 16.sp          // Tamaños de texto
padding(8.dp)             // Espaciados
```

### Cambiar URLs de API:
`Constants.kt`:
```kotlin
const val BASE_URL = "https://tu-api.com/api/"
```

---

## 🧪 Probando Funcionalidades

### Prueba 1: Listar usuarios
1. Abre la app
2. Verifica que aparezcan usuarios en la lista
3. ✅ Debe funcionar al iniciar

### Prueba 2: Ver detalles
1. Haz click en un usuario
2. Se debe abrir un diálogo
3. Verifica que muestre todos los datos
4. ✅ Cierra con botón "Cerrar"

### Prueba 3: Crear usuario
1. Click "+ Crear nuevo usuario"
2. Verifica que se añada a la lista
3. Recarga la app (debería persistir)
4. ✅ El usuario debe estar en la API

### Prueba 4: Editar usuario
1. Abre detalle de usuario
2. Click "Editar"
3. Cambia nombre/email
4. Click "Guardar"
5. Verifica cambios en la lista
6. ✅ Los cambios deben persistir

### Prueba 5: Eliminar usuario
1. Click en icono 🗑️
2. Usuario se debe eliminar de la lista
3. Recarga app (no debe aparecer)
4. ✅ Eliminado correctamente

---

## 📱 Compatibilidad

- **Versión mínima:** Android 7.0 (API 24)
- **Versión objetivo:** Android 15 (API 36)
- **Kotlin:** 1.9+
- **Compose:** Material 3

---

## 🔗 Referencias

- API: https://reqres.in/
- Retrofit: https://square.github.io/retrofit/
- Jetpack Compose: https://developer.android.com/jetpack/compose
- Kotlin Coroutines: https://kotlinlang.org/docs/coroutines-overview.html

---

## ❓ Preguntas Frecuentes

**P: ¿Por qué a veces desaparecen mis usuarios?**
R: reqres.in es una API de prueba que resetea datos periódicamente.

**P: ¿Cómo cambio los datos por defecto al crear usuarios?**
R: Edita `UserViewModel.kt` en el método `createUser()`.

**P: ¿Por qué no veo avatares de las imágenes?**
R: Se usa un placeholder con la inicial por optimización. Puedes agregar Coil para imágenes reales.

**P: ¿Cómo agrego más campos al usuario?**
R: Edita `User.kt`, `UserService.kt` y los diálogos de UI.

**P: ¿Cómo persisto datos localmente?**
R: Integra Room Database o DataStore en el ViewModel.

---

## 🐛 Debugging

### Ver logs en Android Studio:
1. Abre "Logcat" en la parte inferior
2. Busca por "UserViewModel" o "API_ERROR"
3. Verás información sobre cada operación

### Logs importantes:
```
D/UserViewModel: Usuarios cargados: 6
D/UserViewModel: Usuario creado: User(...)
E/UserViewModel: Error loading users: Network error
```

---

**¡Disfruta usando la app! 🚀**

