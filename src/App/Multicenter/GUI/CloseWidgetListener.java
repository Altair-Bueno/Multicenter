package App.Multicenter.GUI;

import App.Multicenter.Widget.AbstractWidget;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

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
        int res = JOptionPane.showConfirmDialog(board, "¿Estás seguro de borrar este widget?\nLos cambios serán permanentes.");
        if (res == 0) {
            board.deleteWidget(widget);
            widget.dispose();
        }
    }
}
