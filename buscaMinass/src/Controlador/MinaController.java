/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.MinaModel;
import Vista.MinaView;

/**
 *
 * @author juans
 */
public class MinaController {
    private MinaModel model;
    private MinaView view;

    public MinaController(MinaModel model, MinaView view) {
        this.model = model;
        this.view = view;
    }

    public void iniciarJuego() {
        int size = view.solicitarCoordenada("Ingrese el tamaño del tablero (ej. 5 para un tablero 5x5): ");
        int numMinas = view.solicitarCoordenada("Ingrese la cantidad de minas (min 1, max " + (size * size - 1) + "): ");

        // Inicializar el modelo
        model.inicializarTablero(size, numMinas);

        boolean juegoTerminado = false;
        while (!juegoTerminado) {
            view.mostrarTablero(model.getTablero());

            int x = view.solicitarCoordenada("Ingrese la coordenada X: ");
            int y = view.solicitarCoordenada("Ingrese la coordenada Y: ");

            if (model.esCeldaDescubierta(x, y)) {
                view.mostrarMensaje("¡La celda ya ha sido descubierta! Intenta con otra.");
                continue; // Repetir el turno
            }

            if (model.esMina(x, y)) {
                view.mostrarMensaje("¡Has encontrado una mina! Fin del juego.");
                juegoTerminado = true;
            } else {
                model.descubrirCelda(x, y);
                if (model.todasCeldasDescubiertas()) {
                    view.mostrarMensaje("¡Felicidades! Has descubierto todas las celdas. Ganaste.");
                    juegoTerminado = true;
                }
            }
        }

        view.mostrarTablero(model.getTablero());
    }
}

