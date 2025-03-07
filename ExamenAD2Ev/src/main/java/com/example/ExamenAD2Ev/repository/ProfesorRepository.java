package com.example.ExamenAD2Ev.repository;

import com.example.ExamenAD2Ev.model.Curso;
import com.example.ExamenAD2Ev.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfesorRepository extends JpaRepository<Profesor,Integer> {

    List<Profesor> findByCursos(Curso curso);
}
