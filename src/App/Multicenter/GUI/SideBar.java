package App.Multicenter.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SideBar extends JPanel {
    //Attributes
    JTextField searchBox;
    List<PersonalSpaceView> psv;
    JButton addButton;
    JButton delButton;
    JButton zoom;
    Boolean isShown;

    //Constructor

    /**
     * Crea una barra lateral vacia, solo se
     * hará una vez al generar el proyecto
     */
    public SideBar() {
        isShown = true;

        //setBackground(new Color(45, 45, 50));
        setPreferredSize(new Dimension(250, 100));
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.lightGray));

        searchBox = new JTextField(" \uD83D\uDD0D SearchBox");
        searchBox.setPreferredSize(new Dimension(249, 25));
        //searchBox.setBackground(Color.gray);
        searchBox.setBorder(BorderFactory.createEmptyBorder());
        add(searchBox);

        addButton = new JButton();
        addButton.setText("ADD");
        addButton.setPreferredSize(new Dimension(70, 25));
        //addButton.setForeground(Color.lightGray);
        //addButton.setBackground(new Color(77,77,77));
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        add(addButton);

        delButton = new JButton();
        delButton.setText("DEL");
        delButton.setPreferredSize(new Dimension(70, 25));
        //delButton.setForeground(Color.lightGray);
        //delButton.setBackground(new Color(77,77,77));
        delButton.setBorderPainted(false);
        delButton.setFocusPainted(false);
        add(delButton);

        zoom = new JButton();
        zoom.setText("\uD83D\uDD0D +");
        zoom.setPreferredSize(new Dimension(70, 25));
        //zoom.setForeground(Color.lightGray);
        //zoom.setBackground(new Color(77,77,77));
        zoom.setBorderPainted(false);
        zoom.setFocusPainted(false);
        add(zoom);

        setVisible(isShown);
    }

    //Methods

    /**
     * Alterna la visibilidad de la barra lateral
     */
    public void toggleShow() {
        isShown = !isShown;
    }

    /**
     * Añade espacios personales nuevos a la barra lateral
     *
     * @param ps PersonalSpaceView a añadir
     */
    public void addPersonalSpace(PersonalSpaceView ps) {
        psv.add(ps);
    }

}