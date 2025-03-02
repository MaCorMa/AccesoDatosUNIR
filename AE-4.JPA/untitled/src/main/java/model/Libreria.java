package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "librerias")
public class Libreria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private List<Libro> libros;

    public Libreria(Long id, String nombre, String propietario, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.propietario = propietario;
        this.direccion = direccion;
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
