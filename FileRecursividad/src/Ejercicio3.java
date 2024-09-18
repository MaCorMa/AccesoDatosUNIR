import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio3 {

    public static void main(String[] args) {
        String path = "C:/Users";
        buscaArchivos(path);
    }

    public static void buscaArchivos (String path){
        File dir = new File(path);
        if (!dir.canRead()) {
            System.out.println("No se puede leer la ruta: " + path);
            return;
        }
        File[] fileLista = dir.listFiles();
        if (fileLista != null) {
            for(File file:fileLista){
                System.out.println(file.getName());
                if(file.isDirectory()){
                    buscaArchivos(file.getAbsolutePath());
                }
            }
        }
    }
}





