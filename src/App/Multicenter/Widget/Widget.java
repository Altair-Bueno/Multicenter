package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;

import java.awt.*;
import java.util.SortedSet;

public interface Widget {

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
    SortedSet<SearchedString<Widget>> buscar(String cadena);

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

    boolean toggleEditMode();
}
