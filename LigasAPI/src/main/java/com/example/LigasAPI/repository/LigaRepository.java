package com.example.LigasAPI.repository;

import com.example.LigasAPI.model.Liga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//métodos que van contra BBDD
//tantos métodos adicionales como necesitemos
//los nétodos "por defecto" los da el JPA repository  -> persist, save, merge, list
public interface LigaRepository extends JpaRepository<Liga, Integer> {

   Liga findByNombre(String nombre);
}
