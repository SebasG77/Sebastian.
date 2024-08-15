/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.Scanner;

/**
 *
 * @author juans
 */
public class CalificacionesVista {
    private Scanner scanner;

    public CalificacionesVista() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public double leerCalificacion(String estudiante, String materia) {
        System.out.println("Ingrese la calificación de " + estudiante + " en " + materia + ": ");
        return scanner.nextDouble();
    }

    public void mostrarReporte(String reporte) {
        System.out.println(reporte);
    }
}
