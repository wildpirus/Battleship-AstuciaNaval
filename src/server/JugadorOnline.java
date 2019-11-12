/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import battleship.Jugador;
import battleship.TableroFlota;
import battleship.TableroTiro;
import battleship.TipoFlota;

/**
 *
 * @author Guacha
 */
public class JugadorOnline extends Jugador{

    private final ServerHandler handler;
    private final TableroFlota miFlota;
    private TableroTiro tableroTiro;
    
    public JugadorOnline(ServerHandler handler){
        this.handler = handler;
        miFlota = new TableroFlota(this, TipoFlota.NORMAL);
    }
    
    
    public boolean enviarDisparo() {
        return true;
    }
    
}
