package com.example.ExamenAD2Ev.controller;

import com.example.ExamenAD2Ev.model.Alumno;
import com.example.ExamenAD2Ev.model.Aula;
import com.example.ExamenAD2Ev.service.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aula")
public class AulaController {
    @Autowired
    AulaService aulaService;

    @GetMapping("/error")
    public String getError(){
        return "Error en la app";
    }
    @GetMapping("/Mas30")
    public ResponseEntity<List<Aula>> getAulaMas30(){
        List<Aula>lista = aulaService.getAllAulasMas30();
        if(lista.isEmpty()){
            return new ResponseEntity<>(new ArrayList<Aula>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

}
