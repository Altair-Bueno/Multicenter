package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;

import java.io.File;
import java.util.SortedSet;
import java.net.URL;
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
    public boolean toggleEditMode() {
        return false;
    }

}
