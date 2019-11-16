/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import battleship.CasillaTiro;
import battleship.TableroTiro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Guacha
 */
public class TableroTiroOnline extends TableroTiro{
    
    public TableroTiroOnline(JugadorOnline jugador) {
        super(jugador);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        JugadorOnline j = (JugadorOnline) jugador;
        if (j.inTurno()) {
            if (me.getComponent() instanceof CasillaTiro) {
                CasillaTiro c = (CasillaTiro) me.getComponent();
                if (!c.itHasBeenShot()) {
                    c.setShot();
                    j.disparo(c.getI(), c.getJ());
                    System.out.println("Disparaste en: " + c.getI() + ", " + c.getJ());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No es tu turno!");
        }
    }
    
    public void showMark(int i, int j, boolean hit) {
        CasillaTiro[][] casillas = getCasillas();
        if (hit) {
            System.out.println("Hit");
            casillas[i][j].setIcon(new ImageIcon("src/sources/explo.gif"));
            ActionListener action = new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                                if(e.getSource() instanceof Timer){
                                    Timer timer = (Timer) e.getSource();
                                    timer.stop();
                                }
                            }
                        };
            Timer timer = new Timer(2000, action);
            timer.start();
            casillas[i][j].setIcon(new ImageIcon("src/sources/mark.png"));
        } else {
            System.out.println("Fail");
            casillas[i][j].setIcon(new ImageIcon("src/sources/miss.png"));
        }
    }
    
}
