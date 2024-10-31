package database;

public interface SchemaDB {

    //1. interfaz para conectar 2 clases que no tienen nada que ver
    //con los métodos abstractos de dicha interfaz

    //2. Almacén de variables constantes → finales (no de métodos)
        //ex:
        String DB_NAME = "concesionario";
        String TAB_EMP = "empleados";
    //En 2a clase se añade otra TABLA "tipos"
        String TAB_KIN = "tipos";
        String COL_ID = "id";
        String COL_EMP_NAME = "nombre";
        String COL_EMP_SURNAME = "apellido";
        String COL_EMP_MAIL = "correo";
        String COL_EMP_PHO = "telefono";
        //En 2a clase se añade otra tabla "tipos"
        String COL_EMP_KIN = "tipo";
        String COL_KIN_DES = "descripcion";
        String COL_KIN_SIG = "siglas";
     //En 3a clase se añade TABLA coches
        String TAB_CH = "coches";
        String COL_CH_MAR = "marca";
        String COL_CH_MOD = "modelo";
        String COL_CH_PRE = "precio";
        String COL_CH_CV = "cv";




    public abstract void metodoInt();
}
