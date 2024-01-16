package corenetworks.Ejercicio1;

import java.io.File;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        //Declaramos variables
        Scanner scanner = new Scanner(System.in);
        String rutaArchivo;
        File archivo= null;

        // pedir al usuario que ingrese la ruta completa del archivo

        System.out.println("Ingrese la ruta completa del archivo: ");
        rutaArchivo = scanner.nextLine();

        // crear un objeto file con la ruta proporcionada
        archivo = new File(rutaArchivo);

        // verificar si el archivo existe
        if (archivo.exists()) {
            // imprimir informacion sobre el archivo
            System.out.println("Información del archivo:");
            System.out.println("Nombre: " + archivo.getName());
            System.out.println("Ruta: " + archivo.getAbsolutePath());
            System.out.println("Tamaño: " + archivo.length() + " bytes");
            System.out.println("Es un directorio: " + archivo.isDirectory());
            System.out.println("Es un archivo: " + archivo.isFile());
            System.out.println("Última modificación: " + archivo.lastModified());
        } else {
            System.out.println("El archivo no existe.");
        }

        // Cerrar el scanner
        scanner.close();
    }
}
