package App.Multicenter.Space;

import App.Multicenter.Buddy.XMLBuddy;
import App.Multicenter.DataStructures.HierarchyTree;
import App.Multicenter.DataStructures.Tree;
import App.Multicenter.Preferences.Preferences;
import App.Multicenter.Widget.Widget;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Nodes.collect;

/**
 * Clase que representa un espacio personal del usuario.
 * Cada espacio personal tendrá un árbol de la clase Tree
 * implementada por nosotros donde se almacenen todos los
 * widgets que tenga el usuario en el espacio personal.
 * Ofrecerá operaciones para añadir y eliminar widgets,
 * y para buscar cadenas en el espacio personal.
 */
public class PersonalSpace implements Closeable {
    Tree<Widget> widgetTree;
    File archivo;


    // TODO PersonalSpace Constructor
    public PersonalSpace(){
        widgetTree = new HierarchyTree<>();
        archivo = Preferences.getSpacesFolder();
        XMLBuddy.parseXMLFile(archivo);
    }

    // Operaciones

    /**
     * Añade el widget pasado por parámetro al
     * árbol de widgets del espacio personal.
     *
     * @param w Widget a añadir al árbol.
     * @param padre Widget a ser padre de w.
     */
    public void addWidget(Widget w, Widget padre){
        widgetTree.addChildren(w, padre);
    }

    /**
     * Elimina el widget pasado por parámetro del
     * árbol de widgets del espacio personal.
     *
     * @param w Widget a eliminar del árbol.
     */
    public void deleteWidget(Widget w){
        widgetTree.removeElement(w);
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
    public SortedSet<SearchedString<Widget>> buscarcadena(String cadena){
        // Llama al metodo buscar de cada widget en widgettree
        // Cada uno devuelve su sortedset de searchedstring
        // Ahora los mezclamos todos en un solo sortedset
        Supplier<TreeSet<SearchedString<Widget>>> sortedset = ()->new TreeSet<>(Comparator.reverseOrder());
        //Stream<Widget> stwid = widgetTree.getNodes().parallelStream();
        //stwid.forEach((l) -> res.addAll(l.buscar(cadena)));
        return widgetTree.
                getNodes().
                parallelStream().
                //map(e->e.buscar(cadena)).
                flatMap(e -> e.buscar(cadena).stream()).
                sorted().
                collect(Collectors.toCollection(sortedset));
    }

    /**
     * Guarda el espacio personal en un archivo
     * con ayuda de la clase XMLBuddy, pasándole
     * como parámetro el archivo almacenado en
     * la variable de clase archivo y el arbol de
     * widgets widgetTree.
     *
     */
    public void savePersonalSpace(){
        // TODO PersonalSpace savePersonalSpace (dependemos de App.Multicenter.XMLBuddy)
        XMLBuddy<Widget> s = new XMLBuddy<>();
        s.parseTreeStructure(archivo, widgetTree);
    }

    public void close() throws IOException {
        savePersonalSpace();
    }
}
