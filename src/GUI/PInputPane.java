/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JDialog;
import tipografias.Fuente;

/**
 * Clase que crear una ventana emergente para que el usuario ingrese un String.
 * @author wildg
 */
public class PInputPane extends javax.swing.JPanel {
    JDialog dialog;
    String de;
    /**
     * Constructor  para crear una ventana emergente para que el usuario ingrese
     * un String.
     */
    public PInputPane() {
        initComponents();
    }
    
    /**
     * Constructor  para crear una ventana emergente para que el usuario ingrese
     * un String.
     * @param dialog JDialog que contendrá al panel
     * @param mensaje String
     */
    public PInputPane(JDialog dialog,String mensaje) {
        initComponents();
        this.dialog = dialog;
        this.avisoLabel.setText(mensaje);
    }
    
    /**
     * Metodo para cambiar color de un componente.
     * @param c Componente al qe se le cabiará el color.
     */
    public void mEn(Component c){
        c.setBackground(Color.BLACK);
        c.setForeground(Color.white);
    }
    
    /**
     * Metodo para cambiar color de un componente.
     * @param c Componente al qe se le cabiará el color.
     */
    public void mEx(Component c){
        c.setBackground(Color.CYAN);
        c.setForeground(Color.BLACK);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        avisoLabel = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setBackground(java.awt.Color.gray);
        setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(java.awt.Color.green, 2, true), "Aviso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, Fuente.EuroStyle.getFuente(0, 10), java.awt.Color.green));

        avisoLabel.setFont(Fuente.EuroStyle.getFuente(0, 18));
        avisoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton1.setBackground(java.awt.Color.green);
        jButton1.setText("Aceptar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(avisoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(avisoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(this.jTextField1.getText().length()>0){
            de = this.jTextField1.getText();
            dialog.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        // TODO add your handling code here:
        mEn(jButton1);
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        // TODO add your handling code here:
        mEx(jButton1);
    }//GEN-LAST:event_jButton1MouseExited

    /**
     * Función para retornar lo que ingresó el usuario.
     * @return String con lo que ingresó el usuario.
     */
    public String getDe() {
        return de;
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel avisoLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
