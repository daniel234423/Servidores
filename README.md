# Servidores: App Monitor (Celular B, quien recibe)

> **Esta es la aplicación de quien recibe.** Corresponde al **Celular B (Monitor)** del proyecto de monitoreo de temperatura en salas de servidores. La aplicación de quien envía los datos (Celular A, Gateway) está en el repositorio de mi dúo: [LINK AL REPO DEL DÚO].

Proyecto de la asignatura **TI3042 - Aplicaciones Móviles para IoT** (Unidad 2: Aplicaciones Móviles Android).

## Problemática

En las salas de servidores los equipos funcionan las 24 horas y la temperatura puede subir más de lo recomendado. Si nadie lo detecta a tiempo, existe riesgo de fallas o daño de hardware. Este sistema mide la temperatura, avisa cuando se sobrepasa un límite y permite revisarla desde un celular.

## Arquitectura

```
[Arduino + sensor] --Bluetooth--> [Celular A: Gateway] --WiFi--> [Celular B: Monitor]
```

| Componente | Función | Responsable |
|---|---|---|
| Arduino + sensor de temperatura + módulo Bluetooth | Mide la temperatura y la envía | Dúo |
| Celular A (Gateway) | Recibe del Arduino, valida, evalúa estado, guarda historial y sirve los datos | Dúo |
| **Celular B (Monitor)** | **Se vincula con el Gateway, muestra las salas y recibe alertas** | **Este repositorio** |

## Funcionalidades de esta app (Celular B)

- **Vinculación** con el Gateway mediante un PIN.
- **Lista de salas** con nombre, temperatura actual y estado.
- **Detalle de sala** (al hacer clic en una sala):
  - Servidor asociado
  - Temperatura exacta
  - Estado (OK, Advertencia o Crítico)
  - Gráfico de la temperatura durante el día
- **Alertas** cuando una sala pasa a Advertencia o Crítico.

## Umbrales de temperatura

| Estado | Rango |
|---|---|
| OK | hasta 27 °C |
| Advertencia | 27 a 32 °C |
| Crítico | más de 32 °C |

## Tecnologías

- Android Studio
- Kotlin
- Comunicación WiFi con el Celular A (Gateway)
- [COMPLETAR: biblioteca del gráfico, por ejemplo MPAndroidChart]

## Seguridad (ISO 27400)

- Vinculación con el Gateway mediante PIN.
- Comunicación cifrada entre celulares.
- Credenciales guardadas de forma segura, sin claves escritas en el código.
- Permisos mínimos y validación de los datos recibidos.

## Cómo probar la app

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/daniel234423/Servidores.git
   ```
2. Abrir el proyecto en **Android Studio** y esperar a que Gradle sincronice.
3. Conectar un dispositivo Android (o usar un emulador) y presionar **Run**.
4. Tener el **Celular A (Gateway)** funcionando en la misma red WiFi.
5. En la app, ingresar el **PIN** que muestra el Gateway para vincularse.

## Requisitos

- Android Studio (versión reciente)
- Dispositivo Android con WiFi
- Celular A (Gateway) en la misma red

## Autor

- **Daniel** ([@daniel234423](https://github.com/daniel234423))
- Asignatura: TI3042 - Aplicaciones Móviles para IoT
