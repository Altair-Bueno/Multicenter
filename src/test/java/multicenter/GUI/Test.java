package multicenter.GUI;

import app.multicenter.Preferences.Preferences;

import java.lang.reflect.InvocationTargetException;

import static app.multicenter.GUI.AppWindow.createAndShowGUI;

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
