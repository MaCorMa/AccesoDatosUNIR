package com.example.ExamenAD2Ev.service;

import com.example.ExamenAD2Ev.model.Curso;
import com.example.ExamenAD2Ev.model.Profesor;
import com.example.ExamenAD2Ev.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImp implements ProfesorService{

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public Profesor agregarProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public List<Profesor> getProfesorCurso(Curso curso) {
        return profesorRepository.findByCursos(curso);
    }
}
