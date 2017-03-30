package com.innovate.himnario;

/**
 * Created by Joel on 28-Mar-17.
 */

public class Coro {

    int id;
    int orden;
    String nombre;
    String cuerpo;
    String ton;
    String ton_alt;
    String vel_let;
    int tiempo;
    String audio;
    String partitura;
    String aut_mus;
    String aut_let;
    String cita;
    String historia;
    String sName;
    String nuevo;

    public Coro() {

    }

    public Coro(int id, int orden, String nombre, String cuerpo, String ton, String ton_alt, String vel_let, int tiempo,
                String audio, String partitura, String aut_mus, String aut_let, String cita, String historia, String sName,
                String nuevo) {
        this.id = id;
        this.orden = orden;
        this.nombre = nombre;
        this.cuerpo = cuerpo;
        this.ton = ton;
        this.ton_alt = ton_alt;
        this.vel_let = vel_let;
        this.tiempo = tiempo;
        this.audio = audio;
        this.partitura = partitura;
        this.aut_mus = aut_mus;
        this.aut_let = aut_let;
        this.cita = cita;
        this.historia = historia;
        this.sName = sName;
        this.nuevo = nuevo;
    }
}
