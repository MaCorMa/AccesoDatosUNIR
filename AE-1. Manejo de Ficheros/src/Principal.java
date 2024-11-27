import dao.Concesionario;
import model.Coche;

import java.util.Scanner;

public class Principal {
    private static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) {

        Concesionario concesionario = new Concesionario();
        int menu;
        int id;

        do{
            menu = printMenu();

            //las opciones llaman a los metodos de la clase Concesionario
            switch (menu){
                case 1:
                    lector.nextLine();//para consumir última línea
                    System.out.print("Introduce la matrícula del coche: ");
                    String matricula = lector.nextLine();
                    System.out.print("Introduce la marca del coche: ");
                    String marca = lector.nextLine();
                    System.out.print("Introduce el modelo del coche: ");
                    String modelo = lector.nextLine();
                    System.out.print("Introduce el color del coche: ");
                    String color = lector.nextLine();
                    if(concesionario.altaCoche(new Coche(concesionario.listaTodo().size()+1,matricula,marca,modelo,color))){
                        System.out.println("Coche agregado correctamente.");
                    }  else{
                        System.out.println("No se ha podido agregar");
                    }
                    break;
                case 2:
                    System.out.print("Introduce el ID del coche a borrar: ");
                    id = lector.nextInt();
                    concesionario.borrarCoche(id);
                    break;
                case 3:
                    System.out.print("Introduce el ID del coche a buscar: ");
                    id = lector.nextInt();
                    System.out.println(concesionario.buscaCocheId(id));;
                    break;
                case 4:
                    System.out.print("Aquí está el listado completo: ");
                    System.out.println(concesionario.listaTodo());
                    break;
                case 5:
                    concesionario.exportaDat(concesionario.listaTodo());
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                case 6:
                    System.out.print("Exportando a csv: ");
                    concesionario.exportaCsv(concesionario.listaTodo());
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

        } while (menu !=5);
        

    }

    //metodo imprime menú en consola para usuario
    public static int printMenu() {
        int menu = 0;
        System.out.println("MENÚ:");
        System.out.println("1. Añadir nuevo coche");
        System.out.println("2. Borrar coche por ID");
        System.out.println("3. Consulta coche por ID");
        System.out.println("4. Listado de coches");
        System.out.println("5. Salida");
        System.out.println("6. Exportar a CSV");

        System.out.print("Elige una opción: ");
        menu = lector.nextInt();
        while (menu <=0 || menu>6) {
            System.out.println("Opción errónea, sólo de 1 a 6");
            menu = lector.nextInt();
        }
        return menu;
    }
}
