package com.tfg.cliente;

import com.tfg.biblioteca.Biblioteca.Mensaje;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Clase UICliente de Cliente
 * 
 * @author peblo
 */
public class UICliente extends javax.swing.JFrame implements MensajeListener {

    public static Cliente cliente;
    public static String destinatario;
    public static String nombre;

    private final DefaultListModel<String> amigosModel;

    public UICliente(Cliente cliente, String nombre) throws IOException, ClassNotFoundException {

        initComponents();

        this.cliente = cliente;
        this.nombre = nombre;
        this.cliente.setMensajeListener((MensajeListener) this);
        this.cliente.setAmigoListener((MensajeListener) this);

        //Crear el modelo de la lista de amigos
        amigosModel = new DefaultListModel<>();
        listaAmigos.setModel(amigosModel);
        etiquetaNombreUsuario.setText(nombre);

        //Solicitamos la lista de amigos al servidor
        actualizarListaAmigos(solicitarAmigos());

        //Inicializamos los listeners en un método aparte para no modificar el 
        //codigo generado automaticamente (codigo del autor)
        initListeners();
        System.out.println("UUID del cliente: " + cliente.usuario.getKey());
        //new Thread(cliente).start(); // Iniciar la lógica de comunicación en un hilo separado*/
        panelChat.setVisible(false);
    }

    /**
     * Método que conecta la interfaz con Cliente.java. Usa la llamada de 
     * Cliente.java para solicitar la lista de amigos
     * 
     * @return devuelve la lista de amigos del solicitante
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private List<String> solicitarAmigos() throws IOException, ClassNotFoundException {
        UUID usuarioId = cliente.usuario.getKey();
        return cliente.solicitarListaAmigos(usuarioId);
    }
    
    /**
     * Añade un elemento al modelo de la lista de amigos
     * 
     * @param amigos 
     */
    private void actualizarListaAmigos(List<String> amigos) {
        amigosModel.clear();
        for (String amigo : amigos) {
            amigosModel.addElement(amigo);
        }
    }
    /**
     * Método de la interfaz que llena la lista de amigos cuando llega
     * del servidor
     * 
     * @param listaAmigos 
     */
    @Override
    public void onAmigosRecibidos(List<String> listaAmigos) {
        SwingUtilities.invokeLater(() -> {
            amigosModel.clear();
            for (String amigo : listaAmigos) {
                amigosModel.addElement(amigo);
            }
        });
    }
    
    /**
     * Método de la interfaz que refresca la lista de amigos cada vez que se añade
     * un nuevo amigo
     * 
     * @param nombreAmigo 
     */
    @Override
    public void onAmigoAgregado(String nombreAmigo) {
        SwingUtilities.invokeLater(() -> {
            if (!amigosModel.contains(nombreAmigo)) {
                amigosModel.addElement(nombreAmigo); // Añadir el amigo al modelo
                System.out.println("Amigo añadido: " + nombreAmigo);
                JOptionPane.showMessageDialog(this, "Amigo añadido exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al añadir amigo.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    
    /**
     * Metodo que muestra el mensaje en el area de texto cuando se recibe
     * 
     * @param mensaje 
     */
    @Override
    public void onMensajeRecibido(Mensaje mensaje) {
        SwingUtilities.invokeLater(() -> {
            if (mensaje.getRemitente().getValue().equals(destinatario)) {
                chatArea.append(mensaje.getRemitente().getValue() + ": " + mensaje.getMsg() + "\n");
            }
        });
    }

    /**
     * Metodo que muestra el historial en el area de texto cuando se recibe.
     * 
     * @param historial 
     */
    @Override
    public void onHistorialRecibido(LinkedList<Mensaje> historial) {
        SwingUtilities.invokeLater(() -> {
            for (Mensaje mensaje : historial) {
                chatArea.append(mensaje.getRemitente().getValue() + ": " + mensaje.getMsg() + "\n");
            }
            System.out.println("Historial mostrado en chatArea");
        });
    }

    /**
     * Metodo que inicia los listeners.
     */
    private void initListeners() {
        //Agregar el ListSelectionListener a la lista de amigos
        listaAmigos.addListSelectionListener(evt -> {
            if (!evt.getValueIsAdjusting()) {
                String selectedFriend = listaAmigos.getSelectedValue();
                if (selectedFriend != null) {
                    panelChat.setVisible(true); // Mostrar el panel de chat cuando se selecciona un amigo
                    destinatario = selectedFriend;

                    chatArea.setText("Conversación con: " + destinatario + "\n");

                    try {
                        cliente.solicitarHistorial(cliente.usuario.getValue(), destinatario);
                    } catch (IOException | ClassNotFoundException e) {
                        System.err.println("Error al cargar el historial: " + e.getMessage());
                    }

                } else {
                    panelChat.setVisible(false); // Ocultar el panel de chat si no hay amigo seleccionado
                }
                revalidate();
                repaint();
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarAplicacion();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelUsuario = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaAmigos = new javax.swing.JList<>();
        imagenPerfil = new javax.swing.JLabel();
        etiquetaNombreUsuario = new javax.swing.JLabel();
        botonAnadirAmigo = new javax.swing.JButton();
        panelChat = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        areaMensaje = new javax.swing.JTextField();
        enviarMensaje = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelUsuario.setBackground(new java.awt.Color(204, 255, 255));

        jScrollPane2.setViewportView(listaAmigos);

        imagenPerfil.setBackground(new java.awt.Color(0, 0, 0));
        imagenPerfil.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        etiquetaNombreUsuario.setText("Usuario");

        botonAnadirAmigo.setText("Añadir amigo");
        botonAnadirAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnadirAmigoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelUsuarioLayout = new javax.swing.GroupLayout(panelUsuario);
        panelUsuario.setLayout(panelUsuarioLayout);
        panelUsuarioLayout.setHorizontalGroup(
            panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsuarioLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botonAnadirAmigo)
                    .addGroup(panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imagenPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelUsuarioLayout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(etiquetaNombreUsuario))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panelUsuarioLayout.setVerticalGroup(
            panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUsuarioLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(imagenPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaNombreUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonAnadirAmigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        panelChat.setBackground(new java.awt.Color(204, 255, 204));

        chatArea.setColumns(20);
        chatArea.setRows(5);
        jScrollPane1.setViewportView(chatArea);

        areaMensaje.setText("Escribe el mensaje...");
        areaMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaMensajeActionPerformed(evt);
            }
        });

        enviarMensaje.setText("Enviar");
        enviarMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarMensajeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelChatLayout = new javax.swing.GroupLayout(panelChat);
        panelChat.setLayout(panelChatLayout);
        panelChatLayout.setHorizontalGroup(
            panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChatLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelChatLayout.createSequentialGroup()
                        .addComponent(areaMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(enviarMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panelChatLayout.setVerticalGroup(
            panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChatLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(areaMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enviarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void areaMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areaMensajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_areaMensajeActionPerformed
    
    /**
     * Controla la acción del boton enviar mensaje.
     * 
     * @param evt 
     */
    private void enviarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarMensajeActionPerformed
        enviarMensaje();
    }//GEN-LAST:event_enviarMensajeActionPerformed

    /**
     * Controla la accion del boton anadir amigo.
     * 
     * @param evt 
     */
    private void botonAnadirAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnadirAmigoActionPerformed
        String nombreAmigo = JOptionPane.showInputDialog(this, "Introduce el nombre de la persona que quieres agregar: ");

        if (nombreAmigo != null && !nombreAmigo.trim().isEmpty()) {
            cliente.anadirAmigo(nombreAmigo, cliente.usuario.getKey());
        } else if (nombreAmigo != null) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            cliente.anadirAmigo("", cliente.usuario.getKey());
        }
    }//GEN-LAST:event_botonAnadirAmigoActionPerformed
    
    /**
     * Metodo de enviar mensaje para conectar la interfaz con el Cliente.java.
     */
    private void enviarMensaje() {
        String mensaje = areaMensaje.getText().trim();
        if (!mensaje.isEmpty() && destinatario != null) {
            cliente.enviarMensaje(mensaje,cliente.usuario.getValue(), destinatario);
            chatArea.append(nombre+" : " + mensaje+"\n");
            areaMensaje.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un amigo para enviar el mensaje.", "Error", JOptionPane.WARNING_MESSAGE);
        }

    }

    /**
     * Cierra la aplicacion y los sockets de manera segura.
     */
    private void cerrarAplicacion() {
        try {
            if (cliente != null) {
                cliente.enviarDesconexion(nombre);
                cliente.cerrarConexion(); // Cierra la conexión si está abierta
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.exit(0); // Cierra completamente la aplicación
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField areaMensaje;
    private javax.swing.JButton botonAnadirAmigo;
    private javax.swing.JTextArea chatArea;
    private javax.swing.JButton enviarMensaje;
    private javax.swing.JLabel etiquetaNombreUsuario;
    private javax.swing.JLabel imagenPerfil;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaAmigos;
    private javax.swing.JPanel panelChat;
    private javax.swing.JPanel panelUsuario;
    // End of variables declaration//GEN-END:variables

}
