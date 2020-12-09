package com.example.amigos.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.amigos.R;
import com.example.amigos.model.data.DataHolder;
import com.example.amigos.model.room.pojo.Amigo;
import com.example.amigos.model.room.pojo.ContadorLlamadas;
import com.example.amigos.view.adapter.AmigoRecyclerAdapter;
import com.example.amigos.view.adapter.ContactoRecyclerAdapter;
import com.example.amigos.view.listener.RecyclerItemClickListener;
import com.example.amigos.viewmodel.ViewModelActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISO_CONTACTO = 1;
    private static final int PERMISO_LOG = 2;
    private static final int PERMISO_RPS = 3;


    private ViewModelActivity viewModelActivity;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Amigo> amigo = new ArrayList<>();

    private List<Amigo> lista = DataHolder.getInstance().lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModelActivity = new ViewModelProvider(this).get(ViewModelActivity.class);
        obtenerPermisoLogs();
        obtenerPermisoRPS();
        obtenerPermisoContactos();
        cargarAmigos();
        init();

        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.fabañadir);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fabRemove = (FloatingActionButton) findViewById(R.id.fabeliminar);
        fabRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Remove.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fabEdit = (FloatingActionButton) findViewById(R.id.fabeditar);
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Edit.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        RecyclerView mi_recycler = findViewById(R.id.rvamigos);
        mi_recycler.addOnItemTouchListener(new RecyclerItemClickListener(this,mi_recycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, VerAmigo.class);
                intent.putExtra("objeto2",amigo.get(position).toString());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        mi_recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AmigoRecyclerAdapter(amigo);
        mi_recycler.setAdapter(adapter);
    }

    private void cargarAmigos() {
        recyclerView = findViewById(R.id.rvamigos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AmigoRecyclerAdapter(amigo);
        recyclerView.setAdapter(adapter);

        viewModelActivity.getLista().observe(MainActivity.this, new Observer<List<Amigo>>() {
            @Override
            public void onChanged(List<Amigo> amigos) {
                amigo.clear();
                amigo.addAll(amigos);
                adapter.notifyDataSetChanged();

                lista.clear();
                lista.addAll(amigos);
            }
        });

    }

    private void obtenerPermisoContactos() {
        int result = PackageManager.PERMISSION_GRANTED;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            result = checkSelfPermission(Manifest.permission.READ_CONTACTS);
        }
        if(result == PackageManager.PERMISSION_DENIED) {
            pedirPermisoContactos();
        }
    }

    private void pedirPermisoContactos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                explicarRazon();
            } else {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISO_CONTACTO);
            }
        }
    }

    private void explicarRazon() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo);
        builder.setMessage(R.string.mensaje);
        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISO_CONTACTO);
            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }

    private void obtenerPermisoLogs() {
        int result = PackageManager.PERMISSION_GRANTED;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            result = checkSelfPermission(Manifest.permission.READ_CALL_LOG);
        }
        if(result == PackageManager.PERMISSION_DENIED) {
            pedirPermisoLogs();
        }
    }

    private void pedirPermisoLogs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(shouldShowRequestPermissionRationale(Manifest.permission.READ_CALL_LOG)) {
                explicarRazon2();
            } else {
                requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG}, PERMISO_LOG);
            }
        }
    }

    private void explicarRazon2() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo2);
        builder.setMessage(R.string.mensaje2);
        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG}, PERMISO_LOG);
            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }

    private void obtenerPermisoRPS() {
        int result = PackageManager.PERMISSION_GRANTED;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            result = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
        }
        if(result == PackageManager.PERMISSION_DENIED) {
            pedirPermisoRPS();
        }
    }

    private void pedirPermisoRPS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {
                Log.v("XYZ","explico razón RPS");
                explicarRazon3();
            } else {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISO_RPS);
            }
        }
    }

    private void explicarRazon3() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo3);
        builder.setMessage(R.string.mensaje3);
        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISO_RPS);
            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISO_CONTACTO: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED
                            && ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
                    }
                } else {
                    finish();
                } return;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.mnImportar:
                return importarContactos();
            case R.id.mnLlamadas:
                return verLlamadas();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean verLlamadas() {
        Intent intent = new Intent(this, VerLlamadas.class);
        startActivity(intent);
        return true;
    }

    private boolean importarContactos() {
        Intent intent = new Intent(this, ObtenerContactos.class);
        startActivity(intent);
        return true;
    }

}