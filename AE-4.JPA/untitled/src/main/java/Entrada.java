import DAO.AutorDAO;
import DAO.EditorialDAO;
import DAO.LibreriaDAO;
import DAO.LibroDAO;
import database.HibernateUtil;
import model.Autor;
import model.Editorial;
import model.Libreria;
import model.Libro;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entrada {

    private static AutorDAO autorDAO = new AutorDAO();
    private static LibroDAO libroDAO = new LibroDAO();
    private static LibreriaDAO libreriaDAO = new LibreriaDAO();
    private static EditorialDAO editorialDAO = new EditorialDAO();

    public static void main(String[] args) {

        
        List<Autor> autores = insertarAutores();
        List<Editorial> editoriales = insertarEditoriales();
        List<Libro> libros = insertarLibros(autores, editoriales);
        insertarLibrerias(libros);

        mostrarLibros();
        mostrarAutores();
        mostrarLibrerias();
        mostrarLibrosByLibreria();
    }

    private static void mostrarLibrosByLibreria(){
        List<Libro> listaLibros = libroDAO.getAllLibros();
        for (Libro libro:listaLibros){
            System.out.println("Titulo: "+ libro.getTitulo()+", " +
                    "Autor: "+libro.getAutor().getNombre()+", "+libro.getAutor().getApellidos()+", " +
                    "Editorial: "+libro.getEditorial().getNombre());
            for (Libreria libreria : libro.getLibrerias()){
                System.out.println("Libreria: "+libreria.getNombre());
            }
        }
    }

    private static void mostrarLibrerias() {
       List<Libreria>listaLibreria=libreriaDAO.getAllLibrerias();
       for (Libreria libreria : listaLibreria){
           System.out.println(libreria.getNombre());
           for (Libro libro : libreria.getLibros()){
               System.out.println("Titulo: "+libro.getTitulo() +", " +
                       "Editorial:"+libro.getEditorial().getNombre()+
                       ", Autor: "+libro.getAutor().getNombre()+", "+libro.getAutor().getApellidos());
           }
       }
    }

    private static void mostrarLibros() {
        List<Libro> listaLibros = libroDAO.getAllLibros();
        for (Libro libro:listaLibros){
            System.out.println("Titulo: "+ libro.getTitulo()+", " +
                    "Autor: "+libro.getAutor().getNombre()+", "+libro.getAutor().getApellidos()+", " +
                    "Editorial: "+libro.getEditorial().getNombre());
        }
    }

    private static void mostrarAutores(){
        List<Autor>listaAutores = autorDAO.getAllAutores();
        for(Autor autor:listaAutores){
            System.out.println(autor.getNombre()+", "+autor.getApellidos());
            for (Libro libro : autor.getLista()){
                System.out.println("Titulo: "+libro.getTitulo() +", Editorial:"+libro.getEditorial().getNombre());
            }
        }
    }

    private static List<Autor> insertarAutores() {

        List<Autor> autores = new ArrayList<>();

        autores.add(new Autor(null, "Cixin", "Liu", new Date()));
        autores.add(new Autor(null, "James S. A.", "Corey", new Date()));
        autores.add(new Autor(null, "Miguel de", "Cervantes", new Date()));

        for (Autor autor : autores) {
            autorDAO.altaAutor(autor);
        }


        System.out.println("Autores insertados correctamente.");
        return autores;
    }

    private static List<Editorial> insertarEditoriales() {
        List<Editorial> editoriales = new ArrayList<>();
        editoriales.add(new Editorial(null, "Nova", "Calle UNIR 1"));
        editoriales.add(new Editorial(null, "Planeta", "Calle DAM 1"));

        for (Editorial editorial : editoriales) {
            editorialDAO.altaEditorial(editorial);
        }

        System.out.println("Editoriales insertados correctamente.");
        return editoriales;
    }

    private static List<Libro> insertarLibros(List<Autor> autores, List<Editorial> editoriales) {
        List<Libro> libros = new ArrayList<>();
        libros.add(new Libro(null, "El problema de los tres cuerpos", 20.0, autores.get(0), editoriales.get(0)));
        libros.add(new Libro(null, "El bosque oscuro", 22.0, autores.get(0), editoriales.get(0)));
        libros.add(new Libro(null, "El fin de la muerte", 25.0, autores.get(0), editoriales.get(0)));

        libros.add(new Libro(null, "El despertar del Leviatán", 18.0, autores.get(1), editoriales.get(1)));
        libros.add(new Libro(null, "La guerra de Calibán", 19.5, autores.get(1), editoriales.get(1)));
        libros.add(new Libro(null, "La puerta de Abadón", 21.0, autores.get(1), editoriales.get(1)));

        libros.add(new Libro(null, "Don Quijote de la Mancha", 30.0, autores.get(2), editoriales.get(1)));
        libros.add(new Libro(null, "Novelas ejemplares", 27.0, autores.get(2), editoriales.get(1)));

        for (Libro libro : libros) {
            libroDAO.altaLibro(libro);
        }

        System.out.println("Libros insertados correctamente.");
        return libros;
    }

    private static void insertarLibrerias(List<Libro> libros) {
        List<Libro> librosLibreria1 = new ArrayList<>();
        librosLibreria1.add(libros.get(0));
        librosLibreria1.add(libros.get(1));
        librosLibreria1.add(libros.get(3));
        librosLibreria1.add(libros.get(6));

        List<Libro> librosLibreria2 = new ArrayList<>();
        librosLibreria2.add(libros.get(2));
        librosLibreria2.add(libros.get(4));
        librosLibreria2.add(libros.get(5));
        librosLibreria2.add(libros.get(7));

        Libreria libreria1 = new Libreria(null, "Librería UNIR", "Borja", "Calle UNIR 2", librosLibreria1);
        Libreria libreria2 = new Libreria(null, "Librería DAM", "Manuel", "Calle DAM 2", librosLibreria2);

        libreriaDAO.altaLibreria(libreria1);
        libreriaDAO.altaLibreria(libreria2);

        System.out.println("Librerías insertadas correctamente.");
    }
}