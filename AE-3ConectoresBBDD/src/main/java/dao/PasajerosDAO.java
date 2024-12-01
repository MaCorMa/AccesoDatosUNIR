package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasajerosDAO {
    //conexión
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    //Constructor por defecto
    public PasajerosDAO(){
        connection = new DBConnection().getConnection();
    }

    public void insertarPasajero(Pasajero pasajero) throws SQLException { //se queda el throw ahí para tratar en el controller
        // para hacer el insert → prepareStatement
        // connection
        String query = String.format("INSERT INTO %s (%s, %s,%s,%s,%s) VALUES (?,?)",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_PAS_NAME,
                SchemaDB.COL_PAS_SURNAME);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, pasajero.getNombre());
        preparedStatement.setString(2, pasajero.getApellido());
        preparedStatement.execute();

    } //ejecutamos directamente sobre la BBDD, tenemos al DAO haciendo la gestión en lugar de desde el controller

    
    public Pasajero getPasajero(String nombre, String apellido){
        return new Pasajero(nombre,apellido);
    }
    public Pasajero getPasajero(int id) throws SQLException {
        preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s WHERE %s=?", SchemaDB.TAB_PAS,SchemaDB.COL_ID));
        preparedStatement.setInt(1, id);
        //para guardar el resultado de la queryresultSet
        resultSet  = preparedStatement.executeQuery();

        while(resultSet.next()){
            String nombre = resultSet.getString(SchemaDB.COL_PAS_NAME);
            String apelido = resultSet.getString(SchemaDB.COL_PAS_SURNAME);

            return getPasajero(nombre,apelido);
        }
        return null;
    }

    public void subirCoche(int idCoche, int idPasajero) throws SQLException{
        String query = String.format("INSERT INTO %s (%s, $s) VALUES (?, ?)",
                SchemaDB.TAB_PASCAR,
                SchemaDB.COL_PASCAR_IDCAR,
                SchemaDB.COL_PASCAR_IDPAS);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCoche);
        preparedStatement.setInt(2, idPasajero);
        preparedStatement.execute();
        System.out.println("Pasajero con ID " + idPasajero + " añadido al coche con ID " + idCoche);
    }

    public void bajarCoche(int idCoche, int idPasajero) throws SQLException {
        String query = String.format("DELETE FROM coche_pasajero WHERE id_coche = ? AND id_pasajero = ?",
                SchemaDB.TAB_PASCAR,
                SchemaDB.COL_PASCAR_IDCAR,
                SchemaDB.COL_PASCAR_IDPAS);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCoche);
        preparedStatement.setInt(2, idPasajero);
        preparedStatement.execute();
        System.out.println("Pasajero con ID " + idPasajero + " eliminado del coche con ID " + idCoche);
    }

    public void listarPasajerosEnCoches() throws SQLException {
        String query = "SELECT c.id AS coche_id, c.marca, c.modelo, p.id AS pasajero_id, p.nombre, p.apellido " +
                "FROM coches c " +
                "LEFT JOIN coche_pasajero cp ON c.id = cp.id_coche " +
                "LEFT JOIN pasajeros p ON cp.id_pasajero = p.id " +
                "ORDER BY c.id";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int cocheId = resultSet.getInt("coche_id");
            String marca = resultSet.getString("marca");
            String modelo = resultSet.getString("modelo");
            int pasajeroId = resultSet.getInt("pasajero_id");
            String nombre = resultSet.getString("nombre");
            String apellido = resultSet.getString("apellido");

            System.out.printf("Coche ID: %d, Marca: %s, Modelo: %s%n", cocheId, marca, modelo);
            if (pasajeroId != 0) {
                System.out.printf("\tPasajero ID: %d, Nombre: %s %s%n", pasajeroId, nombre, apellido);
            } else {
                System.out.println("\tSin pasajeros asociados.");
            }
        }
    }
}
