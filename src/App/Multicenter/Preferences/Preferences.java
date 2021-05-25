package App.Multicenter.Preferences;

import App.Multicenter.Platform.LanguageManager;
import App.Multicenter.Platform.ThemeManager;

import java.io.*;
import java.util.Locale;
import java.util.Properties;
import java.util.prefs.*;

import java.awt.*;

/**
 * Proporciona los métodos necesarios para cambiar las preferencias
 * de la aplicación temporalmente, y para cambiarlas definitivamente
 * mediante el uso de un archivo oculto en formato xml guardado en
 * "user.dir", ".mctrpreferences.xml".
 *
 * Limitado a 1 instancia (Constructor privado)
 * Establece el workingDirectory por defecto en su primera creación.
 * Establece como tema principal el tema claro.
 * Establece como dimensión inicial de la ventana  800x800.
 */
public class Preferences{
    private static File SpacesFolder = new File(System.getProperty("user.dir"));;
    private static Dimension WindowsSize = new Dimension(800,800);
    private static Properties prop = new Properties();
    private final static File propertiesFile = new File(System.getProperty("user.home") + "/.mctrproperties.xml");

    private final static Preferences preferences = new Preferences();

    /**
     * Constructor privado de la clase Preferences.
     *
     * Abre el archivo .mctrpreferences.xml del directorio de usuario
     * "user.dir" y carga las propiedades en el objeto Properties "prop"
     * que tenemos como variable de clase.
     *
     */

    private Preferences(){
        try{
            InputStream in = new FileInputStream(propertiesFile);
            prop.loadFromXML(in);
            in.close();

            String ajustes = prop.toString().substring(1, prop.toString().length()-1);
            for(String s : ajustes.split(",")){
                String[] settings = s.split("=");

                switch (settings[0]) {
                    case "window_size" -> {
                        String[] dim = settings[1].split("-");
                        setWindowsSize(new Dimension(Integer.parseInt(dim[0]), Integer.parseInt(dim[1])));
                    }
                    case "working_directory" -> setSpacesFolder(new File(settings[1]));
                    case "theme" -> setTheme(Integer.parseInt(settings[1]));
                    case "lang" -> setLanguage(settings[1]);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @return El SpacesFolder (directorio de trabajo)
     */
    public static File getSpacesFolder(){
        return SpacesFolder;
    }

    /**
     * Define el SpacesFolder.
     *
     * @param spacesFolder el nuevo SpacesFolder.
     */
    public static void setSpacesFolder(File spacesFolder) {
        SpacesFolder = spacesFolder;
    }

    /**
     * Devuelve el tema usado ctualmente en la aplicación.
     *
     * @return El número indexado al tema actual.
     *         {
     *         0 = "LIGHT",
     *         1 = "DARK",
     *         2 = "DARCULA",
     *         3 = "INTELLIJ"
     *         }
     */
    public static int getTheme() {
        return ThemeManager.getCurrentTheme();
    }

    /**
     * Cambia el tema de la aplicación.
     *
     * @param theme El entero asociado al tema al que se quiere cambiar.
     *              {
     *              0 = "LIGHT",
     *              1 = "DARK",
     *              2 = "DARCULA",
     *              3 = "INTELLIJ"
     *              }
     */
    public static void setTheme(int theme) {
        ThemeManager.setTheme(theme);
    }

    /**
     * Devuelve el idioma que está siendo
     * usado en la aplicación.
     *
     * @return El idioma.
     *         {
     *         "es" = Español,
     *         "en" = Inglés
     *         }
     */
    public static String getLanguage() {
        return LanguageManager.getActualLocale();
    }

    /**
     * Cambia el idioma de la aplicación.
     *
     * @param language El prefijo del idioma
     *                 "es" = Español
     *                 "en" = Inglés
     */
    public static void setLanguage(String language) {
        LanguageManager.setLanguage(language);
    }

    /**
     * Devuelve la dimensión de la ventana
     * de la aplicación.
     *
     * @return La dimensión de la ventana.
     */
    public static Dimension getWindowsSize() {
        return WindowsSize;
    }

    /**
     * Cambia la dimensión de la ventana.
     *
     * @param windowsSize La dimensión de la ventana.
     */
    public static void setWindowsSize(Dimension windowsSize) {
        WindowsSize = windowsSize;
    }

    public static boolean save() {
        prop.setProperty("working_directory", getSpacesFolder().getAbsolutePath());
        prop.setProperty("theme", Integer.toString(getTheme()));
        prop.setProperty("window_size", getWindowsSize().getWidth() + "-" + getWindowsSize().getHeight());
        prop.setProperty("lang", getLanguage());

        try {
            OutputStream out = new FileOutputStream(propertiesFile);
            prop.storeToXML(out, "");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }
}

