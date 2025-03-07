package com.example.LigasAPI.service;

import com.example.LigasAPI.model.Liga;

import java.util.List;

public interface LigaService {
    //métodos que aportan la lógica del negocio, no va contra la BBDD
    //escribo todos los métodos que quiero llamar desde el controller. Sólo se escribe la firma
    //ej: Liga updateLigaFecha(LocalDate localDate) -> No va contra BBDD

    public Liga agregarLiga(Liga liga);

    List<Liga> getAllLigas();
}
