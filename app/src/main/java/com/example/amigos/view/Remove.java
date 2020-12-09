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

public class Remove extends AppCompatActivity {
    private Button btcancelar, bteliminar;
    private EditText etid;

    private ViewModelActivity viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ViewModelActivity.class);
        init();
    }

    private void init() {
        etid = findViewById(R.id.etid);
        btcancelar = findViewById(R.id.btcancelar2);
        bteliminar = findViewById(R.id.bteliminar);

        btcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Remove.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bteliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id;

                // control de errores
                if(etid.getText().toString().isEmpty()){
                    etid.setError("Debe introducir una ID");
                } else {
                    id = Integer.parseInt(etid.getText().toString());
                    viewModel.delete(id);
                    Intent intent = new Intent(Remove.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}