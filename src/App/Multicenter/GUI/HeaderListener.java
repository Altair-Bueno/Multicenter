package App.Multicenter.GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HeaderListener implements MouseListener {
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
                sideBar.psv.get(sideBar.selected).header.changeTitle(title);
                sideBar.selected.changeTitle(title);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
