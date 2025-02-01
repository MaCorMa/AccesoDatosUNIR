package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Columns;

import java.io.Serializable;
import java.util.List;

//@Data sustituye a todos estos
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@NamedQuery(
        name = "Trabajador.findAll",
        query = "FROM Trabajador"
)
@NamedQuery(
        name = "Trabajador.findAllByLocalidad", query = "SELECT t FROM Trabajador t WHERE t.direccion.localidad=:localidad"
)
@NamedQuery(
        name = "Trabajador.findAllByProvincia", query = "SELECT t FROM Trabajador t WHERE t.direccion.provincia=:provincia"
)
@NamedQuery(
        name = "Trabajador.updateName", query = "UPDATE Trabajador t SET t.nombre=:nombre WHERE t.telefono=:telefono"
)




@Entity
@Table(name = "empleados")
 
public class Trabajador implements Serializable {
    //serialVerionUID → A ficheros
    @Id //al ser PK va con @ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indica que es un elemento generado por la BD
    private int id;
    @Column //(name="name")en caso de que no tuviera el mismo nombre que en la bbdd
    private String nombre;
    @Column
    private String apellido;
    @Embedded
    private Direccion direccion;
    //Al añadirle otro atributo de la misma clase, es necesario aclarar a la bbdd cuál enviar a qué columna
    @Embedded
    @AttributeOverrides({
            //le indica que atributo del objeto va a que columna de la bd
            @AttributeOverride(name="localidad",column = @Column(name="localidad_2")),
            @AttributeOverride(name="provincia",column = @Column(name="provincia_2"))
    })
    private Direccion direccion2;
    @Column
    private String telefono;

    @OneToOne(cascade = CascadeType.ALL)//si se actualiza, los asociados también
    @JoinColumn(name="id_habitacion")//se indica el nombre de la columna que recibe la relación
    private Habitacion habitacion;

    //conf ManyToMany
    //en clase cliente hay un atributo llamado listaTrabajadores
    @ManyToMany(mappedBy = "listaTrabajadores",fetch = FetchType.EAGER) //eager para que en el momento pida la relacion lo haga
    private List<Cliente> listaCliente;




    @Override
    public String toString() {
        return "Trabajador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion=" + direccion +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public Trabajador(String nombre, String apellido, Direccion direccion, Direccion direccion2, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.direccion2 = direccion2;
        this.telefono = telefono;
    }

    public Trabajador(String nombre, String apellido, Direccion direccion, Direccion direccion2, String telefono, Habitacion habitacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.direccion2 = direccion2;
        this.telefono = telefono;
        this.habitacion = habitacion;
    }

    public Trabajador(int id) {
        this.id = id;
    }


}
