package com.example.amigos.model.room.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "llamadas",
        foreignKeys = @ForeignKey(
                entity = Amigo.class,
                parentColumns = "id",
                childColumns = "idamigo",
                onDelete = ForeignKey.RESTRICT))

public class Llamada {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "idamigo")
    private long idamigo;

    @NonNull
    @ColumnInfo(name = "fechallamada")
    private String fechallamada;

    public Llamada(long idamigo, String fechallamada) {
        this.idamigo = idamigo;
        this.fechallamada = fechallamada;
    }

    public Llamada(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdamigo() {
        return idamigo;
    }

    public void setIdamigo(long idamigo) {
        this.idamigo = idamigo;
    }

    @NonNull
    public String getFechallamada() {
        return fechallamada;
    }

    public void setFechallamada(@NonNull String fechallamada) {
        this.fechallamada = fechallamada;
    }

    @Override
    public String toString() {
        return id + " | idamigo:" + idamigo + " | fecha:" + fechallamada;
    }
}