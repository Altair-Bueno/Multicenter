package App.Multicenter.GUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SectionListener implements MouseListener {
    //Atributtes
    AppWindow app;
    SideBar parent;
    Section section;
    Section selected;

    public SectionListener(AppWindow app, SideBar parent, Section section, Section selected) {
        this.app = app;
        this.parent = parent;
        this.section = section;
        this.selected = selected;
    }
    //Methods

    /**
     * Muestra el espacio personal que representa la
     * seccion en la cual se ha hecho click
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println(parent.selected);
        app.changePersonalSpace(parent.psv.get(section));
        app.ps.header.updateUI();
        app.ps.board.updateUI();
        if (parent.selected != null) {
            parent.selected.setBackground(section.def);
        }
        section.setBackground(Color.lightGray);
        parent.selected = section;
        section.updateUI();
        parent.selected.updateUI();
        parent.updateUI();
        System.out.println(parent.selected);
    }

    /**
     * Mueve la seccion para cambiarla de orden en
     * la barra lateral
     *
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * Situa la seccion en la posicion donde se haya
     * dejado de pulsar el click del raton
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    //Unused method
    @Override
    public void mouseEntered(MouseEvent e) {
        section.setBackground(Color.lightGray);
    }

    //Unused method
    @Override
    public void mouseExited(MouseEvent e) {
        if(section != parent.selected) {
            section.setBackground(section.def);
        }
    }

}
