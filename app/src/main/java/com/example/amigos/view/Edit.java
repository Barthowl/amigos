package com.example.amigos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.amigos.R;
import com.example.amigos.viewmodel.ViewModelActivity;

public class Edit extends AppCompatActivity {

    private Button btcancelar, bteditar;
    private EditText etid, etnombre, ettelefono, etfecha;

    private ViewModelActivity viewModel;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ViewModelActivity.class);
        init();
    }

    private void init() {
        etid = findViewById(R.id.etidE);
        etnombre = findViewById(R.id.etnombreE);
        ettelefono = findViewById(R.id.ettelefonoE);
        etfecha = findViewById(R.id.etfechaE);

        btcancelar = findViewById(R.id.btcancelar3);
        bteditar = findViewById(R.id.bteditar2);

        btcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Edit.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bteditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // control de errores
                if(etid.getText().toString().isEmpty()){
                    etid.setError("Introduzca la id que va a editar");
                }
                if(!etnombre.getText().toString().isEmpty() && !etid.getText().toString().isEmpty()){
                    String nombre = etnombre.getText().toString();
                    viewModel.updateNombre2(nombre,Integer.parseInt(etid.getText().toString()));
                }
                if(!etfecha.getText().toString().isEmpty() && !etid.getText().toString().isEmpty()){
                    String fecha = etfecha.getText().toString();
                    viewModel.updateFecha(fecha,Integer.parseInt(etid.getText().toString()));
                }
                if(!ettelefono.getText().toString().isEmpty() && !etid.getText().toString().isEmpty()){
                    String telefono = ettelefono.getText().toString();
                    viewModel.updateTelefono(telefono,Integer.parseInt(etid.getText().toString()));
                }
                if(!etid.getText().toString().isEmpty()){
                    Intent intent = new Intent(Edit.this, MainActivity.class);
                    startActivity(intent);
                }

            }

        });
    }
}