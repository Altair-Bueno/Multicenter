package App.Multicenter.GUI;

import javax.swing.*;
import java.awt.*;

public class Section extends JPanel {
    //Atributtes
    String title;
    JLabel label;

    //Constructor
    public Section(int numSections) {
        title = "Prueba " + numSections;
        label = new JLabel();
        label.setText("Prueba " + numSections);
        add(label);
        setPreferredSize(new Dimension(200, 40));
        setBackground(Color.lightGray);
        setVisible(true);
    }

    //Methods
    public String toString() {
        return title;
    }
}
