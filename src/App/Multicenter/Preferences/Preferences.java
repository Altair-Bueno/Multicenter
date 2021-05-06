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
        // TODO
        return SpacesFolder;
    }

    /**
     * Define el SpacesFolder.
     *
     * @param spacesFolder el nuevo SpacesFolder.
     */
    public void setSpacesFolder(File spacesFolder) {
        // TODO
        SpacesFolder = spacesFolder;
    }

    /**
     * Devuelve el tema de la aplicación.
     *
     * @return El tema.
     */
    public String getTheme() {
        // TODO
        return Theme;
    }

    /**
     * Cambia el tema de la aplicación.
     *
     * @param theme El tema.
     */
    public void setTheme(String theme) {
        // TODO
        Theme = theme;
    }

    /**
     * Devuelve el idioma de la aplicación.
     *
     * @return El idioma.
     */
    public String getLanguage() {
        // TODO
        return Language;
    }

    /**
     * Cambia el idioma de la aplicación.
     *
     * @param language El idioma
     */
    public void setLanguage(String language) {
        // TODO
        Language = language;
    }

    /**
     * Devuelve la dimensión de la ventana.
     *
     * @return La dimensión de la ventana.
     */
    public Dimension getWindowsSize() {
        // TODO
        return WindowsSize;
    }

    /**
     * Cambia la dimensión de la ventana.
     *
     * @param windowsSize La dimensión de la ventana.
     */
    public void setWindowsSize(Dimension windowsSize) {
        // TODO
        WindowsSize = windowsSize;
    }
}
