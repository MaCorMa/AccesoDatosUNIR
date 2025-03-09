package com.example.AE_5.Libreria.controller;

import com.example.AE_5.Libreria.model.Libreria;
import com.example.AE_5.Libreria.service.LibreriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/libreria")
public class LibreriaController {
    @Autowired
    LibreriaService libreriaService;

    @GetMapping("/error")
    public String getError(){
        return "Error en la app";
    }
    @PostMapping("/add")
    public String addLibreria(@RequestBody Libreria libreria){
        libreriaService.agregarLibreria(libreria);
        return "Libreria agregado correctamente con nombre: "+libreria.getNombre();
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Libreria>> getAllLibrerias(){
        List<Libreria> lista = libreriaService.getAllLibrerias();
        if(lista.isEmpty()){
            return new ResponseEntity<>(new ArrayList<Libreria>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

}
