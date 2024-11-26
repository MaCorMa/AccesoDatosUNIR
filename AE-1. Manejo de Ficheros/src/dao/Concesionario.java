package dao;

import model.Coche;
import model.CocheDao;

import java.io.*;
import java.util.ArrayList;

public class Concesionario implements CocheDao {

    private ArrayList<Coche>lista;
    ObjectInputStream objectInputStream = null;
    ObjectOutputStream objectOutputStream = null;

    public Concesionario(){
        //crea el arrayList
        lista = new ArrayList<Coche>();
        //El método carga los objetos Coche del archivo coches.dat en el arrayList
        leeCoches("src/archivos/coches.dat");
    }

    
    @Override
    public boolean altaCoche(Coche coche) {
        if(lista.contains(coche)){
            System.out.println("Coche ya existe en la lista");
            return false;
        }else{
            lista.add(coche);
            return true;
        }
    }
    @Override
    public boolean borrarCoche(int id) {
        if((buscaCocheId((id)))!=null){
            lista.remove(buscaCocheId((id)));
            System.out.println("Coche eliminado");
            return true;
        }
        System.out.println("Coche no encontrado");
        return false;
    }

    @Override
    public Coche buscaCocheId(int id) {
        Coche coche = new Coche();   //se crea objeto clase coche
        coche.setId(id);   //se le pasa la id a buscar
        int pos = lista.indexOf(coche);  //busca con indexOf
        if (pos!=1){   // Si no está, dará -1
            return lista.get(pos);   //si está, devuelve el objeto buscado
        }return null;
    }

    @Override
    public ArrayList<Coche> listaTodo() {
        return lista;
    }

    @Override
    public void leeCoches(String path) {
        File file = new File(path);

        if (file.exists()) {

            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(file));
                lista = (ArrayList<Coche>) objectInputStream.readObject();

            } catch (IOException e) {
                System.out.println("No se encuentra el archivo");
            } catch (ClassNotFoundException e) {
                System.out.println("No se encuentra la clase destino");
                ;
            } finally {
                // Cerrar el flujo en caso de que esté abierto
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e) {
                        System.out.println("Error al cerrar el flujo: " + e.getMessage());
                    }
                }
            }
        } else {
            System.out.println("El archivo no existe en " + path);
        }
    }

    @Override
    public boolean exportaDat(ArrayList<Coche> lista) {
        String path = "src/archivos/coches.dat";
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            objectOutputStream.writeObject(lista);
            System.out.println("Se han guardado los coches en el archivo " + path);
        } catch (IOException e) {
            System.out.println("Error al guardar los coches en el archivo: " + e.getMessage());
            return false;
        }finally {
            return true;
        }
    }

    @Override
    public boolean exportaCsv(ArrayList<Coche> lista) {
        String path = "src/archivos/coches.csv";
        try {
            FileWriter fileWriter = new FileWriter(path);

            for (Coche coche : lista) {
                fileWriter.write(coche.getId() + ";" + coche.getMatricula() + ";" + coche.getMarca() + ";" + coche.getModelo() +";" + coche.getColor() +"\n");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los coches en el archivo: " + e.getMessage());
            return false;
        }finally {
            System.out.println("Se han guardado los coches en el archivo " + path);
            return true;
        }
    }
}
