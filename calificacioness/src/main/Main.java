/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controlador.CalificacionesControlador;
import modelo.CalificacionesModelo;
import vista.CalificacionesVista;

/**
 *
 * @author juans
 */
public class Main {
    public static void main(String[] args) {
        String[] estudiantes = {"Juan", "María", "Carlos"};
        String[] materias = {"Matemáticas", "Ciencias", "Historia"};

        CalificacionesModelo modelo = new CalificacionesModelo(estudiantes, materias);
        CalificacionesVista vista = new CalificacionesVista();
        CalificacionesControlador controlador = new CalificacionesControlador(modelo, vista);

        controlador.ingresarCalificaciones();
        controlador.mostrarReporte();
        
        // Ejemplo de actualización de una calificación
        controlador.actualizarCalificacion(0, 1, 8.5);
        controlador.mostrarReporte();
    }
}
