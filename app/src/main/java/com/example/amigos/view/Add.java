package com.example.amigos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.amigos.R;
import com.example.amigos.model.room.pojo.Amigo;
import com.example.amigos.viewmodel.ViewModelActivity;

public class Add extends AppCompatActivity {

    private Button btvolver, btaceptar;
    private EditText etnombre, ettelefono, etfecha;

    private Amigo a;

    private ViewModelActivity viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ViewModelActivity.class);
        init();
    }

    private void init() {
        etnombre = findViewById(R.id.etnombre);
        ettelefono = findViewById(R.id.ettelefono);
        etfecha = findViewById(R.id.etfecha);

        btvolver = findViewById(R.id.btcancelar);
        btaceptar = findViewById(R.id.btaceptar);

        btvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // control de errores
                if(etnombre.getText().toString().isEmpty()){
                    etnombre.setError("Debe introducir un nombre");
                } else if(etfecha.getText().toString().isEmpty()){
                    etfecha.setError("Debe introducir una fecha");
                } else if(ettelefono.getText().toString().isEmpty()){
                    ettelefono.setError("Debe introducir un teléfono");
                } else {
                    String nombre = etnombre.getText().toString();
                    String telefono = ettelefono.getText().toString();
                    String fecha = etfecha.getText().toString();

                    a = new Amigo(nombre,fecha,telefono);
                    viewModel.insert(a);
                    Intent intent = new Intent(Add.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}