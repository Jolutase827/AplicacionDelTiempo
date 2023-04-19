package com.example.myweatherbase.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myweatherbase.API.Connector;
import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Ciudad;
import com.example.myweatherbase.activities.model.CiudadRepository;
import com.example.myweatherbase.activities.model.PrevisionesReciclerView;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.BaseActivity;
import com.example.myweatherbase.base.CallInterface;

public class MainActivity extends BaseActivity implements CallInterface {

    private Root root;
    private RecyclerView recyclerView;

    private TextView textView;
    private ImageButton imageButton;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("root",root);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton = findViewById(R.id.buscar);
        textView = findViewById(R.id.nombre_ciudad);
        imageButton.setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_OK,intent);
            finish();
        });
        if (savedInstanceState==null) {
            // Mostramos la barra de progreso y ejecutamos la llamada a la API
            showProgress();
            executeCall(this);
        }else {
            root = (Root) (savedInstanceState.getSerializable("root"));
            recyclerView = findViewById(R.id.recicleview);
            PrevisionesReciclerView previsionesReciclerView = new PrevisionesReciclerView(this,root);
            recyclerView.setAdapter(previsionesReciclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            textView.setText(root.getCity());
        }



    }

    // Realizamos la llamada y recogemos los datos en un objeto Root
    @Override
    public void doInBackground() {



        root = Connector.getConector().get(Root.class,((Ciudad)getIntent().getExtras().getSerializable("ciudad")).getCoordenada());


    }

    // Una vez ya se ha realizado la llamada, ocultamos la barra de progreso y presentamos los datos
    @Override
    public void doInUI() {
        hideProgress();
        recyclerView = findViewById(R.id.recicleview);
        PrevisionesReciclerView previsionesReciclerView = new PrevisionesReciclerView(this,root);
        recyclerView.setAdapter(previsionesReciclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        textView.setText(root.getCity());

    }
}