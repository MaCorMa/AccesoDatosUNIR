package com.example.AE_5.Libreria.controller;

import com.example.AE_5.Libreria.model.Libreria;
import com.example.AE_5.Libreria.model.Libro;
import com.example.AE_5.Libreria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/libro")
public class LibroController {
    @Autowired
    LibroService libroService;

    @GetMapping("/error")
    public String getError(){
        return "Error en la app";
    }
    @PostMapping("/add")
    public String addLibro(@RequestBody Libro libro){
        libroService.agregarLibro(libro);
        return "Libro agregado correctamente con nombre: "+libro.getTitulo();
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Libro>> getAllLibros(){
        List<Libro> lista = libroService.getAllLibros();
        if(lista.isEmpty()){
            return new ResponseEntity<>(new ArrayList<Libro>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    @GetMapping("/getAllLibreria")
    public ResponseEntity<List<Libro>> getAllLibreria(@RequestParam int id_libreria){
        //se crea instancia de curso y se hace el filtrado según el curso creado en nueva instancia
        Libreria libreria = new Libreria();
        libreria.setId(id_libreria);
        List<Libro> lista = libroService.getAllLibrosLibreria(libreria);
        if(lista.isEmpty()){
            return new ResponseEntity<>(new ArrayList<Libro>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
