public class Entrada {

    public static void main(String [] args){

        GestorFicheros gestorFicheros = new GestorFicheros();
        //gestorFicheros.escribirBinarios("src/main/java/resources/datos.bin");
        //gestorFicheros.leerBinarios("src/main/java/resources/datos.bin");
        //gestorFicheros.escribirObjeto("src/main/java/resources/almacen.obj");
        //private static final Long serialVersionUID = 12345L;
        gestorFicheros.lecturaObjeto("src/main/java/resources/almacen.obj");
        //Producto producto = new Producto(1, "Titulo", 30.5, 12);
        
    }
}
