/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 *
 * @author wildg
 */
public class TableroTiro extends Tablero implements MouseListener{
    
    private CasillaTiro[][] casillas;
    
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
    
    public void colocarNave(Nave nave){
        this.naves.add(nave);
        this.add(nave, 1);
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
                    c.setState(b);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
    
}
