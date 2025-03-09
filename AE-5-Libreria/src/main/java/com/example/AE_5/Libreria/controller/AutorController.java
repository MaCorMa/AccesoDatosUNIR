package com.example.AE_5.Libreria.controller;

import com.example.AE_5.Libreria.model.Autor;
import com.example.AE_5.Libreria.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    private AutorService autorService;

    @GetMapping("/error")
    public String getError(){
        return "Error en la app";
    }
    @PostMapping("/add")
    public String addAutor(@RequestBody Autor autor){
        autorService.agregarAutor(autor);
        return "Autor agregado correctamente con nombre: "+autor.getNombre();
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Autor>> getAllAlumno(){
        List<Autor> lista = autorService.getAllAutores();
        if(lista.isEmpty()){
            return new ResponseEntity<>(new ArrayList<Autor>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
