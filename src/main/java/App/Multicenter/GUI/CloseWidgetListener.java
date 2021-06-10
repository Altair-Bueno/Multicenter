package app.multicenter.GUI;

import app.multicenter.Widget.AbstractWidget;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class CloseWidgetListener extends InternalFrameAdapter {
    //Atributtes
    AbstractWidget widget;
    Board board;

    //Constructor
    public CloseWidgetListener(AbstractWidget widget, Board board) {
        this.widget = widget;
        this.board = board;
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        //widget.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
        int res = JOptionPane.showConfirmDialog(board, "¿Estás seguro de borrar este widget?\nLos cambios serán permanentes.");
        if (res == 0) {
            board.deleteWidget(widget);
            widget.dispose();
        }
    }
}
