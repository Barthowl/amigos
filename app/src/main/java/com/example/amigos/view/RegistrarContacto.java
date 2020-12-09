package com.example.amigos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.amigos.R;
import com.example.amigos.model.room.pojo.Amigo;
import com.example.amigos.viewmodel.ViewModelActivity;

import java.util.regex.Pattern;

public class RegistrarContacto extends AppCompatActivity {
    Button btimportar, btcancelar;
    TextView etnombre, etfecha, ettelefono;

    private Amigo a;

    private ViewModelActivity viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_contacto);
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ViewModelActivity.class);

        btcancelar = findViewById(R.id.btvolver2);
        btimportar = findViewById(R.id.btimportar);

        etnombre = findViewById(R.id.etnombreI);
        etfecha = findViewById(R.id.etfechaI);
        ettelefono = findViewById(R.id.ettelefonoI);

        Intent intent = getIntent();
        String valor = intent.getStringExtra("objeto");
        String[] parte = valor.split("\\|");
        String nombre = parte[0];
        String fecha = parte[1];
        String telefono = parte[2];
        etnombre.setText("Nombre: " + nombre);
        etfecha.setText("Fecha: " + fecha);
        ettelefono.setText("Teléfono: " + telefono);

        btcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarContacto.this, ObtenerContactos.class);
                startActivity(intent);
            }
        });

        btimportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = new Amigo(nombre,fecha,telefono);
                viewModel.insert(a);
                Intent intent = new Intent(RegistrarContacto.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}