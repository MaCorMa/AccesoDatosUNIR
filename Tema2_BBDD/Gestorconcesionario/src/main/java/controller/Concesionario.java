package controller;

import database.DBConnection;
import database.SchemaDB;
import model.Empleado;

import java.sql.*;

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
            String PSquery =  String.format("INSERT INTO %s (%s, %s,%s,%s,%s) VALUES (?,?,?,?,?)",
                    SchemaDB.TAB_EMP,
                    SchemaDB.COL_EMP_NAME,SchemaDB.COL_EMP_SURNAME,SchemaDB.COL_EMP_MAIL,SchemaDB.COL_EMP_PHO, SchemaDB.COL_EMP_KIN,
                    empleado.getNombre(), empleado.getApellido(), empleado.getCorreo(), empleado.getTelefono(), empleado.getTipo());

            PreparedStatement preparedStatement = connection.prepareStatement(PSquery);
             /*   para meter uno a pelo
            preparedStatement.setString(1, "ManuelPS");
            preparedStatement.setString(2, "CorrecherPS");
            preparedStatement.setString(3, "correo@gmail.comPS");
            preparedStatement.setInt(4, 987654);
            */
            //para meter en la bbdd el que se le pase por el metodo
            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2, empleado.getApellido());
            preparedStatement.setString(3, empleado.getCorreo() );
            preparedStatement.setInt(4, empleado.getId() );
            preparedStatement.setInt(5, empleado.getTipo().getId());


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
    public void leerUsuario(int tipo){
         //No se puede mapear de forma directa → Vector[[nombre, apellido, correo],[nombre, apellido, correo],[nombre, apellido, correo]]
        //Connection → Statement/PreparedStatement → executeQuery → ResultSet

        Connection connection = new DBConnection().getConnection();

        //String query = "SELECT * FROM "+SchemaDB.TAB_EMP;
        String query = String.format("SELECT * FROM %s WHERE %s=?",SchemaDB.TAB_EMP, SchemaDB.COL_EMP_KIN);
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tipo);
            ResultSet resultSet = preparedStatement.executeQuery();

            //vamos al primer resultado
            //resultSet.next();

            while(resultSet.next()){
                String nombre = resultSet.getString(SchemaDB.COL_EMP_NAME);
                String correo = resultSet.getString(SchemaDB.COL_EMP_MAIL);
                int tipo1 = resultSet.getInt(SchemaDB.COL_EMP_KIN);
                System.out.printf("Nombre del empleado %s \n\t Correo del empleado %s\n",nombre,correo, tipo1);
            }
        } catch (SQLException e) {
            System.out.println("Error en la query");
        }
    }
}
