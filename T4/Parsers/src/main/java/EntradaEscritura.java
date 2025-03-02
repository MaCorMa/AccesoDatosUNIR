import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class EntradaEscritura {
    public static void main(String[] args) {

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance(); //para crear el builder
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();    //crea el builder
            Document document = builder.newDocument(); //preparado para escribir el xml

            //Element -> cada uno de los datos, nodos o valores del XML

            Element elementroot = document.createElement("videoclub");  //crear el nodo raíz
            document.appendChild(elementroot); //mete el nodo raíz creado en el documento

            //crea nodos para meter dentro del raiz
            Element elementChild = document.createElement("pelicula");
            elementChild.setAttribute("titulo", "Titulo de la pelicula");
            elementChild.setAttribute("categoria", "accion");
            elementChild.setAttribute("valoracion", "5.3");
            elementroot.appendChild(elementChild);   //lo mete dentro del nodo raiz

            Element elementChildInner = document.createElement("sinopsis");
            elementChildInner.setTextContent("Sinopsis de la pelicula");
            elementChild.appendChild(elementChildInner);

            //el transformer coge los element y traduce el anidado de elements
            TransformerFactory transformerFactory  = TransformerFactory.newInstance();   //prara crear el transformer
            Transformer transformer = transformerFactory.newTransformer(); //crea el transformer

            //traduce de document a XML
            DOMSource domFile = new DOMSource(document);
            StreamResult escritura = new StreamResult(new File("videoclub.xml"));
            transformer.transform(domFile, escritura);

            

            

        } catch (ParserConfigurationException e) {
            System.out.println("Error en la creación del document builder");
        } catch (TransformerConfigurationException e) {
            System.out.println("Error en la creación del transformer");
        } catch (TransformerException e) {
            System.out.println("Error en la transformación de los datos");
        }
    }
    
}
