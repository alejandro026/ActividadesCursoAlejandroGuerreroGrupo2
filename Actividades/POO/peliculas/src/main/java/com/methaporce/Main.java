package com.methaporce;

import com.methaporce.modelo.Pelicula;
import com.methaporce.service.PeliculaService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Servicio para gestionar las peliculas
        PeliculaService gestor = new PeliculaService();

        Pelicula pelicula1 = new Pelicula(1, "Spiderman", true);
        Pelicula pelicula2 = new Pelicula(2, "Titanic", false);
        Pelicula pelicula3 = new Pelicula(3, "Transformers", true);

        gestor.agregarPelicula(pelicula1);
        gestor.agregarPelicula(pelicula2);
        gestor.agregarPelicula(pelicula3);

        System.out.println("Listando todas las peliculas:");
        List<Pelicula> todas = gestor.obtenerPeliculas();
        for (Pelicula pelicula : todas) {
            System.out.println(pelicula.getNombre());
        }

        System.out.println("\nPelículas disponibles:");
        List<Pelicula> disponibles = gestor.obtenerPeliculasDisponibles();
        for (Pelicula pelicula : disponibles) {
            System.out.println(pelicula.getNombre());
        }

        System.out.println("\nPelículas no disponibles:");
        List<Pelicula> noDisponibles = gestor.obtenerPeliculasNoDisponibles();
        for (Pelicula pelicula : noDisponibles) {
            System.out.println(pelicula.getNombre());
        }

        gestor.marcarPeliculaComoDisponible(2); // Marcar película 2 como disponible

        System.out.println("\nPelículas después de marcar una como disponible:");
        todas = gestor.obtenerPeliculas();
        for (Pelicula pelicula : todas) {
            System.out.println(pelicula.getNombre() + " - Disponible: " + pelicula.isDisponible());
        }

        gestor.eliminarPelicula(1); // Eliminar película 1

        System.out.println("\nPelículas después de eliminar una película:");
        todas = gestor.obtenerPeliculas();
        for (Pelicula pelicula : todas) {
            System.out.println(pelicula.getNombre());
        }
    }

}
