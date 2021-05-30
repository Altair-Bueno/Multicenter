package App.Multicenter;

import App.Multicenter.GUI.AppWindow;
import App.Multicenter.GetStarted.GetStartedMenu;
import App.Multicenter.GetStarted.LoadingScreen;
import App.Multicenter.Preferences.Preferences;

import java.awt.*;
import java.util.concurrent.Semaphore;

/**
 * Contiene el método main de la aplicación. Es el punto de entrada
 * para la aplicación de escritorio Multicenter
 */
public class Multicenter {

    public static void main(String[] args) {
        // TODO: Main method
        LoadingScreen loadingScreen = new LoadingScreen();
        boolean b = Preferences.loadPreferences();
        loadingScreen.setValue(10);
        if(Taskbar.getTaskbar().isSupported(Taskbar.Feature.PROGRESS_VALUE))
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
        if(Taskbar.getTaskbar().isSupported(Taskbar.Feature.PROGRESS_VALUE))
            Taskbar.getTaskbar().setProgressValue(40);
        // Multithreading: Load necesary classes
        // Index neccesary data
        // Load GUI
        loadingScreen.setValue(100);
        if(Taskbar.getTaskbar().isSupported(Taskbar.Feature.PROGRESS_VALUE))
            Taskbar.getTaskbar().setProgressValue(100);
        loadingScreen.dispose();
        if(Taskbar.getTaskbar().isSupported(Taskbar.Feature.PROGRESS_VALUE))
            Taskbar.getTaskbar().setProgressValue(-1);

        // ShowGUI
        javax.swing.SwingUtilities.invokeLater(AppWindow::createAndShowGUI);
        // Save preferences
    }
}
