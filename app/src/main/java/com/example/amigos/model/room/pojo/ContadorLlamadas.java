package com.example.amigos.model.room.pojo;

import androidx.room.Embedded;

public class ContadorLlamadas {

    @Embedded
    private Amigo amigo;

    private long count;

    public Amigo getAmigo() {
        return amigo;
    }

    public void setAmigo(Amigo amigo) {
        this.amigo = amigo;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return amigo + " | " + count;
    }
}
