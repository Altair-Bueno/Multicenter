package App.Multicenter.Preferences;

import App.Multicenter.Platform.LanguageManager;
import App.Multicenter.Platform.ThemeManager;

import java.io.*;
import java.util.Properties;

import java.awt.*;

public class Preferences{
    // Variables estáticas
    private static File spacesFolder;
    private static Dimension windowsSize;
    private static Properties prop;
    private final static File propertiesFile = new File(System.getProperty("user.home") , ".mctrproperties.xml");

    // Cierre de clase
    private Preferences(){}
    /**
     * Inicializa las preferencias de Multicenter para este usuario. Si
     * dicho usuario no tiene preferencias guardadas se creará una configurarión
     * por defecto:
     * <ul>
     *     <li>Establece como working directory la carpeta home</li>
     *     <li>Establece como tamaño de ventana predeterminada 800x800</li>
     *     <li>Establece el idioma de la aplicación al idioma por defecto</li>
     *     <li>Establece como tema de la aplicación LIGHT</li>
     *     <li>Crea el archivo de preferencias y guarda esta configuración inicial</li>
     * </ul>
     * @return true si existía una configuración anterior y false si dicho archivo
     *          no existe
     */
    public static boolean loadPreferences(){
        spacesFolder = new File(System.getProperty("user.dir"));
        windowsSize = new Dimension(800,800);
        prop = new Properties();
        LanguageManager.setLanguage(LanguageManager.DEFAULT);
        ThemeManager.setTheme(ThemeManager.LIGHT);

        boolean out = true;
        try(InputStream in = new FileInputStream(propertiesFile)){
            prop.loadFromXML(in);

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
        } catch (Exception e){
            //e.printStackTrace();
            out = false;
        }
        return out;
    }

    /**
     * @return El spacesFolder (directorio de trabajo)
     */
    public static File getSpacesFolder(){
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

    /**
     * Devuelve el tema de la aplicación.
     *
     * @return El tema.
     */
    public static int getTheme() {
        if (prop == null) throw new IllegalStateException("Preferences not correctly initialized");
        return ThemeManager.getCurrentTheme();
    }

    /**
     * Cambia el tema de la aplicación.
     *
     * @param theme El tema.
     */
    public static void setTheme(int theme) {
        if (prop == null) throw new IllegalStateException("Preferences not correctly initialized");
        ThemeManager.setTheme(theme);
    }

    /**
     * Devuelve el idioma de la aplicación.
     *
     * @return El idioma.
     */
    public static String getLanguage() {
        if (prop == null) throw new IllegalStateException("Preferences not correctly initialized");
        return LanguageManager.getActualLocale();
    }

    /**
     * Cambia el idioma de la aplicación.
     *
     * @param language El idioma
     */
    public static void setLanguage(String language) {
        if (prop == null) throw new IllegalStateException("Preferences not correctly initialized");
        LanguageManager.setLanguage(language);
    }

    /**
     * Devuelve la dimensión de la ventana.
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

    /**
     * Almacena la configuración actual de forma persistente en el disco. Si este método
     * no es llamado antes del cierre de la aplicación no se almacenarán los cambios
     *
     * @return true si se ha podido almacenar correctamente o false si dicha acción
     *          ha fallado
     */
    public static boolean save() {
        prop.setProperty("working_directory", getSpacesFolder().getAbsolutePath());
        prop.setProperty("theme", Integer.toString(getTheme()));
        prop.setProperty("window_size", (int)getWindowsSize().getWidth() + "-" + (int)getWindowsSize().getHeight());
        prop.setProperty("lang", getLanguage());

        try(OutputStream out = new FileOutputStream(propertiesFile)) {
            prop.storeToXML(out, null);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

