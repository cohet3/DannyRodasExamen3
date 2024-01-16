package corenetworks.Ejercicio2;

import java.io.*;

public class cifrar {
    public static void main(String[] args) {
        try {
            // obtener los nombres de los archivos de la consola
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Ingrese el nombre del archivo de origen: ");
            String archivoOrigen = br.readLine();
            System.out.print("Ingrese el nombre del archivo cifrado: ");
            String archivoCifrado = br.readLine();

            // obtener el valor numerico para XOR
            System.out.print("Ingrese un valor numérico entre 1 y 255: ");
            int valorXOR = Integer.parseInt(br.readLine());

            // validar que el valor este en el rango adecuado
            if (valorXOR < 1 || valorXOR > 255) {
                System.out.println("El valor numérico debe estar entre 1 y 255.");
                return;
            }

            // cifrar el archivo
            cifrarArchivo(archivoOrigen, archivoCifrado, valorXOR);

            System.out.println("Cifrado completado con éxito.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void cifrarArchivo(String archivoOrigen, String archivoCifrado, int valorXOR) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOrigen));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCifrado))) {

            int caracter;
            while ((caracter = br.read()) != -1) {
                // realizar XOR con el valor proporcionado por el usuario
                int caracterCifrado = caracter ^ valorXOR;
                // escribir el carácter cifrado en el archivo de destino
                bw.write(caracterCifrado);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
