package app.multicenter.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveSectionListener implements ActionListener {
    //Attributes
    SideBar sideBar;

    //Constructor
    public RemoveSectionListener(SideBar sideBar) {
        this.sideBar = sideBar;
    }

    //Methods

    /**
     * Al pulsar el boton borrará el espacio personal seleccionado
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (sideBar.selected != null) {
            int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de borrar el espacio " + sideBar.selected + "?\nEste cambio será permanente.");
            if (option == 0) {
                sideBar.delPersonalSpace(sideBar.selected);
                sideBar.app.setDefaultPersonalSpace();
                sideBar.app.ps = null;
                sideBar.selected = null;
            }
        }
    }
}
