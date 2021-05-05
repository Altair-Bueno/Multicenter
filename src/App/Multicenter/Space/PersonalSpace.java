package App.Multicenter.Space;

import App.Multicenter.DataStructures.Tree;
import App.Multicenter.Widget.Widget;

import java.util.Set;

/**
 * Clase que representa un espacio personal del usuario.
 * Cada espacio personal tendrá un árbol de la clase Tree
 * implementada por nosotros donde se almacenen todos los
 * widgets que tenga el usuario en el espacio personal.
 * Ofrecerá operaciones para añadir y eliminar widgets,
 * y para buscar cadenas en el espacio personal.
 */
public class PersonalSpace {
    Tree<Widget> widgetTree;

    // Constructor

    // Operaciones

    /**
     * Añade el widget pasado por parámetro al
     * árbol de widgets del espacio personal.
     *
     * @param w Widget a añadir al árbol.
     */
    public void addWidget(Widget w){

    }

    /**
     * Elimina el widget pasado por parámetro del
     * árbol de widgets del espacio personal.
     *
     * @param w Widget a eliminar del árbol.
     */
    public void deleteWidget(Widget w){

    }


    /**
     * Busca la cadena pasada como parámetro
     * en los diferentes Strings de todos los
     * widgets del espacio personal actual.
     *
     * @param cadena La cadena a buscar
     * @return El conjunto de widgets en los que
     * se ha encontrado la cadena.
     */
    public Set<Widget> buscar(String cadena){
        return null;
    }
}
