package com.example.amigos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

import com.example.amigos.R;
import com.example.amigos.model.Repository;
import com.example.amigos.model.data.DataHolder;
import com.example.amigos.model.room.pojo.Amigo;
import com.example.amigos.model.room.pojo.Llamada;
import com.example.amigos.view.adapter.LlamadasRecyclerAdapter;
import com.example.amigos.viewmodel.ViewModelActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class VerLlamadas extends AppCompatActivity{

    Button btvolver;
    private ViewModelActivity viewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Llamada> call = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_llamadas);
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ViewModelActivity.class);
        cargarLlamadas();
        btvolver = findViewById(R.id.btvolverVL);
        btvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerLlamadas.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void cargarLlamadas() {
       recyclerView = findViewById(R.id.rvllamadas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LlamadasRecyclerAdapter(call);
        recyclerView.setAdapter(adapter);
            viewModel.getListaL().observe(VerLlamadas.this, new Observer<List<Llamada>>() {
                @Override
                public void onChanged(List<Llamada> llamadas) {
                    call.clear();
                    call.addAll(llamadas);
                    adapter.notifyDataSetChanged();
                }
            });

    }
}