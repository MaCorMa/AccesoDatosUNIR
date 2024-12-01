package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import database.MongoDBConnection;
import model.Alumno;
import model.Profesor;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

public class ProfesoresDAO {


    MongoCollection coleccionProfesores;


    public ProfesoresDAO(){
        coleccionProfesores = new MongoDBConnection().getProfesoresCollection();
    }

    public void insertarProfesor(Profesor profesor){
        Document documento = new Document().append("nombre",profesor.getName()).append("gender",profesor.getGender());
        coleccionProfesores.insertOne(profesor);
    }

    public List<Document> buscarProfeEdad(int min, int max){
        List<Document>profePorEdad=null;
        Document filtroEdad = new Document().append("age", new Document().append("$gte",min).append("$lte",max));
        FindIterable iterableEdad = coleccionProfesores.find(filtroEdad);
        MongoCursor<Document> cursor = iterableEdad.cursor();
        while (cursor.hasNext()){
            Document document = cursor.next();
            profePorEdad.add(document);
        }
        System.out.println("Aquí está el resultado de la búsqueda:"+"\n");
        return profePorEdad;
    }


    public List<Document> mostrarProfesores(){
        List<Document>todosProfesores = (List<Document>) coleccionProfesores.find();
        return todosProfesores;
    }

    public void actualizarProfesor(String email,double rating) {
        Profesor profesor=null;
        Document docBuscar = new Document().append("email",email);
        Document docCambio = new Document().append("$set",new Document("rating",rating));
        FindIterable iterableMail = coleccionProfesores.find(docBuscar);
        MongoCursor<Document> cursor = iterableMail.cursor();
        while (cursor.hasNext()){
            coleccionProfesores.updateOne(docBuscar,docCambio);
        }
        System.out.println("Profesor actualizado con éxito");
    }
}
