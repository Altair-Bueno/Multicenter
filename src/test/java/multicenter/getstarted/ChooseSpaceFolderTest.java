package multicenter.getstarted;

import app.multicenter.getstarted.ChooseSpaceFolderMenu;
import app.multicenter.preferences.Preferences;

import javax.swing.*;

public class ChooseSpaceFolderTest {
    public static void main(String[] args) {
        Preferences.loadPreferences();
        JFrame j = new JFrame();
        j.add(new ChooseSpaceFolderMenu(j));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}
