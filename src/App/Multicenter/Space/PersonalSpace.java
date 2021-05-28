package App.Multicenter.Space;

import App.Multicenter.Widget.AbstractWidget;
import App.Multicenter.Widget.Data.WidgetData;
import App.Multicenter.Widget.Widget;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.function.Supplier;
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
    private final SortedSet<Widget> widgetTree;
    private final String id;
    private File carpeta;


    /**
     * Instancia un nuevo espacio personal.
     * <p>
     * Se intenta abrir el carpeta XML con la información
     * del árbol de widgets almacenada dentro y si no se
     * encuentra el carpeta (porque no está creado, o
     * porque haya habido algún error), se crea un árbol
     * de widgets vacío)
     */

    public PersonalSpace(File f) {
        RandomNameGenerator rng = new RandomNameGenerator();
        id = rng.generate(f);
        widgetTree = new TreeSet<>();

        try {
            carpeta = new File(f, id);
            carpeta.mkdir();
            //Queda creada la carpeta del Espacio Peronal.
        } catch (Exception e) {
        }
    }

    private PersonalSpace(PersonalSpaceData data) {
        id = data.id;
        carpeta = new File(data.folderPath);
        widgetTree = new TreeSet<>();
        for (WidgetData w : data.widgetData) {
            widgetTree.add(Widget.instanciateWidgetsFromData(w));
        }
    }

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
        widgetTree.add(w);
    }

    /**
     * Elimina el widget pasado por parámetro del
     * árbol de widgets del espacio personal.
     *
     * @param w Widget a eliminar del árbol.
     */
    public void deleteWidget(Widget w) {
        widgetTree.remove(w);
    }


    /**
     * Busca la cadena pasada como parámetro
     * en los diferentes Strings de todos los
     * widgets del espacio personal actual.
     *
     * @param cadena La cadena a buscar
     * @return El conjunto de objetos de la
     * clase SearchedString, que almacena
     * Strings en los que se ha encontrado
     * la cadena junto con el widget del que
     * provienen.
     */
    public SortedSet<SearchedString<PersonalSpace>> buscarcadena(String cadena) {
        Supplier<TreeSet<SearchedString<PersonalSpace>>> sortedset = () -> new TreeSet<>(Comparator.reverseOrder());

        return widgetTree.
                parallelStream().
                map(e -> {
                    SearchedString<Widget> searchedString = e.buscar(cadena);
                    return new SearchedString<PersonalSpace>(this, searchedString.getCadena(), searchedString.getRatio());
                }).
                //flatMap(e -> e.buscar(cadena).stream()).
                //sorted().
                        collect(Collectors.toCollection(sortedset));
    }

    public String getId() {
        return id;
    }

    public void close() throws IOException {
        for (Widget w : widgetTree) {
            ((AbstractWidget) w).close();
        }
    }

    public SortedSet<Widget> getWidgetTree() {
        return widgetTree;
    }

    public File getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(File carpeta) {
        this.carpeta = carpeta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalSpace)) return false;

        PersonalSpace that = (PersonalSpace) o;

        if (!Objects.equals(widgetTree, that.widgetTree)) return false;
        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(carpeta, that.carpeta);
    }

    @Override
    public int hashCode() {
        int result = widgetTree != null ? widgetTree.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (carpeta != null ? carpeta.hashCode() : 0);
        return result;
    }

    public PersonalSpaceData getPersonalSpaceDataInstance() {
        Set<WidgetData> set = new HashSet<>();
        for (Widget w : widgetTree) {
            set.add(w.getWidgetsDataInstance());
        }

        PersonalSpaceData psd = new PersonalSpaceData();
        psd.widgetData = set;
        psd.id = id;
        psd.folderPath = carpeta.getAbsolutePath();

        return psd;
    }

    @Override
    public String toString() {
        return "PersonalSpace{" +
                "widgetTree=" + widgetTree +
                ", id='" + id + '\'' +
                ", carpeta=" + carpeta +
                '}';
    }
}
