/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipografias;

import java.awt.Font;
import java.io.InputStream;

/**
 *
 * @author wildg
 */
public enum Fuente {
    MW ("ModernWarfare-8MM6z.ttf"),
    EuroStyle ("EurostileExtendedBlack.ttf"),
    Impact("impact.ttf");
    
    String path;
    private Fuente(String path){
        this.path = path;
    }
    
    public Font getFuente(int estilo, float tamaño){
        Font font;
        try {
            InputStream is =  getClass().getResourceAsStream(path);
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception ex) {
            System.err.println(path + " No se cargo la fuente");
            font = new Font("Arial", Font.PLAIN, 14);
        }
        Font tfont = font.deriveFont(estilo, tamaño);
        return tfont;
    }
}
