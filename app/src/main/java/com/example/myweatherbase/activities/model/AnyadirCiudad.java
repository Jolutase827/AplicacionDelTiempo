package com.example.myweatherbase.activities.model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.PreferenceActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AnyadirCiudad extends AppCompatActivity {

    private String proveedor;
    private LocationManager managerloc;
    private EditText cityName;
    private ImageButton settings;
    private EditText lat;
    private EditText lon;
    private EditText url;

    private Button anyadir;
    private ImageButton atras;
    private FloatingActionButton miUbicacion;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("lat",lat.getText().toString());
        outState.putSerializable("lon",lon.getText().toString());
        outState.putSerializable("url",url.getText().toString());
        outState.putSerializable("name",cityName.getText().toString());




    }

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
        settings = findViewById(R.id.setting);
        settings.setOnClickListener(view -> {
            Intent i = new Intent(this, PreferenceActivity.class);
            startActivity(i);
        });
        miUbicacion= findViewById(R.id.miubicacion);


        managerloc = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setCostAllowed(false);
        criteria.setAltitudeRequired(true);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);

        proveedor = managerloc.getBestProvider(criteria,true);
        @SuppressLint("MissingPermission")
        Location location = managerloc.getLastKnownLocation(proveedor);

        miUbicacion.setOnClickListener(view -> {
            lat.setText(location.getLatitude()+"");
            lon.setText((location.getLongitude()+""));
        });
        if (savedInstanceState!=null){
            lat.setText(savedInstanceState.getSerializable("lat").toString());
            lon.setText(savedInstanceState.getSerializable("lon").toString());
            url.setText(savedInstanceState.getSerializable("url").toString());
            cityName.setText(savedInstanceState.getSerializable("name").toString());
        }

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