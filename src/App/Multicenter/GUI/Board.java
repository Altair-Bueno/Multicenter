package App.Multicenter.GUI;

import App.Multicenter.Space.PersonalSpace;
import App.Multicenter.Widget.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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
        List<Widget> widgetList = widgets.getWidgets();

        for (Widget w : widgets.getWidgets()) {
            add((Component) w);
            ((Component) w).setVisible(true);
        }
        numWidgets = widgetList.size();
        setLayout(null);

        JPopupMenu pm = new JPopupMenu("tools");
        JMenu addWidget = new JMenu("Añadir Widget");
        JMenuItem noteWidget = new JMenuItem("Nota de texto");
        JMenuItem imageWidget = new JMenuItem("Imagen(es)");
        JMenuItem filmWidget = new JMenuItem("Pelicula");

        pm.add(addWidget);
        addWidget.add(noteWidget);
        addWidget.add(imageWidget);
        addWidget.add(filmWidget);
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
        imageWidget.addActionListener(new addImageWidgetListener());
        filmWidget.addActionListener(new addfilmWidgetListener());
        setVisible(true);
    }

    //Methods

    /**
     * Añade un widget al tablero donde se muestran
     *
     * @param widget Widget
     */
    public void addWidget(AbstractWidget widget) {
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
    @Deprecated // Los widget se editan todos a la vez y a chuparla
    public void editWidget(Widget widget) {

    }


    private class addNotesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            NotesWidget notes = new NotesWidget(personalSpace.getCarpeta());
            notes.setVisible(true);
            notes.setLocation(lastPos.x, lastPos.y);
            notes.setResizable(true);
            notes.setSize(250, 250);
            //notes.setBounds(lastPos.x, lastPos.y, 250, 250);
            addWidget(notes);
        }
    }

    private class addImageWidgetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ImageWidget imageWidget = new ImageWidget(personalSpace.getCarpeta());
            imageWidget.setVisible(true);
            imageWidget.setLocation(lastPos.x, lastPos.y);
            imageWidget.setResizable(true);
            imageWidget.setSize(250, 250);
            //imageWidget.setBounds(lastPos.x, lastPos.y, 250, 250);
            addWidget(imageWidget);
        }
    }

    private class addfilmWidgetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MovieWidget movieWidget = new MovieWidget();
            movieWidget.setVisible(true);
            movieWidget.setLocation(lastPos.x, lastPos.y);
            movieWidget.setResizable(true);
            movieWidget.setSize(250, 250);
            addWidget(movieWidget);
        }
    }
}
