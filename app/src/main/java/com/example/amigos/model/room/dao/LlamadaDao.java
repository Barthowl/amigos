package com.example.amigos.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.amigos.model.room.pojo.AmigoLlamada;
import com.example.amigos.model.room.pojo.Llamada;

import java.util.List;

@Dao
public interface LlamadaDao {

    @Query("select * from llamadas where id = :id")
    Llamada get(long id);

    @Query("select * from llamadas order by id")
    LiveData<List<Llamada>> getAll();

    @Insert
    long insert(Llamada llamada);

    @Query("delete from llamadas where idamigo = :id")
    void deleteLlamadas(long id);

    @Query("select count(*) from llamadas where idamigo = :id")
    LiveData<Long> getLlamadas(long id);

    @Query("select l.id l_id, l.idamigo l_idamigo, l.fechallamada l_fechallamada, a.* from llamadas l join amigos a on a.id = l.idamigo order by l.fechallamada desc")
    LiveData<List<AmigoLlamada>> getAllLlamadas();

    @Query("select l.id l_id, l.idamigo l_idamigo, l.fechallamada l_fechallamada, a.* from llamadas l join amigos a on a.id = l.idamigo where l.idamigo = :id " +
            "order by l.fechallamada desc")
    LiveData<List<AmigoLlamada>> getAllLlamadas(long id);

}
