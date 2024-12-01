import dao.AlumnosDAO;
import dao.ProfesoresDAO;
import model.Alumno;
import model.Profesor;

import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {

    static AlumnosDAO alumnosDAO = new AlumnosDAO();
    static ProfesoresDAO profesoresDAO = new ProfesoresDAO();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    insertarProfesor();
                    break;
                case 2:
                    insertarAlumno();
                    break;
                case 3:
                    System.out.println(profesoresDAO.mostrarProfesores());
                    System.out.println(alumnosDAO.mostrarAlumnos());
                    break;
                case 4:
                    System.out.println(profesoresDAO.mostrarProfesores());
                    break;
                case 5:
                    System.out.println(alumnosDAO.mostrarAlumnos());
                    break;
                case 6:
                    String mail = scanner.nextLine();
                    System.out.println(alumnosDAO.buscarAlumno(mail));
                    break;
                case 7:
                    int min, max;
                    System.out.println("Indica edad mínima");
                    min = scanner.nextInt();
                    System.out.println("Indica edad máxima");
                    max = scanner.nextInt();
                    System.out.println(profesoresDAO.buscarProfeEdad(min, max));
                    break;
                case 8:
                    System.out.print("Ingrese el email del profesor que desea actualizar: ");
                    String email = scanner.nextLine();
                    System.out.print("Ingrese la nueva calificación del profesor: ");
                    double rating = scanner.nextDouble();
                    profesoresDAO.actualizarProfesor(email,rating);
                    break;
                case 9:
                    alumnosDAO.darBaja();
                    break;
                case 10:
                    System.out.println("SALIDA");
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        } while (opcion != 10);
    }

    private static void mostrarMenu() {
        System.out.println("\n Bienvenido");
        System.out.println("1. Insertar un profesor");
        System.out.println("2. Insertar un alumno");
        System.out.println("3. Mostrar todos los datos");
        System.out.println("4. Mostrar profesores");
        System.out.println("5. Mostrar alumnos");
        System.out.println("6. Buscar alumno");
        System.out.println("7. Buscar profesor");
        System.out.println("8. Actualizar profesor");
        System.out.println("9. Dar de baja alumnos");
        System.out.println("10. Salir");
        System.out.print("Seleccione una opción: ");
    }

    static void insertarProfesor() {

        System.out.print("Ingrese rating del profesor: ");
        double rating = scanner.nextDouble();
        System.out.print("Ingrese edad del profesor: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese nombre del profesor: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese género del profesor: ");
        String gender = scanner.nextLine();
        System.out.print("Ingrese email del profesor: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese telefono del profesor: ");
        String phone = scanner.nextLine();
        // asignaturas
        ArrayList<String> subjects = new ArrayList<>();
        System.out.println("Ingrese las asignaturas del profesor, escribe FIN para termnar de añadir:");
        while (true) {
            String subject = scanner.nextLine();
            if (subject.equals("FIN")) {
                break;
            }
            subjects.add(subject);
        }
        System.out.print("Ingrese el título del profesor: ");
        String title = scanner.nextLine();

        profesoresDAO.insertarProfesor(new Profesor(rating, age, name, gender, email, phone, subjects, title));
        System.out.println("Profesor agregado con éxito.");
    }

    static void insertarAlumno() {
        System.out.print("Ingrese el nombre del alumno: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el género del alumno: ");
        String gender = scanner.nextLine();
        System.out.print("Ingrese el email del alumno: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese el teléfono del alumno: ");
        String phone = scanner.nextLine();
        System.out.print("Ingrese la edad del alumno: ");
        int age = scanner.nextInt();
        System.out.print("Ingrese la calificación del alumno: ");
        int calification = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el grado académico del alumno: ");
        String higherGrade = scanner.nextLine();
        System.out.print("¿Ha realizado FCT? (true/false): ");
        boolean fct = scanner.nextBoolean();
        System.out.print("Ingrese el rating del alumno: ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer
        alumnosDAO.insertarAlumno(new Alumno(rating,age,name,gender,email,phone,calification,higherGrade,fct));
        System.out.println("Alumno agregado con éxito.");
    }
}
