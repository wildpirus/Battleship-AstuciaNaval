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
 *
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
    
    private static int[][] cloneMatrix(int[][] m){
        int[][] copy = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            System.arraycopy(m[i], 0, copy[i], 0, m[i].length);
        }
        return copy;
    }
    
    private Rectangle getAreaInGrid(){
        Dimension d = getAbsDimension();
        return new Rectangle(this.pointInGrid.x,this.pointInGrid.y,d.width,d.height);
    }
    
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

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setPointInGrid(Point pointInGrid) {
        this.pointInGrid = pointInGrid;
    }

    public Point getPointInGrid() {
        return pointInGrid;
    }

    public int[][] getDimensiones() {
        return dimensiones;
    }
    
    public void isHit(Point pG){
        this.health[(pG.x-this.pointInGrid.x)+(pG.y-this.pointInGrid.y)]=true;
    }
    
    public boolean isDestroyed(){
        for (boolean b : health) {
            if(!b){
                return false;
            }
        }
        return true;
    }
    
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
