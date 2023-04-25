package com.example.myweatherbase.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myweatherbase.R;

public class PreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content,new PreferenciasFragment())
                .commit();
    }
}