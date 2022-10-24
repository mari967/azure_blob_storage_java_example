import java.util.Scanner;

public class Main {


    public static void main(String[ ]  args) {

        Scanner lineScanner = new Scanner(System.in);
        Scanner integerScanner = new Scanner(System.in);
        
        int continueOption = 0;
        do {
            System.out.println("**** Azure Blob Storage Example - Seleccione la opción ****");
            System.out.println("""
                1) Subir, descargar o eliminar archivos de un container en particular
                2) Crear o eliminar un container
                """);

            int firstQuestionOpotion = integerScanner.nextInt();
            System.out.println("Ingrese el nombre del container");

            String containerName = lineScanner.nextLine();
            AzureBlobStorageExample azureBlobStorageExample = new AzureBlobStorageExample(containerName);

            if(firstQuestionOpotion == 1) {

                do {
                    System.out.println("""
                1) Subir datos binarios
                2) Subir archivo local
                3) Descargar archivo
                4) Eliminar archivo en Azure\s""");

                    int option = integerScanner.nextInt();
                    String remoteBlobName;
                    String path;
                    String dataToUpload;
                    switch(option) {
                        case 1:
                            System.out.println("Ingrese el nombre que tendrá el archivo en Azure");
                            remoteBlobName = lineScanner.nextLine();
                            System.out.println("Ingrese los datos a subir como string");
                            dataToUpload = lineScanner.nextLine();
                            azureBlobStorageExample.uploadDataToBlob(remoteBlobName, dataToUpload);
                            break;
                        case 2:
                            System.out.println("Ingrese el nombre que tendrá el archivo en Azure");
                            remoteBlobName = lineScanner.nextLine();
                            System.out.println("Ingrese el path del archivo local");
                            path = lineScanner.nextLine();
                            azureBlobStorageExample.uploadLocalFileToBlob(remoteBlobName, path);
                            break;
                        case 3:
                            System.out.println("Ingrese el nombre del archivo en Azure");
                            remoteBlobName = lineScanner.nextLine();
                            azureBlobStorageExample.downloadFileToLocalPath(remoteBlobName);
                            break;
                        case 4:
                            System.out.println("Ingrese el nombre del archivo a eliminar");
                            remoteBlobName = lineScanner.nextLine();
                            Boolean resultado = azureBlobStorageExample.deleteBlobIfExists(remoteBlobName);
                            System.out.println("Eliminado " + remoteBlobName + ": " + resultado);
                            break;

                    }
                    System.out.println("¿Continuar trabajando con el mismo container? 1-si / 0-no");
                    continueOption = integerScanner.nextInt();
                } while(continueOption == 1);
            }

            //New container
            else if(firstQuestionOpotion == 2) {
                System.out.println("""
                1) Crear contenedor
                2) Eliminar contenedor
                """);

                int containerOption = integerScanner.nextInt();

                switch (containerOption) {
                    case 1:
                        Boolean wasContainerCreated = azureBlobStorageExample.createBlobContainer();
                        System.out.println("Contenedor " + azureBlobStorageExample.getContainerName() + " creado: " + wasContainerCreated);
                        break;
                    case 2: ;
                        Boolean wasContainerDeleted = azureBlobStorageExample.deleteBlobContainer();
                        System.out.println("Contenedor " + azureBlobStorageExample.getContainerName() + " borrado: " + wasContainerDeleted);
                        break;
                }

            }

            System.out.println(" \n\n===========================\n\n ");
        } while(continueOption == 0);
    }

}
