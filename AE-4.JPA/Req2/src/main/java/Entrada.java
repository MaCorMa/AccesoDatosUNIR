import DAO.*;
import database.HibernateUtil;
import model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entrada {

    private static PersonaDAO personaDAO = new PersonaDAO();
    private static EstudianteDAO estudianteDAO = new EstudianteDAO();
    private static ProfesorDAO profesorDAO = new ProfesorDAO();
    private static DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    private static CursoDAO cursoDAO = new CursoDAO();

    public static void main(String[] args) {

        // Insertar
        List<Persona> personas = insertarPersonas();
        List<Departamento> departamentos = insertarDepartamentos();
        List<Estudiante> estudiantes = insertarEstudiantes(personas);
        List<Profesor> profesores = insertarProfesores(personas, departamentos);
        List<Curso> cursos = insertarCursos(departamentos);

        // Mostrar insertados
        mostrarPersonas();
        mostrarEstudiantes();
        mostrarProfesores();
        mostrarDepartamentos();
        mostrarCursos();
    }

    private static List<Persona> insertarPersonas() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Manuel Correcher", new Date()));
        personas.add(new Persona("Iván Pérez", new Date()));
        personas.add(new Persona("Mario Gómez", new Date()));

        for (Persona persona : personas) {
            personaDAO.altaPersona(persona);
        }
        System.out.println("Personas añadidas");
        return personas;
    }

    private static List<Departamento> insertarDepartamentos() {
        List<Departamento> departamentos = new ArrayList<>();
        departamentos.add(new Departamento("Inglés"));
        departamentos.add(new Departamento("Matemáticas"));

        for (Departamento departamento : departamentos) {
            departamentoDAO.altaDepartamento(departamento);
        }
        System.out.println("Departamentos añadidos");
        return departamentos;
    }

    private static List<Estudiante> insertarEstudiantes(List<Persona> personas) {
        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante("11111111", personas.get(0)));
        estudiantes.add(new Estudiante("22222222", personas.get(1)));

        for (Estudiante estudiante : estudiantes) {
            estudianteDAO.altaEstudiante(estudiante);
        }

        System.out.println("Estudiantes añadidos");
        return estudiantes;
    }

    private static List<Profesor> insertarProfesores(List<Persona> personas, List<Departamento> departamentos) {
        List<Profesor> profesores = new ArrayList<>();
        profesores.add(new Profesor( "Titular001", departamentos.get(0), personas.get(2)));

        for (Profesor profesor : profesores) {
            profesorDAO.altaProfesor(profesor);
        }
        System.out.println("Profesores añadidos");
        return profesores;
    }

    private static List<Curso> insertarCursos(List<Departamento> departamentos) {
        List<Curso> cursos = new ArrayList<>();
        cursos.add(new Curso("DAM", 4));
        cursos.add(new Curso("DAW", 3));

        for (Curso curso : cursos) {
            cursoDAO.altaCurso(curso);
        }
        System.out.println("Cursos añadidos");
        return cursos;
    }

    private static void mostrarPersonas() {
        List<Persona> personas = personaDAO.getAllPersonas();
        for (Persona persona : personas) {
            System.out.println("ID: "+persona.getId()+", Nombre:" + persona.getNombre());
        }
    }

    private static void mostrarEstudiantes() {
        List<Estudiante> estudiantes = estudianteDAO.getAllEstudiantes();
        for (Estudiante estudiante : estudiantes) {
            System.out.println("ID: "+estudiante.getPersona().getId()+
                    ", Nombre:" + estudiante.getPersona().getNombre()+
                    ", Matrícula: "+ estudiante.getNumeroMatricula());
        }
    }

    private static void mostrarProfesores() {
        List<Profesor> profesores = profesorDAO.getAllProfesores();
        for (Profesor profesor : profesores) {
            System.out.println("ID: "+profesor.getPersona().getId()+
                    ", Nombre:" + profesor.getPersona().getNombre()+
                    ", Empleado: "+ profesor.getNumeroEmpleado()+
                    ", Departamento: "+profesor.getDepartamento().getNombre());
        }
    }

    private static void mostrarDepartamentos() {
        List<Departamento> departamentos = departamentoDAO.getAllDepartamentos();
        for (Departamento departamento : departamentos) {
            System.out.println(departamento.getNombre());
        }
    }

    private static void mostrarCursos() {
        List<Curso> cursos = cursoDAO.getAllCursos();
        for (Curso curso : cursos) {
            System.out.println(curso.getNombreCurso());
        }
    }
}
