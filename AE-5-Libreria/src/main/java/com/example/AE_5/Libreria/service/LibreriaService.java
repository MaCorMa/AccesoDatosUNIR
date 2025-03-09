package com.example.AE_5.Libreria.service;

import com.example.AE_5.Libreria.model.Libreria;

import java.util.List;

public interface LibreriaService {
    public Libreria agregarLibreria(Libreria libreria);
    List<Libreria> getAllLibrerias();
}
