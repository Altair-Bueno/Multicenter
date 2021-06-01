package App.Multicenter.GUI;

import App.Multicenter.Buddy.XMLBuddy;
import App.Multicenter.Preferences.Preferences;
import App.Multicenter.Space.PersonalSpace;
import App.Multicenter.Space.PersonalSpaceData;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

public class AppWindow extends JFrame {
    static JEditorPane psDefault = null;

    static {
        try {
            psDefault = new JEditorPane(ClassLoader.getSystemResource("App/Multicenter/Placeholder/Files/PersonalSpacePlaceholder.html"));
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

        setBounds(0, 0, (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.75), (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.75));
        //add(ps, BorderLayout.CENTER);
        add(sb, BorderLayout.WEST);
        setLocationRelativeTo(null);
        //setVisible(true); // Set visible disable until app completely loaded
        setMinimumSize(new Dimension(400, 400));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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

    public static void createAndShowGUI(Semaphore semaphore){
        createAndShowGUI();
        semaphore.release();
    }

    /**
     * Crea y muestra todos los elementos visuales de la aplicación
     */
    public static void createAndShowGUI() {
        PersonalSpaceView personalSpaceView = null;
        SideBar sideBar = new SideBar();
        AppWindow app = new AppWindow(personalSpaceView, sideBar);
        app.setDefaultPersonalSpace();
        sideBar.app = app;

        if (Preferences.getSpacesSaveFile().exists()) {
            XMLBuddy<List<PersonalSpaceData>> x = new XMLBuddy<>();
            List<PersonalSpaceData> data = new ArrayList<>();

            try {
                data = x.readFromFile(Preferences.getSpacesSaveFile());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            for (PersonalSpaceData d : data) {
                PersonalSpace personalSpace = PersonalSpace.loadPersonalSpaces(d);
                PersonalSpaceView psv = new PersonalSpaceView(
                        new Header(d.personalSpaceName),
                        new Board(personalSpace),
                        d.personalSpaceName);
                Section section = new Section(sideBar.numSections, d.personalSpaceName, sideBar);
                sideBar.addPersonalSpace(section, psv);
            }
        }
        app.setVisible(true);
    }


    @Override
    public void dispose() {
        // Save settings
        Preferences.save();
        // Save personalSpace
        List<PersonalSpaceData> personalSpaceData = sb.getPersonalSpaces().
                parallelStream().
                map(PersonalSpace::getPersonalSpaceDataInstance).
                collect(Collectors.toList());

        XMLBuddy<List<PersonalSpaceData>> x = new XMLBuddy<>();
        x.saveToFile(Preferences.getSpacesSaveFile(), personalSpaceData);

        super.dispose();

        System.exit(0);
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
        SwingUtilities.updateComponentTreeUI(this);
        //ps.header.updateUI();
        //ps.board.updateUI();
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
