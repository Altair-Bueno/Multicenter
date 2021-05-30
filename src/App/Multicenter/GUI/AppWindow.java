package App.Multicenter.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class AppWindow extends JFrame {
    static JEditorPane psDefault = null;

    static {
        try {
            psDefault = new JEditorPane(ClassLoader.getSystemResource("App/Multicenter/PlaceholderFiles/PersonalSpacePlaceholder.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Attributes
    PersonalSpaceView ps;
    SideBar sb;

    //Constructor
    public AppWindow(PersonalSpaceView personalSpaceView, SideBar sideBar) {
        ps = personalSpaceView;
        sb = sideBar;

        setName("Multicenter");
        setLayout(new BorderLayout());
        setBounds(0, 0, 800, 800);
        //add(ps, BorderLayout.CENTER);
        add(sb, BorderLayout.WEST);
        setLocationRelativeTo(null);
        setVisible(true);
        setMinimumSize(new Dimension(400, 400));

        // Iconos (Windows)
        Image im = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("App/Multicenter/Icons/512x512.png"));
        ArrayList<Image> icons = new ArrayList<Image>();
        icons.add(im.getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        icons.add(im.getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        setIconImages(icons);

        // Titulo del Jframe
        setTitle("Multicenter");
    }

    //Methods

    /**
     * Crea y muestra todos los elementos visuales de la aplicación
     */
    public static void createAndShowGUI() {
        PersonalSpaceView personalSpaceView = null;
        SideBar sideBar = new SideBar();

        AppWindow app = new AppWindow(personalSpaceView, sideBar);
        app.setDefaultPersonalSpace();
        sideBar.app = app;
    }

    /**
     * Cambia el espacio personal a mostrar en la vista principal de la aplicación
     *
     * @param newPs Espacio personal a mostrar
     */
    public void changePersonalSpace(PersonalSpaceView newPs) {
        if (ps != null) {
            remove(ps);
        } else {
            remove(psDefault);
        }
        ps = newPs;
        add(ps);
        ps.header.updateUI();
        ps.board.updateUI();
    }

    public void setDefaultPersonalSpace() {
        if (ps != null) {
            remove(ps);
        }
        psDefault.setEnabled(false);
        add(psDefault);
        psDefault.setContentType("text/html");

        SwingUtilities.updateComponentTreeUI(this);
    }
}
