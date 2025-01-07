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
        String query =  String.format("INSERT INTO %s ( %s, %s, %s, %s) VALUES (?,?,?,?)",
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

    public Coche getCocheId(int id) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                SchemaDB.TAB_CH, SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String marca = resultSet.getString(SchemaDB.COL_CH_MAR);
                    String modelo = resultSet.getString(SchemaDB.COL_CH_MOD);
                    int cv = resultSet.getInt(SchemaDB.COL_CH_CV);
                    int precio = resultSet.getInt(SchemaDB.COL_CH_PRE);
                    return mapearCoche(marca, modelo, cv, precio);
                }
        return null; //Devuelve null si no encuentra coche
    }

    public boolean borraCocheId(int id) throws SQLException {
        Coche coche = getCocheId(id);
        if (coche != null) {
            // query para borrar si el id existe
            String query = String.format("DELETE FROM %s WHERE %s = ?",
                    SchemaDB.TAB_CH, SchemaDB.COL_ID);
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0; // Devuelve true si se borra 1 fila
        }else{
            return false; //Devuelve false si no encuentra id coche
        }
    }


    public boolean updateCocheById(int id, Coche modCoche) throws SQLException {
        Coche existingCoche = getCocheId(id);
        if (existingCoche != null) {
            // query para el update
            String query = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
                    SchemaDB.TAB_CH,
                    SchemaDB.COL_CH_MAR,
                    SchemaDB.COL_CH_MOD,
                    SchemaDB.COL_CH_CV,
                    SchemaDB.COL_CH_PRE,
                    SchemaDB.COL_ID);
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, modCoche.getMarca());
            preparedStatement.setString(2, modCoche.getModelo());
            preparedStatement.setInt(3, modCoche.getCv());
            preparedStatement.setInt(4, modCoche.getPrecio());
            preparedStatement.setInt(5, id);

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0; // Devuelve true si se modifica fila
        }else {
            return false; //Devuelve false si no encuentra id coche
        }
    }

    public ArrayList<Coche> getTodosCoches() throws SQLException {
        //saca cuantos coches de cada marca hay en el concesionario
        String query = String.format("SELECT * FROM %s", SchemaDB.TAB_CH);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        return  getResultados(resultSet);
    }


    private Coche mapearCoche(String marca, String modelo, int cv, int precio){
        return new Coche(marca,modelo,cv,precio);
    }


    private ArrayList<Coche> getResultados(ResultSet datosResultantes) throws SQLException {

        ArrayList<Coche> listaResutlado = new ArrayList<>();
        while(datosResultantes.next()) {
            String marca = datosResultantes.getString(SchemaDB.COL_CH_MAR);
            String modelo = datosResultantes.getString(SchemaDB.COL_CH_MOD);
            int cv = datosResultantes.getInt(SchemaDB.COL_CH_CV);
            int precio = datosResultantes.getInt(SchemaDB.COL_CH_PRE);

            listaResutlado.add(mapearCoche(marca, modelo, cv, precio));
        }
        return  listaResutlado;
    }

}
