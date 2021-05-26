package App.Multicenter.GUI;

import App.Multicenter.Space.PersonalSpace;

import javax.swing.*;
import javax.swing.plaf.ScrollPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SideBar extends JDesktopPane {
    //Attributes
    JTextField searchBox;
    Map<Section, PersonalSpaceView> psv;
    JButton addButton;
    JButton delButton;
    JButton zoom;
    Boolean isShown;
    int numSections;
    //JScrollPane scrollContainer = new JScrollPane();

    //Constructor

    /**
     * Crea una barra lateral vacia, solo se
     * hará una vez al generar el proyecto
     */
    public SideBar() {
        isShown = true;
        numSections = 0;
        psv = new HashMap<>();

        //setBackground(new Color(45, 45, 50));
        setPreferredSize(new Dimension(250, 100));
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.lightGray));
        //scrollContainer.setViewportView(this);

        /**
         * SEARCHBOX
         */
        searchBox = new JTextField(" \uD83D\uDD0D SearchBox");
        searchBox.setPreferredSize(new Dimension(249, 25));
        //searchBox.setBackground(Color.gray);
        searchBox.setBorder(BorderFactory.createEmptyBorder());
        add(searchBox);

        addButton = createButton("ADD", addButton);
        delButton = createButton("DEL", delButton);
        zoom = createButton("\uD83D\uDD0D +", zoom);

        addButton.addActionListener(e -> {
            Section newSec = new Section(numSections);
            PersonalSpace widgets = new PersonalSpace();
            Header newH = new Header("Prueba " + numSections);
            Board newB = new Board(widgets);
            PersonalSpaceView newPsv = new PersonalSpaceView(newH, newB, "Prueba "+ numSections);

            addPersonalSpace(newSec, newPsv);
            System.out.println(psv.toString());
        });
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
        //addButton.setForeground(Color.lightGray);
        //addButton.setBackground(new Color(77,77,77));
        button = new JButton();
        button.setText(type);
        button.setPreferredSize(new Dimension(70, 25));
        //delButton.setForeground(Color.lightGray);
        //delButton.setBackground(new Color(77,77,77));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        add(button);
        return button;
    }



}