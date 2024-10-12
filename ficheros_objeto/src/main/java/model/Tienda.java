package model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Tienda implements Serializable {
    
    private static final long serialVersionUID = 3456L;
    private String nombre;
    private int id;
    private int empleados;

    public void mostrarDatos() {
        System.out.println("SerialVersionUIDD = "+ serialVersionUID);
        System.out.println("nombre = " + nombre);
        System.out.println("id = " + id);
        System.out.println("empleados = " + empleados);
    }
}
