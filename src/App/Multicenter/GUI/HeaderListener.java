package App.Multicenter.GUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HeaderListener extends MouseAdapter {
    //Atributtes
    SideBar sideBar;

    //Constructor
    public HeaderListener(SideBar sideBar) {
        this.sideBar = sideBar;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            String title = JOptionPane.showInputDialog(sideBar.psv.get(sideBar.selected), "Introduce el nombre para el espacio personal.");
            if (title != null) {
                sideBar.psv.get(sideBar.selected).board.personalSpace.setPersonalSpaceName(title);
                sideBar.psv.get(sideBar.selected).header.changeTitle(title);
                sideBar.selected.changeTitle(title);
            }
        }
    }
}
