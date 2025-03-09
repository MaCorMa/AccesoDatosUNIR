package com.example.AE_5.Libreria.service;

import com.example.AE_5.Libreria.model.Autor;

import java.util.List;

public interface AutorService {

    public Autor agregarAutor(Autor autor);

    List<Autor> getAllAutores();
}
