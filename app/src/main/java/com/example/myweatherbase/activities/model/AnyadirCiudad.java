package com.example.myweatherbase.activities.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myweatherbase.R;

public class AnyadirCiudad extends AppCompatActivity {

    private EditText cityName;
    private EditText lat;
    private EditText lon;
    private EditText url;

    private Button anyadir;
    private ImageButton atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anyadir_ciudad);
        cityName = findViewById(R.id.nombreciudad);
        lat = findViewById(R.id.lat);
        lon = findViewById(R.id.lon);
        url = findViewById(R.id.url);
        anyadir = findViewById(R.id.anyadir);
        atras = findViewById(R.id.atras);

        anyadir.setOnClickListener(view -> {
            Intent intent = new Intent();
            Ciudad ciudad = new Ciudad(url.getText().toString(),"&lat="+lat.getText()+"&lon="+lon.getText(),cityName.getText().toString());
            intent.putExtra("nuevaCiudad",ciudad);
            setResult(RESULT_OK,intent);
            finish();
        });
        atras.setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED,intent);
            finish();
        });




    }
}