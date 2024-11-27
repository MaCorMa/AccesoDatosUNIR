package dao;

import model.Coche;
import model.CocheDao;

import java.io.*;
import java.util.ArrayList;

//Clase concesionario implementa interfaz CocheDAO
public class Concesionario implements CocheDao {

    private ArrayList<Coche>lista;
    ObjectInputStream objectInputStream = null;


    public Concesionario(){
        //crea el arrayList
        lista = new ArrayList<Coche>();
        //El método carga los objetos Coche del archivo coches.dat en el arrayList
        leeCoches("src/archivos/coches.dat");
    }


    @Override
    //comprueba si ID o matrícula existen en la lista, en caso contrario, añade el nuevo Coche
    public boolean altaCoche(Coche coche) {
        for (Coche carro : lista) {
            if (carro.getId() == coche.getId()) {
                System.out.println("Error: Ya existe un coche con el mismo ID.");
                return false;
            } else if (carro.getMatricula().equals(coche.getMatricula())) {
                System.out.println("Error: Ya existe un coche con la misma matrícula.");
                return false;
            }
        }
        lista.add(coche);
        System.out.println("Coche agregado correctamente.");
        return true;
    }
    
    @Override
    //usa el método buscaCoche para encontrar el coche con esa ID en la lista, si existe lo borra
    public boolean borrarCoche(int id) {
        Coche coche = buscaCocheId(id);
        if(coche !=null){
            lista.remove(coche);
            System.out.println("Coche eliminado");
            return true;
        }
        System.out.println("Coche no encontrado");
        return false;
    }

    @Override
    //busca Coche con id en la lista y lo devuelve
    public Coche buscaCocheId(int id) {
        Coche coche = new Coche();   //se crea objeto clase coche
        coche.setId(id);   //se le pasa la id a buscar

        int pos = lista.indexOf(coche);  //busca con indexOf
        System.out.println(pos);
        if (pos!=-1){   // Si no está, dará -1
            return lista.get(pos);   //si está, devuelve el objeto buscado
        }else {
            System.out.println("ID indicada no está registrada");
            return null;
        }
    }

    @Override
    //devuelve toda la lista
    public ArrayList<Coche> listaTodo() {
        return lista;
    }

    @Override
    //usa ObjectInputStream para leer del archivo coches.dat un ArrayList de objetos serializables Coche
    //lo guarda en lista para poder usar en los metodos de la clase Concesioanrio
    public void leeCoches(String path) {
        //crea nuevo File con el path indicado al método
        File file = new File(path);
        //si el archivo existe, se crea el FileInputStream para leer del File creado
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
        //path donde guardar/crear el archivo coches.dat
        String path = "src/archivos/coches.dat";
        try {
            //usamos ObjectOutputStream para enviar datos a un archivo.dat, para poder usarlo, Coche debe ser serializable
            ObjectOutputStream objectOutputStream = null;
            //con FileOutputStream dirigimos la salida de ObjectOutputStream hacia un File. Con false conseguimos que sobreescriba cada vez.
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(path,false));
            //método writeObject para realizar la escritura
            objectOutputStream.writeObject(lista);
            System.out.println("Se han guardado los coches en el archivo " + path);
            //es importante usar el metodo close para asegurar que cerramos el flujo y se ejecuta la escritura.
            objectOutputStream.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error al guardar los coches en el archivo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean exportaCsv(ArrayList<Coche> lista) {
        //Usamos FileWriter para escribir texto en un archivo
        String path = "src/archivos/coches.csv";
        try {
            //le decimos en que ruta se encuentra el archivo en el que escribir, con false para sobreescribir cada vez.
            FileWriter fileWriter = new FileWriter(path,false);
            //for para formatear a csv
            for (Coche coche : lista) {
                fileWriter.write(coche.getId() + ";" + coche.getMatricula() + ";" + coche.getMarca() + ";" + coche.getModelo() +";" + coche.getColor() +"\n");
            }
            //cerramos escritura para ejecutarla.
            fileWriter.close();
            System.out.println("Se han guardado los coches en el archivo " + path);
            return true;
        } catch (IOException e) {
            System.out.println("Error al guardar los coches en el archivo: " + e.getMessage());
            return false;
        }
    }
    }
