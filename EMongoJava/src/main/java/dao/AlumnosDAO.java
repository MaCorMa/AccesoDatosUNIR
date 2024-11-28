package dao;


import com.mongodb.client.MongoCollection;
import database.MongoDBConnection;
import model.Alumno;
import org.bson.Document;

public class AlumnosDAO {

    //creamos la coleccion alumnos, sacandola del cluster con los métodos de la clase MongoDBConnection
    MongoCollection coleccionAlumnos;
    
    public AlumnosDAO(){
        coleccionAlumnos = new MongoDBConnection().getAlumnosCollection();
    }
    



    public void insertarAlumno(Alumno alumno){

        Document documento = new Document().append("nombre",alumno.getName()).append("gender",alumno.getGender());
        coleccionAlumnos.insertOne(documento);
    }

    public void buscarAlumno(String email){

        //Document documento = new Document.append("email",alumno.getEmail);
        //collection.find(documento) --> cursor --> while
    }

}
