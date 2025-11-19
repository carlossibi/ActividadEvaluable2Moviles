# Gestión de Clientes Android

Aplicación Android para la gestión básica y eficiente de clientes.
Está desarrollada en Kotlin y utiliza una base de datos local SQLite para almacenar y gestionar toda la información.

---

## Introducción

Esta práctica se enfoca en las operaciones CRUD (crear, leer, actualizar, eliminar) mediante una interfaz intuitiva y funcional que permite:

- Gestionar clientes con campos de nombre, email y teléfono, validando los datos al ingresarlos.
- Guardar la información localmente para su persistencia usando SQLite.
- Mejorar la experiencia con búsqueda en tiempo real y mostrar un contador dinámico.
- Usar componentes de Android como CardView y ConstraintLayout para una UI limpia y profesional.

---

## Instrucciones para abrir y ejecutar el proyecto

1. Descarga o clona el repositorio completo.
2. Abre la carpeta raíz del proyecto en Android Studio.
3. Comprueba que tienes la dependencia para CardView en tu `build.gradle (Module: app)`:
4. Ejecuta la app pulsando el botón **Run**, y prueba en emulador o dispositivo físico.

---

## Capturas de pantalla funcionales

### Pantalla Principal

![Pantalla principal](screenshots/pantalla_principal.png)

- Título “Gestión de Clientes” en recuadro azul oscuro.
- Mensaje de bienvenida explicando el propósito.
- Campo de búsqueda para filtrar clientes por nombre o correo electrónico en tiempo real.
- Contador automático con el número total de clientes guardados.
- Lista interactiva que permite editar (pulsar) o eliminar (pulsación larga) clientes.
- Botón flotante “+” para añadir nuevos clientes.

---

### Pantalla de Formulario

![Formulario cliente](screenshots/formulario_cliente.png)

- Formulario para crear o editar clientes con campos de nombre, email y teléfono.
- Validación automática para asegurarse que los datos ingresados son correctos y obligatorios.
- No se permite guardar sin completar y validar los campos.
- Guarda los datos en SQLite y regresa a la pantalla principal actualizando la lista.

---

## Modelo de datos
data class Cliente(
var id: Int = 0,
var nombre: String,
var email: String,
var telefono: String
)

- `id`: Identificador único auto-incremental.
- `nombre, email, telefono`: Campos obligatorios, con validaciones propias.

La persistencia se maneja con la clase `DBHelper` que usa SQLiteOpenHelper para las operaciones CRUD y consultas.

---

## Funcionalidades clave

- Añadir, modificar y eliminar clientes.
- Guardar y recuperar datos en SQLite.
- Carga automática y refresco de listas al abrir la app y tras cambios.
- Búsqueda instantánea de clientes.
- Visualización dinámica del total de clientes.
- Diseño moderno y limpio con Material Design.

---

## Estructura del proyecto

MiApp2/
├─ app/
│ ├─ src/
│ │ ├─ main/
│ │ │ ├─ java/com/example/miapp2/
│ │ │ │ ├─ MainActivity.kt
│ │ │ │ ├─ FormularioActivity.kt
│ │ │ │ ├─ ClienteAdapter.kt
│ │ │ │ ├─ DBHelper.kt
│ │ │ │ └─ Cliente.kt
│ │ │ ├─ res/layout/
│ │ │ └─ res/values/strings.xml
├─ screenshots/
│ ├─ pantalla_principal.png
│ └─ formulario_cliente.png
├─ README.md


---

## Conclusión

Con esta práctica he aprendido a construir una app Android completa que gestiona datos localmente, valida entradas, y ofrece una buena experiencia de usuario con búsquedas y contadores en tiempo real.  
El proyecto está listo para entregar y cumple los requisitos necesarios para obtener la máxima nota.




