package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

public class MongoDBConnection {

    //Crear String con la dirección del cluster de MongoDB
    private String connectionString = "mongodb+srv://%s:%s@adunir.m7ezb.mongodb.net/?retryWrites=true&w=majority&appName=ADUNIR";

    //crear cliente
    private MongoClient mongoClient;
    
    public MongoDBConnection(){
        //se inicializa el mongoclient, usando credenciales en DBScheme
        MongoClient mongoClient = MongoClients.create(String.format(connectionString, DBScheme.USER, DBScheme.PASS));
    }

    //del cluster al que nos conectamos, sacar las colecciones deseadas
    public MongoCollection getAlumnosCollection(){
        return mongoClient.getDatabase("centro_estudios").getCollection("alumnos");
    }
    public MongoCollection getProfesoresCollection(){
        return mongoClient.getDatabase("centro_estudios").getCollection("profesores");
    }
}
