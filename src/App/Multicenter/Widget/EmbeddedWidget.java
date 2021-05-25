package App.Multicenter.Widget;

public class EmbeddedWidget extends AbstractWidget{
    // TODO EmbeddedWidget Constructor

    public SortedSet<SearchedString<Widget>> buscar(String cadena) {
        SortedSet<SearchedString<Widget>> res = new TreeSet<>();
        res.add(new SearchedString<>(this,URL,cadena));
        res.add(new SearchedString<>(this,nombre,cadena));
        return res;
    }

}
