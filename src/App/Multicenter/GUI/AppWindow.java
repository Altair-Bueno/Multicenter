package App.Multicenter.GUI;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class AppWindow extends JFrame {
    static final PersonalSpaceView psDefault = new PersonalSpaceView();
    //Attributes
    PersonalSpaceView ps;
    SideBar sb;

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
        Image im = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("App/Multicenter/Icons/512x512.png"));
        setIconImage(im.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        //setIconImage(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("App/Multicenter/Icons/512x512.png")));
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
