package com.example.AE_5.Libreria.service;

import com.example.AE_5.Libreria.model.Libreria;
import com.example.AE_5.Libreria.model.Libro;

import java.util.List;

public interface LibroService {
    public Libro agregarLibro(Libro libro);
    List<Libro>getAllLibros();
    List<Libro>getAllLibrosLibreria(Libreria libreria);
}
