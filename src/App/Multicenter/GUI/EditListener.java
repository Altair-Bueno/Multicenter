package App.Multicenter.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditListener implements ActionListener {
    //Attributes
    SideBar sideBar;

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
            sideBar.editMode = !sideBar.editMode;
            sideBar.psv.get(sideBar.selected).board.personalSpace.toggleEditMode();
            // TODO colours
            if (sideBar.editMode) {
                sideBar.delButton.setEnabled(false);
                sideBar.addButton.setEnabled(false);
                sideBar.editButton.setText("Edit: ON");
                //sideBar.editButton.setBackground(new Color(51, 208, 1, 255));
            } else {
                sideBar.delButton.setEnabled(true);
                sideBar.addButton.setEnabled(true);
                sideBar.editButton.setText("Edit: OFF");
                //sideBar.editButton.setBackground(new Color(215, 0, 0,255));
            }
        }
    }
}
