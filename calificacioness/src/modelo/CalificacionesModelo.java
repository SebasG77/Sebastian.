/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author juans
 */
public class CalificacionesModelo {
    private double[][] calificaciones; // Almacena las calificaciones de los estudiantes en las materias
    private String[] estudiantes; // Nombres de los estudiantes
    private String[] materias; // Nombres de las materias

    public CalificacionesModelo(String[] estudiantes, String[] materias) {
        this.estudiantes = estudiantes;
        this.materias = materias;
        this.calificaciones = new double[estudiantes.length][materias.length];
    }

    public double[][] getCalificaciones() {
        return calificaciones;
    }

    public String[] getEstudiantes() {
        return estudiantes;
    }

    public String[] getMaterias() {
        return materias;
    }

    public void setCalificacion(int estudianteIndex, int materiaIndex, double calificacion) {
        this.calificaciones[estudianteIndex][materiaIndex] = calificacion;
    }

    public double calcularPromedioEstudiante(int estudianteIndex) {
        double suma = 0;
        for (int i = 0; i < materias.length; i++) {
            suma += calificaciones[estudianteIndex][i];
        }
        return suma / materias.length;
    }

    public double calcularPromedioMateria(int materiaIndex) {
        double suma = 0;
        for (int i = 0; i < estudiantes.length; i++) {
            suma += calificaciones[i][materiaIndex];
        }
        return suma / estudiantes.length;
    }

    public int obtenerEstudianteMejorPromedio() {
        int mejorEstudianteIndex = 0;
        double mejorPromedio = calcularPromedioEstudiante(0);
        for (int i = 1; i < estudiantes.length; i++) {
            double promedio = calcularPromedioEstudiante(i);
            if (promedio > mejorPromedio) {
                mejorPromedio = promedio;
                mejorEstudianteIndex = i;
            }
        }
        return mejorEstudianteIndex;
    }

    public int obtenerEstudiantePeorPromedio() {
        int peorEstudianteIndex = 0;
        double peorPromedio = calcularPromedioEstudiante(0);
        for (int i = 1; i < estudiantes.length; i++) {
            double promedio = calcularPromedioEstudiante(i);
            if (promedio < peorPromedio) {
                peorPromedio = promedio;
                peorEstudianteIndex = i;
            }
        }
        return peorEstudianteIndex;
    }

    public int obtenerMateriaMejorPromedio() {
        int mejorMateriaIndex = 0;
        double mejorPromedio = calcularPromedioMateria(0);
        for (int i = 1; i < materias.length; i++) {
            double promedio = calcularPromedioMateria(i);
            if (promedio > mejorPromedio) {
                mejorPromedio = promedio;
                mejorMateriaIndex = i;
            }
        }
        return mejorMateriaIndex;
    }

    public int obtenerMateriaPeorPromedio() {
        int peorMateriaIndex = 0;
        double peorPromedio = calcularPromedioMateria(0);
        for (int i = 1; i < materias.length; i++) {
            double promedio = calcularPromedioMateria(i);
            if (promedio < peorPromedio) {
                peorPromedio = promedio;
                peorMateriaIndex = i;
            }
        }
        return peorMateriaIndex;
    }
}
