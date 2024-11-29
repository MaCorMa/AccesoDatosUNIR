package dao;

import com.mongodb.client.MongoCollection;
import database.MongoDBConnection;
import model.Profesor;
import org.bson.Document;

public class ProfesoresDAO {


    MongoCollection coleccionProfesores;


    public ProfesoresDAO(){
        coleccionProfesores = new MongoDBConnection().getProfesoresCollection();
    }

    public void insertarProfesor(Profesor profesor){
        Document documento = new Document().append("nombre",profesor.getName()).append("gender",profesor.getGender());
        coleccionProfesores.insertOne(documento);
    }
}
