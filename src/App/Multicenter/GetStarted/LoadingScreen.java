package App.Multicenter.GetStarted;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class LoadingScreen extends JDialog {

    private JPanel rootJPanel;
    private JPanel imagePanel;
    private JPanel progressPanel;
    private JProgressBar progressBar;
    private JLabel imageLabel;

    public LoadingScreen(){
        progressBar.setMaximum(100);
        imageLabel.setIcon(new ImageIcon(ClassLoader.getSystemResource("App/Multicenter/LoadingScreen/loadingscreen_1024.png")));
        add(rootJPanel);

        imageLabel.setVisible(true);
        progressBar.setVisible(true);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setValue(int v){
        progressBar.setValue(v);
    }
}
