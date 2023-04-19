package com.example.myweatherbase.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myweatherbase.API.Connector;
import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.AnyadirCiudad;
import com.example.myweatherbase.activities.model.Ciudad;
import com.example.myweatherbase.activities.model.CiudadRepository;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.ImageDownloader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class BuscarCiuda extends AppCompatActivity {

    private Spinner spinner;
    private ImageView imageView;

    private Button buscar;
    private ImageButton anyadirCiudad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucador_ciudad);
        spinner = findViewById(R.id.spinner);
        imageView = findViewById(R.id.imageView);
        buscar = findViewById(R.id.button);

        ArrayAdapter<Ciudad>   adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, CiudadRepository.getInstance().getAll());

        spinner.setAdapter(adapter);
        anyadirCiudad= findViewById(R.id.anyadirCiudad);

        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_CANCELED) {

                    }else if (result.getResultCode() == Activity.RESULT_OK) {

                    }
                });

        buscar.setOnClickListener(view -> {
            Intent i = new Intent(this,MainActivity.class);
            i.putExtra("ciudad",((Ciudad)spinner.getSelectedItem()));
            someActivityResultLauncher.launch(i);
        });


        ActivityResultLauncher<Intent> nuevaciudad = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_CANCELED)
                        Toast.makeText(this, "Cancelado por el usuario", Toast.LENGTH_LONG).show();
                    else if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Ciudad ciudad = (Ciudad) data.getExtras().getSerializable("nuevaCiudad");
                        CiudadRepository.getInstance().add(ciudad);
                        adapter.add(ciudad);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "Nuevo: " + ciudad.getNombre() , Toast.LENGTH_LONG).show();
                    }
                });

        anyadirCiudad.setOnClickListener(v->{
            Intent i = new Intent(this, AnyadirCiudad.class);
            nuevaciudad.launch(i);
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ImageDownloader.downloadImage(adapter.getItem(i).getImagen(),imageView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}