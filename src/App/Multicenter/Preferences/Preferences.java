package App.Multicenter.Preferences;

import App.Multicenter.Platform.LanguageManager;
import App.Multicenter.Platform.ThemeManager;

import java.io.*;
import java.util.Locale;
import java.util.Properties;
import java.util.prefs.*;

import java.awt.*;

public class Preferences {
    private static File SpacesFolder;
    private static Dimension WindowsSize;
    private static Properties prop;
    private final static File propertiesFile = new File(System.getProperty("user.home") + "\\Multicenter");

    /**
     * Constructor de la clase Preferences.
     * Establece el workingDirectory por defecto en su primera creación, siendo este el lugar de ejecución del programa.
     * Establece como tema principal el tema claro.
     * Establece como dimensión inicial de la ventana  800x800
     */

    public Preferences(){
        if(propertiesFile.isFile()){ //si ya existe
            load();
        } else {//primera ejecucion
            initialPreferences();
        }
    }

    public static void initialPreferences(){

        propertiesFile.mkdir();
        prop = new Properties();

        prop.setProperty("working_directory", "~/Multicenter");
        SpacesFolder = new File(System.getProperty("user.dir"));

        prop.setProperty("theme", "0");
        ThemeManager.setTheme(0);

        prop.setProperty("window_size", "800-800");
        WindowsSize = new Dimension(800,800);

        prop.setProperty("lang", "1"); // Default: Español

        //push();

    }

    public void push(){
        try{
            OutputStream out = new FileOutputStream(propertiesFile);
            prop.storeToXML(out, "");
            out.close();

            System.out.println("SALIDA DE GUARDAR: " + prop.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void load(){
        try{
            InputStream in = new FileInputStream(propertiesFile);
            prop.loadFromXML(in);
            in.close();

            String ajustes = prop.toString().substring(1, prop.toString().length()-1);
            for(String s : ajustes.split(",")){
                String[] settings = s.split("=");
                if(settings[0].equals("window_size")){
                    String[] dim = settings[1].split("-");
                    setWindowsSize(new Dimension(Integer.parseInt(dim[0]),Integer.parseInt(dim[1])));
                }else if(settings[0].equals("working_directory")){
                    setSpacesFolder(new File(settings[1]));
                }else if(settings[0].equals("theme")){
                    setTheme(Integer.parseInt(settings[1]));
                }else if(settings[0].equals("lang")){
                    setLanguage(Integer.parseInt(settings[1]));
                }
            }

            System.out.println("SALIDA DE CARGAR: " + prop.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
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
        //Actualizamos tanto el SpacesFolder que tenemos como el archivo properties:
        prop.setProperty("working_directory", spacesFolder.getAbsolutePath());
        SpacesFolder = spacesFolder;
        //push();
    }

    /**
     * Devuelve el tema de la aplicación.
     *
     * @return El tema.
     */
    public int getTheme() {
        //return ThemeManager.getTheme();
        return 0;
    }

    /**
     * Cambia el tema de la aplicación.
     *
     * @param theme El tema.
     */
    public void setTheme(int theme) {
        prop.setProperty("theme", Integer.toString(theme));
        ThemeManager.setTheme(theme);
        //push();
    }

    /**
     * Devuelve el idioma de la aplicación.
     *
     * @return El idioma.
     */
    public int getLanguage() {
        return LanguageManager.getActualLocale();
    }

    /**
     * Cambia el idioma de la aplicación.
     *
     * @param language El idioma
     */
    public void setLanguage(int language) {
        LanguageManager.setLanguage(language);

        prop.setProperty("lang", Integer.toString(language));
        //push();
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
        prop.setProperty("window_size", windowsSize.getWidth() + "-" + windowsSize.getHeight());
        WindowsSize = windowsSize;
        //push();
    }
}

