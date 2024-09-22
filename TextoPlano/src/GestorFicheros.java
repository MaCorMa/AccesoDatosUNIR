import javax.imageio.IIOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GestorFicheros {

    public static void main(String[] args) {
        GestorFicheros gestorFicheros = new GestorFicheros();
        gestorFicheros.lecturaTextoPlano("src/resources/lectura.txt");
    }

    public void lecturaTextoPlano(String path) {
        //FILE → FileReader → cuando se terminan SE CIERRAN
        File file = new File(path);
        FileReader fileReader = null;
        if(file.exists() && file.isFile()){
            //existe y es fichero

            //1º Forma de tratar excepciones
            try {
                fileReader = new FileReader(file); //En rojo por ser necesario tratar la excepción
               //Para leer
               /* int lectura = fileReader.read();
                System.out.println((char)lectura);*/
                // Mientras exista el numero haz la lectura
                int lectura =0;
                while ((lectura = fileReader.read())!=-1){
                    //imprime lectura
                    System.out.print((char)lectura);
                }
            } catch (FileNotFoundException e){
                System.out.println("Fallo en la lectura del archivo");
                System.out.println(e.getMessage()); //Si salta el error, saca el mensaje de error
            } catch (IOException e) {
                System.out.println("Error en la lectura");
            } finally {
                //Bloque que se ejecuta siempre, haya fallo o no
                try{
                    fileReader.close(); //Es necesario cerrar el tratamiento de flujo de datos
                } catch (IOException e){
                    System.out.println("Error en el cerrado de flujo");
                } catch (NullPointerException e) {
                    System.out.println("Error en el cerrado por ser nulo");
                }
            }
            //2ºForma de tratar excepciones → throws FileNotFoundException en el metodo
            //filereader = new FileReader((file));
        }
    }
}
