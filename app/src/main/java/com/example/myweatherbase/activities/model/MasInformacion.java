package com.example.myweatherbase.activities.model;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.PreferenceActivity;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MasInformacion extends AppCompatActivity {

    private TextView max,min,tem,presion,sens,diahora,humedad,descip;
    private ImageButton atras;
    private ImageView icon;
    private ImageButton settings;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_informacion);
        atras = findViewById(R.id.atras);
        max = findViewById(R.id.tempmax);
        min = findViewById(R.id.tempmin);
        tem = findViewById(R.id.temperatura);
        presion = findViewById(R.id.presion);
        sens = findViewById(R.id.senstermica);
        diahora = findViewById(R.id.diahora);
        humedad = findViewById(R.id.humedad);
        descip = findViewById(R.id.estadocielo);
        icon = findViewById(R.id.icono);
        settings = findViewById(R.id.setting);
        settings.setOnClickListener(view -> {
            Intent i = new Intent(this, PreferenceActivity.class);
            startActivity(i);
        });
        Root root = (Root)getIntent().getExtras().getSerializable("root");
        int posicion = (int) getIntent().getExtras().getSerializable("posicion");
        Date date = new Date((long)root.list.get(posicion).dt*1000);
        max.setText("MAX: "+(String.valueOf(root.getList().get(posicion).main.temp_max))+"º");
        min.setText("MIN: "+(String.valueOf(root.getList().get(posicion).main.temp_min))+"º");
        tem.setText("Temp: "+(String.valueOf(root.getList().get(posicion).main.temp))+"º");
        presion.setText("Pres: "+ (String.valueOf(root.getList().get(posicion).main.pressure)));
        humedad.setText("Humedad: "+(String.valueOf(root.getList().get(posicion).main.humidity)));
        sens.setText("Sensacion: "+String.valueOf(root.getList().get(posicion).main.feels_like)+"º");
        diahora.setText((new SimpleDateFormat("EEEE")).format(date)+" at "+String.valueOf(root.getList().get(posicion).dt_txt.substring(11,16)));
        ImageDownloader.downloadImage(Parameters.ICON_URL_PRE+root.getList().get(posicion).getWeather().get(0).icon+Parameters.ICON_URL_POST,icon);
        descip.setText(root.getList().get(posicion).getWeather().get(0).getDescription());
        atras.setOnClickListener(view-> {
            finish();
        });



    }
}