import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;

public class Entrada {

    public static void main(String []args) throws MalformedURLException {

       /* File file = new File("src/main/resources/usuario.txt");
        //txt → JSON
       BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            StringBuffer lecturaCompleta = new StringBuffer();
            String linea = null;

            while((linea= bufferedReader.readLine())!=null){
                lecturaCompleta.append(linea);
            }

            System.out.println(lecturaCompleta.toString());
            JSONObject usuario = new JSONObject(lecturaCompleta.toString());//pasa el contenido a string para poder crear el objeto JSON
            System.out.println(usuario);
            //Preguntar por clave y leer el dato asociado
            String nombreUsuario = usuario.getString("nombre");
            System.out.println(nombreUsuario);

            //Preguntar por objeto y leer dato
            Object apellidoUsuario = usuario.get("apellido");
            JSONArray asignaturas = usuario.getJSONArray("asignaturas");
            System.out.println(apellidoUsuario);
            System.out.println(asignaturas);

        } catch (FileNotFoundException e) {
            System.out.println("Error en el fichero");
        } catch (IOException e) {
            System.out.println("Error al leer");
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }*/

        //PeticionJSON
        PeticionJSON peticionJSON = new PeticionJSON();
        peticionJSON.procesarPeticion();


    }
}
