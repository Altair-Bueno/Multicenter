package app.multicenter.widget.data;

import java.io.Serializable;

/**
 * Clase serializable intermedia utilizada para almacenar los
 * datos de la aplicación y recuperarlos sin problemas. Para poder
 * cargar estos datos en su instancia correspondiente (clase que implementa Widgets)
 * es necesario utilizar {@link app.multicenter.widget.Widget#instanciateWidgetsFromData(WidgetData)}
 *
 * @see app.multicenter.widget.Widget#instanciateWidgetsFromData(WidgetData)
 */
public class ImageWidgetData extends WidgetData implements Serializable {
    public String imagesFolder;
    public String[] images;
    public String[] footer;
    public int position;
}
