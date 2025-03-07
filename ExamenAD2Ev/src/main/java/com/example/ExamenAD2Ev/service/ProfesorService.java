package com.example.ExamenAD2Ev.service;

import com.example.ExamenAD2Ev.model.Curso;
import com.example.ExamenAD2Ev.model.Profesor;

import java.util.List;

public interface ProfesorService {

    public Profesor agregarProfesor(Profesor profesor);

    List<Profesor>getProfesorCurso(Curso curso);
}
