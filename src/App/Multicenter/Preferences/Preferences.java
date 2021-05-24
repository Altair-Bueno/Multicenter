package App.Multicenter.Preferences;

import App.Multicenter.Platform.LanguageManager;
import App.Multicenter.Platform.ThemeManager;

import java.awt.*;
import java.io.File;

public class Preferences {
    // TODO Preferences Constantes
    private File SpacesFolder;
    private ThemeManager theme;
    private LanguageManager language;
    private Dimension WindowsSize;
    // TODO Preferences Constructor

    /**
     * Constructor de la clase Preferences.
     * Establece el workingDirectory por defecto en su primera creación, siendo este el lugar de ejecución del programa.
     * Establece como tema principal el tema claro.
     * Establece como dimensión inicial de la ventana  800x800
     */

    public Preferences(){
        //Creamos el objeto Properties y el archivo donde se guardan las propiedades, establecemos los valores por defecto de todo.
        prop = new Properties();
        final File propertiesFile = new File("~/.multicenter_config.mtcr");

        prop.setProperty("working_directory", System.getProperty("user.dir"));
        SpacesFolder = new File(System.getProperty("user.dir"));

        prop.setProperty("theme", "0");
        ThemeManager.setTheme(0);

        prop.setProperty("window_size", "null"); //Saved only once bc cube window.
        WindowsSize = null;

        prop.setProperty("lang", Locale.getDefault().getLanguage());
        //No cambia el lenguaje, ya que pone el default del sistema, si el user lo quisiera cambiar que lo haga en el setter.
        try{
            OutputStream out = new FileOutputStream(propertiesFile);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
    /**
     * @return El SpacesFolder
     */
    public File getSpacesFolder() {
        // TODO Preferences getSpacesFolder
        return SpacesFolder;
    }

    /**
     * Define el SpacesFolder.
     *
     * @param spacesFolder el nuevo SpacesFolder.
     */
    public void setSpacesFolder(File spacesFolder) {
        // TODO Preferences setSpacesFolder
        SpacesFolder = spacesFolder;
    }

    /**
     * Devuelve el tema de la aplicación.
     *
     * @return El tema.
     */
    public String getTheme() {
        // TODO Preferences getTheme
        return null;
    }

    /**
     * Cambia el tema de la aplicación.
     *
     * @param theme El tema.
     */
    public void setTheme(int theme) {
        prop.setProperty("theme", Integer.toString(theme));
        ThemeManager.setTheme(theme);
    }

    /**
     * Devuelve el idioma de la aplicación.
     *
     * @return El idioma.
     */
    public String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * Cambia el idioma de la aplicación.
     *
     * @param language El idioma
     */
    public void setLanguage(int language) {
        LanguageManager.setLanguage(language);

        prop.setProperty("lang", Integer.toString(language));
        push();
    }

    /**
     * Devuelve la dimensión de la ventana.
     *
     * @return La dimensión de la ventana.
     */
    public Dimension getWindowsSize() {
        // TODO Preferences getWindowsSize
        return WindowsSize;
    }

    /**
     * Cambia la dimensión de la ventana.
     *
     * @param windowsSize La dimensión de la ventana.
     */
    public void setWindowsSize(Dimension windowsSize) {
        prop.setProperty("window_size", windowsSize.getWidth() + "," + windowsSize.getHeight());
        WindowsSize = windowsSize;
    }
}

