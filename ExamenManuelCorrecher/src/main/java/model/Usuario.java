package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Usuario implements Serializable {
    private int id;
    private String nombre;
    private String correo;
    private String pass;

    public Usuario(String nombre, String mail){
        this.nombre=nombre;
        this.correo=mail;
    }
    public Usuario(String nombre, String mail, String pass){
        this.nombre=nombre;
        this.correo=mail;
        this.pass=pass;
    }

    public void mostrarDatos(){
        System.out.println("Nombre "+nombre);
        System.out.println("Correo "+correo);
        System.out.println("Pass "+pass);
    }


}
