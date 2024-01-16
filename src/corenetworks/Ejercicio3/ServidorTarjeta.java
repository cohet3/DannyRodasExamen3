package corenetworks.Ejercicio3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServidorTarjeta {

public static void main(String[] args) {
    //1. declarar variables
    int codigoAutorizacion = 0;
    String descripcion = obtenerDescripcion(codigoAutorizacion);
    System.out.println(descripcion);
    try {
        // inicializar el Server en el puerto 3000
        ServerSocket serverSocket = new ServerSocket(3000);
        System.out.println("Servidor esperando peticiones...");

        while (true) {
            // aceptar peticiones
            Socket socketCliente = serverSocket.accept();
            System.out.println("Cliente conectado -> " + socketCliente.getInetAddress());

            // crear un hilo para manejar la conexión con el cliente
            Thread hiloCliente = new Thread(() -> manejarConexion(socketCliente));
            hiloCliente.start();
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private static void manejarConexion(Socket socketCliente) {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
             BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream()))) {

            // leer la secuencia del cliente
            String secuencia = entrada.readLine();
            System.out.println("Recibido del cliente: " + secuencia);

            // procesar la autorizacion
            String respuesta = procesarAutorizacion(secuencia);

            // enviar la respuesta al cliente
            salida.write(respuesta);
            salida.newLine();
            salida.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String procesarAutorizacion(String secuencia) {
        // dividir la secuencia en partes
        String[] partes = secuencia.split(" ");

        // obtener la información de la transaccion
        String fechaTransaccion = partes[0];
        String numTarjeta = partes[1];
        String fechaVencimiento = partes[2];
        String cvv = partes[3];
        String cantidad = partes[4];

        // generar un número aleatorio para simular la autorizacion o el error
        Random random = new Random();
        int resultado = random.nextInt(4); // 0, 1, 2, 3

        // construir la respuesta
        String respuesta;
        if (resultado == 0) {
            // autorizado
            String codigoAutorizacion = String.valueOf(random.nextInt(1000000));
            respuesta = String.format("%s %s %s %s", fechaTransaccion, codigoAutorizacion,resultado, numTarjeta, cantidad);
        } else {
            // error
            String error = "";
            switch (resultado) {
                case 1:
                    error = "9137";
                    break;
                case 2:
                    error = "9221";
                    break;
                case 3:
                    error = "9677";
                    break;
            }
            respuesta = String.format("%s %s %s", fechaTransaccion, error, numTarjeta);
        }

        return respuesta;
    }

    private static String obtenerDescripcion(int codigoAutorizacion) {
        String formato = "Código: %d - %s";
        String descripcion;

        switch (codigoAutorizacion) {
            case 0:
                descripcion = String.format(formato, 0, "Autorizado");
                break;
            case 9137:
                descripcion = String.format(formato, 9137, "Error: Código 9137");
                break;
            case 9221:
                descripcion = String.format(formato, 9221, "Error: Código 9221");
                break;
            case 9677:
                descripcion = String.format(formato, 9677, "Error: Código 9677");
                break;
            default:
                descripcion = String.format(formato, codigoAutorizacion, "Código no reconocido");
                break;
        }

        return descripcion;
    }
}
