/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import GUI.Display;
import battleship.Jugador;
import battleship.TableroFlota;
import battleship.TipoFlota;

/**
 * Clase para un jugador en una partida online.
 * @author Guacha
 */
public class JugadorOnline extends Jugador{

    private ServerHandler handler;
    private final TableroFlota miFlota;
    private final TableroTiroOnline tableroTiro;
    private boolean inTurno;
    private boolean inGame;
    
    /**
     * Constructor 
     */
    public JugadorOnline(){
        miFlota = new TableroFlota(this, TipoFlota.NORMAL);
        tableroTiro = new TableroTiroOnline(this);
        inTurno = false;
        inGame = true;
    }
    
    /**
     * Función para determinar si el jugador está en la partida.
     * @return 
     */
    public boolean isInGame() {
        return inGame;
    }
    
    /**
     * Método para enviar disparo.
     * @param i int coordenada vertical de la posición del disparo en la parrilla.
     * @param j int coordenada horizontal de la posición del disparo en la parrilla.
     */
    public void disparo(int i, int j) {
        if (inTurno) {
            handler.shoot(i,j);
        }
    }
    
    /**
     * Método para asignar controlador de la partida online al jugador.
     * @param handler ServerHandler que controla una partida de multijugador online.
     */
    public void setHandler(ServerHandler handler) {
        this.handler = handler;
    }
    
    /**
     * Función para obtener el tablero de tiro del jugador. 
     * @return  TableroTiroOnline tablero de tiro del jugador.
     */
    @Override
    public TableroTiroOnline getTableroTiro() {
        return tableroTiro;
    }
    
    /**
     * Asignar turno a este jugador
     */
    void setInTurno() {
        this.inTurno = true;
    }
    
    /**
     * Fución para saber si este jugador está en turno.
     * @return 
     */
    boolean inTurno(){
        return inTurno;
    }
    
    /**
     * Método para recibir un disparo.
     * @param i int coordenada vertical de la posición del disparo en la parrilla.
     * @param j int coordenada horizontal de la posición del disparo en la parrilla.
     * @param hit boolean, true si le dió a una nave, false si falló.
     */
    public void updateHit(int i, int j, boolean hit) {
        tableroTiro.showMark(i, j, hit);
    }
    
    /**
     * Método para finalizar el turno de este jugador.
     */
    void finTurno() {
        Display.showMessageDialog(this.tableroTiro, "Has fallado, se ha terminado tu turno :v"); //PlaceHolder
        this.inTurno = false;
        handler.declaraFinTurno();
    }
    
    /**
     * Método para finalizar la partida.
     */
    void finishGame() {
        this.inGame = false;
        handler.exit();
    }
}
