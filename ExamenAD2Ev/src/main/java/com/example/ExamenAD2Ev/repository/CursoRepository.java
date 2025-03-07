package com.example.ExamenAD2Ev.repository;

import com.example.ExamenAD2Ev.model.Aula;
import com.example.ExamenAD2Ev.model.Curso;
import com.example.ExamenAD2Ev.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso,Integer> {

    List<Curso> findByProfesores(Profesor profesor);
    Curso findByAula(Aula aula);
}
