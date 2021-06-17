# Taller de prototipos web y móvil

Hola! Te dejo unas líneas de código que necesitarás para completar la conexión con la API. Estas líneas de código por supuesto que fueron usadas en los videos del taller.

## Permisos de Android Manifest

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

## Dependencia OkHttp3 Gradle

```gradle
implementation('com.squareup.okhttp3:okhttp') { version {strictly '3.12.12'} }
implementation 'com.squareup.okhttp3:logging-interceptor:3.12.12'
```