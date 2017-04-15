package com.innovate.himnario;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by Joel on 28-Mar-17.
 */

public class Coro implements Parcelable{

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

    public Coro(DataSnapshot snapshot){
        id = Integer.parseInt(snapshot.getKey());

        Coro coro = snapshot.getValue(Coro.class);
        coro.id = id;
    }

    public Coro(Parcel in) {
        String[] data = new String[16];
        in.readStringArray(data);
        id = Integer.parseInt(data[0]);
        orden = Integer.parseInt(data[1]);
        nombre = data[2];
        cuerpo = data[3];
        ton = data[4];
        ton_alt = data[5];
        vel_let = data[6];
        tiempo = Integer.parseInt(data[7]);
        audio = data[8];
        partitura = data[9];
        aut_mus = data[10];
        aut_let = data[11];
        cita = data[12];
        historia = data[13];
        sName = data[14];
        nuevo = data[15];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                Integer.toString(id),
                Integer.toString(orden),
                nombre,
                cuerpo,
                ton,
                ton_alt,
                vel_let,
                Integer.toString(tiempo),
                audio,
                partitura,
                aut_mus,
                aut_let,
                cita,
                historia,
                sName,
                nuevo
        });
    }

    public static final Parcelable.Creator<Coro>CREATOR = new Parcelable.Creator<Coro>() {

        @Override
        public Coro createFromParcel(Parcel source) {
            return new Coro(source);
        }

        @Override
        public Coro[] newArray(int size) {
            return new Coro[size];
        }

    };
}
