import dao.CochesDAO;
import dao.PasajerosDAO;
import model.Coche;
import model.Pasajero;

import java.util.Scanner;
import java.sql.SQLException;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CochesDAO cochesDAO = new CochesDAO();
        PasajerosDAO pasajerosDAO = new PasajerosDAO();

        while (true) {
            System.out.println("\nMenu Principal");
            System.out.println("1. Añadir nuevo coche");
            System.out.println("2. Borrar coche por ID");
            System.out.println("3. Consulta coche por ID");
            System.out.println("4. Modificar coche por ID");
            System.out.println("5. Listado de coches");

            System.out.println("\n7. Añadir nuevo pasajero");
            System.out.println("8. Borrar pasajero por ID");
            System.out.println("9. Consulta pasajero por ID");
            System.out.println("10. Listar todos los pasajeros");
            System.out.println("11. Añadir pasajero a coche");
            System.out.println("12. Eliminar pasajero de un coche");
            System.out.println("13. Listar todos los pasajeros de un coche");

            System.out.println("\n6. Terminar el programa");

            System.out.print("Elija una opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1: // Añadir nuevo coche
                    addCoche(cochesDAO, scanner);
                    break;
                case 2: // Borrar coche por ID
                    deleteCocheId(cochesDAO, scanner);
                    break;
                case 3: // Consulta coche por ID
                    getCocheId(cochesDAO, scanner);
                    break;
                case 4: // Modificar coche por ID
                    modifcaCocheId(cochesDAO, scanner);
                    break;
                case 5: // Listado de coches
                    listAllCoches(cochesDAO);
                    break;
                case 6: // Terminar el programa
                    System.out.println("Programa terminado.");
                    scanner.close();
                    return;
                case 7: // Añadir nuevo pasajero
                    addPasajero(pasajerosDAO, scanner);
                    break;
                case 8: // Borrar pasajero por ID
                    borraPasajeroId(pasajerosDAO, scanner);
                    break;
                case 9: // Consulta pasajero por ID
                    getPasajeroById(pasajerosDAO, scanner);
                    break;
                case 10: // Listar todos los pasajeros
                    listAllPasajeros(pasajerosDAO);
                    break;
                case 11: // Añadir pasajero a coche
                    subirCoche(pasajerosDAO, scanner);
                    break;
                case 12: // Eliminar pasajero de un coche
                    bajarCoche(pasajerosDAO, scanner);
                    break;
                case 13: // Listar todos los pasajeros de un coche
                    listarPasajerosEnCoches(pasajerosDAO, scanner);
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        }
    }

    private static void addCoche(CochesDAO cochesDAO, Scanner scanner) {
        System.out.println("Añadir nuevo coche");
        System.out.print("Marca: ");
        String marca = scanner.next();
        System.out.print("Modelo: ");
        String modelo = scanner.next();
        System.out.print("CV: ");
        int cv = scanner.nextInt();
        System.out.print("Precio: ");
        int precio = scanner.nextInt();
        Coche coche = new Coche(marca, modelo, cv, precio);

        try {
            cochesDAO.addCoche(coche);
            System.out.println("Coche añadido correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al añadir el coche: " + e.getMessage());
        }
    }


    private static void deleteCocheId(CochesDAO cochesDAO, Scanner scanner) {
        System.out.print("Introduzca el ID del coche a borrar: ");
        int id = scanner.nextInt();
        try {
            if (cochesDAO.borraCocheId(id)) {
                System.out.println("Coche borrado correctamente.");
            } else {
                System.out.println("No se encontró el coche con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al borrar el coche: " + e.getMessage());
        }
    }


    private static void getCocheId(CochesDAO cochesDAO, Scanner scanner) {
        System.out.print("Introduzca el ID del coche a consultar: ");
        int id = scanner.nextInt();
        try {
            Coche coche = cochesDAO.getCocheId(id);
            if (coche != null) {
                coche.mostrarDatos();
            } else {
                System.out.println("Coche no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar el coche: " + e.getMessage());
        }
    }


    private static void modifcaCocheId(CochesDAO cochesDAO, Scanner scanner) {
        System.out.print("Introduzca el ID del coche a modificar: ");
        int id = scanner.nextInt();
        System.out.print("Nueva marca: ");
        String marca = scanner.next();
        System.out.print("Nuevo modelo: ");
        String modelo = scanner.next();
        System.out.print("Nuevo CV: ");
        int cv = scanner.nextInt();
        System.out.print("Nuevo precio: ");
        int precio = scanner.nextInt();
        Coche modCoche = new Coche(marca, modelo, cv, precio);

        try {
            if (cochesDAO.updateCocheById(id, modCoche)) {
                System.out.println("Coche modificado correctamente.");
            } else {
                System.out.println("No se encontró el coche con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar el coche: " + e.getMessage());
        }
    }


    private static void listAllCoches(CochesDAO cochesDAO) {
        try {
            var coches = cochesDAO.getTodosCoches();
            for (Coche coche : coches) {
                coche.mostrarDatos();
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los coches: " + e.getMessage());
        }
    }


    private static void addPasajero(PasajerosDAO pasajerosDAO, Scanner scanner) {
        System.out.println("Añadir nuevo pasajero");
        System.out.print("Nombre: ");
        String nombre = scanner.next();
        System.out.print("Apellido: ");
        String apellido = scanner.next();
        Pasajero pasajero = new Pasajero(nombre, apellido);

        try {
            pasajerosDAO.insertarPasajero(pasajero);
            System.out.println("Pasajero añadido correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al añadir el pasajero: " + e.getMessage());
        }
    }


    private static void borraPasajeroId(PasajerosDAO pasajerosDAO, Scanner scanner) {
        System.out.print("Introduzca el ID del pasajero a borrar: ");
        int id = scanner.nextInt();
        try {
            pasajerosDAO.borrarPasajeroId(id);
        } catch (SQLException e) {
            System.out.println("Error al borrar el pasajero: " + e.getMessage());
        }
    }


    private static void getPasajeroById(PasajerosDAO pasajerosDAO, Scanner scanner) {
        System.out.print("Introduzca el ID del pasajero a consultar: ");
        int id = scanner.nextInt();
        try {
            Pasajero pasajero = pasajerosDAO.getPasajeroId(id);
            if (pasajero != null) {
                pasajero.mostrarDatos();
            } else {
                System.out.println("Pasajero no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar el pasajero: " + e.getMessage());
        }
    }

    // List all passengers
    private static void listAllPasajeros(PasajerosDAO pasajerosDAO) {
        try {
            var pasajeros = pasajerosDAO.getTodosPasajeros();
            for (Pasajero pasajero : pasajeros) {
                pasajero.mostrarDatos();
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los pasajeros: " + e.getMessage());
        }
    }


    private static void subirCoche(PasajerosDAO pasajerosDAO, Scanner scanner) {
        System.out.print("Introduzca el ID del coche: ");
        int idCoche = scanner.nextInt();
        System.out.print("Introduzca el ID del pasajero: ");
        int idPasajero = scanner.nextInt();
        try {
            pasajerosDAO.subirCoche(idCoche, idPasajero);
        } catch (SQLException e) {
            System.out.println("Error al añadir el pasajero al coche: " + e.getMessage());
        }
    }


    private static void bajarCoche(PasajerosDAO pasajerosDAO, Scanner scanner) {
        System.out.print("Introduzca el ID del coche: ");
        int idCoche = scanner.nextInt();
        System.out.print("Introduzca el ID del pasajero: ");
        int idPasajero = scanner.nextInt();
        try {
            pasajerosDAO.bajarCoche(idCoche, idPasajero);
        } catch (SQLException e) {
            System.out.println("Error al eliminar el pasajero del coche: " + e.getMessage());
        }
    }


    private static void listarPasajerosEnCoches(PasajerosDAO pasajerosDAO, Scanner scanner) {
        System.out.print("Introduzca el ID del coche: ");
        int idCoche = scanner.nextInt();
        try {
            pasajerosDAO.listarPasajerosEnCoches(idCoche);
        } catch (SQLException e) {
            System.out.println("Error al listar los pasajeros del coche: " + e.getMessage());
        }
    }
}
