package app.multicenter.Space;

import java.util.Arrays;

public class SearchedString<E> implements Comparable<SearchedString> {
    private final E widget;
    private final String cadena;
    private final double ratio;

    // Recibirá un widget y dos cadenas por parámetro (la encontrada y la referencia).
    // Calculará el ratio de semejanza entre la cadena encontrada y la referencia.
    // Inicializa widget (parámetro), cadena (parámetro - encontrada), ratio (calculado).

    public SearchedString(E w, String cadena, String ref) {
        widget = w;
        this.cadena = cadena;
        ratio = diceCoefficientOptimizado(cadena, ref);
    }

    public SearchedString(E w, String cadena, double ratio) {
        widget = w;
        this.cadena = cadena;
        this.ratio = ratio;
    }

    /**
     * Aquí tenemos una versión optimizada del cálculo algoritmo de Dice. Se
     * aprovecha del hecho de que un bigrama de 2 carácteres se puede guardar en
     * un entero, y aplica un algoritmo de igual funcionamiento, pero con
     * complejidad mejorada de O(n*log(n)), en vez de de O(n*n).
     * <p>
     * Teniendo en cuenta que, en el momento de escribir, la implementación difiere
     * sobre las otras implementaciones que se pueden encontrar on-line. Mientras
     * otros algoritmos almacenan de manera incorrecta los bigramas generados en un
     * set (descartando duplicados), esta implementación trata múltiples ocurrencias
     * de un bigrama de manera única. La exactitud de este comportamiento se
     * explica mucho mejor cuando obtenemos una similitud entre "GG" y ""GGGGG",
     * que podría no ser obviamente 1.
     *
     * @param s La primera cadena
     * @param t La segunda cadena
     * @return El coeficiente de Dice entre las dos cadenas de entrada. Devuelve 0 si
     * una o ambas cadenas son {@code null}. También devuelve 0 si una o ambas
     * cadenas contienen menos de 2 carácteres y no son iguales.
     */

    public static double diceCoefficientOptimizado(String s, String t) {
        // Verificamos la entrada:
        if (s == null || t == null)
            return 0;

        // Comprobación rapida para detectar si los strings son iguales.
        if (s.equals(t))
            return 1;
        // Evita la excepción si una de ellas tiene un solo carácter
        if (s.length() < 2 || t.length() < 2)
            return 0;

        // Crea el bigrama para la cadena s:
        final int n = s.length() - 1;
        final int[] sPairs = new int[n];
        for (int i = 0; i <= n; i++)
            if (i == 0)
                sPairs[i] = s.charAt(i) << 16;
            else if (i == n)
                sPairs[i - 1] |= s.charAt(i);
            else
                sPairs[i] = (sPairs[i - 1] |= s.charAt(i)) << 16;

        // Crea el bigrama para la cadena t:
        final int m = t.length() - 1;
        final int[] tPairs = new int[m];
        for (int i = 0; i <= m; i++)
            if (i == 0)
                tPairs[i] = t.charAt(i) << 16;
            else if (i == m)
                tPairs[i - 1] |= t.charAt(i);
            else
                tPairs[i] = (tPairs[i - 1] |= t.charAt(i)) << 16;

        // Ordena las listas de los bigramas:
        Arrays.sort(sPairs);
        Arrays.sort(tPairs);

        // Cuenta las igualdades:
        int matches = 0, i = 0, j = 0;
        while (i < n && j < m) {
            if (sPairs[i] == tPairs[j]) {
                matches += 2;
                i++;
                j++;
            } else if (sPairs[i] < tPairs[j])
                i++;
            else
                j++;
        }
        return (double) matches / (n + m);
    }


    // Operaciones

    /**
     * @return El widget.
     */
    public E getWidget() {
        return widget;
    }

    /**
     * @return La cadena.
     */
    public String getCadena() {
        return cadena;
    }

    /**
     * @return El ratio.
     */
    public double getRatio() {
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
    @Override
    public int compareTo(SearchedString o) {
        return Double.compare(ratio, o.getRatio());
    }

    @Override
    public String toString() {
        return "SearchedString{" +
                "widget=" + widget +
                ", cadena='" + cadena + '\'' +
                ", ratio=" + ratio +
                '}';
    }
}
