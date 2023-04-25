package com.example.myweatherbase.activities;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class MyPreferenceManager {
    private static MyPreferenceManager instance;
    private SharedPreferences preferences;
    private MyPreferenceManager(){

    }

    public static MyPreferenceManager getInstance(Context context){
        if (instance == null){
            instance = new MyPreferenceManager();
        }
        instance.inicializa(context);
        return instance;
    }
    private void inicializa(Context context){
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getStringUnits(){
        return preferences.getString("unidades","metric");
    }
    public String getStringIdioma(){
        return preferences.getString("idiomas","es");
    }
}
