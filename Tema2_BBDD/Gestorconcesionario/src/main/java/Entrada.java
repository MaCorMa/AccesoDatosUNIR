import java.sql.Connection;

public class Entrada {

    public static void main(String[] args) {

        //Patron Singleton
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();

        //cerramos conexion
        //dbConnection.closeConnection();

        DBConnection dbConnection2 = new DBConnection();
        Connection connection2 = dbConnection2.getConnection();
        //hay dos conexiones pero solo se realiza una para ambas llamadas
        //dbConnection2.closeConnection();
    }
}
