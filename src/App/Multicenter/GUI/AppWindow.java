package App.Multicenter.GUI;

import App.Multicenter.Space.PersonalSpace;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class AppWindow extends JFrame {
    //Attributes
    PersonalSpaceView ps;
    SideBar sb;
  
    //Constructor
    public AppWindow(PersonalSpaceView personalSpaceView, SideBar sideBar) {
        ps = personalSpaceView;
        sb = sideBar;
        setLayout(new BorderLayout());
        setBounds(0,0,800,800);
        add(ps, BorderLayout.CENTER);
        add(sb, BorderLayout.WEST);
        setVisible(true);
    }
    
    //Methods
    /**
     * Cambia el espacio personal a mostrar en la vista principal de la aplicación
     * 
     * @param newPs Espacio personal a mostrar
     */
    public void changePersonalSpace(PersonalSpaceView newPs) {
        ps = newPs;   
    }
    
    /**
     * Crea y muestra todos los elementos visuales de la aplicación
     *
     */
    public static void createAndShowGUI() {
        PersonalSpace widgets = new PersonalSpace();

        Header header = new Header("THIS IS A TEST HEADER");
        Board board = new Board(widgets);
        PersonalSpaceView personalSpaceView = new PersonalSpaceView(header, board, "PERSONAL SPACE TEST");
        SideBar sideBar = new SideBar();

        AppWindow app = new AppWindow(personalSpaceView, sideBar);
    }
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
