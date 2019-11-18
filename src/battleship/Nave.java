/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

/**
 * Clase para las fichas de la nave.
 * @author wildg
 */
public class Nave extends JPanel {
    private static int id=0;
    private Point point;
    private Point pointInGrid;
    private final int[][] dimensiones;
    private final boolean[] health;
    private JLabel icon;
    private boolean sw;
    private final TipoNave type;
    
    /**
     * Constructor.
     * @param size tamaño.
     * @param point Point ubicación en el panel
     * @param pointInGrid Point ublicacion en el tablero de Flota.
     * @param type TipoNave.
     */
    public Nave(int size, Point point, Point pointInGrid, TipoNave type) {
        this.point = point;
        this.pointInGrid = pointInGrid;
        this.dimensiones = cloneMatrix(type.getDimensiones());
        this.type = type;
        health = new boolean[getAbsDimension().height];
        this.setMinimumSize(new Dimension(size,size*getAbsDimension().height));
        this.setBounds(point.x, point.y, size,size*getAbsDimension().height);
        this.setOpaque(false);
        this.setLayout(new OverlayLayout(this));
        this.icon = new JLabel();
        this.add(icon);
        this.icon.setIcon(new ImageIcon("src/sources/"+type.toString()+"0.png"));
        this.sw = false;
        this.setName(type.toString()+id);
        id++;
    }
    
    /**
     * Método para crear una copia de una matriz.
     * @param m int[][] matriz
     * @return int[][] matriz copiada en una nueva dirección de memoria.
     */
    private static int[][] cloneMatrix(int[][] m){
        int[][] copy = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            System.arraycopy(m[i], 0, copy[i], 0, m[i].length);
        }
        return copy;
    }
    
    /**
     * Función para obtener area de la nave en el tablero mi flota. 
     * @return Rectangle que representa el area ocupada por la nave en el 
     * tablero mi flota.
     */
    private Rectangle getAreaInGrid(){
        Dimension d = getAbsDimension();
        return new Rectangle(this.pointInGrid.x,this.pointInGrid.y,d.width,d.height);
    }
    
    /**
     * Función para pbtener la dimensión absoluta de la nave.
     * @return Dimension que representa el area ocupada por la nave.
     */
    private Dimension getAbsDimension(){
        int[][] p = this.dimensiones;
        int[] dims = {0, 0};
        for (int[] pos : p) {
            if (pos[0] > dims[0]) {
                dims[0] = pos[0];
            }
            if (pos[1] > dims[1]) {
                dims[1] = pos[1];
            }
        }
        return new Dimension(dims[1] +1, dims[0] +1);
    }
    
    /**
     * Fución para Point ubicación en el panel
     * @return Ponit con ubicación en el panel.
     */
    public Point getPoint() {
        return point;
    }
    
    /**
     * Método para asignar ubicación en el panel.
     * @param point Point ubicación en el panel.
     */
    public void setPoint(Point point) {
        this.point = point;
    }
    
    /**
     * Metodo para asigna ubicación en tablero de flota.
     * @param pointInGrid ubicación en tablero de flota.
     */
    public void setPointInGrid(Point pointInGrid) {
        this.pointInGrid = pointInGrid;
    }

    /**
     * Función para obtener ubicación en tablero de flota
     * @return 
     */
    public Point getPointInGrid() {
        return pointInGrid;
    }
    
    /**
     * Función para obtener matriz de dimensiones.
     * @return int[][] matriz de dimensiones.
     */
    public int[][] getDimensiones() {
        return dimensiones;
    }
    
    /**
     * Método para enviar disparo en el Point dado.
     * @param pG Point donde ocurrió el disparo.
     */
    public void isHit(Point pG){
        this.health[(pG.x-this.pointInGrid.x)+(pG.y-this.pointInGrid.y)]=true;
    }
    
    /**
     * Función para saber si un disparo destruyó una nave.
     * @return true si destruyó, false si no.
     */
    public boolean isDestroyed(){
        for (boolean b : health) {
            if(!b){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Método para girar una nave 90 grados durante la formación de la flota
     */
    public void girar(){
        if(!sw){
            this.removeAll();
            this.setMinimumSize(new Dimension(this.getHeight(),this.getWidth()));
            this.setSize(this.getHeight(),this.getWidth());
            this.icon = new JLabel();
            this.add(icon);
            this.icon.setIcon(new ImageIcon("src/sources/"+type.toString()+"1.png"));
            sw=true;
        }else {
            this.removeAll();
            this.setMinimumSize(new Dimension(this.getHeight(),this.getWidth()));
            this.setSize(this.getHeight(),this.getWidth());
            this.icon = new JLabel();
            this.add(icon);
            this.icon.setIcon(new ImageIcon("src/sources/"+type.toString()+"0.png"));
            sw=false;
        }
        for (int[] p : dimensiones) {
            int t = p[1];
            p[1]=p[0];
            p[0]=t;
        }
    }
}
