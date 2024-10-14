package controller;

import database.DBConnection;
import database.SchemaDB;
import model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Concesionario {
    //CRUD
    //Create, Update, Delete → Modifican la tabla
    //Read → Obtiene vector de valores


    //Statement → Query directa → Insert into empleados (nombre. apellido...) VALUES ('Manuel', 'Correcher', ...)
        //True o false
        //nº filas afectadas

    //PrepareStatement → Query con template → Insert into empleados (nombre. apellido...) VALUES (?,?, ...)
    //setInt(4,123)→mete el Int 123 en el 4º hueco
    //setString(1, "Manuel")→mete el String "Manuel" en el 1er hueco
    
    //aquí haremos las queries

    //insertar trabajador: ejemplo

    public void insertarTrabajador(Empleado empleado){

        //Con el Statement

        //Connection  → Statement (query) → execute

        Connection connection = new DBConnection().getConnection();
        //ya tenemos acceso a la BD

        try {
            Statement statement = connection.createStatement();
            //statement.execute(); //hay o no fallo, boolean
            //statement.executeUpdate(); //cuantas filas han sido afectadas, int

            //Con Statement
            /*String query = "INSERT INTO "+ SchemaDB.TAB_EMP+" ("+SchemaDB.COL_EMP_NAME+","+SchemaDB.COL_EMP_SURNAME
                    +","+SchemaDB.COL_EMP_MAIL+","+SchemaDB.COL_EMP_PHO+") VALUES ('"+empleado.getNombre()+"','"
                    +empleado.getApellido()+"','"+empleado.getCorreo()+"','"+empleado.getTelefono()+")";  */


            //Con Statement pero con sustitución

            // %s String
            // %d int
            // %f double o float
            // %b boolean
            // %c char
            /*
            String query =  String.format("INSERT INTO %s (%s, %s,%s,%s) VALUES ('%s','%s','%s',%d)",
                    SchemaDB.TAB_EMP,
                    SchemaDB.COL_EMP_NAME,SchemaDB.COL_EMP_SURNAME,SchemaDB.COL_EMP_MAIL,SchemaDB.COL_EMP_PHO,
                    empleado.getNombre(), empleado.getApellido(), empleado.getCorreo(), empleado.getTelefono());
           statement.execute(query);  */


            // Con PreparedStatement
            String PSquery =  String.format("INSERT INTO %s (%s, %s,%s,%s) VALUES (?,?,?,?)",
                    SchemaDB.TAB_EMP,
                    SchemaDB.COL_EMP_NAME,SchemaDB.COL_EMP_SURNAME,SchemaDB.COL_EMP_MAIL,SchemaDB.COL_EMP_PHO,
                    empleado.getNombre(), empleado.getApellido(), empleado.getCorreo(), empleado.getTelefono());
            PreparedStatement preparedStatement = connection.prepareStatement(PSquery);
            preparedStatement.setString(1, "ManuelPS");
            preparedStatement.setString(2, "CorrecherPS");
            preparedStatement.setString(3, "correo@gmail.comPS");
            preparedStatement.setInt(4, 987654);
            preparedStatement.execute();
           // preparedStatement.executeUpdate();



        } catch (SQLException e) {
            System.out.println("Error en la conexión de la BBDD");
        }


    }
    public void borrarUsuario(int id){

        Connection connection = new DBConnection().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + SchemaDB.TAB_EMP+ " WHERE id > ?");
            preparedStatement.setInt(1,id);
            int row = preparedStatement.executeUpdate();
            System.out.println("El número de registros borrados es: "+row);

        } catch (SQLException e) {
            System.out.println("Error en la query");
        }

    }
}
