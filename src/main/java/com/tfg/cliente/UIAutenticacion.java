package com.tfg.cliente;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Clase UIAutenticacion del Cliente
 * 
 * @author peblo
 */
public class UIAutenticacion extends javax.swing.JFrame {

    private Cliente cliente;
    private String nombre;

    public UIAutenticacion() {
        initComponents();
        cliente = new Cliente();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        panelAutenticacion = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        campoUsuario = new javax.swing.JTextField();
        campoContrasena = new javax.swing.JTextField();
        botonLogin = new javax.swing.JButton();
        botonRegistro = new javax.swing.JButton();
        iconos = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));

        panelAutenticacion.setBackground(new java.awt.Color(221, 221, 255));
        panelAutenticacion.setForeground(new java.awt.Color(255, 255, 255));

        logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\peblo\\Desktop\\TFG\\Cliente\\src\\main\\java\\com\\tfg\\cliente\\images\\logo.png")); // NOI18N

        campoUsuario.setFont(new java.awt.Font("Segoe Print", 0, 24)); // NOI18N
        campoUsuario.setForeground(new java.awt.Color(204, 204, 204));
        campoUsuario.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        campoUsuario.setText("Usuario");
        campoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoUsuarioActionPerformed(evt);
            }
        });

        campoContrasena.setFont(new java.awt.Font("Segoe Print", 0, 24)); // NOI18N
        campoContrasena.setForeground(new java.awt.Color(204, 204, 204));
        campoContrasena.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        campoContrasena.setText("Contraseña");
        campoContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoContrasenaActionPerformed(evt);
            }
        });

        botonLogin.setText("LOGIN");
        botonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLoginActionPerformed(evt);
            }
        });

        botonRegistro.setText("REGISTRO");
        botonRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistroActionPerformed(evt);
            }
        });

        iconos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconos.setIcon(new javax.swing.ImageIcon("C:\\Users\\peblo\\Desktop\\TFG\\Cliente\\src\\main\\java\\com\\tfg\\cliente\\images\\iconos.png")); // NOI18N

        javax.swing.GroupLayout panelAutenticacionLayout = new javax.swing.GroupLayout(panelAutenticacion);
        panelAutenticacion.setLayout(panelAutenticacionLayout);
        panelAutenticacionLayout.setHorizontalGroup(
            panelAutenticacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAutenticacionLayout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addGroup(panelAutenticacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo)
                    .addGroup(panelAutenticacionLayout.createSequentialGroup()
                        .addComponent(iconos, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addGroup(panelAutenticacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelAutenticacionLayout.createSequentialGroup()
                                .addComponent(botonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAutenticacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(campoContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(campoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        panelAutenticacionLayout.setVerticalGroup(
            panelAutenticacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAutenticacionLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAutenticacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAutenticacionLayout.createSequentialGroup()
                        .addComponent(iconos, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelAutenticacionLayout.createSequentialGroup()
                        .addComponent(campoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(campoContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(panelAutenticacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAutenticacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAutenticacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoUsuarioActionPerformed

    private void campoContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoContrasenaActionPerformed
    
    /**
     * Metodo que controla la accion del botón de login al ser pulsado
     * 
     * @param evt 
     */
    private void botonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLoginActionPerformed
        String usuario = campoUsuario.getText();
        nombre = usuario;
        String contrasena = campoContrasena.getText();

        try {
            if (cliente.iniciarSesion(usuario, contrasena)) {
                // Autenticación exitosa, cerramos esta ventana y abrimos UICliente
                this.dispose();  // Cierra UIAutenticacion
                new UICliente(cliente, nombre).setVisible(true);
                new Thread(() -> cliente.recibirMensajes()).start();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }//GEN-LAST:event_botonLoginActionPerformed

    /**
     * Metodo que controla la accion del boton de registro al ser pulsado
     * 
     * @param evt 
     */
    private void botonRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistroActionPerformed
        String usuario = campoUsuario.getText();
        String contrasena = campoContrasena.getText();

        try {
            if (cliente.registrarUsuario(usuario, contrasena)) {
                JOptionPane.showMessageDialog(this, "Registro exitoso", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrecta", "Exito", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }//GEN-LAST:event_botonRegistroActionPerformed

    /**
     * @param args the command line arguments
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UIAutenticacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIAutenticacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIAutenticacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIAutenticacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new UIAutenticacion().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonLogin;
    private javax.swing.JButton botonRegistro;
    private javax.swing.JTextField campoContrasena;
    private javax.swing.JTextField campoUsuario;
    private javax.swing.JLabel iconos;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel panelAutenticacion;
    // End of variables declaration//GEN-END:variables
}
