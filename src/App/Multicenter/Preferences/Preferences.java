package App.Multicenter.Preferences;

import java.awt.*;
import java.io.File;

public class Preferences {
    private File SpacesFolder;
    private String Theme, Language;
    private Dimension WindowsSize;


    /**
     * @return El SpacesFolder
     */
    public File getSpacesFolder() {
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
    public String getTheme() {
        return Theme;
    }

    /**
     * Cambia el tema de la aplicación.
     *
     * @param theme El tema.
     */
    public void setTheme(String theme) {
        Theme = theme;
    }

    /**
     * Devuelve el idioma de la aplicación.
     *
     * @return El idioma.
     */
    public String getLanguage() {
        return Language;
    }

    /**
     * Cambia el idioma de la aplicación.
     *
     * @param language El idioma
     */
    public void setLanguage(String language) {
        Language = language;
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
}
