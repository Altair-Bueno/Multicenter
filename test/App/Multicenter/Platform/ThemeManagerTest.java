package App.Multicenter.Platform;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

public class ThemeManagerTest {
    JFrame jFrame = new JFrame();

    @Before
    public void loadGUI(){
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton jButton = new JButton();
        JCheckBox jCheckBox = new JCheckBox();
        JLabel jLabel = new JLabel("This is a test");
        jFrame.add(jButton);
        jFrame.add(jCheckBox);
        jFrame.add(jLabel);
        jButton.setSize(new Dimension(100,100));
        jFrame.setSize(300,300);
        jFrame.setVisible(true);
    }

    @Test
    public void main() throws InterruptedException {
        ThemeManager.setTheme(0);
        SwingUtilities.updateComponentTreeUI(jFrame);
        Thread.sleep(1000);
        ThemeManager.setTheme(1);
        SwingUtilities.updateComponentTreeUI(jFrame);
        Thread.sleep(1000);
        ThemeManager.setTheme(2);
        SwingUtilities.updateComponentTreeUI(jFrame);
        Thread.sleep(1000);
        ThemeManager.setTheme(3);
        SwingUtilities.updateComponentTreeUI(jFrame);
        Thread.sleep(1000);
        ThemeManager.setTheme(4);
        SwingUtilities.updateComponentTreeUI(jFrame);
        Thread.sleep(1000);
    }
}
