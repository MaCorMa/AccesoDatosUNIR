import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio2 {

    public static void main(String[] args) throws IOException {

        File archivo = new File("src/directorio");
        File archivo2 = new File("src/directorio/directorio");
        File archivo3 = new File("src/directorio/directorio/4.txt");


        if(archivo.exists()&&archivo.isDirectory()){
            if (!new File("src/directorio/directorio").exists()){
                try{
                    archivo2.mkdir();
                }catch (Exception e){
                    System.out.println("Error al crear archivo2");
                }
            }
            if (!new File("src/directorio/directorio/4.txt").exists()){
                try{
                    archivo3.createNewFile();
                }catch (IOException e){
                    System.out.println("Error al crear archivo3");
                }
            }
            File[] archivoAry = archivo.listFiles();
            File[] archivo2Ary = archivo2.listFiles();
            List <File> archivoList = new ArrayList<>();

            if (archivoAry != null) {
                for (File a : archivoAry) {
                    archivoList.add(a);
                }
            }
            if (archivo2Ary != null) {
                for (File a : archivo2Ary) {
                    archivoList.add(a);
                }
            }
            if (archivoList != null){
                for(File arch:archivoList){
                    if(!arch.isDirectory())
                    System.out.println(arch.getName());
                }
            }
        }
    }
}

