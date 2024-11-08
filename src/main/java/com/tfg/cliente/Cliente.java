package com.tfg.cliente;

import com.tfg.biblioteca.Biblioteca.Mensaje;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Clase Cliente del cliente
 * 
 * @author peblo
 */
public class Cliente {

    private Socket cliente;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public SimpleEntry<UUID, String> usuario;
    private MensajeListener mensajeListener;
    private MensajeListener amigoListener;
    private LinkedList<Mensaje> historialTemporal;

    public Cliente() {
        try {
            this.cliente = new Socket("127.0.0.1", 9999);
            this.usuario = new SimpleEntry<>(null, null);
            this.out = new ObjectOutputStream(cliente.getOutputStream());
            this.out.flush();
            this.in = new ObjectInputStream(cliente.getInputStream());
            System.out.println("Cliente inicializado correcamente.");
        } catch (IOException e) {
            System.err.println("Error al inicializar el cliente: " + e.getMessage());
            cerrarConexion();
        }
    }
    /**
     * El metodo devuelve un booleano. True si el inicio de sesion
     * es exitoso y false en caso contratio.
     * 
     * @param usuario
     * @param contrasena
     * @return devuelve una respuesta del servidor booleana. True si el inicio
     * de sesion fue exitoso y false en caso contrario
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    protected boolean iniciarSesion(String usuario, String contrasena) throws IOException, ClassNotFoundException {
        boolean inicio_exitoso = false;

        // |--> API
        // Enviar solicitud de login
        out.writeObject("login");
        out.writeObject(usuario);
        out.writeObject(contrasena);
        out.flush();

        // <--| API
        // Leer respuesta del servidor
        String respuesta = (String) in.readObject();
        UUID respuesta_uuid = (UUID) in.readObject();

        switch (respuesta) {
            case "OK" -> {
                this.usuario = new SimpleEntry<>(respuesta_uuid, usuario);
                System.out.println("Inicio de sesión exitoso.");

                //Enviar nombre usuario al servidor despues de la autenticacion
                out.writeObject("setNombreUsuario");
                out.writeObject(usuario);
                out.flush();
                inicio_exitoso = true;
            }
            case "OKNO" -> {
                System.out.println("Credenciales incorrectas. Intente de nuevo.");
                inicio_exitoso = false;
            }
            default ->
                System.out.println("Fallo en la lógica");
        }

        return inicio_exitoso;
    }

    /**
     * Método usado para registrar un usuario en la base de datos proporcionando
     * el usuario y la contrasena
     * 
     * @param usuario
     * @param contrasena
     * @return devuelve una respuesta del servidor booleana. True si el inicio
     * de sesion fue exitoso y false en caso contrario
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    protected boolean registrarUsuario(String usuario, String contrasena) throws IOException, ClassNotFoundException {

        //boolean toRet = false;
        // Enviar solicitud de registro
        out.writeObject("registro");
        out.writeObject(usuario);
        out.writeObject(contrasena);
        out.flush();

        // Leer respuesta del servidor
        String accion = (String) in.readObject();
        boolean respuestaServidor = (boolean) in.readObject();
        if (respuestaServidor) {
            System.out.println("Registro exitoso. Ahora puede iniciar sesión.");
            //toRet = true;
        } else {
            System.out.println("Registro fallido. El usuario ya existe.");
        }
        return respuestaServidor;
    }
    
    /**
     * Solicita al servidor que se agregue a un amigo
     * 
     * @param amigo
     * @param uuidUsuario 
     */
    protected void anadirAmigo(String amigo, UUID uuidUsuario) {

        try {
            // Enviar solicitud a la API para añadir un amigo
            out.writeObject("anadirAmigo");
            out.writeObject(amigo);
            out.writeObject(uuidUsuario);
            out.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Solicita la lista de amigos de un usuario. En este caso de le pasa
     * por parámetro el uuid propio del usuario emisor.
     * 
     * @param uuidUsuario
     * @return devuelve la lista de amigos
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    protected List<String> solicitarListaAmigos(UUID uuidUsuario) throws IOException, ClassNotFoundException {

        // Enviar solicitud a la API para solicitar la lista de amigos
        out.writeObject("solicitarListaAmigos");
        out.writeObject(uuidUsuario);
        out.flush();

        String accion = (String) in.readObject();
        List<String> usuarios = (List<String>) in.readObject();

        return usuarios;
    }
    
    /**
     * Cierra la conexion y cierra corrextamente los recursos
     */
    public void cerrarConexion() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (cliente != null && !cliente.isClosed()) {
                cliente.close();
            }
        } catch (IOException ex) {
            System.err.println("Error al cerrar los recursos: " + ex.getMessage());
        }
    }
    
    /**
     * Metodo que pide la desconexion del usuario al servidor
     * 
     * @param usuario 
     */
    public void enviarDesconexion(String usuario) {
        try {
            out.writeObject("desconectar"); // Enviar mensaje de desconexión
            out.writeObject(usuario);
            out.flush();
            System.out.println("//////DESCONECTAR CLIENTE///////////");
        } catch (IOException e) {
            System.err.println("Error al enviar mensaje de desconexión: " + e.getMessage());
        }
    }
    
    /**
     * Método que envia un mensaje de texto al servidor.
     * 
     * @param mensaje
     * @param emisor
     * @param destinatario 
     */
    public void enviarMensaje(String mensaje,String emisor, String destinatario) {
        try {
            out.writeObject("mensaje");
            out.writeObject(emisor);
            out.writeObject(destinatario);
            out.writeObject(new Mensaje(usuario, mensaje));
            out.flush();

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Solicita el historial de una conversacion entre dos personas
     * 
     * @param usuario1
     * @param usuario2
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    protected void solicitarHistorial(String usuario1, String usuario2) throws IOException, ClassNotFoundException {
        out.writeObject("solicitarHistorial");
        out.writeObject(usuario1);
        out.writeObject(usuario2);
        out.flush();
    }
    
    /**
     * Metodo set para el listener del mensaje
     * 
     * @param listener 
     */
    public void setMensajeListener(MensajeListener listener) {
        this.mensajeListener = listener;
    }

    /**
     * Metodo set para el listener de anadirAmigo
     * 
     * @param listener 
     */
    public void setAmigoListener(MensajeListener listener) {
        this.amigoListener = listener;
    }

    /**
     * Método principal para gestion de respuestas del servidor. Dependiendo
     * del tipo de respuesta que sea, se hace unas cosas u otras
     */
    public void recibirMensajes() {
        try {
            while (true) {

                String tipoMensaje = (String) in.readObject();

                switch (tipoMensaje) {
                    case "mensaje" -> {
                        Mensaje mensaje = (Mensaje) in.readObject();
                        if (mensajeListener != null) {
                            mensajeListener.onMensajeRecibido(mensaje);
                        }
                    }
                    case "amigoAgregado" -> {
                        String amigoAgregado = (String) in.readObject();
                        if (amigoListener != null && !amigoAgregado.isEmpty()) {
                            amigoListener.onAmigoAgregado(amigoAgregado);
                        }
                    }
                    case "historialRespuesta" -> {
                        historialTemporal = (LinkedList<Mensaje>) in.readObject();
                        if (mensajeListener != null) {
                            mensajeListener.onHistorialRecibido(historialTemporal);
                        }
                    }
                    default -> {
                        System.err.println("Tipo de mensaje desconocido: " + tipoMensaje);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
        }
    }

}
