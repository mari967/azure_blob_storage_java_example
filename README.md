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

## Azure SDK para java. Descripción del proyecto

### Dependencias

Para poder utilizar la SDK de Azure se bede incluir lo siguiente en el POM:


```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.azure</groupId>
            <artifactId>azure-sdk-bom</artifactId>
            <version>{bom_version_to_target}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

Luego la dependencia directa sin el tag de versión

```xml
<dependencies>
  <dependency>
    <groupId>com.azure</groupId>
    <artifactId>azure-storage-blob</artifactId>
  </dependency>
</dependencies>

```

Más información sobre las dependencias se puede encontrar en la [documentación de Azure](https://learn.microsoft.com/en-us/java/api/overview/azure/storage-blob-readme?source=recommendations&view=azure-java-stable#include-the-package "Include the package")

### Código
El proyecto se compone de las siguientes clases 

* **Clase Main** con sentencias switch para seleccionar alguna de las opciones para trabajar con contenedores (subir, descargar, eliminar archivos en un contenedor y crear o elimnar contenedores)
* **Clase AzureBlobStorageExample** donde se implementan los métodos correspondientes a cada acción

La clase AzureBlobStorageExample es la más interesante ya que utiliza la librería de Azure. 

Las clases más relevantes para comprender el proyecto que pertenecen a esa librería son las siguientes:

#### **BlobServiceClient** 
Clase para enviar requests y gestionar la cuenta de almacenamiento de Azure. Se instancia a través de BlobServiceClientBuilder. 
Ver [documentación oficial](https://learn.microsoft.com/en-us/java/api/com.azure.storage.blob.blobserviceclient?view=azure-java-stable)

#### **BlobContainerClient** 
Usado en el ejemplo. Clase cliente para enviar requests y gestionar un contenedor en particular. Algunas operaciones sobre contenedores son crearlos o eliminarlos. Se puede instanciar a través de la clase BlobContainerClientBuilder por el método BlobServiceClient.getBlobContainerClient(). 
Ver [documentación oficial](https://learn.microsoft.com/en-us/java/api/com.azure.storage.blob.blobcontainerclient?view=azure-java-stable)

Los métodos createBlobContainer() y deleteBlobContainer() del ejemplo usan esta clase y los métodos createIfNotExists() y deleteIfExists() de la misma.

#### **BlobClient** 
Clase cliente para enviar requests y gestionar un blob (archivo) en particular. Las operaciones permitidas por el cliente son carga y descarga, copia de un blob, recuperación y configuración de metadatos, recuperación y configuración de encabezados HTTP y eliminación y recuperación de un blob. Ver [documentación oficial](https://learn.microsoft.com/en-us/java/api/com.azure.storage.blob.blobclient?view=azure-java-stable)

El método uploadDataToBlob() utiliza el método upload() para subir datos ingresados por el usuario en terminal.

Los métodos uploadLocalFileToBlob(), downloadFileToLocalPath(), deleteBlobIfExists() utilizan los métodos uploadFromFile(), downloadToFile(), deleteIfExists() de esta clase.

### Conectarse a una cuenta de almacenamiento en Azure
El la clase AzureBlobStorageExample contamos con un atributo string llamado connectionString con la siguiente forma
```java
private static String connectionString = "DefaultEndpointsProtocol=http;AccountName=devstoreaccount1;AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;BlobEndpoint=http://127.0.0.1:10000/devstoreaccount1;QueueEndpoint=http://127.0.0.1:10001/devstoreaccount1;TableEndpoint=http://127.0.0.1:10002/devstoreaccount1;";
```

Este es el string de conexión local por defecto para conectarse a la instancia del emulador Azurite. Para conectarse a una cuenta de Azure Storage se debe obtener un string similar desde la página de la cuenta y reemplezarlo en el código. Se puede obtener de la siguiente manera:
1. Ir al [portal de Azure](portal.azure.com "Portal de Azure")\
2. Cuentas de Almacenamiento
3. seleccionar la cuenta con la que se desea trabajar
4. ir a claves de acceso
5. copiar la cadena de conexión correspondiente a una clave

