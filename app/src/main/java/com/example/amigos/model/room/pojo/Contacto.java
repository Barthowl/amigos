package com.example.amigos.model.room.pojo;

import androidx.annotation.NonNull;

public class Contacto {

    private String nombre;
    private String fecha;
    private String telefono;


    public Contacto(@NonNull String nombre, String fecha,@NonNull String telefono) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.telefono = telefono;
    }

    public Contacto(){}

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre + " | " + fecha + " | " + telefono;
    }
}

