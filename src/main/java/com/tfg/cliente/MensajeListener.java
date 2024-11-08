package com.tfg.cliente;

import com.tfg.biblioteca.Biblioteca.Mensaje;
import java.util.LinkedList;
import java.util.List;

public interface MensajeListener {
    void onMensajeRecibido(Mensaje mensaje);
    void onAmigosRecibidos(List<String> amigos);
    void onAmigoAgregado(String nombreAmigo);
    void onHistorialRecibido(LinkedList<Mensaje> historial);
}
