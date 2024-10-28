package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Empleado;
import model.Tipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoDAO {

     //conexión
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    //Constructor por defecto
    public EmpleadoDAO(){
        connection = new DBConnection().getConnection();
    }

    public void insertarEmpleado(Empleado empleado) throws SQLException { //se queda el throw ahí para tratar en el controller
        // para hacer el insert → prepareStatement
        // connection
        preparedStatement = connection.prepareStatement(String.format("INSERT INTO %s (%s, %s,%s,%s,%s) VALUES (?,?,?,?,?)",
                SchemaDB.TAB_EMP,
                SchemaDB.COL_EMP_NAME,
                SchemaDB.COL_EMP_SURNAME,
                SchemaDB.COL_EMP_MAIL,
                SchemaDB.COL_EMP_PHO,
                SchemaDB.COL_EMP_KIN));
        preparedStatement.setString(1, empleado.getNombre());
        preparedStatement.setString(2, empleado.getApellido());
        preparedStatement.setInt(3, empleado.getTelefono());
        preparedStatement.setString(4, empleado.getCorreo());
        preparedStatement.setInt(5, empleado.getTipo().getId());
        preparedStatement.execute();
    } //ejecutamos directamente sobre la BBDD, tenemos al DAO haciendo la gestión en lugar de desde el controller

    public Empleado getEmpleado(String nombre, String apellido, String correo, int telefono, int tipo){
        Tipo tipo1 = null;
        switch (tipo){
            case 1:
                tipo1 = Tipo.EXTERNO;
                break;
            case 2:
                tipo1 = Tipo.INDEFINIDO;
                break;
            case 3:
                tipo1 = Tipo.BECARIO;
                break;
        }
        return new Empleado(nombre,apellido,correo,telefono,tipo1);
    }
    public Empleado getEmpleado(int id) throws SQLException {
      preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s WHERE %s=?", SchemaDB.TAB_EMP,SchemaDB.COL_ID));
      preparedStatement.setInt(1, id);
      //para guardar el resultado de la queryresultSet
      resultSet  = preparedStatement.executeQuery();

      while(resultSet.next()){
          String nombre = resultSet.getString(SchemaDB.COL_EMP_NAME);
          String apelido = resultSet.getString(SchemaDB.COL_EMP_SURNAME);
          int telefono = resultSet.getInt(SchemaDB.COL_EMP_PHO);
          String correo = resultSet.getString(SchemaDB.COL_EMP_MAIL);
          int tipo = resultSet.getInt(SchemaDB.COL_EMP_KIN);

          return getEmpleado(nombre,apelido,correo,telefono,tipo);
      }
      return null;
    }
}
