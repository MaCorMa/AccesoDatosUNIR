package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import database.MongoDBConnection;
import model.Alumno;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class AlumnosDAO {

    //creamos la coleccion, sacandola del cluster con los métodos de la clase MongoDBConnection
    MongoCollection coleccionAlumnos;
    
    public AlumnosDAO(){
        //al crear el DAO, inicializar la coleccion Profesores
        coleccionAlumnos = new MongoDBConnection().getAlumnosCollection();
    }

    public void insertarAlumno(Alumno alumno){

        Document documento = new Document().append("nombre",alumno.getName()).append("gender",alumno.getGender());
        coleccionAlumnos.insertOne(documento);
    }

    public Alumno buscarAlumno(String email){
        Alumno alumno=null;
        Document docBuscar = new Document().append("email",email);
        //collection.find(documento) --> cursor --> while

        //método find devuelve un FindIterable, es tipable a Document
        FindIterable iterableMail = coleccionAlumnos.find(docBuscar);
        //el cursor nos devuelve Document, en lugar de tipar el FindIterable, podemos tipar a Cursor
        MongoCursor<Document> cursor = iterableMail.cursor();
        //mientras haya un siguiente Document, se ejecuta el bucle
        while (cursor.hasNext()){
            Document document = cursor.next();
            String id = document.getString("id");
            Double rating = document.getDouble("rating");
            int age = document.getInteger("age");
            String name = document.getString("name");
            String gender = document.getString("gender");
            String mail = document.getString(email);
            String phone = document.getString("phone");
            int calification = document.getInteger("calification");
            String higher_grade = document.getString("higher_grade");
            boolean fct = document.getBoolean("FCTs");
            alumno = new Alumno(id,rating,age,name,gender,mail,phone,calification,higher_grade,fct);
        }
        return alumno;
    }

    public void darBaja(){
        List<Document> listaBaja = null;
        Document docBaja = new Document().append("calification", new Document().append("$gte",5));
        FindIterable iterableBaja = coleccionAlumnos.find(docBaja);
        MongoCursor<Document> cursor = iterableBaja.cursor();

        while (cursor.hasNext()){
            Document document = cursor.next();
            listaBaja.add(document);
        }
        coleccionAlumnos.deleteMany((Bson) listaBaja);
        System.out.println("Alumnos con calification mayor que 5 borrados");
    }

    public List<Document> mostrarAlumnos(){
        List<Document>todosAlumnos = (List<Document>) coleccionAlumnos.find();
        return todosAlumnos;
    }

}
