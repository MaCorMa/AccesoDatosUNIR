package com.example.ExamenAD2Ev.repository;

import com.example.ExamenAD2Ev.model.Alumno;
import com.example.ExamenAD2Ev.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno,Integer> {

    List<Alumno> findByCurso(Curso curso);
}
