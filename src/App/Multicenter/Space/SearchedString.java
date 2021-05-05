package App.Multicenter.Space;

public class SearchedString<E> implements Comparable<SearchedString> {
    private E widget;
    private String cadena;
    private int ratio;

    // Constructor
    // Recibirá un widget y dos cadenas por parámetro (la encontrada y la referencia).
    // Calculará el ratio de semejanza entre la cadena encontrada y la referencia.
    // Inicializa widget (parámetro), cadena (parámetro - encontrada), ratio (calculado).

    // Operaciones

    /**
     * @return El widget.
     */
    public E getWidget(){
        return widget;
    }

    /**
     * @return La cadena.
     */
    public String getCadena(){
        return cadena;
    }

    /**
     * @return El ratio.
     */
    public int getRatio(){
        return ratio;
    }

    /**
     * Compara dos SearchedString por su ratio.
     *
     * @param o El SearchedString a comparar con this.
     * @return Un valor > 0 si this.ratio es mayor que o.ratio.
     * Un valor < 0 si this.ratio es menor que o.ratio.
     * 0 si los ratios son iguales.
     */
    public int compareTo(SearchedString o) {
        return Integer.compare(ratio, o.getRatio());
    }
}
