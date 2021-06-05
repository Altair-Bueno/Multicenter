package App.Multicenter.GetStarted;

import App.Multicenter.Preferences.Preferences;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ChooseMulticenterTheme extends JPanel {
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JPanel buttons = new JPanel();
    private JLabel label = new JLabel();

    public ChooseMulticenterTheme(JFrame parent) {
        buttons.setLayout(new GridLayout(2, 2, 5, 5));
        setLayout(new BorderLayout(10,0));
        label.setText("<html><h2 style=\"text-align:center;font-family:verdana;\">Selecciona el tema de la aplicación, podrás cambiarlo más adelante</h2></html>");
        label.setVisible(true);

        JRadioButton first = new JRadioButton();
        first.setText("Light");
        first.addActionListener(e -> {
            Preferences.setTheme(0);
            SwingUtilities.updateComponentTreeUI(parent);
        });
        buttonGroup.add(first); // 0

        JRadioButton second = new JRadioButton();
        second.setText("Dark");
        second.addActionListener(e -> {
            Preferences.setTheme(1);
            SwingUtilities.updateComponentTreeUI(parent);
        });
        buttonGroup.add(second); // 1

        JRadioButton third = new JRadioButton();
        third.setText("Darcula");
        third.addActionListener(e -> {
            Preferences.setTheme(2);
            SwingUtilities.updateComponentTreeUI(parent);
        });
        buttonGroup.add(third); // 2

        JRadioButton fourth = new JRadioButton();
        fourth.setText("IntelliJ");
        fourth.addActionListener(e -> {
            Preferences.setTheme(3);
            SwingUtilities.updateComponentTreeUI(parent);
        });
        buttonGroup.add(fourth); // 3

        switch (Preferences.getTheme()) {
            case 0 -> first.setSelected(true);
            case 1 -> second.setSelected(true);
            case 2 -> third.setSelected(true);
            case 3 -> fourth.setSelected(true);
            default -> throw new IllegalStateException("Dude wtf");
        }


        add(label, BorderLayout.NORTH);
        buttons.add(first);
        buttons.add(second);
        buttons.add(third);
        buttons.add(fourth);

        buttons.setVisible(true);
        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(60, 20));
        add(left, BorderLayout.LINE_START);
        add(buttons, BorderLayout.CENTER);

        setVisible(true);
        //buttonGroup.setSelected((ButtonModel) first,true);
    }


}
