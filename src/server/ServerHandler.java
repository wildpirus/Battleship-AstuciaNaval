/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import GUI.Display;
import GUI.MultiGamePanel;
import battleship.Jugador;
import battleship.Nave;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase que controla una partida de multijugador online.
 * @author Guacha
 */
public class ServerHandler {
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final Jugador jugador;
    private final MultiGamePanel panel;
    private final String nombre;

    /**
     * Constructor.
     * @param socket Socket
     * @param input DataInputStream información de entradas.
     * @param output DataOutputStream información de salidas.
     * @param jugador Jugador que pertenece a la partida online.
     * @param nombre String nombre del jugador
     * @param panel MultiGamePanel donde se es visible el juego.
     */
    public ServerHandler(Socket socket, DataInputStream input, DataOutputStream output, Jugador jugador, String nombre, MultiGamePanel panel) {
        this.socket = socket;
        this.input = input;
        this.output = output;
        this.jugador = jugador;
        this.panel = panel;
        this.nombre = nombre;
        try {
            output.writeUTF(nombre);
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        panel.setNombre(nombre);
        gameThread.start();
    }

    
    /**
     * Hilo donde se desarrolla el juego.
     */
    private final Thread gameThread = new Thread(new Runnable() {
        @Override
        public void run() {
            String serverResponse;            
            JugadorOnline jug = (JugadorOnline) jugador;
            while(jug.isInGame()) { //Esta condición debe cambiar
                try {
                    serverResponse = input.readUTF();
                    ServerHandler.this.interpretarRespuesta(serverResponse);
                    System.out.println(serverResponse);
                } catch (IOException ex) {
                    Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });
    
    /**
     * Método para enviar disparo. 
     * @param i int coordenada vertical de la posición del disparo en la parrilla.
     * @param j int coordenada horizontal de la posición del disparo en la parrilla.
     */
    public void hitSent(int i, int j) {
        
    }
    
    /**
     * Función para enviar información de Nave si un disparo destruye una.
     * @param i int coordenada vertical de la posición del disparo en la parrilla.
     * @param j int coordenada horizontal de la posición del disparo en la parrilla.
     * @return Nave si se destruyo alguna, null si no destruyó.
     */
    public Nave hasHitDestroyed(int i, int j) {
        return null; //Debe cambiar
    }
    
    /**
     * Método para enviar disparo. 
     * @param i int coordenada vertical de la posición del disparo en la parrilla.
     * @param j int coordenada horizontal de la posición del disparo en la parrilla.
     */
    void shoot(int i, int j) {
        try {
            output.writeUTF("CLIENTE#DISPARO$" + i + "," + j);
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que interpreta los comandos que el servidor le envia y los aplica
     * a la partida.
     * @param serverResponse String con los comandos que envía el servido.
     */
    private void interpretarRespuesta(String serverResponse) {
        if (serverResponse.startsWith("SERVER#")) {
            String comando = serverResponse.substring(7);
            if (comando.startsWith("DISPARO")) {
                int i, j;
                i = Integer.parseInt(comando.substring(8, 9));
                j = Integer.parseInt(comando.substring(10));
                if (getHit(i, j)) {
                    try {
                        output.writeUTF("CLIENTE#CONFIRMHIT$" + i + "," + j);
                        if (this.jugador.flotaDestruida()) {
                            output.writeUTF("CLIENTE#DERROTA");
                            Display.showMessageDialog(panel, "Toda tu flota ha sido destruida, Has perdido!");
                            exit();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        output.writeUTF("CLIENTE#DENYHIT$" + i + "," + j);
                    } catch (IOException ex) {
                        Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if(comando.startsWith("INICIOTURNO")) {
                JugadorOnline j = (JugadorOnline) this.jugador;
                j.setInTurno();
                panel.setTableroTiro();
                Display.showMessageDialog(panel, "Es tu turno!");
            } else if (comando.contains("HIT")){
                JugadorOnline jug = (JugadorOnline) jugador;
                if (comando.contains("CONFIRM")) {
                    
                    int i = Integer.parseInt(comando.substring(11,12));
                    int j = Integer.parseInt(comando.substring(13));
                    jug.updateHit(i, j, true);
                } else {
                    int i = Integer.parseInt(comando.substring(8,9));
                    int j = Integer.parseInt(comando.substring(10));
                    jug.updateHit(i, j, false);
                    jug.finTurno();
                    panel.setTableroFlota();
                }
            } else if(comando.equals("VICTORIA")) {
                Display.showMessageDialog(panel, "Felicidades! Ganaste!!!");
                JugadorOnline j = (JugadorOnline) jugador;
                j.finishGame();
            } else if(comando.equals("DEFAULT")) {
                Display.showMessageDialog(panel, "El oponente se ha desconectado, ganas por default");
                JugadorOnline j = (JugadorOnline) jugador;
                j.finishGame();
            }
        }
    }
    
     /**
     * Función para recibir disparo.
     * @param i int coordenada vertical de la posición del disparo en la parrilla.
     * @param j int coordenada horizontal de la posición del disparo en la parrilla.
     * @return true si el disparo dió en una nave sino false.
     */
    private Boolean getHit(int i, int j) {
        return jugador.recibirDisparo(i, j);
    }
    
    /**
     * Método para avisar al servidor que el jugador ha colocado su flota 
     * correctamente.
     */
    public void setReady() {
        try {
            output.writeUTF("CLIENTE#ISREADY");
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Envia al servidor el comando para cambiar de turno.
     */
    public void declaraFinTurno() {
        try {
            output.writeUTF("CLIENTE#FINTURNO");
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método pára abandonar de la partida.
     */
    void exit() {
        panel.exit();
    }
    
    /**
     * Método pára terminar el socket abandonar de la partida.
     */
    public void endSocket() {
        try {
            this.socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
