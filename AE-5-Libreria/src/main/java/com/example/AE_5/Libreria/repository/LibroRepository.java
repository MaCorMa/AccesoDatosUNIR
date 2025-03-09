package com.example.AE_5.Libreria.repository;

import com.example.AE_5.Libreria.model.Libreria;
import com.example.AE_5.Libreria.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Integer> {
    List<Libro> findByLibrerias(Libreria libreria);
}
