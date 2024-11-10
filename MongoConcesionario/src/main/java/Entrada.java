import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;

public class Entrada {

    public static void main(String[] args) {

        //Connection
        String connectionString = "mongodb+srv://manuelcorrecher:unirDAM@adunir.m7ezb.mongodb.net/?retryWrites=true&w=majority&appName=ADUNIR";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
    }
}
