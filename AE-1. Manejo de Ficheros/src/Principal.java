import java.util.Scanner;

public class Principal {
    private static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) {

        



    }

    public static String printMenu() {
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
        while (menu <=0 || menu>5) {
            System.out.println("Opción errónea, sólo de 1 a 5");
            menu = lector.nextInt();
        }
        return String.valueOf(menu);
    }
}
