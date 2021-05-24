package App.Multicenter.Preferences;

import App.Multicenter.Platform.LanguageManager;
import App.Multicenter.Platform.ThemeManager;

import java.io.*;
import java.util.Locale;
import java.util.Properties;
import java.util.prefs.*;

import java.awt.*;

public class Preferences implements Closeable{
    private static File SpacesFolder = new File(System.getProperty("user.dir"));;
    private static Dimension WindowsSize = new Dimension(800,800);
    private static Properties prop = new Properties();
    private final static File propertiesFile = new File(System.getProperty("user.home") + ".mctrproperties.xml");

    private final static Preferences preferences = new Preferences();

    /**
     * Constructor de la clase Preferences.
     * Establece el workingDirectory por defecto en su primera creación, siendo este el lugar de ejecución del programa.
     * Establece como tema principal el tema claro.
     * Establece como dimensión inicial de la ventana  800x800
     */

    private Preferences(){
        try{
            InputStream in = new FileInputStream(propertiesFile + "/preferences.xml");
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

    public static Preferences getInstance(){
        return preferences;
    }

    /**
     * @return El SpacesFolder (directorio de trabajo)
     */
    public File getSpacesFolder(){
        return SpacesFolder;
    }

    /**
     * Define el SpacesFolder.
     *
     * @param spacesFolder el nuevo SpacesFolder.
     */
    public void setSpacesFolder(File spacesFolder) {
        SpacesFolder = spacesFolder;
    }

    /**
     * Devuelve el tema de la aplicación.
     *
     * @return El tema.
     */
    public int getTheme() {
        return ThemeManager.getCurrentTheme();
    }

    /**
     * Cambia el tema de la aplicación.
     *
     * @param theme El tema.
     */
    public void setTheme(int theme) {
        ThemeManager.setTheme(theme);
    }

    /**
     * Devuelve el idioma de la aplicación.
     *
     * @return El idioma.
     */
    public String getLanguage() {
        return LanguageManager.getActualLocale();
    }

    /**
     * Cambia el idioma de la aplicación.
     *
     * @param language El idioma
     */
    public void setLanguage(String language) {
        LanguageManager.setLanguage(language);
    }

    /**
     * Devuelve la dimensión de la ventana.
     *
     * @return La dimensión de la ventana.
     */
    public Dimension getWindowsSize() {
        return WindowsSize;
    }

    /**
     * Cambia la dimensión de la ventana.
     *
     * @param windowsSize La dimensión de la ventana.
     */
    public void setWindowsSize(Dimension windowsSize) {
        WindowsSize = windowsSize;
    }

    public void close() throws IOException {
        prop.setProperty("working_directory", getSpacesFolder().getAbsolutePath());
        prop.setProperty("theme", Integer.toString(getTheme()));
        prop.setProperty("window_size", getWindowsSize().getWidth() + "-" + getWindowsSize().getHeight());
        prop.setProperty("lang", getLanguage());

        OutputStream out = new FileOutputStream(propertiesFile + "/preferences.xml");
        prop.storeToXML(out, "");
        out.close();
    }
}

