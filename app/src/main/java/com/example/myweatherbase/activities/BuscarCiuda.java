package com.example.myweatherbase.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myweatherbase.API.Connector;
import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Ciudad;
import com.example.myweatherbase.activities.model.CiudadRepository;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.ImageDownloader;

public class BuscarCiuda extends AppCompatActivity {

    private Spinner spinner;
    private ImageView imageView;

    private Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucador_ciudad);
        spinner = findViewById(R.id.spinner);
        imageView = findViewById(R.id.imageView);
        buscar = findViewById(R.id.button);
        ArrayAdapter<Ciudad> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, CiudadRepository.getInstance().getAll());
        spinner.setAdapter(adapter);

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