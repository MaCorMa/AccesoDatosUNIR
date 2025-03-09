package com.example.AE_5.Libreria.service;


import com.example.AE_5.Libreria.model.Autor;
import com.example.AE_5.Libreria.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServiceImp implements AutorService{

    @Autowired
    AutorRepository autorRepository;


    @Override
    public Autor agregarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public List<Autor> getAllAutores() {
        return autorRepository.findAll();
    }
}
