package App.Multicenter.Widget.Data;

import java.io.Serializable;

/**
 * Clase serializable intermedia utilizada para almacenar los
 * datos de la aplicación y recuperarlos sin problemas. Para poder
 * cargar estos datos en su instancia correspondiente (clase que implementa Widgets)
 * es necesario utilizar {@link App.Multicenter.Widget.Widget#instanciateWidgetsFromData(WidgetData)}
 *
 * @see App.Multicenter.Widget.Widget#instanciateWidgetsFromData(WidgetData)
 */
public class ImageWidgetData extends WidgetData implements Serializable {
    public String imagesFolder;
    public String [] images;
    public String [] footer;
    public int position;
}
