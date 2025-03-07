package com.example.LigasAPI.service;

import com.example.LigasAPI.model.Liga;
import com.example.LigasAPI.repository.LigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LigaServiceImp implements LigaService{
    @Autowired
    private LigaRepository ligaRepository;

    @Override
    public Liga agregarLiga(Liga liga) {
        //quiero que se pueda agregar la Liga si no existe previamente una liga con ese nombre
        //se necesita también un Repository para ejecutar los métodos contra la BBDD
        //@Autowired autoinicializa el repository
        /*
        if(ligaRepository.findByNombre("Calcio")==null){
            //ejemplo de método de repository
            return ligaRepository.save(liga);//como el persist de hibernate
        }else{
            return null;
        } */
        return ligaRepository.save(liga);
    }

    @Override
    public List<Liga> getAllLigas() {
        return ligaRepository.findAll(); //del repository creado automaticamente
    }

}
