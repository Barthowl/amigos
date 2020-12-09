package com.example.amigos.model.room.pojo;

import androidx.room.Embedded;

public class AmigoLlamada {
    @Embedded
    private Amigo amigo;

    @Embedded(prefix = "l_")
    private Llamada llamada;

    public Amigo getAmigo() {
        return amigo;
    }

    public void setAmigo(Amigo amigo) {
        this.amigo = amigo;
    }

    public Llamada getLlamada() {
        return llamada;
    }

    public void setLlamada(Llamada llamada) {
        this.llamada = llamada;
    }

    @Override
    public String toString() {
        return "Amigo: " + amigo + " | Llamadas: " + llamada;
    }
}
