package com.example.ExamenAD2Ev.service;

import com.example.ExamenAD2Ev.model.Alumno;
import com.example.ExamenAD2Ev.model.Curso;

import java.util.List;

public interface AlumnoService {

    public Alumno agregarAlumno(Alumno alumno);

    List<Alumno> getAllAlumnos();

    List<Alumno> getAlumnosCurso(Curso curso);
}
