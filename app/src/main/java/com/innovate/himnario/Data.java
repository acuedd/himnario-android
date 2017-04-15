package com.innovate.himnario;

import java.util.ArrayList;

/**
 * Created by Joel on 13-Apr-17.
 */

public class Data {

    private static ArrayList<Coro> listaCoros;

    public Data() {

    }

    public void setListaCoros(ArrayList<Coro> listaCoros) {
        this.listaCoros = listaCoros;
    }

    public ArrayList<Coro> getListaCoros() {
        return listaCoros;
    }
}
