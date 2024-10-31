package controller;

import dao.CochesDAO;
import dao.EmpleadoDAO;
import database.DBConnection;
import database.SchemaDB;
import model.Coche;
import model.Empleado;

import java.sql.*;
import java.util.Scanner;

public class Concesionario {
    //CRUD
    //Create, Update, Delete → Modifican la tabla
    //Read → Obtiene vector de valores

    EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    CochesDAO cochesDAO = new CochesDAO();
    //Statement → Query directa → Insert into empleados (nombre. apellido...) VALUES ('Manuel', 'Correcher', ...)
        //True o false
        //nº filas afectadas


    //Aquí debería ir sólamente la lógica de negocio, para gestión con la BBDD se debería usar patrón DAO
    //Se crea clase DAO para ello, por lo que estos métodos són sólo para ejemplo


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

    //métodos con patrón DAO
    public void instarTrabajadorDAO(Empleado empleado){
        try {
            empleadoDAO.insertarEmpleado(empleado);
        } catch (SQLException e) {
            System.out.println("Error en la query");
        }
    }
    public void agregarCoche(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce Marca");
        String marca = scanner.next();


        //Si dan una marca y ya tengo X coches de una marca, no quiero comprarlo
        //ejemplo método cochesDAO.getModeloCochesMArca

         //meterlo en un objeto cocheDAO
        try {
            if(cochesDAO.getCochesMarca(marca).size()<2){
                System.out.println("Introduce Modelo");
                String modelo = scanner.next();
                System.out.println("Introduce CV");
                int cv = scanner.nextInt();
                System.out.println("Introduce Precio");
                int precio = scanner.nextInt();
                cochesDAO.addCoche(new Coche(marca,modelo,cv,precio));
                System.out.println("Coche agregado correctamente");
            } else{
                System.out.println("No interesa al concesionario");
            }
        } catch (SQLException e) {
            System.out.println("BBDD no disponible, quieres guardarlo para más adelante?");
            //guardar dato en fichero
            
        }
    }

    public void filtrarPrecio(){

        Scanner scanner = new Scanner(System.in);
         System.out.println("Por que precio quieres filtrar?");
         int precio = scanner.nextInt();

        try {
            for( Coche coche:cochesDAO.getCochePrecio(precio)){
                 coche.mostrarDatos();
            };
        } catch (SQLException e) {
            System.out.println("No se puede realizar la consulta, ¿hacer otra cosa?");
        }
    }
    //Funcionalidad de vender un coche -> matrícula
    //el coche lo vende un vendedor (tengo que decir quien lo vende)
    //funcionalidad para saber quién es el vendedor que más coches ha vendido

}
