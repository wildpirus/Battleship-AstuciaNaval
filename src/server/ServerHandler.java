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
import java.net.Socket;


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
    }

    
    
    private final Thread gameThread = new Thread(() -> {
        String serverResponse;
        while(true) { //Esta condición debe cambiar
            //Recibir mensaje del servidor, procesarlo, y hacer la acción correspondiente
        }
    });
    
    public void sigTurno() {
        
    }
    
    public void hitSended(int i, int j) {
        
    }
    
    public Nave hasHitDestroyed(int i, int j) {
        return null; //Debe cambiar
    }
    
}
