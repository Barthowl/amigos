package com.example.amigos.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.amigos.model.Repository;
import com.example.amigos.model.data.DataHolder;
import com.example.amigos.model.room.pojo.Amigo;
import com.example.amigos.model.room.pojo.Llamada;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecibidorLlamadas extends BroadcastReceiver {
        private Repository repository;
        private List<Amigo> lista = DataHolder.getInstance().lista;

        private Llamada l;


        @Override
        public void onReceive(Context context, Intent intent) {
            repository = new Repository(context);
            final String action = intent.getAction();
            if (action.equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
                String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
                String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

                if((state.equals(TelephonyManager.EXTRA_STATE_RINGING))){
                    for(int i = 0; i <= lista.size()-1;i++){
                        if(lista.get(i).getTelefono().equals(incomingNumber)){
                            String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
                            long id = lista.get(i).getId();
                            l = new Llamada();
                            l.setIdamigo(id);
                            l.setFechallamada(fecha);
                            repository.insert(l);
                        }
                    }

                }
            }
        }

    }