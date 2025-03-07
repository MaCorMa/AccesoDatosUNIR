package com.example.ExamenAD2Ev.service;

import com.example.ExamenAD2Ev.model.Aula;
import com.example.ExamenAD2Ev.repository.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AulaServiceImp implements AulaService{

    @Autowired
    private AulaRepository aulaRepository;

    @Override
    public List<Aula> getAllAulasMas30() {
        List<Aula>lista = new ArrayList<Aula>();
        for(Aula aula:aulaRepository.findAll()){
            if(aula.getCapacidad()>30){
                lista.add(aula);
            }
        }
        return lista;
    }
}
