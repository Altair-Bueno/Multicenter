package App.Multicenter.Platform;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Provee los métodos necesarios para cambiar el idioma de la aplicación
 * de forma sencilla. Incluye las constantes para cambiar el idioma
 * a solo aquellos soportados por Multicenter. Al inicio de la aplicación la
 * JVM decide que idioma debe cargar por defecto. Este comportamiento se puede
 * sobreescribir los flags -Duser.language=CODE y -Duser.country=CODE. Para más
 * información visiar <a href="https://www.oracle.com/technical-resources/articles/javase/locale.html#using">documentación de Oracle</a>
 * Esta clase funciona de forma estática por lo que no se puede instanciar.
 */
public class LanguageManager {
    // Ubicación ResourceBundle
    private static final String LOCALE_PATH = "App/Multicenter/Properties/Strings";
    // Idiomas soportados
    public static final Locale ENGLISH = Locale.ENGLISH;
    public static final Locale SPANISH = new Locale("es");

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE_PATH, Locale.getDefault());

    // Cierre de clase
    private LanguageManager(){}

    /**
     * Cambia el idioma de la aplicación al seleccionado. Actualiza los métodos
     * de la clase LanguageManager con el nuevo idioma seleccionado. Este método
     * afecta al comportamiento de la clase {@link Locale}
     *
     * @see ResourceBundle
     * @see Locale
     * @param supportedLocale Constantes de LanguageManager
     * @return Si el cambio se ha producido de forma satisfactoria
     */
    public static boolean setLanguage(Locale supportedLocale){
        boolean out = true;
        try{
            Locale.setDefault(supportedLocale);
            resourceBundle = ResourceBundle.getBundle(LOCALE_PATH , supportedLocale);
        } catch (Exception e) { out = false; }
        return out;
    }

    /**
     * Dada una clave del ResourceBundle 'Strings', proporciona su correspondiente traducción
     * La ubicación del ResourceBundle es resources/App/Multicenter/Properties/
     *
     * @see ResourceBundle
     * @param key clave
     * @return Cadena correspondiente
     */

    public static String getString(String key) {
        return resourceBundle == null ? null : resourceBundle.getString(key);
    }
}
