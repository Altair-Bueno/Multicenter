package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;

import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class EmbeddedWidget extends AbstractWidget{
    String URL;
    String nombre;

    public EmbeddedWidget(String url, String nombreRecurso){
        URL = url;
        this.nombre = nombreRecurso;
    }

    public SortedSet<SearchedString<Widget>> buscar(String cadena) {
        SortedSet<SearchedString<Widget>> res = new TreeSet<>();
        res.add(new SearchedString<>(this,URL,cadena));
        res.add(new SearchedString<>(this,nombre,cadena));
        return res;
    }

    @Override
    public void toggleEditMode() {
    }

    @Override
    public void close() throws IOException {

    }
}
