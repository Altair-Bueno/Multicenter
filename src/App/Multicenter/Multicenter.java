package App.Multicenter;

import App.Multicenter.GUI.AppWindow;
import App.Multicenter.GetStarted.GetStartedMenu;
import App.Multicenter.GetStarted.LoadingScreen;
import App.Multicenter.Preferences.Preferences;
import com.apple.eawt.Application;

import java.awt.*;
import java.util.concurrent.Semaphore;

/**
 * Contiene el método main de la aplicación. Es el punto de entrada
 * para la aplicación de escritorio Multicenter
 */
public class Multicenter {

    public static void main(String[] args) {
        // MacOS: Vars
        boolean macosx = System.getProperty("os.name").equals("Mac OS X");
        Application macapp = Application.getApplication();
        if(macosx){
            // MacOS: Upper Bar App Name Initialization
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.name", "Multicenter");
            // MacOS: Icon
            macapp.setDockIconImage(
                    Toolkit.getDefaultToolkit().createImage(
                            ClassLoader.getSystemResource("App/Multicenter/Icons/512x512.png")
                    )
            );
        }

        // TODO: Main method
        LoadingScreen loadingScreen = new LoadingScreen();
        boolean b = Preferences.loadPreferences();
        loadingScreen.setValue(10);
        if(macosx) macapp.setDockIconProgress(10);
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
        if(macosx) macapp.setDockIconProgress(40);
        // Multithreading: Load necesary classes
        // Index neccesary data
        // Load GUI
        loadingScreen.setValue(100);
        if(macosx) macapp.setDockIconProgress(100);
        loadingScreen.dispose();
        macapp.setDockIconProgress(0);

        // ShowGUI
        javax.swing.SwingUtilities.invokeLater(AppWindow::createAndShowGUI);
        // Save preferences
    }
}
