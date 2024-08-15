/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author juans
 */
public class MinaModel {
    private char[][] tablero;
    private boolean[][] minas;
    private int size;

    public void inicializarTablero(int size, int numMinas) {
        this.size = size;
        tablero = new char[size][size];
        minas = new boolean[size][size];

        // Inicializar tablero vacío
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tablero[i][j] = '-';
            }
        }

        // Colocar minas sin repetir posiciones
        Set<String> posicionesOcupadas = new HashSet<>();
        Random rand = new Random();
        int minasColocadas = 0;

        while (minasColocadas < numMinas) {
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);
            String posicion = x + "," + y;

            if (!posicionesOcupadas.contains(posicion)) {
                minas[x][y] = true;
                posicionesOcupadas.add(posicion);
                minasColocadas++;
            }
        }
    }

    public char[][] getTablero() {
        return tablero;
    }

    public boolean esMina(int x, int y) {
        return minas[x][y];
    }

    public void descubrirCelda(int x, int y) {
        if (esMina(x, y)) {
            tablero[x][y] = 'X';
        } else {
            tablero[x][y] = '0'; // Aquí podrías implementar la lógica para contar minas cercanas.
        }
    }

    public boolean esCeldaDescubierta(int x, int y) {
        return tablero[x][y] != '-';
    }

    public boolean todasCeldasDescubiertas() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tablero[i][j] == '-' && !minas[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}


