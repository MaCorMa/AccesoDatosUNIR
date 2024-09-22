import java.io.*;

public class ConBuffer {

    public static void main(String[] args) {
        GestorFicheros gestorFicheros = new GestorFicheros();
        gestorFicheros.lecturaTextoPlano("src/resources/lectura.txt");
    }

    public void lecturaTextoPlano(String path) {
        //FILE → FileReader → BufferedReader → cuando se terminan SE CIERRAN
        File file = new File(path);
        BufferedReader bufferedReader = null;
        if(file.exists() && file.isFile()){
            //existe y es fichero
            //1º Forma de tratar excepciones
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                //Para leer una línea
                System.out.println(bufferedReader.readLine());
                //Para lectura recurrente
                String lectura = null;
                //StringBuffer lecturaCompleta = new StringBuffer();//diferencia con StringBuilder en que es sincronizado
                //si tengo varios hilos, se comunican entre ellos para decir quien tiene el objeto ocupado
                //permite acceso múltiple
                StringBuilder lecturaCompleta = new StringBuilder(); //solo va a utilizar un hilo
                while ((lectura = bufferedReader.readLine()) != null){
                    //FORMAS DE HACER LA LECTURA
                    //System.out.println(lectura);

                    //lecturaCompleta+=lectura;

                    //Sirve para StringBuffer y StringBuilder
                    lecturaCompleta.append(lectura); //append anexa strings en el mismo string, sin crear nuevos
                    lecturaCompleta.append("\n");
                    System.out.println(lecturaCompleta.toString());

                }
            } catch (FileNotFoundException e){
                System.out.println("Fallo en la lectura del archivo");
                System.out.println(e.getMessage()); //Si salta el error, saca el mensaje de error
            } catch (IOException e) {
                System.out.println("Fallo en la lectura del archivo");
            } finally {
                //Bloque que se ejecuta siempre, haya fallo o no
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("No se ha podido cerrar");
                }
            }
            //2ºForma de tratar excepciones → throws FileNotFoundException en el metodo
            //filereader = new FileReader((file));
        }
    }
}
