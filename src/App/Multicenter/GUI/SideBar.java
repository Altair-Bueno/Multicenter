package App.Multicenter.GUI;

import App.Multicenter.Space.PersonalSpace;

import javax.swing.*;
import javax.swing.plaf.ScrollPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SideBar extends JPanel {
    //Attributes
    AppWindow app;
    Section selected;
    JTextField searchBox;
    Map<Section, PersonalSpaceView> psv;
    JButton addButton;
    JButton delButton;
    JButton zoom;
    Boolean isShown;
    int numSections;

    //Constructor

    /**
     * Crea una barra lateral vacia, solo se
     * hará una vez al generar el proyecto
     */
    public SideBar() {
        isShown = true;
        numSections = 0;
        selected = null;
        psv = new HashMap<>();

        setPreferredSize(new Dimension(250, 100));
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.lightGray));

        searchBox = new JTextField(" \uD83D\uDD0D SearchBox");
        searchBox.setPreferredSize(new Dimension(249, 25));
        searchBox.setBorder(BorderFactory.createEmptyBorder());
        add(searchBox);

        addButton = createButton("ADD", addButton);
        delButton = createButton("DEL", delButton);
        zoom = createButton("\uD83D\uDD0D +", zoom);

        addButton.addActionListener(new AddSectionListener(this));

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
    public void addPersonalSpace(Section s, PersonalSpaceView ps) {
        psv.put(s, ps);
        add(s);
        numSections++;
        updateUI();
    }

    private JButton createButton(String type, JButton button) {
        button = new JButton();
        button.setText(type);
        button.setPreferredSize(new Dimension(70, 25));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        add(button);
        return button;
    }



}