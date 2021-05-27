package App.Multicenter.GUI;

import App.Multicenter.Space.PersonalSpace;
import App.Multicenter.Widget.Widget;

import javax.swing.*;
import java.util.List;

public class Board extends JDesktopPane {
    //Attributes
    PersonalSpace widgets;
    Widget[][] malla;
    List<WidgetView> widgetV;

    //Constructor

    /**
     * Crea un objeto Board con los widgets pasados por parametro
     *
     * @param widgets PersonalSpace
     */
    public Board(PersonalSpace widgets) {
        this.widgets = widgets;

        //setBackground(new Color(Color.BLUE.getRGB()));

        setVisible(true);
    }

    //Methods

    /**
     * Añade un widget al tablero donde se muestran
     *
     * @param widget Widget
     */
    public void addWidget(Widget widget) {

    }

    /**
     * Elimina un widget del tablero donde se muestran
     *
     * @param widget Widget
     */
    public void deleteWidget(Widget widget) {

    }

    /**
     * Edita un widget del tablero donde se muestran
     *
     * @param widget Widget
     */
    public void editWidget(Widget widget) {

    }
}
