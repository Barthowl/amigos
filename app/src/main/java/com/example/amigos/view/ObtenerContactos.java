package com.example.amigos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import com.example.amigos.R;
import com.example.amigos.model.room.pojo.Contacto;
import com.example.amigos.view.adapter.ContactoRecyclerAdapter;
import com.example.amigos.view.listener.RecyclerItemClickListener;
import com.example.amigos.viewmodel.ViewModelActivity;

import java.util.ArrayList;
import java.util.List;

public class ObtenerContactos extends AppCompatActivity {

    Button btvolver;
    private ViewModelActivity viewModelActivity;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModelActivity = new ViewModelProvider(this).get(ViewModelActivity.class);
        setContentView(R.layout.activity_importar_contactos);
        init(viewModelActivity.guardarContactoLista());
        btvolver = findViewById(R.id.btvolver);
        btvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ObtenerContactos.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init(List<Contacto> contacto) {
        RecyclerView mi_recycler = findViewById(R.id.rv);
        mi_recycler.addOnItemTouchListener(new RecyclerItemClickListener(this,mi_recycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ObtenerContactos.this, RegistrarContacto.class);
                intent.putExtra("objeto",contacto.get(position).toString());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        mi_recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactoRecyclerAdapter(contacto);
        mi_recycler.setAdapter(adapter);
    }


}