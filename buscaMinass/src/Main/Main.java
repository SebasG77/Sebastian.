/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Controlador.MinaController;
import Modelo.MinaModel;
import Vista.MinaView;

/**
 *
 * @author juans
 */
public class Main {
    public static void main(String[] args) {
        MinaModel model = new MinaModel();
        MinaView view = new MinaView();
        MinaController controller = new MinaController(model, view);

        controller.iniciarJuego();
    }
}

