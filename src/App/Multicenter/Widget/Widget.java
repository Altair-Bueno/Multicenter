package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;
import App.Multicenter.Widget.Data.NotesWidgetData;
import App.Multicenter.Widget.Data.WidgetData;

import java.awt.*;

public interface Widget {

    String NOTESWIDGET = "NotesWidget";

    static Widget instanciateWidgetsFromData(WidgetData wd) {
        Widget w = null;
        switch (wd.classname) {
            case NOTESWIDGET:
                w = new NotesWidget((NotesWidgetData) wd);
                break;
        }
        return w;
    }

    /**
     * Busca la cadena pasada como parámetro
     * en todos los Strings del widget.
     * Será diferente para cada tipo de widget
     * porque cada uno tendrá su forma de
     * almacenar y usar Strings.
     *
     * @param cadena La cadena a buscar.
     * @return True si la cadena está, False si no.
     */
    SearchedString<Widget> buscar(String cadena);

    /**
     * Devuelve el identificador
     * del widget, que es un String.
     *
     * @return El identificador.
     */
    String getId();

    /**
     * Devuelve la capa del widget
     * que es un número entero.
     *
     * @return La capa.
     */
    int getLayer();

    /**
     * Cambia la capa del widget por
     * la pasada como parámetro.
     */
    void setLayer(int capa);

    void toggleEditMode();

    void setSize(Dimension d);

    Dimension getSize(Dimension rv);

    float getAlignmentX();

    void setAlignmentX(float alignmentX);

    float getAlignmentY();

    void setAlignmentY(float alignmentY);

    WidgetData getWidgetsDataInstance();
}
