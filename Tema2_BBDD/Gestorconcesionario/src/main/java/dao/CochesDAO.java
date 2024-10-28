package dao;

import database.DBConnection;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;

//Clase destinada a la gestion de los coches contra la BBDD → queries
public class CochesDAO {

    private Connection connection;

    private PreparedStatement preparedStatement;

    public CochesDAO(){

        //patron singleton desde clase Connection
        connection = new DBConnection().getConnection();
    }

    //Añadir coche a la BBDD

    public void addCoche(Coche coche){
        String query =  String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?,?,?,?)")
        preparedStatement = connection.prepareStatement();
    }

}
