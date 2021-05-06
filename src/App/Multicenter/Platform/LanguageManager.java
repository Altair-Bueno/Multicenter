package App.Multicenter.Platform;

import java.util.ResourceBundle;

/**
 * Provee los métodos necesarios para cambiar el idioma de la aplicación
 * de forma sencilla. Incluye las constantes para cambiar el idioma
 * a solo aquellos soportados
 */
public class LanguageManager {
    ResourceBundle resourceBundle;

    //Constantes
    // TODO

    /**
     * Solicita a la JVM cambiar el idioma por el seleccionado
     * NOTA: Para que surja efecto, debe actualizar la cadena
     *
     * @see ResourceBundle
     * @param code Constantes de LanguageManager
     * @return Si se ha cambiado el idioma de forma satisfactoria
     */
    public boolean setLanguage(int code){
        // TODO
        return false;
    }

    /**
     * Dada una clave del ResourceBundle, proporciona su correspondiente traducción
     *
     * @see ResourceBundle
     * @param key clave
     * @return Cadena correspondiente
     */

    public String getString(String key) {
        // TODO
        return null;
    }
}
