package App.Multicenter.GUI;

import javax.swing.*;
import java.awt.*;

public class Section extends JPanel {
    //Atributtes
    SideBar parent;
    String title;
    JLabel label;
    Color def;

    //Constructor
    public Section(int numSections, String nombre, SideBar parent) {
        this.parent = parent;
        def = getBackground();
        title = nombre;

        label = new JLabel();
        label.setText(nombre);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(Color.gray);
        add(label);

        setPreferredSize(new Dimension(249, 40));
        setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.lightGray));
        addMouseListener(new SectionListener(parent.app, parent, this, parent.selected));

        setVisible(true);
    }

    //Methods
    public void changeTitle(String title) {
        PersonalSpaceView old = parent.psv.get(parent.selected);
        parent.psv.remove(this);
        label.setText(title);
        this.title = title;
        parent.addPersonalSpace(this, old);
        updateUI();

    }

    public String toString() {
        return title;
    }
}
