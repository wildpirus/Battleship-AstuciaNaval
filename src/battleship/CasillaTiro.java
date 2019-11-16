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
    

    public CasillaTiro(int i, int j) {
        this.hasBeenShot = false;
        this.i = i;
        this.j = j;
        this.setIcon(new ImageIcon("src/sources/transp.png"));
    }
    
    public void setState(boolean b){
        this.hasBeenShot=true;
        if(!b){
            this.setIcon(new ImageIcon("src/sources/miss.png"));
        }
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean itHasBeenShot() {
        return hasBeenShot;
    }
    
}
