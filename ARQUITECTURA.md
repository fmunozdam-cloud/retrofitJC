# 🏗️ ARQUITECTURA DEL PROYECTO

## Diagrama de Estructura

```
RetrofitJC/
│
├── app/
│   ├── build.gradle.kts ...................... Dependencias (Retrofit, Compose, ViewModel)
│   ├── src/main/
│   │   ├── AndroidManifest.xml ............... Permisos (INTERNET)
│   │   │
│   │   └── java/campalans/m8/retrofitjc/
│   │       │
│   │       ├── 📍 MainActivity.kt ............ Actividad principal (UI)
│   │       ├── 🧠 UserViewModel.kt .......... Lógica de negocio (MVVM)
│   │       ├── ⚙️ Constants.kt .............. Configuración
│   │       │
│   │       ├── 📦 model/
│   │       │   ├── User.kt ................. Data class del usuario
│   │       │   └── UserResponse.kt ......... Envoltorio de respuesta API
│   │       │
│   │       ├── 🌐 network/
│   │       │   └── UserService.kt ......... Interface Retrofit (5 métodos CRUD)
│   │       │
│   │       └── 🎨 ui/theme/
│   │           ├── MainView.kt ............. Pantalla principal
│   │           ├── UserItemView.kt ........ Elemento de lista
│   │           ├── UserDetailDialog.kt ... Diálogo ver/editar (NUEVO)
│   │           ├── Theme.kt
│   │           ├── Color.kt
│   │           └── Type.kt
│   │
│   └── build/
│       └── (Compilados por Gradle)
│
├── MEJORAS_REALIZADAS.md ................... Este documento
├── API_REFERENCE.md ....................... Referencia de APIs
├── GUIA_USO.md ............................ Manual de usuario
└── ARQUITECTURA.md ........................ Documento actual
```

---

## 🔄 Flujo de Datos

```
┌─────────────────────────────────────────────────────┐
│              MainActivity (UI Layer)                │
│                                                     │
│  - Observa UserViewModel                            │
│  - Dispara acciones de usuario                      │
│  - Muestra Loading, Error, Data                     │
└──────────────────┬──────────────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────────────┐
│         UserViewModel (Business Logic)              │
│                                                     │
│  - StateFlow<List<User>> (users)                    │
│  - StateFlow<User?> (selectedUser)                  │
│  - StateFlow<Boolean> (isLoading)                   │
│  - StateFlow<String?> (errorMessage)                │
│                                                     │
│  - loadUsers()                                      │
│  - getUser(id)                                      │
│  - createUser(...)                                  │
│  - updateUser(...)          ← NUEVO                 │
│  - deleteUser(id)                                   │
└──────────────────┬──────────────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────────────┐
│       UserService (API Layer - Retrofit)            │
│                                                     │
│  @GET    /users           → getUsers()              │
│  @GET    /users/{id}      → getUser(id)             │
│  @POST   /users           → createUser(user)        │
│  @PUT    /users/{id}      → updateUser(id, user) ✨ │
│  @DELETE /users/{id}      → deleteUser(id)          │
└──────────────────┬──────────────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────────────┐
│        Retrofit + GsonConverterFactory              │
│        Base URL: https://reqres.in/api/             │
└──────────────────┬──────────────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────────────┐
│              API de Prueba (reqres.in)              │
└─────────────────────────────────────────────────────┘
```

---

## 📊 Modelo de Datos

```
User (Data Class)
├── id: Int? ......................... ID del usuario (null si es nuevo)
├── first_name: String .............. Nombre
├── last_name: String ............... Apellido
├── email: String ................... Correo electrónico
└── avatar: String? ................. URL del avatar (opcional)

UserResponse (Envoltorio)
└── data: List<User> ............... Lista de usuarios de la API
```

---

## 🎯 Operaciones CRUD

### 1. CREATE (Crear Usuario)
```
Usuario entra: first_name, last_name, email
     ↓
UserViewModel.createUser()
     ↓
UserService.createUser(user) → POST /users
     ↓
API responde con id y createdAt
     ↓
ViewModel recarga la lista
     ↓
UI se actualiza automáticamente
```

### 2. READ (Leer Usuarios)
```
getUsers() → GET /users
    ↓
API devuelve lista de 6 usuarios
    ↓
ViewModel.users: StateFlow<List<User>>
    ↓
UI observa y renderiza lista
```

### 3. UPDATE (Actualizar Usuario) ⭐
```
Usuario abre diálogo y edita
     ↓
UserViewModel.updateUser(id, firstName, lastName, email)
     ↓
UserService.updateUser(id, user) → PUT /users/{id}
     ↓
API actualiza el usuario
     ↓
ViewModel recarga la lista
     ↓
UI cierra diálogo y muestra cambios
```

### 4. DELETE (Eliminar Usuario)
```
Usuario hace click en 🗑️
     ↓
UserViewModel.deleteUser(id)
     ↓
UserService.deleteUser(id) → DELETE /users/{id}
     ↓
API elimina el usuario
     ↓
ViewModel recarga la lista
     ↓
Usuario desaparece de la lista
```

---

## 🔗 Patrón MVVM

```
┌──────────────────────────────────────────────────┐
│             VIEW (Composable UI)                 │
│  - MainView.kt                                   │
│  - UserItemView.kt                               │
│  - UserDetailDialog.kt                           │
│                                                  │
│  Responsabilidades:                              │
│  ✓ Renderizar UI                                 │
│  ✓ Capturar eventos del usuario                  │
│  ✓ Mostrar datos del ViewModel                   │
└────────────────────┬─────────────────────────────┘
                     │
                     │ collectAsState()
                     │
┌────────────────────▼─────────────────────────────┐
│          VIEW MODEL (UserViewModel)              │
│                                                  │
│  Responsabilidades:                              │
│  ✓ Gestionar estado (StateFlow)                  │
│  ✓ Ejecutar lógica de negocio                    │
│  ✓ Llamar servicios                              │
│  ✓ Manejar corrutinas                            │
│  ✓ Procesar errores                              │
└────────────────────┬─────────────────────────────┘
                     │
                     │ suspend functions
                     │
┌────────────────────▼─────────────────────────────┐
│            MODEL (UserService)                   │
│                                                  │
│  Responsabilidades:                              │
│  ✓ Comunicar con API                             │
│  ✓ Transformar datos (Gson)                      │
│  ✓ Mapear respuestas HTTP                        │
└──────────────────────────────────────────────────┘
```

---

## 📦 Dependencias Principales

```gradle
// Retrofit 3.0 - Cliente HTTP
implementation("com.squareup.retrofit2:retrofit:3.0.0")
implementation("com.squareup.retrofit2:converter-gson:3.0.0")

// Compose - UI Framework
implementation(libs.androidx.compose.ui)
implementation(libs.androidx.compose.material3)

// ViewModel - Arquitectura MVVM
implementation(libs.androidx.lifecycle.viewmodel.compose)

// Corrutinas - Async
implementation(libs.androidx.lifecycle.runtime.ktx)

// Compatibilidad
implementation(libs.androidx.core.ktx)
implementation(libs.androidx.activity.compose)
```

---

## 🚀 Flujo de Inicio de la App

```
1. MainActivity.onCreate()
   ↓
2. setContent { RetrofitJCTheme { ... } }
   ↓
3. viewModel = UserViewModel()
   ↓
4. UserViewModel.init() → loadUsers()
   ↓
5. UserService.getUsers() → API
   ↓
6. _users.value = response.data
   ↓
7. MainView observa users
   ↓
8. Lista se renderiza automáticamente
```

---

## 🔐 Capas y Responsabilidades

```
┌─────────────────────────────────────────┐
│     PRESENTATION LAYER (UI)             │
│  MainActivity, Composables              │
│  - Solo renderizar                      │
│  - Capturar interacciones                │
│  - NO lógica de negocio                  │
└─────────────────────────────────────────┘
              ↕ (colecta estado)
┌─────────────────────────────────────────┐
│    BUSINESS LOGIC LAYER (ViewModel)     │
│  UserViewModel                           │
│  - Coordinar operaciones                 │
│  - Mantener estado                       │
│  - Manejar errores                       │
└─────────────────────────────────────────┘
              ↕ (servicios)
┌─────────────────────────────────────────┐
│     DATA LAYER (API + Repository)       │
│  UserService, Retrofit                   │
│  - Comunicar con API                     │
│  - Transformar datos                     │
│  - Manejar conexiones                    │
└─────────────────────────────────────────┘
              ↕ (HTTP)
┌─────────────────────────────────────────┐
│        EXTERNAL (Internet)              │
│  https://reqres.in/api/                 │
└─────────────────────────────────────────┘
```

---

## 🧪 Testabilidad

```
✅ ViewModel es testeable (sin Context)
✅ UserService es una interfaz (mockeable)
✅ Lógica separada de UI
✅ Fácil inyectar dependencias
✅ Corrutinas con viewModelScope (scope limpio)

Ejemplo de test unitario:
@Test
fun testCreateUser() = runTest {
    val viewModel = UserViewModel()
    viewModel.createUser("Juan", "Pérez", "juan@example.com")
    
    val users = viewModel.users.value
    assertTrue(users.isNotEmpty())
}
```

---

## 🔄 Ciclo de Vida del ViewModel

```
┌──────────────────┐
│    CREADO        │
│  __init__()      │ → loadUsers()
└────────┬─────────┘
         │
         ▼
┌──────────────────┐
│   EN MEMORIA     │
│  Procesando      │ ← Acciones del usuario
│  Actualizando    │
│  Estado (Flow)   │
└────────┬─────────┘
         │ (Activity destruida)
         ▼
┌──────────────────┐
│   LIMPIADO       │
│  onCleared()     │ → Corrutinas canceladas
│  Recursos libres │
└──────────────────┘
```

---

## 📈 Escalabilidad Futura

```
Fácil agregar:
├── 🗂️ Repository Pattern
├── 🗄️ Room Database (cache local)
├── 🔐 Autenticación (Token JWT)
├── 📡 Websockets (real-time updates)
├── 🖼️ Image Loading (Coil/Glide)
├── 🔍 Búsqueda y filtrado
├── 📄 Paginación
├── 📱 Responsive layouts
└── 🎥 Animaciones

Difícil cambiar:
├── ❌ Estructura base (bien diseñada)
├── ❌ Interfaz UserService (estable)
├── ❌ Model User (contrato con API)
└── ❌ Patrón MVVM
```

---

## 📊 Comparativa: Antes vs Después

| Aspecto | Antes | Después |
|---------|-------|---------|
| **Patrón** | Activity con lógica | MVVM con ViewModel |
| **UPDATE** | ❌ No existía | ✅ Implementado |
| **Errores** | Log simple | Snackbar + Log |
| **UI** | Básica | Material Design 3 |
| **Estado** | Local | StateFlow (reactive) |
| **Código** | 103 líneas | Modular y escalable |
| **Testeable** | Difícil | Muy fácil |
| **Mantenible** | Acoplado | Desacoplado |

---

**Arquitectura moderna y profesional 🏆**

