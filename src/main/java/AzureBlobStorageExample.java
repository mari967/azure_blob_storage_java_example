import com.azure.core.http.rest.PagedIterable;
import com.azure.core.util.BinaryData;
import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobItem;

import java.util.stream.Collectors;


/***
 * Code examples from:
 * https://learn.microsoft.com/en-us/java/api/overview/azure/storage-blob-readme?source=recommendations&view=azure-java-stable#examples
 */

public class AzureBlobStorageExample {


    /**
     * Client to a container. It may only be instantiated through a BlobContainerClientBuilder
     * or via the method getBlobContainerClient(String containerName). This class does not
     * hold any state about a particular container but is instead a convenient way of sending
     * off appropriate requests to the resource on the service. It may also be used to
     * construct URLs to blobs.
     *
     * This client contains operations on a container. Operations on a blob are available on
     * BlobClient through getBlobClient(String blobName), and operations on the service are
     * available on BlobServiceClient.
     *
     * Reference: https://learn.microsoft.com/en-us/java/api/com.azure.storage.blob.blobcontainerclient?view=azure-java-stable
     * */
    private BlobContainerClient blobContainerClient;

    /**
     * Se obtiene del Azure Storage Explorer. Clic derecho en Emulador-Firma de acceso compartido-Copiar cadena de conexión (connection string)
     * */
    //LOCAL
    private String connectionString = "DefaultEndpointsProtocol=http;AccountName=devstoreaccount1;AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;BlobEndpoint=http://127.0.0.1:10000/devstoreaccount1;QueueEndpoint=http://127.0.0.1:10001/devstoreaccount1;TableEndpoint=http://127.0.0.1:10002/devstoreaccount1;";
    private String containerName;
    public AzureBlobStorageExample(String containerName) {
            this.containerName = containerName;
            this.blobContainerClient = new BlobServiceClientBuilder()
                    .connectionString(connectionString)
                    .buildClient()
                    .getBlobContainerClient(containerName);
    }


    public String getContainerName() {
        return this.containerName;
    }
//Operaciones sobre un Storage account en particular


    public Boolean createBlobContainer() {
        return this.blobContainerClient
                .createIfNotExists();
    }

    public Boolean deleteBlobContainer() {
        return this.blobContainerClient
                .deleteIfExists();
    }


    //Operaciones sobre un contenedor en particular dentro del Storage acccount

    public void uploadDataToBlob(String remoteBlobName, String dataToUpload) {
        this.blobContainerClient
                .getBlobClient(remoteBlobName)
                .upload(BinaryData.fromString(dataToUpload));
    }

    public void uploadLocalFileToBlob(String remoteBlobName, String localFilePath) {
        this.blobContainerClient
                .getBlobClient(remoteBlobName)
                .uploadFromFile(localFilePath);
    }

    public void downloadFileToLocalPath(String remoteBlobName) {
        this.blobContainerClient
                .getBlobClient(remoteBlobName)
                .downloadToFile(remoteBlobName); //Al descargar tendrá el mismo nombre que en remoto
    }

    public Boolean deleteBlobIfExists(String remoteBlobName) {
        return this.blobContainerClient
                .getBlobClient(remoteBlobName)
                .deleteIfExists();
    }

    public String listBlobsInContainer() {
        return this.blobContainerClient.listBlobs().stream().map(blobItem -> blobItem.getName() ).collect(Collectors.joining(",\n"));
    }

}
