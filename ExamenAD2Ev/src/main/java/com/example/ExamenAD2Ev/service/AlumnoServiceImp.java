package com.example.ExamenAD2Ev.service;

import com.example.ExamenAD2Ev.model.Alumno;
import com.example.ExamenAD2Ev.model.Curso;
import com.example.ExamenAD2Ev.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImp implements AlumnoService{

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public Alumno agregarAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }

    @Override
    public List<Alumno> getAlumnosCurso(Curso curso) {
        return alumnoRepository.findByCurso(curso);
    }
}
