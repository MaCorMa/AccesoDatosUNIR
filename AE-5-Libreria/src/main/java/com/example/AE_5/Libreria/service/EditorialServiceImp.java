package com.example.AE_5.Libreria.service;

import com.example.AE_5.Libreria.model.Editorial;
import com.example.AE_5.Libreria.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServiceImp implements EditorialService{
    @Autowired
    EditorialRepository editorialRepository;

    @Override
    public Editorial agregarEditorial(Editorial editorial) {
        return editorialRepository.save(editorial);
    }
}
