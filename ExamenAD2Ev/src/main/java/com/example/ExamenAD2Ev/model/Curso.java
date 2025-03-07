package com.example.ExamenAD2Ev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter

@Entity
@Table(name = "curso")
public class Curso implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_curso;
    @Column
    private String nombre;

    @OneToOne
    @JoinColumn(name = "id_aula")
    private Aula aula;

    @ManyToMany
    @JoinTable(
            name = "profesor_curso",
            joinColumns = @JoinColumn(name = "id_curso"),
            inverseJoinColumns = @JoinColumn(name = "id_profesor")
    )
    private List<Profesor> profesores;

    public Curso() {
    }

    public Curso(String nombre) {
        this.nombre = nombre;
    }

    public int getId_curso() {
        return id_curso;
    }

    public String getNombre() {
        return nombre;
    }

    public Aula getAula() {
        return aula;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }
}
