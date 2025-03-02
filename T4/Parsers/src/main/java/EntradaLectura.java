import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class EntradaLectura {

    public static void main(String[] args) {
        //gestor -> permite acceso a la libreria
        DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance(); // static, nueva instancia, similar al singleton
        //lector
        try {
            DocumentBuilder lector = builder.newDocumentBuilder(); //elemento para hacer la lectura
            Document document = lector.parse(new File("src/main/java/peliculas.xml")); // se crea el documento con el lector
            Node nodoRaiz = document.getDocumentElement(); //referencia al primer elemento del documento
            NodeList lista = nodoRaiz.getChildNodes(); //devuelve la lista con los nodos hijos al nodo raíz

            for (int i = 0; i < lista.getLength(); i++) {  //recorre toda la lista y saca los nodos
                Node node = lista.item(i);
                if(!node.getTextContent().trim().isEmpty() && node.getNodeType() == Node.ELEMENT_NODE){ //saca el texto de los nodos tipo elemento
                    //System.out.println(node.getTextContent());
                    Node nodoTitulo = node.getAttributes().getNamedItem("titulo"); //de el node evaluado, saca todos los atributos, saca el llamado "titulo"
                    Node nodoPuntuacion = node.getAttributes().getNamedItem("puntuacion");
                    if (Double.parseDouble(nodoPuntuacion.getTextContent())>8){       //saca las de nota superior a 8
                        System.out.println(nodoTitulo.getTextContent() + " " + nodoPuntuacion.getTextContent());
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            System.out.println("Error en la creación del DocumentBuilder del fichero");
        } catch (IOException | SAXException e) {
            System.out.println("Error en el parseo del fichero");
        }

    }


}
