package app.multicenter.GUI;

import app.multicenter.Preferences.Preferences;
import app.multicenter.Space.PersonalSpace;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSectionListener implements ActionListener {
    //Attributes
    SideBar sideBar;

    //Constructor
    public AddSectionListener(SideBar sideBar) {
        this.sideBar = sideBar;
    }

    //Methods

    /**
     * Al pulsar el boton añadirá un nuevo espacio personal
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String nombre = JOptionPane.showInputDialog("Introduce el nombre para tu espacio personal");
        if (nombre != null) {
            Section newSec = new Section(sideBar.numSections, nombre, sideBar);
            PersonalSpace widgets = new PersonalSpace(nombre, Preferences.getSpacesFolder());
            Header newH = new Header(nombre);
            Board newB = new Board(widgets);
            PersonalSpaceView newPsv = new PersonalSpaceView(newH, newB, nombre);
            sideBar.addPersonalSpace(newSec, newPsv);
        }
    }
}
