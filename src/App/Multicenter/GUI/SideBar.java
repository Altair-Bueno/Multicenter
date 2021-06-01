package App.Multicenter.GUI;

import App.Multicenter.Space.PersonalSpace;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SideBar extends JPanel {
    //Attributes
    AppWindow app;
    Section selected;

    JTextField searchBox;
    JButton addButton;
    JButton delButton;
    JButton editButton;

    Map<Section, PersonalSpaceView> psv;
    Boolean isShown;
    int numSections;
    boolean editMode = false;

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
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.lightGray));

        searchBox = new JTextField(" \uD83D\uDD0D SearchBox");
        searchBox.setPreferredSize(new Dimension(249, 25));
        searchBox.setBorder(BorderFactory.createEmptyBorder());
        add(searchBox);

        addButton = createButton("Añadir", addButton);
        delButton = createButton("Borrar", delButton);
        editButton = createButton("Edit: OFF", editButton);

        addButton.addActionListener(new AddSectionListener(this));
        delButton.addActionListener(new RemoveSectionListener(this));
        editButton.addActionListener(new EditListener(this));

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
        if(!psv.containsKey(s)) {
            numSections++;
        }
        psv.put(s, ps);
        System.out.println(psv);
        ps.header.text.addMouseListener(new HeaderListener(this));
        add(s);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void delPersonalSpace(Section s) {
        psv.get(s).board.personalSpace.deletePersonalSpace();
        remove(s);
        psv.remove(s);
        numSections--;
        SwingUtilities.updateComponentTreeUI(this);
    }

    private JButton createButton(String type, JButton button) {
        button = new JButton();
        button.setText(type);
        button.setPreferredSize(new Dimension(78, 25));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        add(button);
        return button;
    }

    public List<PersonalSpace> getPersonalSpaces() {
        return psv.values().stream().map(e -> e.getBoard().personalSpace).collect(Collectors.toList());
    }
}