package app.multicenter.Platform;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Provee los métodos necesarios para cambiar el idioma de la aplicación
 * de forma sencilla. Incluye las constantes para cambiar el idioma
 * a solo aquellos soportados por Multicenter.
 * Para poder utilizar esta clase de forma satisfactoria, es necesario ejecutar
 * al inicio de la aplicación el método setLanguage para iniciar esta clase estática
 * Esta clase funciona de forma estática por lo que no se puede instanciar.
 */
public class LanguageManager {
    // Idiomas soportados
    public static final String DEFAULT = "und";
    public static final String ENGLISH = "en";//Locale.ENGLISH;
    public static final String SPANISH = "es";//new Locale("es");
    // Idioma de la JVM
    public static final String USER_ENV = Locale.getDefault().toLanguageTag();
    // Ubicación ResourceBundle
    private static final String LOCALE_BUNDLE_PATH = "app/multicenter/Properties/Strings";
    private static ResourceBundle resourceBundle = null;
    private static String actualLocale = null;

    // Cierre de clase
    private LanguageManager() {
    }

    /**
     * Devuelve el código de idioma utilizado actualmente en Multicenter
     *
     * @return Código de idioma utilizado
     * @throws IllegalStateException Si no se ha inicializado correctamente la aplicación
     */
    public static String getActualLocale() {
        if (resourceBundle == null) throw new IllegalStateException("LanguageManager is not correctly initialized");
        return actualLocale;
    }

    /**
     * Cambia el idioma de la aplicación al seleccionado. Actualiza los métodos
     * de la clase LanguageManager con el nuevo idioma seleccionado. Este método
     * afecta al comportamiento de la clase {@link Locale}. Si recibe como
     * parámetro null, cambia al idioma de la JVM. Si existe algún error, retorna
     * null y configura el idioma de la JVM
     *
     * @param supportedLocale Constantes de LanguageManager
     * @return Si el cambio se ha producido de forma satisfactoria
     * @see ResourceBundle
     * @see Locale
     */
    public static boolean setLanguage(String supportedLocale) {
        boolean out = true;
        try {
            Locale locale = new Locale(supportedLocale);
            Locale.setDefault(locale);
            resourceBundle = ResourceBundle.getBundle(LOCALE_BUNDLE_PATH, locale);
            actualLocale = supportedLocale;
        } catch (Exception e) {
            Locale locale = new Locale(USER_ENV);
            Locale.setDefault(locale);
            actualLocale = USER_ENV;
            resourceBundle = ResourceBundle.getBundle(LOCALE_BUNDLE_PATH);
            out = false;
        }
        return out;
    }

    /**
     * Dada una clave del ResourceBundle 'Strings', proporciona su correspondiente traducción
     * La ubicación del ResourceBundle es resources/App/Multicenter/Properties/. Si la cadena
     * no existe o no se ha inicializado correctamente {@link LanguageManager} devolverá null
     *
     * @param key clave
     * @return Cadena correspondiente
     * @see ResourceBundle
     */

    public static String getString(String key) {
        return resourceBundle == null ? null : resourceBundle.getString(key);
    }
}
