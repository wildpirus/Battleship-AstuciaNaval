/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import GUI.LocalGamePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Clase para que controla una partida multijugador local
 * @author wildg
 */
public class AstuciaNaval {
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorEnTurno;
    private Jugador ganador;
    private LocalGamePanel pan;

    
    /**
     * Constructor.
     * @param jugador1 Jugador.
     * @param jugador2 Jugador.
     * @param pan LocalGamePanel donde se muestra la partida local.
     */
    public AstuciaNaval(Jugador jugador1, Jugador jugador2, LocalGamePanel pan) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugador1.setPartida(this);
        this.jugador2.setPartida(this);
        this.jugadorEnTurno = this.jugador1;
        this.ganador=null;
        this.pan = pan;
        this.pan.setTablero(1);
    }
    
    /**
     * Método para pasar turno de un jugador a otro.
     */
    public void sigTurno(){
        if(this.jugadorEnTurno==this.jugador1){
            this.jugadorEnTurno=this.jugador2;
            this.pan.cover();
            JOptionPane.showMessageDialog(null, "Turno jugador 2");
            this.pan.uncover();
            this.pan.setTablero(2);
        }else if(this.jugadorEnTurno==this.jugador2){
            this.jugadorEnTurno=this.jugador1;
            this.pan.cover();
            JOptionPane.showMessageDialog(null, "Turno jugador 1");
            this.pan.uncover();
            this.pan.setTablero(1);
        }
    }
    
    /**
     * Función para verificar que jugador tiene su flota destruida y si la hay
     * se asigna el ganador de la partida.
     * @return true si algun jugador tiene su flota destruida, si no flase.
     */
    public boolean gameOver(){
        if(jugador1.flotaDestruida()){
            ganador=jugador2;
            this.jugadorEnTurno=null;
            JOptionPane.showMessageDialog(null, "GANADOR JUGADOR 2");
            return true;
        }else if(jugador2.flotaDestruida()){
            ganador=jugador1;
            this.jugadorEnTurno=null;
            JOptionPane.showMessageDialog(null, "GANADOR JUGADOR 1");
            return true;
        }
        return false;
    }
    
    /**
     * Función para enviar un disparo desde un jugador a su contrincante.
     * @param i int coordenada vertical de la posición del disparo en la parrilla.
     * @param j int coordenada horizontal de la posición del disparo en la parrilla.
     * @param p Jugador que realiza el disparo.
     * @return Objeto tipo Boolean, true si le dió a una nave, false si falló, 
     * null si no es turno del jugador p.
     */
    public Boolean hitSended(int i, int j, Jugador p){
        if(this.jugadorEnTurno==p){
            Boolean r=null;
            if(p==jugador1){
                r = (jugador2.recibirDisparo(i, j));
            }else if(p==jugador2){
                r = (jugador1.recibirDisparo(i, j));
            }
            if(!r){
                this.jugadorEnTurno=null;
                ActionListener action = new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if(e.getSource() instanceof Timer){
                            Timer timer = (Timer) e.getSource();
                            timer.stop();
                            jugadorEnTurno=p;
                            sigTurno();
                        }
                    }
                };
                Timer timer = new Timer (2000, action);
                timer.start();
            }else if(r){
                ActionListener action = new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if(e.getSource() instanceof Timer){
                            Timer timer = (Timer) e.getSource();
                            timer.stop();
                            if(gameOver()){
                                JOptionPane.showMessageDialog(null, "SE ACABO");
                                pan.exit();
                            }
                        }
                    }
                };
                Timer timer = new Timer (2000, action);
                timer.start();
            }
            return r;
        }else{
            return null;
        }
    }
    
    
    /**
     * Función para saber si el disparo que envia un jugador destruyó una nave.
     * @param i int coordenada vertical de la posición del disparo en la parrilla.
     * @param j int coordenada horizontal de la posición del disparo en la parrilla.
     * @param p Jugador que realiza el disparo.
     * @return Nave si se destruyo alguna, null si no destruyó.
     */
    public Nave hasHitDistroyed(int i, int j, Jugador p){
        if(this.jugadorEnTurno==p){
            if(p==jugador1){
                return (jugador2.hasDistroyed(i, j));
            }else {
                return (jugador1.hasDistroyed(i, j));
            }
        }else{
            return null;
        }
    }
}
