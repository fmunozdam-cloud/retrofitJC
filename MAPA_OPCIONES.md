# 🗺️ MAPA VISUAL DE OPCIONES - APLICACIÓN

## 📱 Pantalla Principal

```
╔════════════════════════════════════════════╗
║        GESTIÓN DE USUARIOS                 ║
║                                            ║
║ ┌──────────────────────────────────────┐  ║
║ │ 🔵 + Crear nuevo usuario             │  ║
║ └──────────────────────────────────────┘  ║
║                                            ║
║ ┌──────────────────────────────────────┐  ║
║ │ ┌────────────────────────────────┐   │  ║
║ │ │ G  George Bluth              │   │  ║
║ │ │    george.bluth@reqres.in    │   │  ║
║ │ │    ID: 1                     │   │  ║
║ │ │  [✏️ Editar]  [🗑️ Eliminar]  │  │  ║
║ │ └────────────────────────────────┘   │  ║
║ │                                        │  ║
║ │ ┌────────────────────────────────┐   │  ║
║ │ │ J  Janet Weaver              │   │  ║
║ │ │    janet.weaver@reqres.in    │   │  ║
║ │ │    ID: 2                     │   │  ║
║ │ │  [✏️ Editar]  [🗑️ Eliminar]  │  │  ║
║ │ └────────────────────────────────┘   │  ║
║ │                                        │  ║
║ │ ┌────────────────────────────────┐   │  ║
║ │ │ E  Emma Wong                 │   │  ║
║ │ │    emma.wong@reqres.in       │   │  ║
║ │ │    ID: 3                     │   │  ║
║ │ │  [✏️ Editar]  [🗑️ Eliminar]  │  │  ║
║ │ └────────────────────────────────┘   │  ║
║ │                                        │  ║
║ │                     ↓ Scroll          │  ║
║ │              (Más usuarios)           │  ║
║ │                                        │  ║
║ └──────────────────────────────────────┘  ║
║                                            ║
╚════════════════════════════════════════════╝
        ↓ Click en usuario o ✏️
        
         (Abre DIALOG)
```

---

## 🪟 Dialog de Ver/Editar Usuario

### Modo LECTURA (Por defecto)

```
╔════════════════════════════════════════╗
║    Detalles del Usuario                ║
├────────────────────────────────────────┤
║           ┌─────────┐                  ║
║           │    G    │  (Avatar)        ║
║           └─────────┘                  ║
║            ID: 1                       ║
├────────────────────────────────────────┤
║                                        ║
║  Nombre:        George        (lectura)║
║  ________________________________________║
║                                        ║
║  Apellido:      Bluth         (lectura)║
║  ________________________________________║
║                                        ║
║  Email:         george.bluth   (lectura)║
║                 @reqres.in              ║
║  ________________________________________║
║                                        ║
├────────────────────────────────────────┤
║                                        ║
║  [Cerrar]                [Editar]      ║
║                                        ║
╚════════════════════════════════════════╝
```

### Modo EDICIÓN (Después de click "Editar")

```
╔════════════════════════════════════════╗
║    Detalles del Usuario                ║
├────────────────────────────────────────┤
║           ┌─────────┐                  ║
║           │    G    │  (Avatar)        ║
║           └─────────┘                  ║
║            ID: 1                       ║
├────────────────────────────────────────┤
║                                        ║
║  Nombre:        [George       ]✏️      ║  ← Editable
║                                        ║
║  Apellido:      [Bluth        ]✏️      ║  ← Editable
║                                        ║
║  Email:         [george.bluth ]✏️      ║  ← Editable
║                 [@reqres.in   ]        ║
║                                        ║
├────────────────────────────────────────┤
║                                        ║
║  [Cerrar]                [Guardar]     ║
║                                        ║
╚════════════════════════════════════════╝
```

---

## 🔄 Flujo de Acciones

```
┌─────────────────────────────────────────────────────────────────┐
│                     INICIO DE SESIÓN                            │
│                                                                 │
│  App se abre → Carga usuarios automáticamente                   │
│                                                                 │
│  ┌────────────────────────────────────────────────────────┐    │
│  │                 PANTALLA PRINCIPAL                    │    │
│  │                                                        │    │
│  │ Muestra lista de usuarios con acciones               │    │
│  │                                                        │    │
│  │ ┌──────────────────────────────────────────────────┐  │    │
│  │ │ OPCIÓN 1: Crear Usuario                          │  │    │
│  │ │ Click [+ Crear nuevo usuario]                    │  │    │
│  │ │                                                   │  │    │
│  │ │ → Envía POST a /users                            │  │    │
│  │ │ → Recibe id y createdAt                          │  │    │
│  │ │ → Recarga lista                                  │  │    │
│  │ │ → Nuevo usuario aparece                          │  │    │
│  │ └──────────────────────────────────────────────────┘  │    │
│  │                                                        │    │
│  │ ┌──────────────────────────────────────────────────┐  │    │
│  │ │ OPCIÓN 2: Ver Detalles                           │  │    │
│  │ │ Click en usuario o [✏️]                           │  │    │
│  │ │                                                   │  │    │
│  │ │ → Obtiene GET /users/{id}                        │  │    │
│  │ │ → Abre DIALOG en modo LECTURA                    │  │    │
│  │ │ → Muestra datos completos                        │  │    │
│  │ │ → Click [Cerrar] → cierra dialog                 │  │    │
│  │ └──────────────────────────────────────────────────┘  │    │
│  │                                                        │    │
│  │ ┌──────────────────────────────────────────────────┐  │    │
│  │ │ OPCIÓN 3: Editar Usuario ⭐                      │  │    │
│  │ │ En DIALOG: Click [Editar]                        │  │    │
│  │ │                                                   │  │    │
│  │ │ → Campos se habilitan                            │  │    │
│  │ │ → Puedes modificar Nombre/Apellido/Email        │  │    │
│  │ │ → Click [Guardar]                                │  │    │
│  │ │ → Envía PUT a /users/{id}                        │  │    │
│  │ │ → Recibe confirmación                            │  │    │
│  │ │ → Recarga lista                                  │  │    │
│  │ │ → Dialog se cierra                               │  │    │
│  │ │ → Usuario actualizado en lista                   │  │    │
│  │ └──────────────────────────────────────────────────┘  │    │
│  │                                                        │    │
│  │ ┌──────────────────────────────────────────────────┐  │    │
│  │ │ OPCIÓN 4: Eliminar Usuario                       │  │    │
│  │ │ Click [🗑️]                                        │  │    │
│  │ │                                                   │  │    │
│  │ │ → Envía DELETE a /users/{id}                     │  │    │
│  │ │ → Recibe 204 No Content                          │  │    │
│  │ │ → Recarga lista                                  │  │    │
│  │ │ → Usuario desaparece                             │  │    │
│  │ └──────────────────────────────────────────────────┘  │    │
│  │                                                        │    │
│  └────────────────────────────────────────────────────────┘    │
│                                                                 │
│  ┌────────────────────────────────────────────────────────┐    │
│  │ OPCIÓN 5: Refrescar Lista (Automático)               │    │
│  │                                                        │    │
│  │ Después de cada acción:                               │    │
│  │ → Spinner aparece                                     │    │
│  │ → GET /users                                          │    │
│  │ → Lista se actualiza                                  │    │
│  │ → Spinner desaparece                                  │    │
│  └────────────────────────────────────────────────────────┘    │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

---

## 🎯 Tabla de Interacciones

| Elemento | Acción | Resultado | Dialog |
|----------|--------|-----------|--------|
| **+ Crear** | Click | Crea usuario con datos por defecto | No |
| **Usuario** | Click | Abre dialog con detalles | Sí (lectura) |
| **✏️ Editar (en list)** | Click | Abre dialog con detalles | Sí (lectura) |
| **✏️ Editar (en dialog)** | Click | Habilita campos en dialog | Sí (edición) |
| **[Guardar]** | Click | Guarda cambios en API | No (cierra) |
| **[Cerrar]** | Click | Cierra dialog | No |
| **🗑️ Eliminar** | Click | Elimina usuario inmediatamente | No |
| **Usuario** | Scroll | Desplaza lista | No |

---

## 💬 Estados Posibles

```
┌─────────────────────────────────────────────────┐
│              ESTADOS DE LA UI                   │
│                                                 │
│  1️⃣ CARGANDO                                    │
│     ○ Spinner visible                           │
│     ○ Texto "Cargando..."                       │
│     ○ Botones deshabilitados                    │
│                                                 │
│  2️⃣ CON DATOS                                   │
│     ○ Lista visible                             │
│     ○ Todos los usuarios mostrados              │
│     ○ Botones habilitados                       │
│                                                 │
│  3️⃣ VACÍO                                       │
│     ○ Mensaje "No hay usuarios"                 │
│     ○ Botón "+ Crear" visible                   │
│     ○ Sin error                                 │
│                                                 │
│  4️⃣ ERROR                                       │
│     ○ Snackbar rojo abajo                       │
│     ○ Mensaje de error                          │
│     ○ Se cierra en 3 segundos                   │
│                                                 │
│  5️⃣ DIALOG LECTURA                              │
│     ○ Campos de solo lectura                    │
│     ○ Botón [Editar]                            │
│     ○ Botón [Cerrar]                            │
│                                                 │
│  6️⃣ DIALOG EDICIÓN                              │
│     ○ Campos editables                          │
│     ○ Botón [Guardar]                           │
│     ○ Botón [Cerrar]                            │
│                                                 │
└─────────────────────────────────────────────────┘
```

---

## 🔐 Validaciones Implícitas

```
❌ Validaciones que se hacen automáticamente:

1. Campos no nulos ✓
   - No puedes enviar null a la API

2. Email válido (reqres.in lo valida) ✓
   - La API rechazará emails inválidos

3. Nombre/Apellido no vacíos ✓
   - El ViewModel requiere strings

4. ID debe existir para UPDATE/DELETE ✓
   - Solo usuarios con ID se pueden editar

5. Usuario debe estar en lista ✓
   - No se puede editar un usuario eliminado

⚠️ Lo que NO valida la app:
   - Unicidad de emails (reqres.in no lo hace)
   - Formato específico de email (basic check)
   - Caracteres especiales en nombres
```

---

## 📡 Secuencia de Peticiones HTTP

### Crear Usuario (POST)
```
Cliente                              Servidor
   │                                    │
   ├─── POST /users ──────────────────→ │
   │     {                              │
   │       first_name: "Juan",          │
   │       last_name: "Pérez",          │
   │       email: "juan@email.com"      │
   │     }                              │
   │                                    │
   │ ← 201 Created ─────────────────────┤
   │     {                              │
   │       id: 901,                     │
   │       first_name: "Juan",          │
   │       last_name: "Pérez",          │
   │       email: "juan@email.com",     │
   │       createdAt: "2024-04-09..."   │
   │     }                              │
   │                                    │
   ├─── GET /users ───────────────────→ │
   │                                    │
   │ ← 200 OK ──────────────────────────┤
   │     {                              │
   │       data: [... lista nuevo ...]  │
   │     }                              │
   │                                    │
```

### Actualizar Usuario (PUT) ⭐
```
Cliente                              Servidor
   │                                    │
   ├─── PUT /users/1 ────────────────→ │
   │     {                              │
   │       id: 1,                       │
   │       first_name: "Juan",          │
   │       last_name: "García",         │
   │       email: "juan@nuevo.com"      │
   │     }                              │
   │                                    │
   │ ← 200 OK ──────────────────────────┤
   │     {                              │
   │       id: 1,                       │
   │       first_name: "Juan",          │
   │       last_name: "García",         │
   │       email: "juan@nuevo.com",     │
   │       updatedAt: "2024-04-09..."   │
   │     }                              │
   │                                    │
   ├─── GET /users ───────────────────→ │
   │                                    │
   │ ← 200 OK ──────────────────────────┤
   │     {                              │
   │       data: [... lista actualizada...]
   │     }                              │
   │                                    │
```

### Eliminar Usuario (DELETE)
```
Cliente                              Servidor
   │                                    │
   ├─── DELETE /users/1 ──────────────→ │
   │                                    │
   │ ← 204 No Content ──────────────────┤
   │     (respuesta vacía)              │
   │                                    │
   ├─── GET /users ───────────────────→ │
   │                                    │
   │ ← 200 OK ──────────────────────────┤
   │     {                              │
   │       data: [... sin usuario 1 ...]
   │     }                              │
   │                                    │
```

---

## 🎮 Keyboard Shortcuts

```
⌨️ Accesos de teclado en dialogs:

Enter / Return ........... Guardar cambios (cuando estás en modo edit)
Escape ................... Cerrar dialog
Tab ...................... Cambiar entre campos
Shift+Tab ................ Campo anterior
```

---

**Todas las opciones están lisas para usar 🎯**


