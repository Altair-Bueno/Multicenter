package App.Multicenter.GetStarted;

import App.Multicenter.Preferences.Preferences;

import javax.swing.*;
import java.io.File;

public class ChooseSpaceFolderMenu extends JPanel{
    private JTextField textField1;
    private JPanel panel1;
    private JLabel chooseSpacesFolder;
    private JButton filechooser;

    public ChooseSpaceFolderMenu(){
        textField1.setText(Preferences.getSpacesFolder().getAbsolutePath());
        chooseSpacesFolder.setText("Choose Spaces Folder used to store all data");
        filechooser.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(Preferences.getSpacesFolder());
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int out = fileChooser.showDialog(null,"Carpeta SpacesFolder");
            fileChooser.setVisible(true);
            if (out == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                Preferences.setSpacesFolder(selectedFile);
                textField1.setText(selectedFile.getAbsolutePath());
            }
        });
        setVisible(true);
    }
}
