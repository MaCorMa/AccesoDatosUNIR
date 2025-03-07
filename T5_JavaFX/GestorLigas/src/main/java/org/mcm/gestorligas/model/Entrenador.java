package org.mcm.gestorligas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "entrenadores")
public class Entrenador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private int calificacion;
    @Column
    private int titulos;

    //bidireccionalidad
    @OneToOne(mappedBy = "entrenador")
    private Equipo equipo;

    public Entrenador(String nombre, int calificacion, int titulos) {
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.titulos = titulos;
    }

    public Entrenador(String nombre) {
        this.nombre = nombre;
    }
}
