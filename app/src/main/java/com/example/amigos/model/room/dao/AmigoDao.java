package com.example.amigos.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.amigos.model.room.pojo.Amigo;
import com.example.amigos.model.room.pojo.ContadorLlamadas;

import java.util.List;

@Dao
public interface AmigoDao {

    @Delete
    void delete(Amigo friend);

    @Query("DELETE FROM amigos where id = :id")
    int delete(long id);

    @Query("select * from amigos where id = :id")
    Amigo get(long id);

    @Query("select * from amigos order by id")
    LiveData<List<Amigo>> getAll();

    @Query("select a.id,a.nombre,a.fecha,a.telefono,count(l.idamigo) count from amigos a left join llamadas l on a.id = l.idamigo group by a.id,a.nombre,a.fecha,a.telefono order by nombre")
    LiveData<List<ContadorLlamadas>> getAllAmigos();

    @Query("update amigos set nombre = :nombrenuevo where id = :id")
    int updateNombre(String nombrenuevo, int id);

    @Query("update amigos set telefono = :telefononuevo where id = :id")
    int updateTelefono(String telefononuevo, int id);

    @Query("update amigos set fecha = :fechanuevo where id = :id")
     int updateFecha(String fechanuevo, int id);


    @Insert
    long insert(Amigo friend);

    @Update
    int update(Amigo friend);
}
