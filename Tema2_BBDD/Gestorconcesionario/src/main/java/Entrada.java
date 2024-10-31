import controller.Concesionario;
import model.Empleado;
import model.Tipo;

public class Entrada {

    public static void main(String[] args) {

        /*
        //Patron Singleton
        database.DBConnection dbConnection = new database.DBConnection();
        Connection connection = dbConnection.getConnection();

        //cerramos conexion
        //dbConnection.closeConnection();

        database.DBConnection dbConnection2 = new database.DBConnection();
        Connection connection2 = dbConnection2.getConnection();
        //hay dos conexiones pero solo se realiza una para ambas llamadas
        //dbConnection2.closeConnection();
        */

        Concesionario concesionario = new Concesionario();
        /*
        concesionario.insertarTrabajador(new Empleado("Manuel","Correcher",
                "correo@gmail.com", 123456, Tipo.INDEFINIDO));
         */
        //concesionario.borrarUsuario(3);
        //concesionario.leerUsuario(1);
        concesionario.agregarCoche();
    }
}
