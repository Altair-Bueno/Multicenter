package App.Multicenter.GUI;

import App.Multicenter.Space.PersonalSpace;
import App.Multicenter.Widget.*;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.awt.event.*;
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
        JMenuItem ytWidget = new JMenuItem("Video de YouTube");

        pm.add(addWidget);
        addWidget.add(noteWidget);
        addWidget.add(imageWidget);
        addWidget.add(filmWidget);
        addWidget.add(ytWidget);
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
        ytWidget.addActionListener(new addytWidgetListener());
        setVisible(true);
    }

    //Methods

    /**
     * Añade un widget al tablero donde se muestran
     *
     * @param widget Widget
     */
    public void addWidget(AbstractWidget widget) {
        widget.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
        widget.addInternalFrameListener(new InternalFrameListener() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {

            }

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                System.out.println("ok");
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                System.out.println("ok");
                System.out.println("ok");
                deleteWidget(widget);
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
        });
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
        personalSpace.deleteWidget(widget);
        numWidgets--;
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
            notes.setSize(250, 250);
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
            movieWidget.setSize(400, 350);
            addWidget(movieWidget);
        }
    }

    private class addytWidgetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            YoutubeWidget ytWidget = new YoutubeWidget();
            ytWidget.setVisible(true);
            ytWidget.setLocation(lastPos.x, lastPos.y);
            ytWidget.setResizable(true);
            addWidget(ytWidget);
        }
    }
}
