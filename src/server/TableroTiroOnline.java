/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import battleship.CasillaTiro;
import battleship.TableroTiro;
import java.awt.event.MouseEvent;

/**
 *
 * @author Guacha
 */
public class TableroTiroOnline extends TableroTiro{
    
    public TableroTiroOnline(JugadorOnline jugador) {
        super(jugador);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getComponent() instanceof CasillaTiro) {
            CasillaTiro c = (CasillaTiro) me.getComponent();
            if (!c.itHasBeenShot()) {
                JugadorOnline j = (JugadorOnline) this.jugador;
                j.disparo(c.getI(), c.getJ());
            }
        }
    }
    
}
