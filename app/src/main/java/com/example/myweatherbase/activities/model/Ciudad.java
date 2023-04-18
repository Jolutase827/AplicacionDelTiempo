package com.example.myweatherbase.activities.model;

import java.io.Serializable;

public class Ciudad implements Serializable {
    private String imagen;
    private String coordenada;
    private String nombre;

    public Ciudad(String imagen, String coordenada, String nombre) {
        this.imagen = imagen;
        this.coordenada = coordenada;
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(String coordenada) {
        this.coordenada = coordenada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre ;
    }
}
