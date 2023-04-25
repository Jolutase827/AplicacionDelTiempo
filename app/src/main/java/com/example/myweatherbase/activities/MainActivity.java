package com.example.myweatherbase.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.myweatherbase.base.Parameters;

public class MainActivity extends BaseActivity implements CallInterface {

    private Root root;
    private ImageButton settings;
    private RecyclerView recyclerView;

    private TextView textView;
    private ImageButton imageButton;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("root",root);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton = findViewById(R.id.buscar);
        textView = findViewById(R.id.nombre_ciudad);
        settings = findViewById(R.id.setting);
        settings.setOnClickListener(view -> {
            Intent i = new Intent(this, PreferenceActivity.class);
            startActivity(i);
        });

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
            inicioWhether();
        }





    }



    // Realizamos la llamada y recogemos los datos en un objeto Root
    @Override
    public void doInBackground() {



        root = Connector.getConector().get(Root.class, "forecast?appid=" + Parameters.API + "&lang=" + MyPreferenceManager.getInstance(this).getStringIdioma() + "&units=" + MyPreferenceManager.getInstance(this).getStringUnits() +((Ciudad)getIntent().getExtras().getSerializable("ciudad")).getCoordenada());


    }

    // Una vez ya se ha realizado la llamada, ocultamos la barra de progreso y presentamos los datos
    @Override
    public void doInUI() {
        inicioWhether();

    }




    public void inicioWhether(){
        hideProgress();
        recyclerView = findViewById(R.id.recicleview);

        PrevisionesReciclerView previsionesReciclerView = new PrevisionesReciclerView(this,root);



        recyclerView.setAdapter(previsionesReciclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        textView.setText(root.getCity());
    }
}