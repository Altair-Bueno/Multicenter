package App.Multicenter.Widget;

import App.Multicenter.Preferences.Preferences;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.ByteOrder;

public class ImageWidgetTest2 {

    public static void panelexaminar() {
        JButton button = new JButton("Examinar");
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(500, 500));

        JPanel panelAñadir = new JPanel();

        button.addActionListener(e -> {

            JFileChooser fileChooser = new JFileChooser(Preferences.getSpacesFolder());
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int out = fileChooser.showDialog(frame, "Carpeta de imágenes");
            fileChooser.setVisible(true);

            if (out == JFileChooser.APPROVE_OPTION) {
                File fileLocation = fileChooser.getSelectedFile();
            }
        });

        panelAñadir.add(button);
        frame.add(panelAñadir);


        frame.setVisible(true);
    }
    public static void main(String[] args) {
        Preferences.loadPreferences();

        Preferences.save();
    }
}
