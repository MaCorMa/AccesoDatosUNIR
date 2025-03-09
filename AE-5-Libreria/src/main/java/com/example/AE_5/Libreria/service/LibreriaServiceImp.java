package com.example.AE_5.Libreria.service;

import com.example.AE_5.Libreria.model.Libreria;
import com.example.AE_5.Libreria.repository.LibreriaRepository;
import com.example.AE_5.Libreria.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibreriaServiceImp implements LibreriaService{

    @Autowired
    LibreriaRepository libreriaRepository;

    @Override
    public Libreria agregarLibreria(Libreria libreria) {
        return libreriaRepository.save(libreria);
    }

    @Override
    public List<Libreria> getAllLibrerias() {
        return libreriaRepository.findAll();
    }
}
