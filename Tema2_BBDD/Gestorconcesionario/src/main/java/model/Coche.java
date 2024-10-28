package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Coche {

    private int id;
    private String marca, modelo;
    private int cv, precio;

    public Coche(String marca, String modelo, int cv, int precio){

        this.marca = marca;
        this.modelo = modelo;
        this.cv = cv;
        this.precio = precio;
    }
}
