package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Embeddable //indica que se va a embeber en otra entidad que va a una tabla
public class Direccion implements Serializable {
    @Column
    private String localidad;
    @Column
    private String provincia;

    @Override
    public String toString() {
        return "Direccion{" +
                "localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
