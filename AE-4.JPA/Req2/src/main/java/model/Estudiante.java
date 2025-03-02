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
@Table(name = "estudiantes")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "num_matricula")
    private String numeroMatricula;

    @OneToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;


    public Estudiante(String numeroMatricula, Persona persona) {
        this.numeroMatricula = numeroMatricula;
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", numeroMatricula='" + numeroMatricula + '\'' +
                ", persona=" + persona +
                '}';
    }
}
