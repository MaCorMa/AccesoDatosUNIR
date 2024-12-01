package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Pasajero{

    private int id;
    private String nombre, apellido;

    public Pasajero(String nombre, String apellido) {
        this.nombre=nombre;
        this.apellido=apellido;
    }


    public void mostrarDatos() {
        System.out.println("nombre = " + nombre);
        System.out.println("apellido = " + apellido);
    }
}
