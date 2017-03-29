package com.innovate.himnario;

/**
 * Created by Joel on 28-Mar-17.
 */

public class Coro {

    int id;
    int orden;
    String nombre;
    String cuerpo;
    String tonalidad;
    String ton_alt;
    String vel_let;
    int tiempo;
    String audio;
    String partitura;
    String aut_musica;
    String aut_letra;
    String cita;
    String historia;
    String sName;
    String nuevo;

    public Coro() {

    }

    public Coro(int id, int orden, String nombre, String cuerpo, String tonalidad, String ton_alt, String vel_let, int tiempo,
                String audio, String partitura, String aut_musica, String aut_letra, String cita, String historia, String sName,
                String nuevo) {
        this.id = id;
        this.orden = orden;
        this.nombre = nombre;
        this.cuerpo = cuerpo;
        this.tonalidad = tonalidad;
        this.ton_alt = ton_alt;
        this.vel_let = vel_let;
        this.tiempo = tiempo;
        this.audio = audio;
        this.partitura = partitura;
        this.aut_musica = aut_musica;
        this.aut_letra = aut_letra;
        this.cita = cita;
        this.historia = historia;
        this.sName = sName;
        this.nuevo = nuevo;
    }
}
