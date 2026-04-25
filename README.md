# 🏰 Clan Searcher - Clash of Clans Explorer

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![Material3](https://img.shields.io/badge/Material_3-7D5260?style=for-the-badge&logo=materialdesign&logoColor=white)

**Clan Searcher** es una aplicación de Android que permite a los usuarios explorar el universo del videojuego **Clash of Clans**  utilizando la [API oficial](https://developer.clashofclans.com/#/) de Supercell. Los usuarios pueden buscar clanes, analizar sus estadísticas detalladas, consultar la lista de miembros y gestionar sus favoritos de forma local.

---

## 🚀 Características Principales

- 🔍 **Búsqueda** Filtrado de clanes en tiempo real a través de la API oficial.
- 📊 **Estadísticas Detalladas:** Visualización de puntos, nivel, victorias en guerra y región.
- 👥 **Gestión de Miembros:** Listado completo de integrantes con sus roles y estadísticas de donación.
- ⭐️ **Sistema de Favoritos:** Persistencia local para guardar tus clanes preferidos.
- 🌓 **Modo Oscuro Adaptativo:** Interfaz totalmente compatible con temas claros y oscuros.
- 📱 **UI Flexible:** Alternancia dinámica entre vista de Lista y Cuadrícula (Grid).

---

## 🛠️ Stack Tecnológico

| Capa | Tecnologías |
| :--- | :--- |
| **Interfaz de Usuario** | Jetpack Compose, Material Design 3, Coil (Imágenes) |
| **Arquitectura** | MVVM + Clean Architecture (Data, Domain, UI) |
| **Networking** | Retrofit, OkHttp, Gson |
| **Persistencia** | Room Database |
| **Asincronía** | Kotlin Coroutines, StateFlow |

---

## 🏗️ Arquitectura del Proyecto

El proyecto sigue los principios de **Clean Architecture**, dividiendo la lógica en capas para asegurar un código mantenible y escalable:

- **`data/`**: Gestión de fuentes de datos (API remota y DB local), DTOs y Mappers.
- **`domain/`**: Modelos de negocio puros, independientes de librerías externas.
- **`ui/`**: Componentes de Compose, ViewModels y gestión de estados de la interfaz.

---

## 💡 ¿Qué he aprendido?

Este proyecto ha sido una pieza clave en mi formación como desarrollador:

1. **Clean Architecture & Mappers:** La importancia de separar los modelos de la API (**DTOs**) de los modelos de la interfaz (**Domain**). Esto protege la app de cambios inesperados en la API externa.
2. **Gestión de Estados:** Implementación de `StateFlow` para manejar estados complejos (carga, datos nulos) de forma reactiva y eficiente.
3. **UI Resiliente:** Cómo manejar datos opcionales de una API para evitar cierres inesperados mediante mappers seguros y valores por defecto.
4. **Tematización Avanzada:** Implementación de colores semánticos para soportar Modo Oscuro de forma automática y consistente.
5. **Persistencia local:** Integración de Room Database para gestionar una lista de favoritos persistente.

---

Desarrollado por [Èric Moral](https://github.com/ElCire12).
