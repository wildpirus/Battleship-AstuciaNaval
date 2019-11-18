/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * Clase para el frame donde se visualizará la interfaz del juego.
 * @author wildg
 */
public class Display extends javax.swing.JFrame {
    private Clip c;
    private boolean soundSw;
    private final JPanel co;
    
    /**
     * Crea nuevo frame te tipo Display.
     */
    public Display() {
        try {
            URL resource = getClass().getResource("/sources/logo.png");
            BufferedImage image = ImageIO.read(resource);
            setIconImage(image);
        } catch (IOException e) {
            
        }
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("BattleShip");
        playBackgroundMusic();
        this.jLabel2.setIcon(new ImageIcon("src/sources/greyBG.png"));
        Welcome w = new Welcome(this);
        this.jPanel1.add(w);
        w.setVisible(true);
        w.setBounds(0, 0, this.jPanel1.getWidth(), this.jPanel1.getHeight());
        co = new JPanel();
        co.setBackground(Color.BLACK);
    }
    
    /**
     * Hace visible el panel principal (Home) del juego.
     */
    public void setHome(){
        this.jPanel1.removeAll();
        Welcome a = new Welcome(this);
        this.jPanel1.add(a);
        a.setBounds(0, 0, this.jPanel1.getSize().width, this.jPanel1.getSize().height);
        a.init();
        this.jPanel1.repaint();
    }
    
    /**
     * Hace visible el panel para seleccionar el modo de juego que se desa jugar.
     */
    public void setSelectGM(){
        this.jPanel1.removeAll();
        SelectGameMode a = new SelectGameMode(this);
        this.jPanel1.add(a);
        a.setBounds(0, 0, this.jPanel1.getSize().width, this.jPanel1.getSize().height);
        a.init();
        this.jPanel1.repaint();
    }
    
    /**
     * Hace visible el panel para una partida local.
     */
    public void setFormF(){
        this.jPanel1.removeAll();
        LocalGamePanel a = new LocalGamePanel(this);
        this.jPanel1.add(a);
        a.setBounds(0, 0, this.jPanel1.getSize().width, this.jPanel1.getSize().height);
        a.init();
        this.jPanel1.repaint();
    }
    
    /**
     * Hace visible el panel para una partida multijugador local.
     */
    public void setFormMulti(){
        int sel = Display.showConfirmationDialog(this, "Está a punto de conectarse a internet, Está seguro que desea continuar?");        
        if (sel == 0) {           
            this.jPanel1.removeAll();
            MultiGamePanel a = new MultiGamePanel(this);
            this.jPanel1.add(a);
            a.setBounds(0, 0, this.jPanel1.getSize().width, this.jPanel1.getSize().height);
            a.init();
            this.jPanel1.repaint();
        }


    }
    
    /**
     * Función que retorna el tamaño del panel en el Frame.
     * @return Dimension con el tamaño del panel en el frame.
     */
    public Dimension getPanelSize(){
        return this.jPanel1.getSize();
    }
    
    /**
     * Método para iniciar la reproducción de la música de fondo.
     */
    public final void playBackgroundMusic(){
        try {
            File m = new File("src/sources/Sounds/Background.wav");
            if(m.exists()){
                AudioInputStream a = AudioSystem.getAudioInputStream(m);
                c = AudioSystem.getClip();
                c.open(a);
                c.start();
                c.loop(Clip.LOOP_CONTINUOUSLY);
                soundSw=true;
            }else {
                System.out.println("No hay archivo de música");
            }
        }catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)   {

        } 
    }
    
    /**
     * Método que añade un panel negro al frame del juego para cubrir los tableros.  
     */
    public void cover(){
        this.jLayeredPane1.add(co, 2);
        co.setSize(this.jLayeredPane1.getSize());
    }
    
    
    /**
     * Método que quita el panel negro agregado al frame del juego para cubrir 
     * los tableros.  
     */
    public void uncover(){
        this.jLayeredPane1.remove(co);
        this.repaint();
    }
    
    /**
     * Función para determinar si la musica de fondo se está reproduciendo.
     * @return boolean true si se está reproduciendo, falso si no.
     */
    public boolean getSoundSw(){
        return this.soundSw;
    }
    
    /**
     * Método para reproducir o detener la música de fondo.
     */
    public void setBGSound(){
        if(this.soundSw){
            c.stop();
            soundSw=false;
        }else if(!this.soundSw){
            c.start();
            this.soundSw=true;
        }
    }
    
    /**
     * Método que desplega una ventana emergente para comunicarle un mensaje 
     * al usuario.
     * @param parentComponent Component con el que se alineará la ventana.
     * @param mensaje String que se va a mostrar
     */
    public static void showMessageDialog(Component parentComponent, String mensaje){
        Frame toUse = null;
        JDialog dialog = new JDialog(toUse, "");
        dialog.setUndecorated(true);
        Pane p = new Pane(dialog,mensaje);
        dialog.getContentPane().add(p);
        dialog.setModal(true);
        dialog.setResizable(false);
        dialog.pack();
        dialog.setLocationRelativeTo(parentComponent);
        dialog.setVisible(true);
    }
    
    /**
     * Función que desplega una ventana emergente para el usuario confirme o 
     * niegue una pregunta.
     * @param parentComponent Component con el que se alineará la ventana.
     * @param mensaje String que se va a mostrar.
     * @return int 0 si acepta, 1 si no.
     */
    public static int showConfirmationDialog(Component parentComponent, String mensaje){
        Frame toUse = null;
        JDialog dialog = new JDialog(toUse, "");
        dialog.setUndecorated(true);
        PPane p = new PPane(dialog,mensaje);
        dialog.getContentPane().add(p);
        dialog.setModal(true);
        dialog.setResizable(false);
        dialog.pack();
        dialog.setLocationRelativeTo(parentComponent);
        dialog.setVisible(true);
        return p.getDe();
    }
    
    /**
     * Función que desplega una ventana emergente para que el ingrese un 
     * dato de tipo String usuario.
     * @param parentComponent Component con el que se alineará la ventana.
     * @param mensaje String que se va a mostrar
     * @return String con la cadena que ingresó el usuario.
     */
    public static String showInputDialog(Component parentComponent, String mensaje){
        Frame toUse = null;
        JDialog dialog = new JDialog(toUse, "");
        dialog.setUndecorated(true);
        PInputPane p = new PInputPane(dialog,mensaje);
        dialog.getContentPane().add(p);
        dialog.setModal(true);
        dialog.setResizable(false);
        dialog.pack();
        dialog.setLocationRelativeTo(parentComponent);
        dialog.setVisible(true);
        return p.getDe();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        jLayeredPane1.setMinimumSize(new java.awt.Dimension(850, 570));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setMinimumSize(new java.awt.Dimension(850, 570));
        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );

        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jLayeredPane1);

        jPanel3.setMinimumSize(new java.awt.Dimension(850, 570));
        jPanel3.setOpaque(false);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Main para iniciar el juego.
     * @param args the command line arguments.
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Display().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
