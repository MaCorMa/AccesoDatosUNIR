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
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombreCurso;

    @Column(name = "creditos")
    private int creditos;

    @ManyToMany(mappedBy = "cursos",fetch = FetchType.EAGER)
    private List<Departamento> departamentos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cursos_estudiante",
            joinColumns = @JoinColumn(name = "id_curso"),
            inverseJoinColumns = @JoinColumn(name = "id_estudiante")
    )
    private List<Estudiante> estudiantes;

    public Curso(String nombreCurso, int creditos) {
        this.nombreCurso = nombreCurso;
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombreCurso='" + nombreCurso + '\'' +
                ", creditos=" + creditos +
                ", departamentos=" + departamentos +
                ", estudiantes=" + estudiantes +
                '}';
    }
}
