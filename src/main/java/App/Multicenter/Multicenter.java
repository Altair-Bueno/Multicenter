package app.multicenter;

import app.multicenter.gui.AppWindow;
import app.multicenter.getstarted.*;
import app.multicenter.preferences.Preferences;
import app.multicenter.getstarted.LoadingScreen;

import java.awt.*;
import java.util.concurrent.Semaphore;

/**
 * Contiene el método main de la aplicación. Es el punto de entrada
 * para la aplicación de escritorio Multicenter
 */
public class Multicenter {

    public static void main(String[] args) {
        LoadingScreen loadingScreen = new LoadingScreen();
        boolean b = Preferences.loadPreferences();
        loadingScreen.setValue(10);
        if (Taskbar.getTaskbar().isSupported(Taskbar.Feature.PROGRESS_VALUE))
            Taskbar.getTaskbar().setProgressValue(10);
        // if(b) get started gui
        if (!b) {
            Semaphore semaphore = new Semaphore(0);
            GetStartedMenu getStartedMenu = new GetStartedMenu(semaphore);
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            Preferences.save();
            semaphore = null;
            getStartedMenu = null;
        }
        loadingScreen.setValue(40);
        if (Taskbar.getTaskbar().isSupported(Taskbar.Feature.PROGRESS_VALUE))
            Taskbar.getTaskbar().setProgressValue(40);
        // Multithreading: Load necesary classes
        // Index neccesary data
        // Load GUI
        loadingScreen.setValue(100);
        if (Taskbar.getTaskbar().isSupported(Taskbar.Feature.PROGRESS_VALUE))
            Taskbar.getTaskbar().setProgressValue(100);
        // ShowGUI
        Semaphore semaphore = new Semaphore(0);
        javax.swing.SwingUtilities.invokeLater(() -> AppWindow.createAndShowGUI(semaphore));

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        // Remove loading screen
        loadingScreen.dispose();
        if (Taskbar.getTaskbar().isSupported(Taskbar.Feature.PROGRESS_VALUE))
            Taskbar.getTaskbar().setProgressValue(-1);
    }
}
