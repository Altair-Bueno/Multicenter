package App.Multicenter.GetStarted;

import App.Multicenter.Preferences.Preferences;

import javax.swing.*;
import java.io.File;

public class ChooseSpaceFolderMenu extends JPanel {
    private final JTextField textField1 = new JTextField();
    private final JPanel panel1 = new JPanel();
    private final JLabel chooseSpacesFolder = new JLabel();
    private final JButton filechooser = new JButton();

    private File fileLocation = Preferences.getSpacesFolder();


    public ChooseSpaceFolderMenu() {
        // TODO GUI looks horrible
        textField1.setText(Preferences.getSpacesFolder().getAbsolutePath());
        textField1.setEnabled(false);
        chooseSpacesFolder.setText("<html><h1>Choose Spaces Folder used to store all data</h1></html>");
        filechooser.setText("Choose folder");
        filechooser.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(Preferences.getSpacesFolder());
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int out = fileChooser.showDialog(null, "Carpeta SpacesFolder");
            fileChooser.setVisible(true);

            if (out == JFileChooser.APPROVE_OPTION) {
                fileLocation = fileChooser.getSelectedFile();
                textField1.setText(fileLocation.getAbsolutePath());
            }
        });

        add(textField1);
        add(panel1);
        add(chooseSpacesFolder);
        add(filechooser);
        setVisible(true);
    }

    public File getFileLocation() {
        return fileLocation;
    }
}
