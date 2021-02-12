package com.example.amigos.model;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amigos.R;
import com.example.amigos.model.room.pojo.Contacto;
import com.example.amigos.view.ObtenerContactos;
import com.example.amigos.view.RegistrarContacto;
import com.example.amigos.view.adapter.ContactoRecyclerAdapter;
import com.example.amigos.view.listener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class GuardarObtencionLista {

    Context context;
    private RecyclerView.Adapter adapter;
    private List<Contacto> contacto = new ArrayList<>();

    public GuardarObtencionLista(Context context) {
        this.context = context.getApplicationContext();
    }

    public List<Contacto> obtenerListaContactos() {
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                Integer comprobarTelefono = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                String telefono = null;
                if (comprobarTelefono > 0) {
                    Cursor cp = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    if (cp != null && cp.moveToFirst()) {
                        telefono = cp.getString(cp.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String tele = telefono.replaceAll("[(-)]","");
                        String tel = tele.replaceAll("-","");
                        String te = tel.replaceAll("\\s","");
                        String nombre = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        ContentResolver bd = context.getContentResolver();
                        String fecha = null;
                        Cursor bdc = bd.query(android.provider.ContactsContract.Data.CONTENT_URI, new String[] { ContactsContract.CommonDataKinds.Event.DATA }, android.provider.ContactsContract.Data.CONTACT_ID+" = "+id+" AND "+ ContactsContract.Data.MIMETYPE+" = '"+ ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE+"' AND "+ ContactsContract.CommonDataKinds.Event.TYPE+" = "+ ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY, null, android.provider.ContactsContract.Data.DISPLAY_NAME);
                        if (bdc.getCount() > 0) {
                            while (bdc.moveToNext()) {
                                fecha = bdc.getString(0);
                            }
                        }
                        Contacto c = new Contacto();
                        c.setNombre(nombre);
                        c.setFecha(fecha);
                        c.setTelefono(te);
                        contacto.add(c);
                    }
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        return contacto;
    }


}
