package ficheros;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejoArchivo {

    public void escribirArchivo(String nombreArchivo, String texto) {
        crearArchivo(nombreArchivo);
        try (FileWriter writer = new FileWriter(nombreArchivo, true)) {
            writer.write(texto + "\n");
        } catch (IOException e) {
            System.err.println("ERROR AL GUARDAR EL ARCHIVO: " + e.getMessage());
        }
    }

    public List<String> leerArchivo(String nombreArchivo) {
        List<String> contenido = new ArrayList<>();
        String linea = "";
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            while ((linea = br.readLine()) != null) {
                contenido.add(linea);
            }
        } catch (IOException e) {
            System.err.println("ERROR AL LEER EL ARCHIVO: " + e.getMessage());
        }
        return contenido;
    }

    public void crearArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                System.out.println("ARCHIVO CREADO EXITOSAMENTE.");
            } catch (IOException e) {
                System.err.println("ERROR AL CREAR AL ARCHIVO: " + e.getMessage());
            }
        }
    }
}
