package App.Multicenter.GUI;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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
        setLocationRelativeTo(null);
        setVisible(true);
        setMinimumSize(new Dimension(400, 400));

        Image im = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("App/Multicenter/Icons/512x512.png"));
        ArrayList<Image> icons = new ArrayList<Image>();
        icons.add(im.getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        icons.add(im.getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        setIconImages(icons);
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
