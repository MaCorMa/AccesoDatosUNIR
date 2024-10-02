import java.io.*;
import java.util.Scanner;


public class GestionFicherosConjunta {

    public static void main(String[] args){

        GestionFicherosConjunta gestionFicherosConjunta = new GestionFicherosConjunta();
        String path = ("src/resources/cifrado.txt");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Indica qué quieres hacer.");
        System.out.println("1. Cifrar");
        System.out.println("2. Descifrar");
        int opcion = scanner.nextInt();

        switch (opcion){
            case 1:
                gestionFicherosConjunta.cifrado(path);
                break;
            case 2:
                gestionFicherosConjunta.descifrado(path);
                break;
        }

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
                fileWriter.write(String.valueOf(codigo * fase + "\n"));
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
    public void cifrado(String path){
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
                fileWriter.write(String.valueOf(codigo * fase + "\n"));
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

    public void descifrado(String path){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor indica la fase de cifrado del mensaje");
        int fase = scanner.nextInt();

        File file = new File(path);
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader(file));
            String lectura = null;
            while(((lectura)= br.readLine())!=null){
                //lectura
                //System.out.println(lectura);
                int codigo = Integer.valueOf(lectura);
                System.out.println((char)(codigo/fase));
            }
            System.out.println("\n");
        } catch (IOException e) {
            System.out.println("Fallo al leer");
        }

    }
}

