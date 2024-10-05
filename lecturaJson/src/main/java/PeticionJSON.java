import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PeticionJSON {

    public void procesarPeticion() throws MalformedURLException {

        //FILE → FILE READER → FILEREADER, esta vez no leeremos desde archivo, leeremos desde URL


        //URL → HTTPCONNECTION → BUFFEREDREADER
        String urlString = "https://dummyjson.com/products";
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));


            //Hacer lectura recurrente para evitar problemas si el API no está formateada de forma correcta
            //StreamReader permite hacer lectura de un flujo entrante en lugar del archivo entero como en fileReader
            //a traves de la conexion connection.getInputReader, se da el flujo de datos de InputStreamReader
            //se lee con BufferedReader
            String linea = null;
            StringBuffer stringBuffer = new StringBuffer();
            while((linea = bufferedReader.readLine())!=null){
                stringBuffer.append(linea);
            }//con esto se garantiza la lectura
            //System.out.println(bufferedReader.readLine());
            JSONObject peticionProductos = new JSONObject(stringBuffer.toString());
            //System.out.println(peticionProductos);

            //Para sacar sólo la lista de productos
            JSONArray listaProductos = peticionProductos.getJSONArray("products"); //Ya que en el api, products es un JSONArray
            //System.out.println(listaProductos);

            //Para sacar sólo el primero, el Array esta formado por JSONObject, por lo que habría que crearlo
            //JSONObject producto = listaProductos.getJSONObject(0);
            //System.out.println(producto.getString("title"));//para sacar el título

            //Para sacar el título y precio de todos los productos
            /* for( Object item: listaProductos){
                // item es un JSONObject → yo lo sé por eso se hace el casteo
                JSONObject producto = (JSONObject) item; // se hace el casteo para poder usar las utilidades de JSONObject
                System.out.println("Título: "+ producto.getString("title")+ ", Precio: "+ producto.getDouble("price"));
            }*/

            //Para sacar los tags dentro de cada prodcuto
            for( Object item: listaProductos) {
                // item es un JSONObject → yo lo sé por eso se hace el casteo
                JSONObject producto = (JSONObject) item; // se hace el casteo para poder usar las utilidades de JSONObject
                System.out.println("Título: " + producto.getString("title") + ", Precio: " + producto.getDouble("price"));
                //tags
                System.out.println("Las categorías del producto son: ");
                JSONArray tags = producto.getJSONArray("tags");
                for(Object tag : tags){
                    System.out.println("\t"+ tag);
                }
            }

        } catch (MalformedURLException e) {
            System.out.println("URL no válida");
        } catch (IOException e) {
            System.out.println("Error en la página, no responde");
        }
    }
}
