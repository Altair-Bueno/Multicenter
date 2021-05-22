package App.Multicenter.GUI;

import java.util.List;
import javax.swing.JPanel;
import App.Multicenter.Space.PersonalSpace;
import App.Multicenter.Widget.Widget;

public class Board {
    //Attributes
    PersonalSpace widgets;
    JPanel panel;
    Widget[][] malla;
    List<WidgetView> widgetV;

    //Constructor
    /** 
     * Crea un objeto Board con los widgets pasados por parametro
     * @param widgets PersonalSpace
     * 
     */
    public Board(PersonalSpace widgets) {
        
    }    

    //Methods
    /** 
     * Añade un widget al tablero donde se muestran
     * @param widget Widget
     * 
     */
    public void addWidget(Widget widget) {

    }

    /** 
     * Elimina un widget del tablero donde se muestran
     * @param widget Widget
     * 
     */
    public void deleteWidget(Widget widget) {

    }

    /** 
     * Edita un widget del tablero donde se muestran
     * @param widget Widget
     * 
     */
    public void editWidget(Widget widget) {

    }
}
