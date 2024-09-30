import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class GestionFicherosConjunta {

    public static void main(String[] args){

        GestionFicherosConjunta gestionFicherosConjunta = new GestionFicherosConjunta();
        GestionFicherosConjunta.cifrado("src/resources/escritura.txt");
    }

    public static void lecturaEscritura(String path){
        Scanner scanner = new Scanner(System.in);
        File file = new File(path);
        FileWriter fileWriter = null;

        System.out.println("Introduce mensaje a guardar");
        String mensaje = scanner.nextLine();
        System.out.println("Por favor indica la fase de cifrado del mensaje");
        int fase = scanner.nextInt();

        try {
            fileWriter = new FileWriter(file, false);
            for (int i = 0; i < mensaje.length(); i++) {
                char letra = mensaje.charAt(i);
                //fileWriter.write(letra+"\n");
                int codigo = (int) letra;
                //System.out.println("codigo= "+ codigo);
                fileWriter.write(String.valueOf(codigo * 5 + "\n"));
            }
        } catch (IOException e) {
            System.out.println("Error en los permisos");
        }finally{
            try {
                fileWriter.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Fallo en el cerrado");
            }
        }
    }
    public static void cifrado(String path){
        Scanner scanner = new Scanner(System.in);
        File file = new File(path);
        FileWriter fileWriter = null;

        System.out.println("Introduce mensaje a guardar");
        String mensaje = scanner.nextLine();
        System.out.println("Por favor indica la fase de cifrado del mensaje");
        int fase = scanner.nextInt();

        try {
            fileWriter = new FileWriter(file, false);
            for (int i = 0; i < mensaje.length(); i++) {
                char letra = mensaje.charAt(i);
                //fileWriter.write(letra+"\n");
                int codigo = (int) letra;
                //System.out.println("codigo= "+ codigo);
                fileWriter.write(String.valueOf(codigo * 5 + "\n"));
            }
        } catch (IOException e) {
            System.out.println("Error en los permisos");
        }finally{
            try {
                fileWriter.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Fallo en el cerrado");
            }
        }
    }

    public static void desdifrado(String path){
        
    }
}

