/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.CalificacionesModelo;
import vista.CalificacionesVista;

/**
 *
 * @author juans
 */
public class CalificacionesControlador {
    private CalificacionesModelo modelo;
    private CalificacionesVista vista;

    public CalificacionesControlador(CalificacionesModelo modelo, CalificacionesVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void ingresarCalificaciones() {
        for (int i = 0; i < modelo.getEstudiantes().length; i++) {
            for (int j = 0; j < modelo.getMaterias().length; j++) {
                double calificacion = vista.leerCalificacion(modelo.getEstudiantes()[i], modelo.getMaterias()[j]);
                modelo.setCalificacion(i, j, calificacion);
            }
        }
    }

    public void mostrarReporte() {
        StringBuilder reporte = new StringBuilder();
        
        reporte.append("Reporte de Calificaciones:\n");
        
        for (int i = 0; i < modelo.getEstudiantes().length; i++) {
            reporte.append("Promedio de " + modelo.getEstudiantes()[i] + ": " + modelo.calcularPromedioEstudiante(i) + "\n");
        }

        for (int j = 0; j < modelo.getMaterias().length; j++) {
            reporte.append("Promedio de " + modelo.getMaterias()[j] + ": " + modelo.calcularPromedioMateria(j) + "\n");
        }

        int mejorEstudiante = modelo.obtenerEstudianteMejorPromedio();
        int peorEstudiante = modelo.obtenerEstudiantePeorPromedio();
        reporte.append("Estudiante con mejor promedio: " + modelo.getEstudiantes()[mejorEstudiante] + "\n");
        reporte.append("Estudiante con peor promedio: " + modelo.getEstudiantes()[peorEstudiante] + "\n");

        int mejorMateria = modelo.obtenerMateriaMejorPromedio();
        int peorMateria = modelo.obtenerMateriaPeorPromedio();
        reporte.append("Materia con mejor promedio: " + modelo.getMaterias()[mejorMateria] + "\n");
        reporte.append("Materia con peor promedio: " + modelo.getMaterias()[peorMateria] + "\n");

        vista.mostrarReporte(reporte.toString());
    }

    public void actualizarCalificacion(int estudianteIndex, int materiaIndex, double nuevaCalificacion) {
        modelo.setCalificacion(estudianteIndex, materiaIndex, nuevaCalificacion);
    }
}
