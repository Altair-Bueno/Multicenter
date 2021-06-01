package App.Multicenter.GUI;

import App.Multicenter.Widget.AbstractWidget;
import App.Multicenter.Widget.Widget;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class CloseWidgetListener implements InternalFrameListener {
    //Atributtes
    AbstractWidget widget;
    Board board;

    //Constructor
    public CloseWidgetListener(AbstractWidget widget, Board board) {
        this.widget = widget;
        this.board = board;
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        int res = JOptionPane.showConfirmDialog(board, "¿Estás seguro de borrar este widget?\nLos cambios serán permanentes.");
        if(res == 0) {
            board.deleteWidget(widget);
            widget.dispose();
        }
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {

    }
}
