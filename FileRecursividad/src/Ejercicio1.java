import java.io.File;


public class Ejercicio1 {

    public static void main(String[] args) {

        File archivo = new File("src/directorio");

        if(archivo.exists()&&archivo.isDirectory()){
            File[] archivoList = archivo.listFiles();

            for(File arch:archivoList){
                System.out.println(arch.getName());
            }
        }
    }
}