package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Usuario;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

     private Connection connection;

     private PreparedStatement preparedStatement;

     private ResultSet resultSet;

     public UsuarioDAO(){

         connection = new DBConnection().getConnection();
     }

     //añadir usuario a la BBDD
    public void addUser(Usuario usuario) throws SQLException {
         String query = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?,?,?)",
                 SchemaDB.TAB_USER,
                 SchemaDB.COL_NAME,
                 SchemaDB.COL_MAIL,
                 SchemaDB.COL_PASS);
         preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, usuario.getNombre());
        preparedStatement.setString(2, usuario.getCorreo());
        preparedStatement.setString(3, usuario.getPass());
        preparedStatement.execute();
    }

    public ArrayList<Usuario>listarUsuarios() throws SQLException{
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        String query = String.format("SELECT * FROM %s",
                SchemaDB.TAB_USER);
        preparedStatement = connection.prepareStatement(query);
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");
            String correo = resultSet.getString("correo");
            String password = resultSet.getString("password");
            lista.add(new Usuario(id, nombre,correo, password));
        }
        return lista;
    }


    private ArrayList<Usuario> getResultados(ResultSet resultSet) throws SQLException{
        ArrayList<Usuario> listaResutlado = new ArrayList<>();
        while(resultSet.next()) {

            String nombre = resultSet.getString(SchemaDB.COL_NAME);
            String mail = resultSet.getString(SchemaDB.COL_MAIL);
            listaResutlado.add(mapearUsuario(nombre,mail));
        }
        return  listaResutlado;

    }
    private Usuario mapearUsuario(String nombre, String mail){
        return new Usuario(nombre,mail);
    }

    public boolean getLogin(String correo, String pass) throws SQLException {
        connection = new DBConnection().getConnection();

        preparedStatement = connection.prepareStatement(String.format("SELECT %s FROM %s WHERE %s=? AND %s=?",
                SchemaDB.COL_ID,
                SchemaDB.TAB_USER,
                SchemaDB.COL_MAIL,
                SchemaDB.COL_PASS));
        preparedStatement.setString(1,correo);
        preparedStatement.setString(2,pass);
        resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }


    public void exportaUsuarios() throws SQLException{

        ArrayList<Usuario> listarUsuarios = listarUsuarios();
        String filePath = "src/main/resources/usuarios.obj";

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(listarUsuarios);

            System.out.println("Lista exportada en " + filePath);
        } catch (Exception e) {
            System.err.println("Error al exportar: " + e.getMessage());
        }
    }

}
