package com.example.ExamenAD2Ev.service;

import com.example.ExamenAD2Ev.model.Aula;
import com.example.ExamenAD2Ev.model.Curso;
import com.example.ExamenAD2Ev.model.Profesor;
import com.example.ExamenAD2Ev.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImp implements CursoService{
    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public Curso getByAula(Aula aula) {
        return  cursoRepository.findByAula(aula);
    }

    @Override
    public List<Curso> getCursosProfesor(Profesor profesor) {
        return cursoRepository.findByProfesores(profesor);
    }
}
