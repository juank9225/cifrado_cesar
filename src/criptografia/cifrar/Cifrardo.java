package criptografia.cifrar;

import config.ConfigArch;
import diccionario.DiccionarioLatino;
import ficheros.ManejoArchivo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Cifrardo extends ManejoArchivo {

    Scanner scanner = new Scanner(System.in);
    private String archivo = "resources/archivo.txt";


    public String guardarLlave() {
        System.out.println("DIGITE UN NUMERO QUE SERVIRA COMO LLAVE DE ENCIPTACION");
        String llave = scanner.nextLine();
        escribirArchivo(ConfigArch.PROPERTIE.getRuta(),llave);
        return llave;
    }

    public void cifrarArchivo() {
        System.out.println("DIGITE EL NOMBRE QUE VA TENER EL ARCHIVO CIFRADO");
        String nombreArchivo = scanner.nextLine();
        List<String> textoCifrar = new ArrayList<>();
        String llave = guardarLlave();
        textoCifrar = leerArchivo(ConfigArch.ARCHIVO_EN_CLARO.getRuta());
        escribirArchivo(ConfigArch.PROPERTIE.getRuta(),ConfigArch.RUTA_ARCHIVOS.getRuta()+nombreArchivo+".txt");
        crearArchivo(ConfigArch.RUTA_ARCHIVOS.getRuta()+nombreArchivo+".txt");

        for (String datos : textoCifrar) {
            char[] caracteres = datos.toCharArray();
            List<Character> datosCifrados = cifrardoCesar(caracteres, llave);
            escribirArchivo(ConfigArch.RUTA_ARCHIVOS.getRuta()+nombreArchivo+".txt", convertirString(datosCifrados));
        }

    }

    private String convertirString(List<Character> datosCifrados) {
        return datosCifrados.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private List<Character> cifrardoCesar(char[] caracteres, String llave) {
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
            System.out.println("CARACTER NO ENCONTRADO.");
            return '\0';
        }
        int nuevoIndex = (index + Integer.parseInt(llave)) % lista.size();
        return lista.get(nuevoIndex);
    }

}
