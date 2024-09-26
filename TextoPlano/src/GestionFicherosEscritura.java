import java.io.*;
import java.util.Scanner;

public class GestionFicherosEscritura {

    public static void main(String[] args){

        GestionFicherosEscritura gestionFicherosEscritura = new GestionFicherosEscritura();
        GestionFicherosEscritura.escribirFichero("src/resources/escritura.txt");
    }

    private static void escribirFichero(String path) {

        //FILE → FILEWRITTER → BUFFEREDWRITTER/PRINTWRITTER → Es obligatorio cerrar, sin cerrar no se hará la escritura

        File file = new File(path);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce lo que quieres guardar");
        String lecturaLinea = scanner.nextLine();

        try {
            fileWriter = new FileWriter(file, true); //append → anexar o no la escritura
            bufferedWriter = new BufferedWriter(fileWriter);
            //fileWriter.write(lecturaLinea);
            bufferedWriter.write(lecturaLinea);
        } catch (IOException e) {
            System.out.println("Error en la escritura del fichero por permisos");
        }finally {
           try {
                fileWriter.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cierre del flujo");
            }
        }
    }
}
