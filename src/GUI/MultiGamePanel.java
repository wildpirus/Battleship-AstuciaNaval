/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import battleship.AstuciaNaval;
import battleship.TableroFlota;
import battleship.TableroTiro;
import java.awt.Dimension;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import server.JugadorOnline;
import server.ServerHandler;

/**
 *
 * @author wildg
 */
public class MultiGamePanel extends javax.swing.JPanel  {
    
    JugadorOnline jugador;
    AstuciaNaval partida;
    Display d;
    TableroFlota f1;
    TableroTiro  t1;
    int ii;
    ServerHandler sh;
    
    public MultiGamePanel(Display d) {
        initComponents();
        this.d = d;
    }
    
    /**
     * Creates new form SetFormationPanel
     */
    public MultiGamePanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setOpaque(false);

        jLabel1.setBackground(new java.awt.Color(255, 153, 102));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(java.awt.Color.orange);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Jugador");

        jLayeredPane1.setBackground(new java.awt.Color(255, 204, 204));
        jLayeredPane1.setMinimumSize(new java.awt.Dimension(500, 500));
        jLayeredPane1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(51, 255, 102));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(java.awt.Color.orange);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Formación de Flota");

        jButton1.setBackground(java.awt.Color.orange);
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
            if(jugador.colocarNaves()){
                System.out.println("bien");
                this.jLayeredPane1.removeAll();
                ii++;
                this.jLabel2.setText("Tablero de tiro");
                this.jButton1.setVisible(false);
                setTablero();
                sh.setReady();
            }else {
                System.out.println("mal");
                JOptionPane.showMessageDialog(null, "Mal");
            }
            
        
    }//GEN-LAST:event_jButton1ActionPerformed

    public void init(){
        try {
            //Server Setup block
            Socket s = new Socket("localhost", 14999);
            DataInputStream input = new DataInputStream(s.getInputStream());
            DataOutputStream output = new DataOutputStream(s.getOutputStream());
            String nombre = JOptionPane.showInputDialog("Escriba su nombre");
            jugador = new JugadorOnline();
            sh = new ServerHandler(s, input, output, jugador, nombre, this);
            JOptionPane.showMessageDialog(null, "Conexión exitosa!"); //Placeholder
            
            initComponents();
            jugador.setHandler(sh);
            f1 = this.jugador.getMiFlota();
            f1.setSize(new Dimension(500,500));
            t1 = this.jugador.getTableroTiro();
            t1.setSize(new Dimension(500,500));
            this.jLabel1.setText(nombre);
            jLayeredPane1.add(f1,new Integer(0));
            ii=0;
            
            /*t1.setVisible(true);
            t1.setOpaque(true);*/
            jLayeredPane1.repaint();
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(null, "Conexión al servidor fallida: El servidor ingresado no existe"); //Placeholder
        }
    }
    
    public void setTablero(){
        this.jLayeredPane1.removeAll();
        this.jLayeredPane1.add(t1,0);
        this.jLabel1.setText("Jugador");
        this.repaint();
    }
    
    public void cover(){
        this.d.cover();
    }
    
    public void uncover(){
        this.d.uncover();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    // End of variables declaration//GEN-END:variables

    public void setNombre(String nombre) {
        jLabel1.setText(nombre);
    }
}
