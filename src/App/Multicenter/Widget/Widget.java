package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;
import App.Multicenter.Widget.Data.ImageWidgetData;
import App.Multicenter.Widget.Data.MovieWidgetData;
import App.Multicenter.Widget.Data.NotesWidgetData;
import App.Multicenter.Widget.Data.WidgetData;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public interface Widget {

    String NOTES = "NotesWidget";
    String COUNTDOWN = "CountdownWidget";
    String EMBEDDEDMOVIE = "MovieWidget";
    String EMBEDDEDYT = "YoutubeWidget";
    String IMAGE = "ImageWidget";

    /**
     * Porporciona una instancia de Widget obtenida a través de
     * un WidgetData
     *
     * @param wd WidgetData
     * @return Instancia de su Widget correspondiente
     */
    static Widget instanciateWidgetsFromData(WidgetData wd) {
        // TODO No todos los widgets están listos para ser instanciados
        // Patrón de factoría abstracta :D
        Widget w = null;
        switch (wd.getClass().getName()) {
            case NOTES:
                w = new NotesWidget((NotesWidgetData) wd);
                break;
            case COUNTDOWN:
                break;
            case EMBEDDEDMOVIE:
                w = new MovieWidget((MovieWidgetData) wd);
                break;
            case EMBEDDEDYT:
                break;
            case IMAGE:
                w = new ImageWidget((ImageWidgetData) wd);
                break;
        }
        return w;
    }

    /**
     * Devuelve un objeto que extiende WidgetData en el que
     * se almacena el estado del JInternalFrame. Las instancias
     * de WidgetData son serializables, por lo que pueden ser
     * facilmente almacenadas y leidas de disco
     *
     * @return WidgetData con la instancia actual
     */
    WidgetData getWidgetsDataInstance();

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
    SearchedString<Widget> search(String cadena);

    /**
     * Activa o desactiva el comportamiento del modo edición
     */
    void toggleEditMode();

    /**
     * Borra los archivos utilizados en este Widget
     */
    void deleteWidget();

    /**
     * Mueve el Widget a la carpeta que recibe como parámetro
     *
     * @param folder carpeta a la que mover los archivos
     */
    void moveFilesToFolder(File folder) throws IOException;

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

    /**
     * @return tamaño
     * @see javax.swing.JInternalFrame#getSize() ()
     */
    Dimension getSize();

    /**
     * @param d tamaño
     * @see javax.swing.JInternalFrame#setSize(Dimension)
     */
    void setSize(Dimension d);

    /**
     * @return posción eje x
     * @see JInternalFrame#getAlignmentX()
     */

    float getAlignmentX();

    /**
     * @param alignmentX posición eje x
     * @see JInternalFrame#setAlignmentY(float)
     */
    void setAlignmentX(float alignmentX);

    /**
     * @return posición eje y
     * @see JInternalFrame#getAlignmentY()
     */
    float getAlignmentY();

    /**
     * @param alignmentY posición eje y
     * @see JInternalFrame#setAlignmentY(float)
     */
    void setAlignmentY(float alignmentY);

}
