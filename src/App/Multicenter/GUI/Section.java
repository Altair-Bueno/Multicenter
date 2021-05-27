package App.Multicenter.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
        setBorder(BorderFactory.createMatteBorder(1,0,1,0,Color.lightGray));

        setVisible(true);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parent.app.changePersonalSpace(parent.psv.get(Section.this));
                parent.app.ps.header.updateUI();
                parent.app.ps.board.updateUI();
                updateUI();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //TODO Drag listener for sections
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //TODO Drop listener for sections
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                def = getBackground();
                setBackground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(def);
            }
        });
    }

    //Methods
    public String toString() {
        return title;
    }
}
