/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;

/**
 *
 * @author wildg
 */
public class TableroFlota extends Tablero implements MouseListener, MouseMotionListener{
    
    private CasillaFlota[][] casillas;
    
    public TableroFlota(Jugador jugador, TipoFlota tipoFlota) {
        super(jugador);
        this.casillas = new CasillaFlota[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.casillas[i][j]= new CasillaFlota(i,j);
                this.gridPanel.add(casillas[i][j]);
            }
        }
        int i=0,j=0;
        for (TipoNave tiponave : tipoFlota.getFlota()) {
            Nave nave = new Nave(50,new Point(j*50,i), new Point(i,j), tiponave);
            this.naves.add(nave);
            this.add(nave, new Integer(1));
            nave.setVisible(true);
            nave.addMouseListener(this);
            nave.addMouseMotionListener(this);
            j++;
            if(j==9){
                j=0;
                i+=5;
            }
        }
    }
    
    /**
     * 
     * @param p matriz de la ficha
     * @param pos ubicacion primera casilla de la ficha
     * @param tab matriz de posiciones
     * @return 
     */
    private boolean cabeEnTablero(int[][] p,int[] pos, boolean[][]tab){
        for (int i = 0; i < p.length; ++i) {
            int[] pp = p[i];
            int x = pos[0] + pp[0];
            int y = pos[1] + pp[1];
            if (x < 0 || y < 0 || x > 9 || y > 9) {
                return false;
            }
            if (tab[x][y]) {
                return false;
            }
        }
        return true;
    }
    
    private void colocarNave(int[][] p,int[] pos,boolean[][] tab){
        for (int i = 0; i < p.length; ++i) {
            int[] pp = p[i];
            int x = pos[0] + pp[0];
            int y = pos[1] + pp[1];
            tab[x][y]=true;
        }
    }
    
    private boolean verificarFormacion(){
        boolean[][] tab = new boolean[this.casillas.length][this.casillas.length];
        for (Nave nave : this.naves) {
            if(cabeEnTablero(nave.getDimensiones(),new int[]{nave.getPointInGrid().x,nave.getPointInGrid().y},tab)){
                colocarNave(nave.getDimensiones(),new int[]{nave.getPointInGrid().x,nave.getPointInGrid().y},tab);
            }else {
                return false;
            }
        }
        return true;
    }
    
    public boolean colocarNaves(){
        if(verificarFormacion()){
            for (Nave nave : this.naves) {
                for (int i = 0; i < nave.getDimensiones().length; ++i) {
                    int[] pp = nave.getDimensiones()[i];
                    int x = nave.getPointInGrid().x + pp[0];
                    int y = nave.getPointInGrid().y + pp[1];
                    casillas[x][y].setNave(nave);
                }
            }
            this.nor();
            return true;
        }else {
            return false;
        }
    }
    
    public Rectangle getArea(int i, int j){
        JLabel t = this.casillas[i][j]; 
        return new Rectangle(t.getX(),t.getY(),t.getWidth(),t.getHeight());
    }
    
    public JLabel getPointInGrid(int i, int j){
        return this.casillas[i][j];
    }
    
    public void nor(){
        for (Nave nave : this.naves) {
            nave.removeMouseListener(this);
            nave.removeMouseMotionListener(this);
        }
    }
    
    public boolean recibirDisparo(int i,int j){
        return casillas[i][j].isHit();
    }
    
    public Nave hasDestroyed(int i,int j){
        return casillas[i][j].hasDestroyed();
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getComponent() instanceof Nave){
            Nave ship = (Nave) me.getComponent();
            ship.girar();
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent me) {
        if(me.getComponent() instanceof Nave){
            Nave ship = (Nave) me.getComponent();
            Point p = ship.getLocation();
            p.x+= 25;
            p.y+= 25;
            int i=0;
            int j=0;
            boolean swF= false;
            while(i<10 && !swF){
                j=0;
                while(j<10 && !swF){
                    if(this.getArea(i, j).contains(p)){
                        swF=true;
                    }else {
                        j++;
                    }
                }
                if(!swF){
                    i++;
                }
            }
            if(swF){
                ship.setLocation(this.getPointInGrid(i,j).getLocation());
                ship.setPoint(this.getPointInGrid(i,j).getLocation());
                ship.setPointInGrid(new Point(i,j));
            }else {
                ship.setLocation(ship.getPoint());
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        int xx = this.getLocationOnScreen().x+this.getBounds().x;
        int yy = this.getLocationOnScreen().y+this.getBounds().y;
        int p1 = me.getXOnScreen()-xx-(me.getComponent().getWidth()/2);
        int p2 = me.getYOnScreen()-yy-(me.getComponent().getHeight()/2);
        
        
        if(p1<0){
            p1=0;
        }else{
            if(p1+me.getComponent().getWidth()>this.getWidth()){
                p1=this.getWidth()-me.getComponent().getWidth();
            }
        }
        if(p2<0){
            p2=0;
        }else {
            if(p2+me.getComponent().getHeight()>this.getHeight()){
                p2=this.getHeight()-me.getComponent().getHeight();
            }
        }
        me.getComponent().setLocation(p1, p2);
    }

    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}

    @Override
    public void mouseMoved(MouseEvent me) {}

    public boolean flotaDestruida() {
        for (Nave nave : naves) {
            if(!nave.isDestroyed()) return false;
        }
        return true;
    }
}
