package com.example.amigos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.amigos.R;
import com.example.amigos.model.room.pojo.Amigo;
import com.example.amigos.viewmodel.ViewModelActivity;

public class VerAmigo extends AppCompatActivity {

    Button btvolver;
    TextView tvnombre, tvfecha, tvtelefono, tvcount;

    private Amigo a;
    private ViewModelActivity viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_amigo);
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ViewModelActivity.class);

        btvolver = findViewById(R.id.btvolver3);

        tvnombre = findViewById(R.id.tvnombreVA);
        tvfecha = findViewById(R.id.tvfechaVA);
        tvtelefono = findViewById(R.id.tvtelefonoVA);
        tvcount = findViewById(R.id.tvcallsVA);

        Intent intent = getIntent();
        String valor = intent.getStringExtra("objeto2");
        String[] parte = valor.split(",");
        String id = parte[0];
        String id2 = id.replaceAll("id:","");
        String id3 = id2.replaceAll("\\s","");
        String nombre = parte[1];
        String fecha = parte[2];
        String telefono = parte[3];
        long i = Long.valueOf(id3);
        tvnombre.setText(nombre);
        tvfecha.setText(fecha);
        tvtelefono.setText(telefono);

        viewModel.getListaLlamadasN(i).observe(VerAmigo.this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                tvcount.setText("Número de llamadas: " + aLong.toString());
            }
        });

        btvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerAmigo.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}