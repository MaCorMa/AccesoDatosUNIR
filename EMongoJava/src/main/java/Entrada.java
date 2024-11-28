import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import dao.AlumnosDAO;
import model.Alumno;

public class Entrada {

    public static void main(String[] args) {

        AlumnosDAO alumnosDAO = new AlumnosDAO();

        //insertar alumno
        alumnosDAO.insertarAlumno(new Alumno());
    }
}
