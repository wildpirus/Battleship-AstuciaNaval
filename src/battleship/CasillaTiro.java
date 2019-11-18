/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Clase para una casilla en un TableroTiro. 
 * @author wildg
 */
public class CasillaTiro extends JLabel{
    private boolean hasBeenShot;
    private final int i,j;
    
    /**
     * Crea una casilla para el panel de tiro.
     * @param i posición en fila.
     * @param j posición en columna.
     */
    public CasillaTiro(int i, int j) {
        this.hasBeenShot = false;
        this.i = i;
        this.j = j;
        this.setIcon(new ImageIcon("src/sources/transp.png"));
    }
    
    /**
     * Método para asignar la casilla como disparada.
     * @param b 
     */
    public void setState(boolean b){
        this.hasBeenShot=true;
        if(!b){
            this.setIcon(new ImageIcon("src/sources/miss.png"));
        }
    }
    
    /**
     * Función para obtener posición en fila.
     * @return int
     */
    public int getI() {
        return i;
    }
    
    /**
     * Función para obtener posición en columna.
     * @return int 
     */
    public int getJ() {
        return j;
    }

    /**
     * Función para saber si han disparado a la casilla.
     * @return true si le han disparado, false si no.
     */
    public boolean itHasBeenShot() {
        return hasBeenShot;
    }
    
    /**
     * Método para asignar la casilla como disparada.
     */
    public void setShot() {
        this.hasBeenShot = true;
    }
    
}
