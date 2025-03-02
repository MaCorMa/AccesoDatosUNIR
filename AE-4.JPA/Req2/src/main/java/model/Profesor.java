package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profesores")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "num_empleado")
    private String numeroEmpleado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @OneToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    public Profesor(String numeroEmpleado, Departamento departamento, Persona persona) {
        this.numeroEmpleado = numeroEmpleado;
        this.departamento = departamento;
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", numeroEmpleado='" + numeroEmpleado + '\'' +
                ", departamento=" + departamento +
                ", persona=" + persona +
                '}';
    }
}
