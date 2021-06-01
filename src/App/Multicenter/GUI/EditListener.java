package App.Multicenter.GUI;

import App.Multicenter.Widget.AbstractWidget;
import App.Multicenter.Widget.Widget;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditListener implements ActionListener {
    //Attributes
    SideBar sideBar;
    boolean editMode = false;

    public EditListener(SideBar sideBar) {
        this.sideBar = sideBar;
    }

    //Methods

    /**
     * Al pulsar el boton se entrará en modo edición
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (sideBar.selected != null) {
            sideBar.psv.get(sideBar.selected).board.personalSpace.toggleEditMode();
        }
    }
}
