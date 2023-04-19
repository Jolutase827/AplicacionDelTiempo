package com.example.myweatherbase.activities.model;

import com.example.myweatherbase.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CiudadRepository {

    private List<Ciudad> ciudades;

    private static CiudadRepository instance;

    private  CiudadRepository(){
        ciudades = new ArrayList<>();
        ciudades.add(new Ciudad("https://interrailero.com/wp-content/uploads/2022/01/que-ver-en-barcelona-mapa.jpg", "&lat=41.38879&lon=2.15899","Barcelona"));
        ciudades.add(new Ciudad("https://a.cdn-hotels.com/gdcs/production133/d1207/7ad2d7f0-68ce-11e8-8a0f-0242ac11000c.jpg","&lat=40.4165&lon=-3.70256", "Madrid" ));
        ciudades.add(new Ciudad( "http://34.77.228.118/export/sites/segtur/.content/imagenes/cabeceras-grandes/valencia/ciudad-artes-ciencias-valencia-c-luca-bravo-u-UyUjtbu5vj4.jpg", "&lat=39.4697500&lon=-0.3773900","Valencia"));
    }

    public static CiudadRepository getInstance() {
        if (instance==null)
            instance = new CiudadRepository();
        return instance;
    }

    public List<Ciudad> getAll(){
        return new ArrayList<>(ciudades);
    }


    public void add(Ciudad ciudad){
        ciudades.add(ciudad);
    }

    public Ciudad getCiudadByName(String name){
        Optional<Ciudad> optionalCiudad= ciudades.stream()
                .filter(p->p.getNombre().equalsIgnoreCase(name))
                .findFirst();

        if (optionalCiudad.isPresent())
            return optionalCiudad.get();

        return null;
    }
}
