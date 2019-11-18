/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.Timer;


/**
 * Clase para representar el tablero de la tiro de un jugador.
 * @author wildg
 */
public class TableroTiro extends Tablero implements MouseListener{
    
    private CasillaTiro[][] casillas;
    
    /**
     * Contructor.
     * @param jugador Jugador dueño del tablero. 
     */
    public TableroTiro(Jugador jugador) {
        super(jugador);
        this.casillas = new CasillaTiro[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.casillas[i][j]= new CasillaTiro(i,j);
                this.gridPanel.add(casillas[i][j]);
                this.casillas[i][j].addMouseListener(this);
            }    
        }
    }
    
    /**
     * Método para agregar una nave enemiga destruida.
     * @param nave Nave enemiga destruida.
     */
    public void colocarNave(Nave nave){
        this.naves.add(nave);
        this.add(nave, 1);
    }
    
    /**
     * Función para obtener las casillas del panel.
     * @return CasillaTiro[][] casillas del panel.
     */
    public CasillaTiro[][] getCasillas() {
        return casillas;
    }

    /**
     * Envia disparo.
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getComponent() instanceof CasillaTiro){
            CasillaTiro c = (CasillaTiro) me.getComponent();
            if(!c.itHasBeenShot()){
                Boolean b = this.jugador.enviarDisparo(c.getI(), c.getJ());
                if(b!=null){
                    if(b){
                        c.setIcon(new ImageIcon("src/sources/explo.gif"));
                        ActionListener action = new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                                if(e.getSource() instanceof Timer){
                                    Timer timer = (Timer) e.getSource();
                                    timer.stop();
                                    c.setIcon(new ImageIcon("src/sources/mark.png"));
                                }
                            }
                        };
                        Timer timer = new Timer (2000, action);
                        timer.start();
                    }
                    c.setState(b);
                    playSoundExplo(b);
                }
            }
        }
    }
    
    /**
     * Overridable
     * @param me MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent me) {}
    
    /**
     * Overridable
     * @param me MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent me) {}
    
    /**
     * Overridable
     * @param me MouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent me) {}
    
    /**
     * Overridable
     * @param me MouseEvent
     */
    @Override
    public void mouseExited(MouseEvent me) {}
    
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
        }catch (Exception e)   {

        } 
    }
    
}
