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

            if(firstQuestionOpotion == 1) {

                System.out.println("Ingrese el nombre del container a usar");

                String containerName = lineScanner.nextLine();
                AzureBlobStorage azureBlobStorage = new AzureBlobStorage(containerName);


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
                            azureBlobStorage.uploadDataToBlob(remoteBlobName, dataToUpload);
                            break;
                        case 2:
                            System.out.println("Ingrese el nombre que tendrá el archivo en Azure");
                            remoteBlobName = lineScanner.nextLine();
                            System.out.println("Ingrese el path del archivo local");
                            path = lineScanner.nextLine();
                            azureBlobStorage.uploadLocalFileToBlob(remoteBlobName, path);
                            break;
                        case 3:
                            System.out.println("Ingrese el nombre del archivo en Azure");
                            remoteBlobName = lineScanner.nextLine();
                            azureBlobStorage.downloadFileToLocalPath(remoteBlobName);
                            break;
                        case 4:
                            System.out.println("Ingrese el nombre del archivo a eliminar");
                            remoteBlobName = lineScanner.nextLine();
                            Boolean resultado = azureBlobStorage.deleteBlobIfExists(remoteBlobName);
                            System.out.println("Eliminado: " + resultado);

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
                        System.out.println("Ingrese nombre del nuevo container en el Blob Storage");
                        String newContainerName = lineScanner.nextLine();
                        Boolean wasContainerCreated = AzureBlobStorage.createBlobContainer(newContainerName);
                        System.out.println("Contenedor " + newContainerName + " creado: " + wasContainerCreated);
                    case 2:
                        System.out.println("Ingrese nombre del container a borrar del Blob Storage");
                        String containerNameToDelete = lineScanner.nextLine();
                        Boolean wasContainerDeleted = AzureBlobStorage.deleteBlobContainer(containerNameToDelete);
                        System.out.println("Contenedor " + containerNameToDelete + " borrado: " + wasContainerDeleted);
                }


            }

            System.out.println(" \n\n===========================\n\n ");
        } while(continueOption == 0);
    }

}
