package App.Multicenter.GUI;

import App.Multicenter.Space.PersonalSpace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AppWindow extends JFrame {
    //Attributes
    PersonalSpaceView ps;
    SideBar sb;
    static final PersonalSpaceView psDefault = new PersonalSpaceView();

    //Constructor
    public AppWindow(PersonalSpaceView personalSpaceView, SideBar sideBar) {
        ps = personalSpaceView;
        sb = sideBar;

        setLayout(new BorderLayout());
        setBounds(0, 0, 800, 800);
        add(ps, BorderLayout.CENTER);
        add(sb, BorderLayout.WEST);
        setVisible(true);
        setMinimumSize(new Dimension(400, 400));
    }

    //Methods

    /**
     * Crea y muestra todos los elementos visuales de la aplicación
     */
    public static void createAndShowGUI() {
        PersonalSpaceView personalSpaceView = new PersonalSpaceView();
        SideBar sideBar = new SideBar();

        AppWindow app = new AppWindow(personalSpaceView, sideBar);
        sideBar.app = app;
    }

    /**
     * Cambia el espacio personal a mostrar en la vista principal de la aplicación
     *
     * @param newPs Espacio personal a mostrar
     */
    public void changePersonalSpace(PersonalSpaceView newPs) {
        remove(ps);
        ps = newPs;
        add(ps);
        ps.header.updateUI();
        ps.board.updateUI();
    }
}
