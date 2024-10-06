package resources;

import model.Producto;

import java.io.*;

public class GestorFicheros {

    public void escribirBinarios(String path){
        //File
        File file = new File(path);

        //Crear data stream
        DataOutputStream dataOutputStream = null;
        //inicializar el DataOutputStream creado
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            //poner que queremos escribir
            //dataOutputStream.writeChars("Ejemplo escritura binaria");
            dataOutputStream.writeUTF("Ejemplo escritura binaria");
            dataOutputStream.writeInt(5);
            dataOutputStream.writeBoolean(true);

        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        } catch (IOException e) {
            System.out.println("Problema con el fichero");
        }finally {
            try {
                dataOutputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error al cerrar");
            }
        }
    }
    public void leerBinarios(String path){
        //File
        File file = new File(path);

        //Crear data stream
        DataInputStream dataInputStream = null;
        //inicializar el DataOutputStream creado
        try {
            dataInputStream = new DataInputStream(new FileInputStream(file));
            //poner que queremos escribir
            //System.out.println(dataInputStream.readLine());
            System.out.println(dataInputStream.readUTF());
            System.out.println(dataInputStream.readInt());
            System.out.println(dataInputStream.readBoolean());
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        } catch (IOException e) {
            System.out.println("Problema con el fichero");
            System.out.println(e.getMessage());
        }finally {
            try {
                dataInputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error al cerrar");
            }
        }
    }

    //escribir objetos
    public void escribirObjeto(String path){
        File file = new File(path);

        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(new DataOutputStream(new FileOutputStream(file)));
            objectOutputStream.writeObject(new Producto(1,"Camisa Blanca", 5.95, 10));
            objectOutputStream.writeObject(new Producto(2,"Camisa Negra", 5.95, 8));
        } catch (IOException e) {
            System.out.println("Error en fichero");
            System.out.println(e.getMessage());
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar");
            }
        }
    }

}
