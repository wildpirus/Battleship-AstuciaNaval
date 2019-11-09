/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author wildg
 */
public abstract class Tablero extends JLayeredPane {
    
    protected Dimension size;
    protected JLabel fondo;
    protected JPanel gridPanel;
    protected ArrayList<Nave> naves;
    protected Jugador jugador;
    

    public Tablero(Jugador jugador) {
        this.jugador = jugador;
        this.size = new Dimension(500,500);
        this.setMinimumSize(size);
        this.setPreferredSize(this.size);
        naves = new ArrayList();
        
        this.fondo = new JLabel();
        this.fondo.setPreferredSize(this.size);
        this.fondo.setIcon(new ImageIcon("src/sources/cuadro500.png"));
        this.add(fondo, new Integer(0));
        fondo.setVisible(true);
        fondo.setSize(this.size);
        
        
        this.gridPanel = new JPanel();
        this.gridPanel.setPreferredSize(this.size);
        this.add(gridPanel, new Integer(2));
        this.gridPanel.setVisible(true);
        this.gridPanel.setSize(this.size);
        this.gridPanel.setOpaque(false);
        this.gridPanel.setLayout(new GridLayout(10, 10));
    }
}
