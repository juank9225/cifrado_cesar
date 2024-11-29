package criptografia.decifrar;

import config.ConfigArch;
import diccionario.DiccionarioLatino;
import ficheros.ManejoArchivo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Decifrado extends ManejoArchivo {

    Scanner scanner = new Scanner(System.in);
    private String archivo = "resources/archivo.txt";

    public List<String> obtenerLlave() {
        List<String> llave = leerArchivo(ConfigArch.PROPERTIE.getRuta());
        return llave;
    }

    public void deCifrarArchivo() {
        System.out.println("DIGITE EL NOMBRE QUE VA TENER EL ARCHIVO DESCIFRADO");
        String nombreArchivoDes = scanner.nextLine();
        List<String> textoDecifrar = new ArrayList<>();
        String llave = obtenerLlave().get(0).toString();
        List<String> nombreArchivoCifrado = leerArchivo(ConfigArch.PROPERTIE.getRuta());
        textoDecifrar = leerArchivo(nombreArchivoCifrado.get(1).toString());
        escribirArchivo(ConfigArch.PROPERTIE.getRuta(),ConfigArch.RUTA_ARCHIVOS.getRuta()+nombreArchivoDes+".txt");
        for (String datos : textoDecifrar) {
            char[] caracteres = datos.toCharArray();
            List<Character> datosDecifrados = deCifrardoCesar(caracteres, llave);
            escribirArchivo(ConfigArch.RUTA_ARCHIVOS.getRuta()+nombreArchivoDes+".txt", convertirString(datosDecifrados));
        }

    }

    private String convertirString(List<Character> datosDecifrados) {
        return datosDecifrados.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private List<Character> deCifrardoCesar(char[] caracteres, String llave) {
        List<Character> nuevoDato = new ArrayList<>();
        for (char caracter : caracteres) {
            nuevoDato.add(textoKey(caracter, llave));
        }
        return nuevoDato;
    }

    private char textoKey(char caracter, String llave) {
        var lista = DiccionarioLatino.ALFABETO;
        int index = lista.indexOf(caracter);

        if (index == -1) {
            System.out.println("CARACTER NO ENCONTRADO");
            return '\0';
        }
        int nuevoIndex = (index - Integer.parseInt(llave)) % lista.size();
        return lista.get(nuevoIndex);
    }

}
