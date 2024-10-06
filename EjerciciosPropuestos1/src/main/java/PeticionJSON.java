import com.google.gson.Gson;
import model.Product;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class PeticionJSON {

    public void procesarPeticion(){

        //URL → HTTPCONNECTION → BUFFEREDREADER
        String urlString = "https://dummyjson.com/products";
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            //Pasar a StringBuffer
            String lectura = null;
            StringBuffer stringBuffer = new StringBuffer();

            while((lectura=bufferedReader.readLine())!=null){
                stringBuffer.append(lectura);
            }
            //Crear objeto JSON y pasar a String
            JSONObject peticionProductos = new JSONObject(stringBuffer.toString());

            //1. Leer productos e imprimir info de nombre, precio, stock y descripción
            //System.out.println(peticionProductos);
            //Se crea array para sacar el array  dentro del JSON products
            JSONArray listaProductos = peticionProductos.getJSONArray("products");

            //Para sacar nombre, precio stock y descripción, tenemos que saber sus keys y qué son
            /* for(Object item:listaProductos){
                JSONObject producto = (JSONObject) item; //Se hace casteo para usar funcionalidades jsonobject
                System.out.println("Nombre: "+producto.getString("title")+". Precio: "+producto.getDouble("price")+" Stock: "+producto.getInt("stock")+". Descripción: "+producto.getString("description"));
            }*/

            //2. Buscar producto pedir por teclado 1 ID y mostrar la infor del producto
            /*System.out.println("Introduce ID de producto: ");
            Scanner leerUsuario = new Scanner(System.in);
            int num = leerUsuario.nextInt();
            for(Object item : listaProductos){
                JSONObject producto = (JSONObject) item;
                if((producto.getInt("id"))==num){
                    System.out.println(producto);
                }
            }*/

            //3.Filtrar por precio, se pide precio min y max y se muestran los productos que cumplen filtro
            /*System.out.println("Introduce Precio Máx: ");
            Scanner leerUsuario = new Scanner(System.in);
            Double numMax = leerUsuario.nextDouble();
            System.out.println("Introduce Precio Mín: ");
            Double numMin = leerUsuario.nextDouble();
            for(Object item : listaProductos){
                JSONObject producto = (JSONObject) item;
                if((((producto.getDouble("price"))>=numMin))&&((producto.getDouble("price"))<=numMax)){
                    System.out.println(producto);
                }
            }*/

            //4. Exportar productos en un .txt
            //FILE → FILEWRITTER → BUFFEREDWRITTER/PRINTWRITTER → Es obligatorio cerrar, sin cerrar no se hará la escritura
            String path = "src/main/resources/productos.txt";
            File file = new File(path);
            BufferedWriter bufferedWriter = null;

            try{
                bufferedWriter = new BufferedWriter(new FileWriter(file));
                for(Object item : listaProductos){
                    //JSONObject producto = (JSONObject) item; //sobra al usar la librería Gson
                    //Product prod = new Product(producto.getString("title"),producto.getInt("stock"),producto.getInt("id"),producto.getDouble("price"));
                    Product prod = new Gson().fromJson(item.toString(),Product.class); //Con un objeto de la clase Gson le pasas un String (viene de convertir el JSON a string) y le dices la clase en la que
                    //ha de basarse, no es necesario hacer nada más.
                    //Se puede sacar el objeto directamente sin extraer info y después tratarla
                    //Es condición necesaria tener las características de clase privadas, tener los getter y setter y tener los mismos nombres que los parámetros del JSON
                    //System.out.println(producto.toString());
                    String exportacionProducto = String.format("title:%s price:%.2f stock:%d", prod.getTitle(), prod.getPrice(),prod.getStock());
                    System.out.println(exportacionProducto);
                    //bufferedWriter.write(exportacionProducto);
                }
            } catch (IOException e) {
                System.out.println("Archivo no encontrado");
            }finally {
                try{
                    bufferedWriter.close();
                }catch (NullPointerException e){
                    System.out.println("Error en el cerrado");
                }

            }
        } catch (MalformedURLException e) {
            System.out.println("No es una URL correcta");
        } catch (IOException e) {
            System.out.println("Página no disponible");
        }


    }
}
