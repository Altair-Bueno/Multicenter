package App.Multicenter.GUI;

import App.Multicenter.Preferences.Preferences;

import java.lang.reflect.InvocationTargetException;

import static App.Multicenter.GUI.AppWindow.createAndShowGUI;

public class Test {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                boolean b = Preferences.loadPreferences();
                //Desktop.getDesktop().set
                createAndShowGUI();
                Preferences.save();
            }
        });
    }
}
