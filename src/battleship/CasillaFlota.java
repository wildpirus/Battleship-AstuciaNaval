/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

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
        boolean hit;
        if(nave!=null){
            nave.isHit(new Point(i,j));
            setIcon(new ImageIcon("src/sources/explo.gif"));
            ActionListener action = (ActionEvent e) -> {
                if(e.getSource() instanceof Timer){
                    Timer timer = (Timer) e.getSource();
                    timer.stop();
                    setIcon(new ImageIcon("src/sources/mark.png"));
                }
            };
            Timer timer = new Timer(2000, action);
            timer.start();
            hit = true;
        }else {
            this.setIcon(new ImageIcon("src/sources/miss.png"));
            hit = false;
        }
        return hit;
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
