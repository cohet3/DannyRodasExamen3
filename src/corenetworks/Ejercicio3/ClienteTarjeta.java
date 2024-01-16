package corenetworks.Ejercicio3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarjeta {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 3000);
             BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Obtener la información de la transacción desde el usuario
            String secuencia = obtenerSecuenciaTransaccion();

            // Enviar la secuencia al servi
            salida.write(secuencia);
            salida.newLine();
            salida.flush();

            // Recibir la respuesta del servidor
            String respuesta = entrada.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String obtenerSecuenciaTransaccion() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la fecha de la transacción (AAAAMMDD): ");
        String fechaTransaccion = scanner.nextLine();

        System.out.print("Ingrese el número de tarjeta (16 dígitos): ");
        String numTarjeta = scanner.nextLine();

        System.out.print("Ingrese la fecha de vencimiento (AAAAMMDD): ");
        String fechaVencimiento = scanner.nextLine();

        System.out.print("Ingrese el CVV (3 dígitos): ");
        String cvv = scanner.nextLine();

        System.out.print("Ingrese la cantidad: ");
        String cantidad = scanner.nextLine();

        return String.format("%s %s %s %s %s", fechaTransaccion, numTarjeta, fechaVencimiento, cvv, cantidad);
    }
}
