package App.Multicenter.GetStarted;

import App.Multicenter.Preferences.Preferences;

import javax.swing.*;
import java.awt.*;

public class ChooseMulticenterTheme extends JPanel {
    private JPanel panel1;
    private JPanel buttons;


    private ButtonGroup buttonGroup = new ButtonGroup();
    private int code = 0;

    public ChooseMulticenterTheme(){
        buttons.setLayout(new GridLayout(4, 1, 5, 5));
        JRadioButton first = new JRadioButton();
        first.setText("Hola");
        first.setSelected(true);
        first.addActionListener(e-> {
            code = 0;
            Preferences.setTheme(0);
            //SwingUtilities.updateComponentTreeUI(this);
        });
        buttonGroup.add(first); // 0

        JRadioButton second = new JRadioButton();
        second.setText("Pito");
        second.addActionListener(e-> {
            code = 1;
            Preferences.setTheme(1);
        });
        buttonGroup.add(second); // 1

        JRadioButton third = new JRadioButton();
        third.addActionListener(e -> {
            code = 2;
            Preferences.setTheme(2);
        });
        buttonGroup.add(third); // 2

        JRadioButton forth = new JRadioButton();
        forth.addActionListener(e-> {
            code = 3;
            Preferences.setTheme(3);
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

    public int getThemeCode(){
        return code;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        ChooseMulticenterTheme c = new ChooseMulticenterTheme();
        f.add(c);
        f.pack();
        f.setVisible(true);
    }

}
