package database;

public interface SchemaDB {


    //1. interfaz para conectar 2 clases que no tienen nada que ver
    //con los métodos abstractos de dicha interfaz

    //2. Almacén de variables constantes → finales (no de métodos)
    //ex:
    String DB_NAME = "concesionario2";
    //ID
    String COL_ID = "id";

    //PASAJEROS
    String TAB_PAS = "pasajeros";
    String COL_PAS_NAME = "nombre";
    String COL_PAS_SURNAME = "apellido";

    //COCHE
    String TAB_CH = "coches";
    String COL_CH_MAR = "marca";
    String COL_CH_MOD = "modelo";
    String COL_CH_PRE = "precio";
    String COL_CH_CV = "cv";

    //PASAJEROS_COCHES
    String TAB_PASCAR = "pasajeros_coches";
    String COL_PASCAR_IDCAR = "id_coches";
    String COL_PASCAR_IDPAS = "id_pasajeros";

}
