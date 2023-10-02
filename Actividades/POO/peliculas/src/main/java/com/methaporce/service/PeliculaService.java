package com.methaporce.service;

import com.methaporce.modelo.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class PeliculaService {

    private List<Pelicula> peliculas;

    //Constructor de la clase
    public PeliculaService(){
        peliculas= new ArrayList<>();
    }

    public void agregarPelicula(Pelicula pelicula){
        peliculas.add(pelicula);
    }

    public void eliminarPelicula(int id){
        peliculas.removeIf(p -> p.getId()==id);
    }

    public List<Pelicula> obtenerPeliculas(){
        return peliculas;
    }

    public List<Pelicula> obtenerPeliculasDisponibles() {
        List<Pelicula> disponibles = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            if (pelicula.isDisponible()) {
                disponibles.add(pelicula);
            }
        }
        return disponibles;
    }

    public List<Pelicula> obtenerPeliculasNoDisponibles() {
        List<Pelicula> noDisponibles = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            if (!pelicula.isDisponible()) {
                noDisponibles.add(pelicula);
            }
        }
        return noDisponibles;
    }

    public void marcarPeliculaComoDisponible(int id) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId() == id) {
                pelicula.setDisponible(true);
                return;
            }
        }
    }

}
