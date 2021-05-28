package App.Multicenter;

import App.Multicenter.Preferences.Preferences;

/**
 * Contiene el método main de la aplicación. Es el punto de entrada
 * para la aplicación de escritorio Multicenter
 */
public class Multicenter {
    public static void main(String[] args) {
        // TODO: Main method
        boolean b = Preferences.loadPreferences();
        // if(b) get started gui
        // Multithreading: Load necesary classes
        // Index neccesary data
        // Load GUI
        // Save preferences
        Preferences.save();
    }
}
