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
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * Clase para representar el tablero de la tiro de un jugador online.
 * @author Guacha
 */
public class TableroTiroOnline extends TableroTiro{
    
    /**
     * Contructor
     * @param jugador Jugador dueño del tablero 
     */
    public TableroTiroOnline(JugadorOnline jugador) {
        super(jugador);
    }
    
    /**
     * Envia disparo.
     * @param me MouseEvent
     */
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
        }
    }
    
    /**
     * Método para mostrar resultados de un disparo en el tablero de tiro.
     * @param i int coordenada vertical de la posición del disparo en la parrilla.
     * @param j int coordenada horizontal de la posición del disparo en la parrilla.
     * @param hit boolean, true si le dió a una nave, false si falló.
     */
    public void showMark(int i, int j, boolean hit) {
        CasillaTiro[][] casillas = getCasillas();
        if (hit) {
            System.out.println("Hit");
            casillas[i][j].setIcon(new ImageIcon("src/sources/explo.gif"));
            ActionListener action = (ActionEvent e) -> {
                if(e.getSource() instanceof Timer){
                    Timer timer = (Timer) e.getSource();
                    timer.stop();
                    casillas[i][j].setIcon(new ImageIcon("src/sources/mark.png"));
                }
            };
            Timer timer = new Timer(2000, action);
            timer.start();
            //casillas[i][j].setIcon(new ImageIcon("src/sources/mark.png"));
        } else {
            System.out.println("Fail");
            casillas[i][j].setIcon(new ImageIcon("src/sources/miss.png"));
        }
        playSoundExplo(hit);
    }
    
    /**
     * Método para reproducir el disparo segun su tipo
     * @param b boolean true para acertado, false para no.
     */
    private void playSoundExplo(boolean b){
        Clip c;
        try {
            File m;
            if(b){
                m = new File("src/sources/Sounds/Blast.wav");
            }else {
                m = new File("src/sources/Sounds/miss.wav");
            }
            if(m.exists()){
                AudioInputStream a = AudioSystem.getAudioInputStream(m);
                c = AudioSystem.getClip();
                c.open(a);
                c.start();
            }else {
                System.out.println("ÑIEEEEEEEEEE");
            }
        }catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)   {

        } 
    }
    
}
