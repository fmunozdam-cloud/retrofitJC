# ⚡ QUICK START - COMIENZA EN 5 MINUTOS

## 📥 Paso 1: Clonar/Abrir Proyecto (1 min)

```
1. Abre Android Studio
2. File → Open
3. Navega a: retrofitJC/
4. Click Open
5. Espera a que se indexe (2-3 segundos)
```

## 🔄 Paso 2: Sincronizar Gradle (2 min)

```
1. Android Studio detectará cambios
2. Verás "Sync Now" arriba a la derecha
3. Click en "Sync Now"
4. Espera a que descargue dependencias
5. ✅ Debe decir "Gradle sync finished"
```

## 🚀 Paso 3: Ejecutar la App (1 min)

### Opción A: En Emulador
```
1. AVD Manager (Tools → Device Manager)
2. Crea un emulador (si no tienes)
3. Inicia el emulador
4. Click ▶️ Run en Android Studio
5. Selecciona el emulador
6. Espera a que compile (puede tardar 30 segundos)
7. ¡App lista! 🎉
```

### Opción B: En Dispositivo Real
```
1. Conecta tu teléfono USB
2. Activa "Depuración USB" en settings del teléfono
3. Click ▶️ Run en Android Studio
4. Selecciona tu dispositivo
5. Click OK
6. ¡La app se instala en tu teléfono! 📱
```

## 🎮 Paso 4: Prueba las Funciones (1 min)

### ✅ Test 1: Listar usuarios
```
• App se abre
• Ves "Gestión de Usuarios"
• Lista con 6 usuarios
• ✓ ÉXITO
```

### ✅ Test 2: Crear usuario
```
• Click "+ Crear nuevo usuario"
• Se añade un usuario nuevo
• Muestra spinner mientras carga
• ✓ ÉXITO
```

### ✅ Test 3: Ver detalles
```
• Click en cualquier usuario
• Se abre un dialog
• Muestra nombre, apellido, email, ID
• ✓ ÉXITO
```

### ✅ Test 4: Editar (NUEVO) ⭐
```
• En el dialog: Click [Editar]
• Los campos se activan
• Cambia algo (ej: nombre)
• Click [Guardar]
• Dialog se cierra
• Usuario aparece actualizado en lista
• ✓ ÉXITO
```

### ✅ Test 5: Eliminar
```
• Click en 🗑️ en cualquier usuario
• Usuario desaparece de la lista
• ✓ ÉXITO
```

---

## 📚 Archivos Clave

```
Modificar comportamiento:
├── UserViewModel.kt ................. Lógica principal
├── Constants.kt ..................... Cambiar BASE_URL
│
Ver UI:
├── MainView.kt ...................... Pantalla principal
├── UserItemView.kt .................. Tarjetas de usuario
├── UserDetailDialog.kt .............. Dialog de detalles (NUEVO)
│
Entender API:
├── network/UserService.kt ........... Interface de API
├── model/User.kt .................... Modelo de datos
└── model/UserResponse.kt ............ Respuesta de API
```

---

## 🔧 Cambios Comunes

### Cambiar URL de API
```kotlin
// Constants.kt
const val BASE_URL = "https://tu-api.com/api/"
```

### Cambiar datos por defecto al crear
```kotlin
// UserViewModel.kt, línea ~75
fun createUser(firstName: String, lastName: String, email: String) {
    val newUser = User(
        first_name = firstName,        // ← Cambiar aquí
        last_name = lastName,
        email = email
    )
    // ...
}
```

### Cambiar colores
```kotlin
// Color.kt
val Primary = Color(0xFF...)       // Color principal azul
val Error = Color(0xFFB3261E)      // Color de error
```

### Agregar más campos a Usuario
```kotlin
// User.kt
data class User(
    val id: Int? = null,
    val first_name: String,
    val last_name: String,
    val email: String,
    val avatar: String? = null,
    val phone: String? = null    // ← Agregado
)

// UserDetailDialog.kt - Agregar campo de input:
OutlinedTextField(
    value = phone.value,
    onValueChange = { phone.value = it },
    label = { Text("Teléfono") },
    // ...
)
```

---

## 🐛 Troubleshooting

### ❌ "Error: Unresolved reference"
```
Solución:
1. File → Invalidate Caches → Invalidate and Restart
2. Espera a que reinicie
3. Gradle → Sync
```

### ❌ "API no responde"
```
Solución:
1. Verifica que tengas INTERNET
2. Mira Logcat: View → Tool Windows → Logcat
3. Busca error en los logs
4. Intenta de nuevo
```

### ❌ "El dialog no se abre"
```
Solución:
1. Verifica que hayas hecho click en un usuario
2. Mira Logcat por errores
3. Recarga la app (Stop + Run)
```

### ❌ "Spinner infinito"
```
Solución:
1. La API puede estar lenta
2. Espera 10 segundos
3. Si sigue, desconecta/conecta Internet
4. Cierra y reabre la app
```

---

## 📊 Estructura Rápida

```
┌─────────────────────────────────────────┐
│         MainActivity.kt                 │
│  (Inicializa UI y ViewModel)           │
└────────────────────┬────────────────────┘
                     │
                     ↓
┌─────────────────────────────────────────┐
│  MainView.kt + UserItemView.kt          │
│         (Interfaz gráfica)              │
└────────────────────┬────────────────────┘
                     │
                     ↓
┌─────────────────────────────────────────┐
│      UserViewModel.kt (MVVM)            │
│   (Lógica y gestión de estado)          │
└────────────────────┬────────────────────┘
                     │
                     ↓
┌─────────────────────────────────────────┐
│    UserService.kt + Retrofit            │
│      (Comunicación con API)             │
└────────────────────┬────────────────────┘
                     │
                     ↓
┌─────────────────────────────────────────┐
│  https://reqres.in/api/users            │
│    (Servidor de prueba)                 │
└─────────────────────────────────────────┘
```

---

## ⚙️ Configuración Inicial

✅ **Ya configurado:**
- Retrofit 3.0
- Gson converter
- ViewModel & LiveData
- Compose & Material3
- Permisos INTERNET
- Manejo de corrutinas

❌ **No configurado (opcional):**
- Base de datos local (Room)
- Autenticación (JWT)
- Caché offline
- Imágenes (Coil)

---

## 🎓 Comandos Útiles

### Sincronizar Gradle
```
Ctrl + Shift + O (macOS: Cmd + Shift + O)
```

### Ejecutar
```
Shift + F10 (macOS: Cmd + R)
```

### Ver Logcat
```
Alt + 6 (macOS: Cmd + 6)
```

### Limpiar Proyecto
```
Build → Clean Project
```

### Reconstruir
```
Build → Rebuild Project
```

---

## 📈 Estadísticas Después del Setup

| Métrica | Valor |
|---------|-------|
| Tiempo compilación | ~30-60s |
| Tamaño APK | ~5-10 MB |
| Usuarios cargados | 6 por página |
| Funciones CRUD | 5 (SELECT, SELECT 1, CREATE, UPDATE, DELETE) |
| APIs disponibles | Todas funcionan |

---

## 🎯 Checklist Rápido

- [ ] Proyecto abierto sin errores
- [ ] Gradle sincronizado
- [ ] Emulador/dispositivo conectado
- [ ] App compila correctamente
- [ ] Usuarios se cargan al abrir
- [ ] Se pueden crear usuarios
- [ ] Se pueden editar usuarios (UPDATE) ⭐
- [ ] Se pueden eliminar usuarios
- [ ] No hay errores en Logcat
- [ ] UI se ve correctamente

---

## 🎉 ¡Listo!

Si ves usuarios en la lista y todos los botones funcionan:

✅ **Felicidades, todo está perfecto!** 🚀

Ahora puedes:
- Personalizarla según tus necesidades
- Agregar más funcionalidades
- Deployarla
- Compartirla

---

## 📞 Ayuda Rápida

| Problema | Solución Rápida |
|----------|-----------------|
| Gradle no sincroniza | File → Invalidate Caches → Restart |
| Código con errores | Alt + Enter para auto-fix |
| App no inicia | Clean → Build → Run |
| Logs no visibles | Alt + 6 → Logcat |
| Conexión a API | Verifica INTERNET en manifest ✓ |

---

**¡Disfruta programando! 💻✨**

*Última actualización: 9 de Abril de 2026*

