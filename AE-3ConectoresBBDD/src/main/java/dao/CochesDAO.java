package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CochesDAO {

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    public CochesDAO(){
        //singleton para hacer la conexión con la BBDD al iniciar CochesDAO
        connection = new DBConnection().getConnection();
    }

    //añadir
    public void addCoche(Coche coche) throws SQLException{

        //se usa mediante preparedStatement
        String query =  String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?,?,?,?,?)",
                SchemaDB.TAB_CH,
                SchemaDB.COL_CH_MAR,
                SchemaDB.COL_CH_MOD,
                SchemaDB.COL_CH_CV,
                SchemaDB.COL_CH_PRE);
        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, coche.getMarca());
        preparedStatement.setString(2, coche.getModelo());
        preparedStatement.setInt(3, coche.getCv());
        preparedStatement.setInt(4, coche.getPrecio());
        preparedStatement.execute();
    }


    public ArrayList<Coche> getCochesMarca(String marcaParam) throws SQLException {
        //saca cuantos coches de cada marca hay en el concesionario
        String query = String.format("SELECT * FROM %s WHERE %s=?", SchemaDB.TAB_CH, SchemaDB.COL_CH_MAR);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, marcaParam);
        resultSet = preparedStatement.executeQuery();

        return  getResultados(resultSet);
    }


    private Coche mapearCoche(String marca, String modelo, int cv, int precio){
        return new Coche(marca,modelo,cv,precio);
    }


    public ArrayList<Coche> getCochePrecio(int precioParam) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s<=?", SchemaDB.TAB_CH, SchemaDB.COL_CH_PRE);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,precioParam);
        resultSet = preparedStatement.executeQuery();

        return  getResultados(resultSet);
    }


    private ArrayList<Coche> getResultados(ResultSet datosResultantes) throws SQLException {

        ArrayList<Coche> listaResutlado = new ArrayList<>();
        while(resultSet.next()) {
            String marca = resultSet.getString(SchemaDB.COL_CH_MAR);
            String modelo = resultSet.getString(SchemaDB.COL_CH_MOD);
            int cv = resultSet.getInt(SchemaDB.COL_CH_CV);
            int precio = resultSet.getInt(SchemaDB.COL_CH_PRE);

            listaResutlado.add(mapearCoche(marca, modelo, cv, precio));
        }
        return  listaResutlado;
    }
}