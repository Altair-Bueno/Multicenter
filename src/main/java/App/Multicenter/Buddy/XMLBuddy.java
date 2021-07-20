package app.multicenter.buddy;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * Proporciona herramientas para leer y escribir clases a
 * escritura de forma sencilla
 *
 * @param <T> Objeto reconstruido esperado. Debe implementar Serializable
 */
public class XMLBuddy<T extends Serializable> {

    /**
     * Reconstruye la clase almacenada en el archivo XML que recibe como
     * parámetro
     *
     * @param file Archivo del que leer
     * @return Objeto reconstruido.
     * @see Serializable
     * @throws FileNotFoundException Si no existe dicho archivo
     */
    @SuppressWarnings("unchecked")
    public T readFromFile(File file) throws FileNotFoundException {
        XMLDecoder decoder = new XMLDecoder(
                new BufferedInputStream(new FileInputStream(file))
        );
        return (T) decoder.readObject();
    }

    /**
     * Almacena el estado de una instancia en el interior de un archivo XML
     *
     * @param file Archivo donde almacenar el contenido
     * @param object Objeto a guardar. Debe ser serializable
     * @return true si se guarda satisfactoriamente, falso en otro caso
     */

    public boolean saveToFile(File file, T object) {
        boolean out;
        XMLEncoder encoder;
        try {
            encoder = new XMLEncoder(
                    new BufferedOutputStream(new FileOutputStream(file))
            );
            encoder.writeObject(object);
            encoder.close();
            out = true;
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            out = false;
        }
        return out;
    }
}
