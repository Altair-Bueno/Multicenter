package app.multicenter.getstarted;

import javax.swing.*;

public class LoadingScreen extends JDialog {

    private JPanel rootJPanel;
    private JPanel imagePanel;
    private JPanel progressPanel;
    private JProgressBar progressBar;
    private JLabel imageLabel;

    public LoadingScreen() {
        toBack();
        progressBar.setMaximum(100);
        imageLabel.setIcon(new ImageIcon(ClassLoader.getSystemResource("app/multicenter/LoadingScreen/loadingscreen_1024.png")));
        add(rootJPanel);

        imageLabel.setVisible(true);
        progressBar.setVisible(true);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setValue(int v) {
        progressBar.setValue(v);
    }
}
