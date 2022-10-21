import com.azure.core.util.BinaryData;
import com.azure.storage.blob.*;


/***
 * Code examples from:
 * https://learn.microsoft.com/en-us/java/api/overview/azure/storage-blob-readme?source=recommendations&view=azure-java-stable#examples
 */

public class AzureBlobStorage {

    /**
     * Se obtiene del Azure Storage Explorer. Clic derecho en Emulador-Firma de eacceso compartido-Copiar cadena de conexión (connection string)
     * */
    private static String connectionString = "DefaultEndpointsProtocol=http;AccountName=devstoreaccount1;AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;BlobEndpoint=http://127.0.0.1:10000/devstoreaccount1;QueueEndpoint=http://127.0.0.1:10001/devstoreaccount1;TableEndpoint=http://127.0.0.1:10002/devstoreaccount1;";


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

    public AzureBlobStorage(String containerName) {
            this.blobContainerClient = new BlobContainerClientBuilder()
                    .connectionString(connectionString)
                    .containerName(containerName) //container name
                    .buildClient();
    }


    //Operaciones sobre un Storage account en particular

    public static Boolean createBlobContainer(String nameOfNewContainer) {
        return new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient()
                .getBlobContainerClient(nameOfNewContainer)
                .createIfNotExists();
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

}
