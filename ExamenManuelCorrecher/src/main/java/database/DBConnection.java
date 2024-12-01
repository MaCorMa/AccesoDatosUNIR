package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

       private static Connection connection;

    public Connection getConnection(){
        //Si alguien pide una conexión

        //si no esta → la creo
        if (connection == null)  {
            //se crea
            newConnection();
        }
        //si esta → se la doy
        return connection;
    }

    private void newConnection() {

        String url = "jdbc:mysql://localhost:3306/examenAD";

        try {
            connection = DriverManager.getConnection(url,"root","");
        } catch (SQLException e) {
            System.out.println("Error en la conexión: "+e.getMessage());
        }
        System.out.println("Conexión creada correctamente");
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: "+e.getMessage());
        } finally {
            connection = null;
        }
    }
}
