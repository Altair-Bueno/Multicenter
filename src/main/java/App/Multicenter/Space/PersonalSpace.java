package app.multicenter.space;

import app.multicenter.widget.AbstractWidget;
import app.multicenter.widget.Widget;
import app.multicenter.widget.data.WidgetData;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Clase que representa un espacio personal del usuario.
 * Cada espacio personal tendrá un árbol de la clase Tree
 * implementada por nosotros donde se almacenen todos los
 * widgets que tenga el usuario en el espacio personal.
 * Ofrecerá operaciones para añadir y eliminar widgets,
 * y para buscar cadenas en el espacio personal.
 */
public class PersonalSpace implements Closeable, Serializable {

    private String personalSpaceName;
    private List<Widget> widgets = new ArrayList<>();
    private String id;
    private File carpeta;


    /**
     * Instancia un nuevo espacio personal. Se intenta abrir el carpeta XML
     * con la información del árbol de widgets almacenada dentro y si no se
     * encuentra el carpeta (porque no está creado, o porque haya habido
     * algún error), se crea un árbol de widgets vacío)
     * @param name Nombre del espacio personal
     * @param f carpeta que le corresponde
     */

    public PersonalSpace(String name, File f) {
        personalSpaceName = name;
        RandomNameGenerator rng = new RandomNameGenerator();
        id = rng.generate(f);
        carpeta = new File(f, id);
        carpeta.mkdir();
    }

    // Constructor privado utilizado para cargar el PersonalSpacesData
    private PersonalSpace(PersonalSpaceData data) {
        personalSpaceName = data.personalSpaceName;
        id = data.id;
        carpeta = new File(data.folderPath);

        for (WidgetData w : data.widgetData)
            widgets.add(Widget.instanciateWidgetsFromData(w));
    }

    /**
     * Carga el PersonalSpacesData en una instancia de PersonalSpaces.
     *
     * @param data PersonalSpacesData con la información deserializada
     * @return Instancia de PersonalSpaces
     */
    public static PersonalSpace loadPersonalSpaces(PersonalSpaceData data) {
        return new PersonalSpace(data);
    }


    /**
     * Añade el widget pasado por parámetro al
     * árbol de widgets del espacio personal.
     *
     * @param w Widget a añadir al árbol.
     */
    public void addWidget(Widget w) {
        widgets.add(w);
    }

    /**
     * Activa el modo edición de para todos los widgets
     */
    public void toggleEditMode() {
        for (Widget w : widgets)
            w.toggleEditMode();
    }

    /**
     * Borra los archivos del PersonalSpace
     */
    public void deletePersonalSpace() {
        for (Widget w : widgets)
            w.deleteWidget();
        carpeta.delete();
        carpeta = null;
        widgets = null;
        id = null;
    }

    /**
     * Elimina el widget pasado por parámetro del
     * árbol de widgets del espacio personal.
     *
     * @param w Widget a eliminar del árbol.
     */
    public void deleteWidget(Widget w) {
        widgets.remove(w);
        w.deleteWidget();
    }


    /**
     * Busca la cadena pasada como parámetro
     * en los diferentes Strings de todos los
     * widgets del espacio personal actual.
     *
     * @param cadena La cadena a search
     * @return El conjunto de objetos de la
     * clase SearchedString, que almacena
     * Strings en los que se ha encontrado
     * la cadena junto con el widget del que
     * provienen.
     */
    public SortedSet<SearchedString<PersonalSpace>> searchString(String cadena) {
        return widgets.
                parallelStream().
                map(e -> {
                    SearchedString<Widget> searchedString = e.search(cadena);
                    return new SearchedString<>(
                            this,
                            searchedString.getCadena(),
                            searchedString.getRatio()
                    );
                }).
                collect(
                        Collectors.toCollection(
                                () -> new TreeSet<SearchedString<PersonalSpace>>(Comparator.reverseOrder())
                        )
                );
    }


    /**
     * Cierra todos los archivos abiertos en el PersonalSpaces
     * y sus widgets.
     *
     * @throws IOException Si se produce algún error al cerrar dichos archivos
     * @see Closeable
     */
    public void close() throws IOException {
        for (Widget w : widgets)
            ((AbstractWidget) w).close();
    }

    /**
     * Devuelve una instancia de PersonalSpacesData utilizada para
     * almacenar los datos del espacio personal. Los objetos de
     * tipo PersonalSpacesData son Serializables
     *
     * @return PersonalSpacesData listo para ser almacenado
     * @see Serializable
     */
    public PersonalSpaceData getPersonalSpaceDataInstance() {
        List<WidgetData> list = new ArrayList<>();
        for (Widget w : widgets)
            list.add(w.getWidgetsDataInstance());

        PersonalSpaceData psd = new PersonalSpaceData();
        psd.personalSpaceName = personalSpaceName;
        psd.widgetData = list;
        psd.id = id;
        psd.folderPath = carpeta.getAbsolutePath();

        return psd;
    }

    /**
     * Mueve la carpeta que contiene los archivos de este PersonalSpace
     * a la carpeta destino
     *
     * @param folder Nueva carpeta donde contener dicho espacio personal
     * @throws IOException Si ocurre algún error
     */
    public void movePersonalSpacesToFolder(File folder) throws IOException {
        folder.mkdir();
        carpeta = new File(folder, id);
        for (Widget w : widgets)
            w.moveFilesToFolder(carpeta);

    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public File getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(File carpeta) {
        this.carpeta = carpeta;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalSpace)) return false;

        PersonalSpace that = (PersonalSpace) o;

        if (!Objects.equals(widgets, that.widgets)) return false;
        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(carpeta, that.carpeta);
    }

    @Override
    public int hashCode() {
        int result = widgets.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (carpeta != null ? carpeta.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "PersonalSpace{" +
                "widgets=" + widgets +
                ", id='" + id + '\'' +
                ", carpeta=" + carpeta +
                '}';
    }

    public void setPersonalSpaceName(String name) {
        personalSpaceName = name;
    }
}
