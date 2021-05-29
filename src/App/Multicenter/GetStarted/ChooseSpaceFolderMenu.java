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
                File selectedFile = fileChooser.getSelectedFile();
                Preferences.setSpacesFolder(selectedFile);
                textField1.setText(selectedFile.getAbsolutePath());
            }
        });

        add(textField1);
        add(panel1);
        add(chooseSpacesFolder);
        add(filechooser);
        setVisible(true);
    }

    public static void main(String[] args) {
        Preferences.loadPreferences();
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        ChooseSpaceFolderMenu c = new ChooseSpaceFolderMenu();
        panel.setLayout(new BorderLayout());
        panel.add(c, BorderLayout.CENTER);
        panel.setVisible(true);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
