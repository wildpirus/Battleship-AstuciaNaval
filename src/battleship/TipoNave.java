/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

/**
 *
 * @author wildg
 */
public enum TipoNave {
    FRAGATA      (new int[][] {{0, 0}}),                        //1x1
    DESTRUCTOR   (new int[][] {{0, 0},{1, 0}}),                 //2x1
    ACORAZADO    (new int[][] {{0, 0},{1, 0},{2, 0}}),          //3x1
    SUBMARINO    (new int[][] {{0, 0},{1, 0},{2, 0}}),          //3x1
    PORTAAVIONES (new int[][] {{0, 0},{1, 0},{2, 0},{3, 0}});   //4x1
    
    private final int[][] dimensiones;

    private TipoNave(int[][] dimensiones) {
        this.dimensiones = dimensiones;
    }

    public int[][] getDimensiones() {
        return dimensiones;
    }
    
    
    
    
}
