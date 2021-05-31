package App.Multicenter.Space;

import App.Multicenter.Widget.Data.WidgetData;

import java.io.Serializable;
import java.util.List;

/**
 * Clase serializable intermedia utilizada para almacenar
 * los datos de la aplicación y recuperarlos sin problemas
 * Para poder cargar estos datos en una instancia de
 * PersonalSpaces, es necesario utilizar {@link PersonalSpace#loadPersonalSpaces(PersonalSpaceData)}
 *
 * @see PersonalSpace#loadPersonalSpaces(PersonalSpaceData)
 */
public class PersonalSpaceData implements Serializable {
    public List<WidgetData> widgetData;
    public String id;
    public String folderPath;
    public String personalSpaceName;
}
