package menu;

import criptografia.cifrar.Cifrardo;
import criptografia.decifrar.Decifrado;

import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private boolean menu = true;
    Cifrardo cifrardo;
    Decifrado decifrado;

    public void imprimirMenu() {
        while (menu) {
            opciones();
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    cifrardo = new Cifrardo();
                    cifrardo.cifrarArchivo();
                    break;
                case 2:
                    decifrado = new Decifrado();
                    decifrado.deCifrarArchivo();
                    break;
                case 3:
                    System.out.println("Gracias por usar el menú");
                    menu = false;
                    return;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
            System.out.println();
        }
    }

    private static void opciones() {
        System.out.println("\n------------------ Cifrado Cesar -------------------");
        System.out.println("1. Cifrar Texto");
        System.out.println("2. Decifrar Texto");
        System.out.println("3. Cerrar Programa");
        System.out.println("\n----------------------------------------------------");
        System.out.print("Seleccione una Opción: ");
    }
}
