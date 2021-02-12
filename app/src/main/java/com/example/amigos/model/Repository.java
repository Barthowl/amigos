package com.example.amigos.model;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.amigos.model.room.AmigoDB;
import com.example.amigos.model.room.dao.AmigoDao;
import com.example.amigos.model.room.dao.LlamadaDao;
import com.example.amigos.model.room.pojo.Amigo;
import com.example.amigos.model.room.pojo.Contacto;
import com.example.amigos.model.room.pojo.ContadorLlamadas;
import com.example.amigos.model.room.pojo.Llamada;
import com.example.amigos.util.UtilThread;

import java.util.List;

public class Repository {

    private Contacto currentContacto;
    private AmigoDao amigoDao;
    private LlamadaDao llamadaDao;
    private GuardarObtencionLista guardar;

    private LiveData<List<Amigo>> liveAmigoList;
    private LiveData<List<Llamada>> liveLlamadaList;
    private LiveData<Long> liveLlamadaList2;
    private LiveData<List<ContadorLlamadas>> liveAmigoLlamadaList;
    private MutableLiveData<Long> liveAmigoInsertId = new MutableLiveData<>();
    private LiveData<List<Contacto>> liveContactoList;

    public Repository(Context context) {
        AmigoDB db = AmigoDB.getDB(context);
        amigoDao = db.getAmigoDao();
        llamadaDao = db.getLlamadaDao();
        liveAmigoList = amigoDao.getAll();
        this.guardar = new GuardarObtencionLista(context);
    }

    public Contacto getCurrentContacto() {
        return currentContacto;
    }

    public LiveData<List<Amigo>> getLista() {
        liveAmigoList = amigoDao.getAll();
        return liveAmigoList;
    }

    public LiveData<List<Llamada>> getListaL() {
        liveLlamadaList = llamadaDao.getAll();
        return liveLlamadaList;
    }

    public LiveData<Long> getLlamadasN(long i){
        liveLlamadaList2 = llamadaDao.getLlamadas(i);
        return liveLlamadaList2;
    }

    public LiveData<List<ContadorLlamadas>> getListaAmigos() {
        liveAmigoLlamadaList = amigoDao.getAllAmigos();
        return liveAmigoLlamadaList;
    }

    public void setCurrentContacto(Contacto currentContacto) {
        this.currentContacto = currentContacto;
    }

    public void insert(Amigo a) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    long id = amigoDao.insert(a);
                    liveAmigoInsertId.postValue(id);
                } catch (Exception e) {
                    liveAmigoInsertId.postValue(0l);
                }
            }
        });
    }

    public void insert(Llamada l) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                llamadaDao.insert(l);
            }
        });
    }

    public void delete(long id){
        new Thread(){
            @Override
            public void run() {
                llamadaDao.deleteLlamadas(id);
                amigoDao.delete(id);
            }
        }.start();
    }

    public void updateNombre(String nombre,int id){
        new Thread(){
            @Override
            public void run() {
                amigoDao.updateNombre(nombre,id);
            }
        }.start();
    }

    public void updateTelefono(String telefono,int id){
        new Thread(){
            @Override
            public void run() {
               amigoDao.updateTelefono(telefono,id);
            }
        }.start();
    }

    public void updateFecha(String fecha,int id){
        new Thread(){
            @Override
            public void run() {
              amigoDao.updateFecha(fecha,id);
            }
        }.start();
    }

    public List<Contacto> guardarContactoLista() {
        return guardar.obtenerListaContactos();
    }



}
