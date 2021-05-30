package App.Multicenter.Preferences;

import App.Multicenter.Platform.LanguageManager;
import App.Multicenter.Platform.PlatformManager;
import App.Multicenter.Platform.ThemeManager;

import java.awt.*;
import java.io.*;
import java.util.Properties;

/**
 * Proporciona los métodos necesarios para cambiar las preferencias
 * de la aplicación temporalmente, y para cambiarlas definitivamente
 * mediante el uso de un archivo oculto en formato xml guardado en
 * "user.dir", ".mctrpreferences.xml".
 */
public class Preferences {
    private final static File propertiesFile = new File(System.getProperty("user.home"), ".mctrpreferences");
    // Variables estáticas
    private static File spacesFolder;
    private static Dimension windowsSize;
    private static Properties prop;

    // Cierre de clase
    private Preferences() {
    }

    /**
     * Inicializa las preferencias de Multicenter para este usuario. Si
     * dicho usuario no tiene preferencias guardadas se creará una configurarión
     * por defecto:
     * <ul>
     *     <li>Establece como working directory la carpeta home</li>
     *     <li>Establece como tamaño de ventana predeterminada 800x800</li>
     *     <li>Establece el idioma de la aplicación al idioma de la JVM</li>
     *     <li>Establece como tema de la aplicación LIGHT</li>
     *     <li>Crea el archivo de preferencias y guarda esta configuración inicial</li>
     * </ul>
     *
     * @return true si existía una configuración anterior y false si dicho archivo
     * no existe
     */
    public static boolean loadPreferences() {
        PlatformManager.setJVMEnviroment();

        spacesFolder = new File(System.getProperty("user.home"), "Multicenter Files");
        spacesFolder.mkdir();

        windowsSize = new Dimension(800, 800);
        prop = new Properties();
        LanguageManager.setLanguage(LanguageManager.USER_ENV);
        ThemeManager.setTheme(ThemeManager.LIGHT);

        boolean out = true;
        try (InputStream in = new FileInputStream(propertiesFile)) {
            prop.loadFromXML(in);

            String ajustes = prop.toString().substring(1, prop.toString().length() - 1);
            for (String s : ajustes.split(",")) {
                String[] settings = s.split("=");

                switch (settings[0]) {
                    case "window_size" -> {
                        String[] dim = settings[1].split("-");
                        setWindowsSize(new Dimension(Integer.parseInt(dim[0]), Integer.parseInt(dim[1])));
                    }
                    case " working_directory" -> setSpacesFolder(new File(settings[1]));
                    case " theme" -> setTheme(Integer.parseInt(settings[1]));
                    case " lang" -> setLanguage(settings[1]);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            out = false;
        }
        return out;
    }

    /**
     * @return El spacesFolder (directorio de trabajo)
     */
    public static File getSpacesFolder() {
        if (prop == null) throw new IllegalStateException("Preferences not correctly initialized");
        return spacesFolder;
    }

    /**
     * Define el spacesFolder.
     *
     * @param spacesFolder el nuevo spacesFolder.
     */
    public static void setSpacesFolder(File spacesFolder) {
        if (prop == null) throw new IllegalStateException("Preferences not correctly initialized");
        Preferences.spacesFolder = spacesFolder;
    }

    public static File getSpacesSaveFile() {
        if (prop == null) throw new IllegalStateException("Preferences not correctly initialized");
        return new File(spacesFolder, "savefile.xml");
    }

    /**
     * Devuelve el tema usado ctualmente en la aplicación.
     *
     * @return El número indexado al tema actual.
     * <ul>
     *     <li>0 = "LIGHT"</li>
     *     <li>1 = "DARK"</li>
     *     <li>2 = "DARCULA"</li>
     *     <li>3 = "INTELLIJ"</li>
     * </ul>
     */
    public static int getTheme() {
        if (prop == null) throw new IllegalStateException("Preferences not correctly initialized");
        return ThemeManager.getCurrentTheme();
    }

    /**
     * Cambia el tema de la aplicación.
     *
     * @param theme El entero asociado al tema al que se quiere cambiar.
     *              <ul>
     *                  <li>0 = "LIGHT"</li>
     *                  <li>1 = "DARK"</li>
     *                  <li>2 = "DARCULA"</li>
     *                  <li>3 = "INTELLIJ"</li>
     *              </ul>
     */
    public static void setTheme(int theme) {
        if (prop == null) throw new IllegalStateException("Preferences not correctly initialized");
        ThemeManager.setTheme(theme);
    }

    /**
     * Devuelve el idioma que está siendo
     * usado en la aplicación.
     *
     * @return El idioma.
     * <ul>
     *     <li>"es" = Español 🇪🇸</li>
     *     <li>"en" = Inglés 🇬🇧</li>
     * </ul>
     */
    public static String getLanguage() {
        if (prop == null) throw new IllegalStateException("Preferences not correctly initialized");
        return LanguageManager.getActualLocale();
    }

    /**
     * Cambia el idioma de la aplicación.
     *
     * @param language El prefijo del idioma
     *                 <ul>
     *                     <li>"es" = Español 🇪🇸</li>
     *                     <li>"en" = Inglés 🇬🇧</li>
     *                 </ul>
     */
    public static void setLanguage(String language) {
        if (prop == null) throw new IllegalStateException("Preferences not correctly initialized");
        LanguageManager.setLanguage(language);
    }

    /**
     * Devuelve la dimensión de la ventana
     * de la aplicación.
     *
     * @return La dimensión de la ventana.
     */
    public static Dimension getWindowsSize() {
        if (prop == null) throw new IllegalStateException("Preferences not correctly initialized");
        return windowsSize;
    }

    /**
     * Cambia la dimensión de la ventana.
     *
     * @param windowsSize La dimensión de la ventana.
     */
    public static void setWindowsSize(Dimension windowsSize) {
        if (prop == null) throw new IllegalStateException("Preferences not correctly initialized");
        Preferences.windowsSize = windowsSize;
    }


    public static Properties getPropObject() {
        return prop;
    }

    /**
     * Almacena la configuración actual de forma persistente en el disco. Si este método
     * no es llamado antes del cierre de la aplicación no se almacenarán los cambios
     *
     * @return true si se ha podido almacenar correctamente o false si dicha acción
     * ha fallado
     */
    public static boolean save() {
        prop.setProperty("working_directory", getSpacesFolder().getAbsolutePath());
        prop.setProperty("theme", Integer.toString(getTheme()));
        prop.setProperty("window_size", (int) getWindowsSize().getWidth() + "-" + (int) getWindowsSize().getHeight());
        prop.setProperty("lang", getLanguage());

        try (OutputStream out = new FileOutputStream(propertiesFile)) {
            prop.storeToXML(out, null);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
}

