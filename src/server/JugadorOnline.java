/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import battleship.Jugador;
import battleship.TableroFlota;
import battleship.TipoFlota;
import javax.swing.JOptionPane;

/**
 *
 * @author Guacha
 */
public class JugadorOnline extends Jugador{

    private ServerHandler handler;
    private final TableroFlota miFlota;
    private final TableroTiroOnline tableroTiro;
    private boolean inTurno;
    
    public JugadorOnline(){
        miFlota = new TableroFlota(this, TipoFlota.NORMAL);
        tableroTiro = new TableroTiroOnline(this);
        inTurno = false;
    }
    
    public void disparo(int i, int j) {
        if (inTurno) {
            handler.shoot(i,j);
        }
    }
    
    public void setHandler(ServerHandler handler) {
        this.handler = handler;
    }
    
    @Override
    public TableroTiroOnline getTableroTiro() {
        return tableroTiro;
    }

    void setInTurno() {
        this.inTurno = true;
    }
    
    boolean inTurno(){
        return inTurno;
    }
    

    public void updateHit(int i, int j, boolean hit) {
        tableroTiro.showMark(i, j, hit);
    }

    void finTurno() {
        JOptionPane.showMessageDialog(null, "Has fallado, se ha terminado tu turno :v"); //PlaceHolder
        this.inTurno = false;
        handler.declaraFinTurno();
    }
}
