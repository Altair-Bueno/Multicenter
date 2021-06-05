package App.Multicenter.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditListener implements ActionListener {
    static Board board;
    //Attributes
    SideBar sideBar;

    public EditListener(SideBar sideBar) {
        this.sideBar = sideBar;
    }

    //Methods

    public static void setBoard(Board b) {
        board = b;
    }

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
                board.addable = false;
                board.addClose();
                sideBar.delButton.setEnabled(false);
                sideBar.addButton.setEnabled(false);
                sideBar.editButton.setText("Edit: ON");
                //sideBar.editButton.setBackground(new Color(51, 208, 1, 255));
            } else {
                board.addable = true;
                board.remClose();
                sideBar.delButton.setEnabled(true);
                sideBar.addButton.setEnabled(true);
                sideBar.editButton.setText("Edit: OFF");
                //sideBar.editButton.setBackground(new Color(215, 0, 0,255));
            }
        }
    }
}
