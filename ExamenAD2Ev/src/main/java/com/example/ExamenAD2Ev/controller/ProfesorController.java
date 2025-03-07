package com.example.ExamenAD2Ev.controller;

import com.example.ExamenAD2Ev.model.Alumno;
import com.example.ExamenAD2Ev.model.Curso;
import com.example.ExamenAD2Ev.model.Profesor;
import com.example.ExamenAD2Ev.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/error")
    public String getError(){
        return "Error en la app";
    }
    @PostMapping("/add")
    public String addLiga(@RequestBody Profesor profesor){
        profesorService.agregarProfesor(profesor);
        return "Profesor agregado correctamente con nombre: "+profesor.getNombre();
    }
    @GetMapping("/getAllCurso")
    public ResponseEntity<List<Profesor>> getAllCurso(@RequestParam int id_curso){
        Curso curso = new Curso();
        curso.setId_curso(id_curso);
        List<Profesor> lista = profesorService.getProfesorCurso(curso);
        if(lista.isEmpty()){
            return new ResponseEntity<>(new ArrayList<Profesor>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
