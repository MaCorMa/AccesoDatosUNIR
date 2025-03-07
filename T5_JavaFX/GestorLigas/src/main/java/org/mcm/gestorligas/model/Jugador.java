package org.mcm.gestorligas.model;

import jakarta.persistence.*;
import javafx.geometry.Pos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@NamedQuery(name="Jugador.findNacionalidad", query = "FROM Jugador j WHERE j.nacionalidad = :nacionalidad")
@NamedQuery(name="Jugador.findAll", query = "FROM Jugador")

@Entity
@Table (name = "jugadores")
public class Jugador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private String nacionalidad;
    @Column (name = "valor_mercado")
    private int valorMercado;
    @Column
    private int goles;

    @ManyToOne
    @JoinColumn(name = "id_posicion")
    private Posicion posicion;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

    public Jugador(String nombre, String nacionalidad, int valorMercado, int goles) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.valorMercado = valorMercado;
        this.goles = goles;
    }

    public Jugador(String nombre, String nacionalidad, int valorMercado) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.valorMercado = valorMercado;
    }
}
