package com.example.amigos.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.amigos.model.room.dao.AmigoDao;
import com.example.amigos.model.room.dao.LlamadaDao;
import com.example.amigos.model.room.pojo.Amigo;
import com.example.amigos.model.room.pojo.Llamada;

@Database(entities = {Amigo.class, Llamada.class}, version = 1, exportSchema = false)
public abstract class AmigoDB extends RoomDatabase {

    public abstract AmigoDao getAmigoDao();
    public abstract LlamadaDao getLlamadaDao();

    // singleton: la única instancia de esta clase
    // volatile: thread , variable con acceso preferente en las hebras
    private static volatile AmigoDB INSTANCE;


    public static synchronized AmigoDB getDB(final Context context){
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AmigoDB.class, "dbamigos").build();
        }
        return INSTANCE;
    }

}
