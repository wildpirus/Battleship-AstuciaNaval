/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

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
 *
 * @author Guacha
 */
public class ServerHandler {
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final Jugador jugador;
    private final MultiGamePanel panel;
    private final String nombre;

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
    }

    
    
    private final Thread gameThread = new Thread(new Runnable() {
        @Override
        public void run() {
            String serverResponse;            
            while(true) { //Esta condición debe cambiar
                try {
                    serverResponse = input.readUTF();
                    ServerHandler.this.interpretarRespuesta(serverResponse);
                } catch (IOException ex) {
                    Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });
    
    public void sigTurno() {
        
    }
    
    public void hitSent(int i, int j) {
        
    }
    
    public Nave hasHitDestroyed(int i, int j) {
        return null; //Debe cambiar
    }

    void shoot(int i, int j) {
        try {
            output.writeUTF("CLIENTE#DISPARO$" + i + "," + j);
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
            } else if (comando.contains("HIT")){
                
            }
        }
    }
    
    private Boolean getHit(int i, int j) {
        return jugador.recibirDisparo(i, j);
    }
    
}
