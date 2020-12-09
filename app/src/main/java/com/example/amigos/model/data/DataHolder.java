package com.example.amigos.model.data;

import com.example.amigos.model.room.pojo.Amigo;

import java.util.ArrayList;
import java.util.List;

public class DataHolder {
     public final List<Amigo> lista = new ArrayList<Amigo>();

    private DataHolder() {}

    public static DataHolder getInstance() {
        if( instance == null ) {
            instance = new DataHolder();
        }
        return instance;
    }

    private static DataHolder instance;
}
