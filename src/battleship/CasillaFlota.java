/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Clase para una casilla en un TableroFlota. 
 * @author wildg
 */
public class CasillaFlota extends JLabel{
    private boolean hasBeenShot;
    private Nave nave;
    private final int i,j;

    public CasillaFlota(int i, int j) {
        this.hasBeenShot = false;
        this.nave = null;
        this.i = i;
        this.j = j;
        this.setIcon(new ImageIcon("src/sources/transp.png"));
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }
    
    public boolean isHit(){
        this.hasBeenShot = true;
        if(nave!=null){
            this.setIcon(new ImageIcon("src/sources/mark.png"));
            nave.isHit(new Point(i,j));
            JOptionPane.showMessageDialog(null, "Le han dado a tu flota en: " + i + ", " + j);
            return true;
        }else {
            this.setIcon(new ImageIcon("src/sources/miss.png"));
            JOptionPane.showMessageDialog(null, "El enemigo ha fallado un disparo en: " + i + ", " + j);
            return false;
            
        }
    }
    
    public Nave hasDestroyed(){
        if(nave!=null){
            if(nave.isDestroyed()) {
                return nave;
            }else {
                return null;
            }   
        }else {
            return null;
        }
    }
    
    
    
    
    
}
