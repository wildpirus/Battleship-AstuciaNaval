/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import battleship.Jugador;
import battleship.TableroFlota;
import battleship.TipoFlota;

/**
 *
 * @author Guacha
 */
public class JugadorOnline extends Jugador{

    private final ServerHandler handler;
    private final TableroFlota miFlota;
    private final TableroTiroOnline tableroTiro;
    private boolean inTurno;
    
    public JugadorOnline(ServerHandler handler){
        this.handler = handler;
        miFlota = new TableroFlota(this, TipoFlota.NORMAL);
        tableroTiro = new TableroTiroOnline(this);
    }
    
    public void disparo(int i, int j) {
        if (inTurno) {
            handler.shoot(i,j);
        }
    }
    
    @Override
    public TableroTiroOnline getTableroTiro() {
        return tableroTiro;
    }

    void setInTurno() {
        this.inTurno = true;
    }
    

}
