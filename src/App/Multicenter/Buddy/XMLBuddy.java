package App.Multicenter.Buddy;

import App.Multicenter.DataStructures.Tree;

import java.io.File;
import java.util.function.Function;

/**
 * Proporciona herramientas para leer y escribir ficheros XML de forma sencilla
 * Ambas acciones pueden utilizar funciones como interpretes para permitir la lectura y
 * escritura de forma sencilla
 * @param <T> Tipo utilizado en la traducción
 */
public class XMLBuddy<T> {
    /**
     * Lee el fichero recibido como parámetro, conservando su estructura
     * jerárquica y transformandola en un Tree
     *
     * @see org.w3c.dom.Document
     * @see javax.xml.parsers.DocumentBuilder
     * @see javax.xml.parsers.DocumentBuilderFactory
     * @param file Archivo del que leer el XML
     * @return Árbol jerárquico de Strings
     */
    public static Tree<String> parseXMLFile(File file){
        XMLBuddy<String> buddy = new XMLBuddy<>();
        return buddy.parseXMLFile(file,e->e);
    }

    /**
     * Lee el fichero recibido como parámetro, conservando su estructura jerárquica
     * y transformandola en un Tree. La transformación utilizará la función para
     * transformar la cada cadena leida en un elemento de tipo T. Si la función toma
     * como parámetro null, se lanzará una excepción de tipo IllegalArgumentException
     *
     * @see org.w3c.dom.Document
     * @see javax.xml.parsers.DocumentBuilder
     * @see javax.xml.parsers.DocumentBuilderFactory
     * @param file Archivo del que leer
     * @param function Función de conversión
     * @return Árbol jerárquico
     * @throws IllegalArgumentException si function == null
     */
    public Tree<T> parseXMLFile(File file, Function<String,T> function){
        // TODO
        return null;
    }

    /**
     * Almacena el contenido del árbol recibido como parámetro en el
     * interior del fichero. El formato utilizado es XML
     *
     * @see org.w3c.dom.Document
     * @see javax.xml.parsers.DocumentBuilder
     * @see javax.xml.parsers.DocumentBuilderFactory
     * @param file Archivo donde escribir el XML
     * @param tree Árbol jerárquico a almacenar
     */
    public static void parseTreeStructure(File file , Tree<String> tree) {
        XMLBuddy<String> buddy = new XMLBuddy<>();
        buddy.parseTreeStructure(file,tree,e->e);
    }

    /**
     * Almacena el contenido del árbol recibido como parámetro en el
     * interior del fichero. El formato utilizado es XML. Utiliza la
     * función para traducir el XML en un árbol de Strings que puede
     * ser almacenado. Si la función toma como parámetro null, lanzará
     * una excepción de tipo IllegalArgumentException
     *
     * @param file Archivo donde escribir
     * @param tree Árbol jerárquico a almacenar
     * @param function Función de conversión
     * @throws IllegalArgumentException si function == null
     */

    public void parseTreeStructure(File file , Tree<T> tree , Function<T,String> function){
        // TODO
    }
}
