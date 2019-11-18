/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import server.JugadorOnline;

/**
 * Clase para un jugador en una partida
 * @author wildg
 */
public class Jugador {
    private final TableroFlota miFlota;
    private final TableroTiro tableroTiro;
    private AstuciaNaval partida;
    
    /**
     * Constructor.
     */
    public Jugador() {
        this.miFlota = new TableroFlota(this,TipoFlota.NORMAL);
        this.tableroTiro = new TableroTiro(this);
        this.partida = null;
    }
    
    /**
     * Función para enviar disparo.
     * @param i int coordenada vertical de la posición del disparo en la parrilla.
     * @param j int coordenada horizontal de la posición del disparo en la parrilla.
     * @return Objeto tipo Boolean, true si le dió a una nave, false si falló, 
     * null si no es turno del jugador.
     */
    public Boolean enviarDisparo(int i,int j){
        Boolean r = this.partida.hitSended(i, j, this);
        Nave n = this.partida.hasHitDistroyed(i, j, this);
        if(n!=null){
            this.tableroTiro.add(n);
        }
        return r;
    }
    
    /**
     * Función para colocar naves en miFlota.
     * @return true si la formación no presenta errores, false si la foración 
     * presenta errores.
     */
    public boolean colocarNaves(){
        return this.miFlota.colocarNaves();
    }
    
    /**
     * Función para recibir disparo.
     * @param i int coordenada vertical de la posición del disparo en la parrilla.
     * @param j int coordenada horizontal de la posición del disparo en la parrilla.
     * @return true si el disparo dió en una nave sino false.
     */
    public boolean recibirDisparo(int i, int j){
        boolean shot = this.miFlota.recibirDisparo(i, j);
        if(this instanceof JugadorOnline){
            playSoundExplo(shot);
        }
        return shot;
    }
    
    /**
     * Función para enviar información de Nave si un disparo destruye una.
     * @param i int coordenada vertical de la posición del disparo en la parrilla.
     * @param j int coordenada horizontal de la posición del disparo en la parrilla.
     * @return Nave si se destruyo alguna, null si no destruyó.
     */
    public Nave hasDistroyed(int i, int j){
        return this.miFlota.hasDestroyed(i, j);
    }
    
    /**
     * Función para saber si se destruyó la flota.
     * @return true si la flota está destruido, sino false.
     */
    public boolean flotaDestruida(){
        return this.miFlota.flotaDestruida();
    }
    
    /**
     * Método para asignar partida al jugador.
     * @param partida AstuciaNaval parida.
     */
    public void setPartida(AstuciaNaval partida) {
        this.partida = partida;
    }
    
    /**
     * Función para obtener el Tablero de la flota del jugador.
     * @return  TableroFlota.
     */
    public TableroFlota getMiFlota() {
        return miFlota;
    }
    
    /**
     * Función para obtener el Tablero de tiros del jugador.
     * @return  TableroTiro.
     */
    public TableroTiro getTableroTiro() {
        return tableroTiro;
    }
    
    public void playSoundExplo(boolean b){
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
