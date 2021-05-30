package App.Multicenter.GUI;

import App.Multicenter.Space.PersonalSpace;
import App.Multicenter.Widget.AbstractWidget;
import App.Multicenter.Widget.NotesWidget;
import App.Multicenter.Widget.Widget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JDesktopPane {
    //Attributes
    PersonalSpace personalSpace;
    int numWidgets;
    Point lastPos = new Point();

    //Constructor

    /**
     * Crea un objeto Board con los widgets pasados por parametro
     *
     * @param widgets PersonalSpace
     */
    public Board(PersonalSpace widgets) {
        this.personalSpace = widgets;
        numWidgets = 0;
        //widgetList = new LinkedList<>();
        setLayout(null);

        JPopupMenu pm = new JPopupMenu("tools");
        JMenu addWidget = new JMenu("Añadir Widget");
        JMenuItem noteWidget = new JMenuItem("Nota de texto");

        pm.add(addWidget);
        addWidget.add(noteWidget);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    lastPos.x = e.getX();
                    lastPos.y = e.getY();
                    pm.show(Board.this, lastPos.x, lastPos.y);
                }
            }
        });
        noteWidget.addActionListener(new addNotesListener());
        setVisible(true);
    }

    //Methods

    /**
     * Añade un widget al tablero donde se muestran
     *
     * @param widget Widget
     */
    public void addWidget(AbstractWidget widget) {
        //JOptionPane.showMessageDialog(null,"No puedes añadir widgets a la ventana de inicio");
        //widgetList.add(widget);
        personalSpace.addWidget(widget);
        add(widget);
        numWidgets++;
    }

    /**
     * Elimina un widget del tablero donde se muestran
     *
     * @param widget Widget
     */
    public void deleteWidget(Widget widget) {
        //TODO Delete selected widget from board
    }

    /**
     * Edita un widget del tablero donde se muestran
     *
     * @param widget Widget
     */
    public void editWidget(Widget widget) {
        //TODO Edit selected widget from board
    }

    // TODO Cuando se pincha sobre un widget debe traerse al frente
    // Hay que modificar la propiedad set layer

    private class addNotesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            NotesWidget notes = new NotesWidget(numWidgets, personalSpace.getCarpeta());
            notes.setVisible(true);
            notes.setBounds(lastPos.x, lastPos.y, 250, 250);
            addWidget(notes);
        }
    }
}
