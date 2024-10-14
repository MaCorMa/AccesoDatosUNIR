package database;

public interface SchemaDB {

    //1. interfaz para conectar 2 clases que no tienen nada que ver
    //con los métodos abstractos de dicha interfaz

    //2. Almacén de variables constantes → finales (no de métodos)
        //ex:
        String DB_NAME = "concesionario";
        String TAB_EMP = "empleados";
        String COL_ID = "id";
        String COL_EMP_NAME = "nombre";
        String COL_EMP_SURNAME = "apellido";
        String COL_EMP_MAIL = "correo";
        String COL_EMP_PHO = "telefono";



    public abstract void metodoInt();
}
