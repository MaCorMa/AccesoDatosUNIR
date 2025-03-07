package com.example.ExamenAD2Ev.controller;



import com.example.ExamenAD2Ev.model.Alumno;
import com.example.ExamenAD2Ev.model.Aula;
import com.example.ExamenAD2Ev.model.Curso;
import com.example.ExamenAD2Ev.model.Profesor;
import com.example.ExamenAD2Ev.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController  {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/error")
    public String getError(){
        return "Error en la app";
    }

    @GetMapping("/getAllProfesor")
    public ResponseEntity<List<Curso>> getAllProfesor(@RequestParam int id_profesor){
        Profesor profesor = new Profesor();
        profesor.setId_profesor(id_profesor);
        List<Curso>lista=cursoService.getCursosProfesor(profesor);
        if(lista.isEmpty()){
            return new ResponseEntity<>(new ArrayList<Curso>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    @GetMapping("/getAula")
    public ResponseEntity<Curso> getByAula(@RequestParam int id_aula){
        Aula aula = new Aula();
        aula.setId_aula(id_aula);
        Curso curso = cursoService.getByAula(aula);
        if(curso!= null){
            return new ResponseEntity<Curso>(curso, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
