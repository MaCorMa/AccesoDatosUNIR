package com.example.AE_5.Libreria.service;

import com.example.AE_5.Libreria.model.Libreria;
import com.example.AE_5.Libreria.model.Libro;
import com.example.AE_5.Libreria.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImp implements LibroService{

    @Autowired
    LibroRepository libroRepository;

    @Override
    public Libro agregarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    @Override
    public List<Libro> getAllLibrosLibreria(Libreria libreria) {
        return libroRepository.findByLibrerias(libreria);
    }
}
