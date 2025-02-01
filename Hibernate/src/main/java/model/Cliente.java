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
@Table(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;

    @ManyToOne(cascade = CascadeType.ALL) //muchos clientes se pueden alojar en una habitacion
    @JoinColumn(name = "id_habitacion")
    private Habitacion habitacion;

    //configurar many to many
    //tipo de carga, como se obtienen los datos, la selección dependerá del tipo de datos que queramos tratar
    //@ManyToMany(fetch = FetchType.LAZY) //lazy se hace después del resto de mapeos, destina recursos a otras relaciones
    @ManyToMany(fetch = FetchType.EAGER) //eager, carga instantanea. Carga en el momento que se indica
    // se indica la tabla con la que hay relación y las columnas con las que hay relación (array)
    @JoinTable(name = "reservas",
            joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_trabajador")) 
    private List<Trabajador> listaTrabajadores;



    public Cliente(String nombre) {
        this.nombre = nombre;
    }
}
