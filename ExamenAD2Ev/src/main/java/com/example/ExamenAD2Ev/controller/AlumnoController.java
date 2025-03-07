package com.example.ExamenAD2Ev.controller;


import com.example.ExamenAD2Ev.model.Alumno;
import com.example.ExamenAD2Ev.model.Curso;
import com.example.ExamenAD2Ev.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/error")
    public String getError(){
        return "Error en la app";
    }
    @PostMapping("/add")
    public String addLiga(@RequestBody Alumno alumno){
        alumnoService.agregarAlumno(alumno);
        return "Alumno agregado correctamente con nombre: "+alumno.getNombre();
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Alumno>> getAllAlumno(){
        List<Alumno> lista = alumnoService.getAllAlumnos();
        if(lista.isEmpty()){
            return new ResponseEntity<>(new ArrayList<Alumno>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    @GetMapping("/getAllCurso")
    public ResponseEntity<List<Alumno>> getAllCurso(@RequestParam int id_curso){
        //se crea instancia de curso y se hace el filtrado según el curso creado en nueva instancia
        Curso curso = new Curso();
        curso.setId_curso(id_curso);
        List<Alumno> lista = alumnoService.getAlumnosCurso(curso);
        if(lista.isEmpty()){
            return new ResponseEntity<>(new ArrayList<Alumno>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
