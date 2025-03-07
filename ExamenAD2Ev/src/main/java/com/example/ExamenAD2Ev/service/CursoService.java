package com.example.ExamenAD2Ev.service;

import com.example.ExamenAD2Ev.model.Aula;
import com.example.ExamenAD2Ev.model.Curso;
import com.example.ExamenAD2Ev.model.Profesor;

import java.util.List;

public interface CursoService {

    public Curso getByAula(Aula aula);
    List<Curso> getCursosProfesor(Profesor profesor);
}
