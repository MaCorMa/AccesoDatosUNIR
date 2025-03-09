package com.example.AE_5.Libreria.controller;

import com.example.AE_5.Libreria.model.Editorial;
import com.example.AE_5.Libreria.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/editorial")
public class EditorialController {
    @Autowired
    private EditorialService editorialService;

    @GetMapping("/error")
    public String getError(){
        return "Error en la app";
    }
    @PostMapping("/add")
    public String addEditorial(@RequestBody Editorial editorial){
        editorialService.agregarEditorial(editorial);
        return "Editorial agregado correctamente con nombre: "+editorial.getNombre();
    }
}
