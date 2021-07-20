package app.multicenter.getstarted;

import app.multicenter.preferences.Preferences;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Clase para preguntar al usuario qué carpeta desea que sea en la que
 * se guarden sus espacios personales.
 */

public class ChooseSpaceFolderMenu extends JPanel {
    private final JTextField dir = new JTextField();
    private final JLabel chooseSpacesFolder = new JLabel();
    private final JButton fileChooser = new JButton();

    private File fileLocation = Preferences.getSpacesFolder();

    /**
     * Método para setear el spaceFolder.
     *
     * @param parent
     */


    public ChooseSpaceFolderMenu(JFrame parent) {
        setLayout(new BorderLayout(0, 10));
        JPanel center = new JPanel();

        chooseSpacesFolder.setText(
                "<html>" +
                        "<h2 style=\"text-align:center;font-family:verdana;\">" +
                        "Elige un directorio para almacenar los datos de la aplicación" +
                        "</h2>" +
                        "</html>");

        dir.setText(Preferences.getSpacesFolder().getAbsolutePath());
        dir.setEnabled(false);
        center.add(dir);

        fileChooser.setText("Choose folder");
        fileChooser.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(Preferences.getSpacesFolder());
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int out = fileChooser.showDialog(parent, "Carpeta SpacesFolder");
            fileChooser.setVisible(true);

            if (out == JFileChooser.APPROVE_OPTION) {
                fileLocation = fileChooser.getSelectedFile();
                dir.setText(fileLocation.getAbsolutePath());
            }
        });
        center.add(fileChooser);

        add(chooseSpacesFolder, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Método que devuelve la localización del archivo.
     *
     * @return localización actual.
     */

    public File getFileLocation() {
        return fileLocation;
    }
}
