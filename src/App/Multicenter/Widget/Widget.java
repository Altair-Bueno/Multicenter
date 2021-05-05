package App.Multicenter.Widget;

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
    boolean buscar(String cadena);

}
