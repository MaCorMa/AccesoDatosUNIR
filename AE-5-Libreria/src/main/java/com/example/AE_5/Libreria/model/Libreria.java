package com.example.AE_5.Libreria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "librerias")
public class Libreria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private String propietario;
    @Column
    private String direccion;

    //lista <Libreria>librerias de la calse libro
    @ManyToMany(fetch = FetchType.EAGER)//eager para forzar la petición
    @JoinTable(name = "libreria_libro", //tabla renacida
            joinColumns = @JoinColumn(name = "id_libreria"),
            inverseJoinColumns = @JoinColumn(name = "id_libro")
    )
    @JsonIgnore
    private List<Libro> libros;

    public Libreria(int id, String nombre, String propietario, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.propietario = propietario;
        this.direccion = direccion;
    }

    public Libreria() {
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPropietario() {
        return propietario;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Libreria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dueño='" + propietario + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
