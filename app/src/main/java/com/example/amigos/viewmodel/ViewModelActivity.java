package com.example.amigos.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.amigos.model.Repository;
import com.example.amigos.model.room.pojo.Amigo;
import com.example.amigos.model.room.pojo.Contacto;
import com.example.amigos.model.room.pojo.ContadorLlamadas;
import com.example.amigos.model.room.pojo.Llamada;

import java.util.List;

public class ViewModelActivity extends AndroidViewModel {
    private Repository repository;

    public ViewModelActivity(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public Contacto getContacto () {
        return repository.getCurrentContacto();
    }

    public LiveData<List<Amigo>> getLista() {
        return repository.getLista();
    }

    public LiveData<List<Llamada>> getListaL() {
        return repository.getListaL();
    }

    public LiveData<Long> getListaLlamadasN(long i) {
        return repository.getLlamadasN(i);
    }

    public LiveData<List<ContadorLlamadas>> getAmigos() {
        return repository.getListaAmigos();
    }

    public void setContacto(Contacto contacto) {
        repository.setCurrentContacto(contacto);
    }

    public void insert(Amigo a) {
        repository.insert(a);
    }

    public void insertL(Llamada l){
        repository.insert(l);
    }

    public void delete(long id) {
        repository.delete(id);
    }

    public void updateNombre2(String nombre, int id){
        repository.updateNombre(nombre,id);
    }

    public void updateTelefono(String telefono, int id){
        repository.updateTelefono(telefono,id);
    }

    public void updateFecha(String fecha, int id){
        repository.updateFecha(fecha,id);
    }

}
