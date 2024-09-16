import java.io.File;

public class Entrada {

    public static void main(String[] args){

        //FILE→ fichero logico → físico

        File ficheroSinPuntero = new File("D:\\GitHubRepos\\AccesoDatosUNIR\\Fichero\\src\\resources\\directorio\\ejemplo_fichero.txt");
        System.out.println(ficheroSinPuntero.getName());

    }
}
