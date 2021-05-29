package App.Multicenter.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
        if(sideBar.selected != null) {
            sideBar.psv.get(sideBar.selected).board.widgets.toggleEditMode();
        }
    }
}
