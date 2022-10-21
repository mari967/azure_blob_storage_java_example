# Ejemplo de uso de la SDK para Java de Azure para gestionar Blob Storage

En este ejemplo veremos cómo usar la SDK de Azure de Java para gestionar el almacenamiento en Blob Storage. 
Las operaciones que haremos serán subida, descarga, eliminación y creación de archivos en un contenedor en particular. Además gestionaremos los contenedores del Blob Storage 
eliminando y creádolos.

No es necesario contar con una cuenta de almacenamiento en Azure (Azure Storage Account) ya que usaremos el emulador oficial que provee microsoft llamado Azurite. 
Levantaremos una instancia del emulador usando Docker.

## Conceptos sobre Azure Storage
 !!! Agregar info teórica

## Pre requisitos
* Docker 
* Java Development Kit (JDK) con versión 8 o superior
* Microsoft Azure Storage Explorer ([Link de descarga](https://azure.microsoft.com/en-us/products/storage/storage-explorer/ "Azure Storage Explorer"))
* Postman o similar


## Paso a paso
### 1. Instalar Azurite y ejecutar el emulador
En una terminal con Docker descargamos la imagen de Azurite desde DockerHub\
```
docker pull mcr.microsoft.com/azure-storage/azurite
```

Ejecutamos la imagen de Azurite\
```
docker run -p 10000:10000 -p 10001:10001 -p 10002:10002 \
    -v c:/azurite:/data mcr.microsoft.com/azure-storage/azurite
```
Los logs de las operaciones sobre el Storage Account que realicemos se verán la terminal.

Nota: 
La instancia de Azurite que estamos levantando emula al servicio Azure Storage Account con el que se pueden gestionar los servicios de almacenamiento 
que provee la plataforma cloud, como Blob Storage, Queue Storage, Table Storage. Los puertos asociados a cada servicio son 1000, 1001, y 1002 respectivamente.

Para mayor información sobre cómo usar Azurite con Docker acceder este [link](https://learn.microsoft.com/en-us/azure/storage/common/storage-use-azurite?tabs=docker-hub "Azurite con docker").

### 2. Acceder al emulador desde Azure Storage Explorer
Azure Storage Explorer permite gestionar fácilmente los recursos de almacenamiento que tiene el Storage Account.
Podremos ver los distintos tipos de almacenamiento (como blob, colas y tablas), ver, dercargar, subir archivos, crear contenedores y más.
Permite que veamos nuestra instancia emulada de Azure Storage.

Abrimos el programa. En la barra lateral del explorador se listan las cuentas de almacenamiento. El emulador debería mostrarse debajo de Emulador y adjunto-Emulador: puertos predeterminados. 
Debajo de este nivel se debería encontra "Blob Containers". Creemos un contenedor para comenzar las pruebas llamado "contenedor1" haciendo clic derecho sobre esta opción y seleccionando "Crear Contenedor de blobs".

!!!Insertar imagen de la barra lateral

### 3. Conectarse a Azure Storage con la SDK de Java
Ejecutar el código que se encuentra en el repo.

El código que tenemos en el repo es una aplicación para ejecutar desde una terminal en la que podremos realizar acciones en nuestro Azure Storage como subir archivos, descargarlos, eliminarlos, crear contenedores y eliminarlos.
Ya se encuentra configurado con las credenciales locales, por lo que se podrá usar el programa sin rpoblemas.

!!!! Describir cómo se usa el programa


### Opcional. Gestionar el Blob Storage con Postman
!!! Compeltar

## Azure SDK para java: Descripción del código


