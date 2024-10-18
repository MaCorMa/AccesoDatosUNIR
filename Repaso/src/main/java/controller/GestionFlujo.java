package controller;

import java.io.*;

public class GestionFlujo {

    //File, el fichero con el que se trabaja. Dependiendo del flujo de datos con el que se trabaja:
    private File file = new File("src/main/java/resources/ejemplo.txt");
        //Texto plano → .txt
            //Escritura → Output → Writer
            public void escrituraTPlano(){
                // Hay que declarar un Writer
                //File → FileWritter (como poner el fichero en modo escritura)
                FileWriter fileWriter = null; //en nulo para poder inicializar en el try/catch y tratar errores
                //Otro tipo de flujo
                //BufferedWriter → Fichero en modo escritura, permite más formato
                BufferedWriter bufferedWriter = null;
                //Otro tipo
                //PrintWriter
                PrintWriter printWriter = null; //Con el PrintWriter no es necesario
                try {
                    //Se inicializa el FileWriter
                    fileWriter = new FileWriter(file);
                    //Escribimos
                    fileWriter.write("Escribo con FileWriter");
                    //Se inicializa el BufferedWriter
                    bufferedWriter = new BufferedWriter(new FileWriter(file)); //BufferedWriter necesita un fichero en modo escritura, por lo que es necesario un nuevo FileWriter haciendo referencia a un File
                    //Escribimos
                    bufferedWriter.write("Escribo con BufferedWriter");
                    bufferedWriter.newLine();//Escribe un separador de línea
                    //Se inicializa el PrintWriter
                    printWriter = new PrintWriter(new FileWriter(file));
                    //Escribimos
                    printWriter.print("Escribo con PrintWriter");
                } catch (IOException e) {
                    System.out.println("Error en la puesta en escritura del File");
                }  finally {
                    try {
                        fileWriter.close(); //Hasta que no cierra no se escribe
                        bufferedWriter.close();
                        printWriter.close();
                    } catch (IOException | NullPointerException e) {
                        System.out.println("Error al cerrar Writer");
                    }
                }
            }
            public  void lecturaTPlano(){
                /*
                //File
                //File → FileReader → Lectura char a char
                FileReader fileReader = null; //Inicializar a null para poder cerrar en el try/catch

                try {
                    fileReader = new FileReader(file);
                    //leemos de forma recurrente
                    int lectura = -1;
                    while((lectura=fileReader.read())>-1){  //Si no hay nada, lee -1. Terminamos la lectura
                        System.out.println((char)lectura);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Error, no encuentra el File");
                } catch (IOException e) {
                    System.out.println("Error en la puesta en lectura del File");;
                } finally{
                     try {
                        fileReader.close(); //Hasta que no cierra no se escribe
                    } catch (IOException | NullPointerException e) {
                        System.out.println("Error al cerrar Reader");
                    }
                }
                 */
                //File → FileReader → BufferedReader → Lectura hasta encontrar salto de línea
                BufferedReader bufferedReader =null; //Inicializar a null para poder cerrar en el try/catch
                try {
                    bufferedReader = new BufferedReader(new FileReader(file));
                    String linea = null;
                    while ((linea = bufferedReader.readLine()) != null){
                        System.out.println(linea);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Error, no se encuentra archivo a leer");
                } catch (IOException e) {
                    System.out.println("Error al poner archivo en lectura");
                } finally {
                    try {
                        bufferedReader.close();
                    } catch (IOException | NullPointerException e) { //Puede llegar a ser nullo, se añade excepcion
                        System.out.println("Error al cerrar lectura");
                    }
                }
            }

        //Binario → tipo de dato (int, boolean, char...)

        //Objeto → Object(asd, asd ,asd)
}
