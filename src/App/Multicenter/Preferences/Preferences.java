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
    public void setTheme(String theme) {
        // TODO Preferences setTheme
    }

    /**
     * Devuelve el idioma de la aplicación.
     *
     * @return El idioma.
     */
    public String getLanguage() {
        // TODO Preferences getLanguage
        return null;
    }

    /**
     * Cambia el idioma de la aplicación.
     *
     * @param language El idioma
     */
    public void setLanguage(String language) {
        // TODO Preferences setLanguage
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
        // TODO Preferences setWindosSize
        WindowsSize = windowsSize;
    }
}
