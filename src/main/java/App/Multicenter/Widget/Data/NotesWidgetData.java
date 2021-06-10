package app.multicenter.Widget.Data;

import java.io.Serializable;

/**
 * Clase serializable intermedia utilizada para almacenar los
 * datos de la aplicación y recuperarlos sin problemas. Para poder
 * cargar estos datos en su instancia correspondiente (clase que implementa Widgets)
 * es necesario utilizar {@link app.multicenter.Widget.Widget#instanciateWidgetsFromData(WidgetData)}
 *
 * @see app.multicenter.Widget.Widget#instanciateWidgetsFromData(WidgetData)
 */
public class NotesWidgetData extends WidgetData implements Serializable {
    public String markdownFile;
}
