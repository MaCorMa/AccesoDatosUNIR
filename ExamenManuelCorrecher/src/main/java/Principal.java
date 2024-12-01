import dao.UsuarioDAO;
import model.Usuario;

import java.sql.SQLException;
import java.util.Scanner;

public class Principal {


    static UsuarioDAO usuarioDAO = new UsuarioDAO();
    static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) {

            int opcion = 0;
            do {
                try {
                    System.out.println("\n--- Menú Gestión de Usuarios ---");
                    System.out.println("1. Registrar usuario");
                    System.out.println("2. Listar usuarios");
                    System.out.println("3. Comprobar credenciales");
                    System.out.println("4. Exportar usuarios");
                    System.out.println("5. Salir");
                    System.out.print("Seleccione una opción: ");

                    opcion = lector.nextInt();
                    lector.nextLine(); // Limpiar buffer

                    switch (opcion) {
                        case 1:
                            registraUsuario();
                            break;
                        case 2:
                            listaUsuarios();
                            break;
                        case 3:
                            checkLogin();
                            break;
                        case 4:
                            exportaUsuario();
                            break;
                        case 5:
                            System.out.println("SALIR");
                            break;
                        default:
                            System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    }
                }catch(Exception e){
                    System.out.println("Error");
                }
            } while (opcion != 5);

            lector.close();

    }

    public static void exportaUsuario() throws SQLException {
        usuarioDAO.exportaUsuarios();
    }

    private static void checkLogin() {

        boolean login = false;
        boolean bloqueo = false;
        int intentos = 4;

        do {
            System.out.println("3. Comprobar credenciales");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Indica el correo");
            String correo = scanner.next();
            System.out.println("Indica las pass");
            String pass = scanner.next();
            try {
                login = usuarioDAO.getLogin(correo, pass);
                intentos--;
                if (login) {
                    System.out.println("Login correcto, adelante");
                } else {
                    if (intentos == 0) {
                        bloqueo = true;
                    }
                    System.out.println("Login incorrecto, fallo");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(intentos);
        } while ( intentos !=0 && !login);

        if (intentos == 0 && bloqueo) {
            System.out.println("Caja bloqueda");
        }
    }

    public static void listaUsuarios() throws SQLException{
        try {
            System.out.println("2. Listar usuarios");
            for (Usuario usuario : usuarioDAO.listarUsuarios()) {
                System.out.println("ID de usuario: " + usuario.getCorreo() + ", Nombre de usuario: " + usuario.getNombre());
            }
        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
    }

    public static void registraUsuario() throws SQLException {

            System.out.println("1. Registrar usuario");
            System.out.print("Nombre: ");
            String nombre = lector.nextLine();
            

            System.out.print("Correo: ");
            String correo = lector.nextLine();

            System.out.print("Contraseña: ");
            String pass = lector.nextLine();
        Usuario nuevoUsuario = new Usuario(nombre,correo,pass);
        usuarioDAO.addUser(nuevoUsuario);

    }




}
