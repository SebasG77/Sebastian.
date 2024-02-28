package main;
/**
 *Nombre: Sebastian Garcia
 */
import java.util.Scanner;

public class Main {
    //Metodo Main
    public static void main(String[] args) {
        
        //Inicializar Variables
        ListaEnlazada list = new ListaEnlazada();
        Scanner scanner = new Scanner(System.in);
        
        //Ciclo para repetir el proceso
        
        System.out.println("Ingrese los numeros de la lista (escriba 'fin' para terminar):");
        while (true) {
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("fin")) {
                break;
            }
            
            //Metodo exepción para evitar errores
            try {
                int num = Integer.parseInt(input);
                list.add(num);
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido o 'fin' para terminar.");
            }
        }
        
        //Llama metodo para imprimir lista
        list.print();
        //Metodo para eliminar duplicados
        list.removerDuplicados();
        //Metodo para imprimir sin duplicados
        list.print();
        
        
    }
}
