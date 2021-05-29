package App.Multicenter.GetStarted;

import App.Multicenter.Preferences.Preferences;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ChooseSpaceFolderMenu extends JPanel{
    private JTextField textField1;
    private JPanel panel1;
    private JLabel chooseSpacesFolder;
    private JButton filechooser;

    private File fileLocation = Preferences.getSpacesFolder();


    public ChooseSpaceFolderMenu(){
        textField1.setText(Preferences.getSpacesFolder().getAbsolutePath());
        textField1.setEnabled(false);
        chooseSpacesFolder.setText("Choose Spaces Folder used to store all data");
        setLayout(new BorderLayout());
        filechooser.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(Preferences.getSpacesFolder());
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int out = fileChooser.showDialog(null,"Carpeta SpacesFolder");
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
