package App.Multicenter.GetStarted;

import App.Multicenter.Preferences.Preferences;

import javax.swing.*;
import java.awt.*;

public class ChooseMulticenterTheme extends JPanel {
    private JPanel panel1;
    private JPanel buttons;


    private ButtonGroup buttonGroup = new ButtonGroup();
    public ChooseMulticenterTheme(JFrame parent){
        buttons.setLayout(new GridLayout(4, 1, 5, 5));
        JRadioButton first = new JRadioButton();
        first.setText("Light");
        first.setSelected(true);
        first.addActionListener(e-> {
            Preferences.setTheme(0);
            SwingUtilities.updateComponentTreeUI(parent);
            parent.pack();
            parent.setLocationRelativeTo(null);

        });
        buttonGroup.add(first); // 0

        JRadioButton second = new JRadioButton();
        second.setText("Dark");
        second.addActionListener(e-> {
            Preferences.setTheme(1);
            SwingUtilities.updateComponentTreeUI(parent);
            parent.pack();
            parent.setLocationRelativeTo(null);

        });
        buttonGroup.add(second); // 1

        JRadioButton third = new JRadioButton();
        third.setText("Darcula");
        third.addActionListener(e -> {
            Preferences.setTheme(2);
            SwingUtilities.updateComponentTreeUI(parent);
            parent.pack();
            parent.setLocationRelativeTo(null);

        });
        buttonGroup.add(third); // 2

        JRadioButton forth = new JRadioButton();
        forth.setText("IntelliJ");
        forth.addActionListener(e-> {
            Preferences.setTheme(3);
            SwingUtilities.updateComponentTreeUI(parent);
            parent.pack();
            parent.setLocationRelativeTo(null);
        });
        buttonGroup.add(forth); // 3

        buttons.add(first);
        buttons.add(second);
        buttons.add(third);
        buttons.add(forth);

        buttons.setVisible(true);
        add(buttons);

        setVisible(true);
        //buttonGroup.setSelected((ButtonModel) first,true);
    }


}
